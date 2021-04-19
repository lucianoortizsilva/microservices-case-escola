package com.lucianoortizsilva.gateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Aluno;
import com.lucianoortizsilva.commom.Boletim;
import com.lucianoortizsilva.gateway.client.AlunoClient;
import com.lucianoortizsilva.gateway.client.BoletimClient;
import com.lucianoortizsilva.gateway.response.ResponseBoletim;

import lombok.extern.log4j.Log4j2;



@Log4j2
@RestController
public class Controller {

	@Lazy
	@Autowired
	private AlunoClient alunoClient;

	@Autowired
	private BoletimClient boletimClient;

	@GetMapping(value = "/boletins/aluno/{id}")
	public ResponseEntity<List<ResponseBoletim>> getBoletim(@PathVariable(name = "id") final Long idAluno) {
		log.info("Pesquisando /boletins/aluno/{id} " + idAluno);
		log.info("alunoClient: " + alunoClient);
		final Aluno aluno = this.alunoClient.getAluno(idAluno);
		if (aluno == null) {
			return ResponseEntity.notFound().build();
		} else {
			final List<Boletim> boletins = this.boletimClient.getById(idAluno);
			if (boletins.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(getResponseBoletimFrom(aluno, boletins));
			}
		}
	}

	private List<ResponseBoletim> getResponseBoletimFrom(final Aluno aluno, final List<Boletim> boletins) {
		final List<ResponseBoletim> response = new ArrayList<>();
		for (final Boletim b : boletins) {
			final ResponseBoletim r = new ResponseBoletim();
			r.setAluno(aluno.getNome());
			r.setNotaMatematica(getNota(b.getNotaMatematica()));
			r.setNotaPortugues(getNota(b.getNotaPortugues()));
			r.setNotaQuimica(getNota(b.getNotaQuimica()));
			r.setPeriodo(b.getSemestre().concat("-").concat(b.getAno()));
			response.add(r);
		}
		return response;
	}

	private String getNota(final double nota) {
		if (nota <= 3.0) {
			return "D";
		} else if (nota <= 5.0) {
			return "C";
		} else if (nota <= 8.0) {
			return "B";
		} else {
			return "A";
		}
	}

}