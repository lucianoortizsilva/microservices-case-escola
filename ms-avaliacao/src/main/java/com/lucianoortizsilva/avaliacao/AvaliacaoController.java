package com.lucianoortizsilva.avaliacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Avaliacao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/avaliacoes")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoService avaliacaoService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/aluno/{matricula}")
	public List<Avaliacao> getAvaliacoesByMatriculaAluno(@PathVariable(name = "matricula") final String matricula) {
		log.info("[ms-avaliacao] GET > /avaliacoes/aluno/{}", matricula);
		return avaliacaoService.findAvaliacoesByMatriculaAluno(matricula);
	}

}