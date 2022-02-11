package com.lucianoortizsilva.aluno;

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
	@GetMapping(value = "/{matricula}")
	public Aluno getAlunoByMatricula(@PathVariable(name = "matricula") final String matricula) {
		log.info("[ms-aluno] GET > /alunos/v1/{}", matricula);
		return alunoService.getAlunoByMatricula(matricula);
	}
	
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{matricula}/boletim")
	public Boletim getBoletimByMatriculaAluno(@PathVariable(name = "matricula") final String matricula) {
		log.info("[ms-aluno] GET > /alunos/v1/{}/boletim", matricula);
		return alunoService.getBoletimByMatriculaAluno(matricula);
	}

}