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

@RestController
@RequestMapping(value = "/avaliacoes")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoService avaliacaoService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/aluno/{id}")
	public List<Avaliacao> getById(@PathVariable(name = "id") final Long id) {
		return avaliacaoService.findAvaliacoesByAlunoId(id);
	}

}