package com.lucianoortizsilva.gateway;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Avaliacao;
import com.lucianoortizsilva.gateway.response.Response;
import com.lucianoortizsilva.gateway.response.Response.Lancamento;
import com.lucianoortizsilva.gateway.service.AlunoService;
import com.lucianoortizsilva.gateway.service.AvaliacaoService;
import com.lucianoortizsilva.gateway.service.ProfessorService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class GatewayController {

	@Autowired
	private AvaliacaoService avaliacaoService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private AlunoService alunoService;

	@GetMapping(value = "/boletins/aluno/{id}")
	public ResponseEntity<List<Response>> gerarBoletimPara(@PathVariable(name = "id") final Long idAluno) {
		final List<Response> resultado = new ArrayList<>();
		try {
			
			log.info("1 >>> Buscando avaliacoes");
			final List<Avaliacao> avaliacoes = this.avaliacaoService.getAvaliacoesBy(idAluno);
			if (avaliacoes.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			log.info("2 >>> Buscando aluno");
			final String nomeAluno = this.alunoService.getNomeAlunoBy(idAluno);

			log.info("3 >>> Agrupando avaliacoes");
			final Map<String, List<Avaliacao>> avaliacoesByPeriodo = avaliacoes.stream().collect(Collectors.groupingBy(Avaliacao::getPeriodo, Collectors.mapping(Function.identity(), Collectors.toList())));

			log.info("4 >>> Criando boletins");
			for (final Entry<String, List<Avaliacao>> map : avaliacoesByPeriodo.entrySet()) {
				final Response response = new Response();
				response.setNomeAluno(nomeAluno);
				response.setPeriodo(map.getKey());
				final Iterator<Avaliacao> it = map.getValue().iterator();
				while (it.hasNext()) {
					final Avaliacao avaliacao = it.next();
					final String nomeProfessor = this.professorService.getNomeProfessorBy(avaliacao.getIdProfessor());
					final Lancamento lancamento = new Lancamento(nomeProfessor, avaliacao.getDisciplina(), avaliacao.getNota());
					response.getLancamentos().add(lancamento);
				}
				resultado.add(response);
			}
		} catch (final Exception e) {
			log.error("############ erro inesperado ############", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().body(resultado);
	}

}