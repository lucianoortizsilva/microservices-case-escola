package com.lucianoortizsilva.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Aluno;
import com.lucianoortizsilva.commom.Boletim;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Aluno getById(@PathVariable(name = "id") final Long id) {
		log.info("GET > /alunos/{}", id);
		return alunoService.getAlunoById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}/boletim")
	public List<Boletim> getBoletimByAluno(@PathVariable(name = "id") final Long id) {
		log.info("GET > /alunos/{}/boletim", id);
		return alunoService.getBoletimByAluno(id);
	}

}