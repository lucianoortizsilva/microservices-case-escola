package com.lucianoortizsilva.gateway;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Aluno;
import com.lucianoortizsilva.commom.Boletim;
import com.lucianoortizsilva.commom.Nota;
import com.lucianoortizsilva.commom.Professor;
import com.lucianoortizsilva.gateway.client.AlunoClient;
import com.lucianoortizsilva.gateway.client.BoletimClient;
import com.lucianoortizsilva.gateway.client.ProfessorClient;
import com.lucianoortizsilva.gateway.response.Response;
import com.lucianoortizsilva.gateway.response.Response.Lancamento;

import feign.RetryableException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class Controller {

	@Autowired
	private AlunoClient alunoClient;

	@Autowired
	private BoletimClient boletimClient;

	@Autowired
	private ProfessorClient professorClient;

	private static final String INDISPONIVEL = "Indisponivel no momento";

	@GetMapping(value = "/boletins/aluno/{id}")
	public ResponseEntity<List<Response>> getBoletim(@PathVariable(name = "id") final Long idAluno) {
		log.info("Pesquisando /boletins/aluno/{id} " + idAluno);
		final List<Response> response = new ArrayList<>();
		try {
			log.info("1 >>> Buscando boletim");
			final List<Boletim> boletins = this.boletimClient.getById(idAluno);
			if (boletins.isEmpty())
				return ResponseEntity.notFound().build();

			log.info("2 >>> Buscando aluno");
			final String nomeAluno = this.getNomeAlunoById(idAluno);

			log.info("3 >>> Criando boletins");
			for (final Boletim boletim : boletins) {
				final Response r = new Response();
				r.setNomeAluno(nomeAluno);
				for (final Nota nota : boletim.getNotas()) {
					final String nomeProfessor = this.getNomeProfessor(nota.getIdProfessor());
					final String periodo = this.getPeriodoFrom(nota);
					r.setPerido(periodo);
					r.getLancamentos().add(new Lancamento(nomeProfessor, nota.getDisciplina(), nota.getValor()));
				}
				response.add(r);
			}
		} catch (final Exception e) {
			log.error("############################################");
			log.error("############ erro inesperado ############", e);
			log.error("############################################");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().body(response);
	}

	private String getPeriodoFrom(final Nota nota) {
		final LocalDate dtLancamento = nota.getDtLancamento();
		String periodo = "";
		if (dtLancamento.getMonthValue() <= 3) {
			periodo = "1º trimestre / ".concat(String.valueOf(dtLancamento.getYear()));
		} else if (dtLancamento.getMonthValue() <= 6) {
			periodo = "2º trimestre / ".concat(String.valueOf(dtLancamento.getYear()));
		} else if (dtLancamento.getMonthValue() <= 9) {
			periodo = "3º trimestre / ".concat(String.valueOf(dtLancamento.getYear()));
		} else {
			periodo = "4º trimestre / ".concat(String.valueOf(dtLancamento.getYear()));
		}
		return periodo;
	}

	private String getNomeAlunoById(final Long idAluno) {
		String nomeAluno = INDISPONIVEL;
		try {
			final Optional<Aluno> aluno = this.alunoClient.getAluno(idAluno);
			if (aluno.isPresent()) {
				nomeAluno = aluno.get().getNome();
			} else {
				log.error("########################################");
				log.error("### aluno inexistente ###");
				log.error("########################################");
			}
		} catch (final RetryableException e) {
			log.error("########################################");
			log.error("### ms-aluno indisponivel no momento ###");
			log.error("########################################");
		} catch (final Exception e) {
			log.error("########################################");
			log.error("############ erro inesperado ###########", e);
			log.error("########################################");
		}
		return nomeAluno;
	}

	private String getNomeProfessor(final Long idProfessor) {
		String nomeProfessor = INDISPONIVEL;
		try {
			final Optional<Professor> professor = this.professorClient.getById(idProfessor);
			if (professor.isPresent()) {
				nomeProfessor = professor.get().getNome();
			} else {
				log.error("########################################");
				log.error("### professor inexistente ###");
				log.error("########################################");
			}
		} catch (final RetryableException e) {
			log.error("############################################");
			log.error("### ms-professor indisponivel no momento ###");
			log.error("############################################");
		} catch (final Exception e) {
			log.error("############################################");
			log.error("############ erro inesperado ###############", e);
			log.error("############################################");
		}
		return nomeProfessor;
	}

}