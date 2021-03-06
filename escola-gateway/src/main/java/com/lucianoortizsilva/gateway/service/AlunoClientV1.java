package com.lucianoortizsilva.gateway.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lucianoortizsilva.commom.Boletim;

@FeignClient(name = "ms-aluno")
public interface AlunoClientV1 {

	@GetMapping(value = "/alunos/{matricula}/boletim")
	public Boletim getBoletimByMatriculaAluno(@PathVariable("matricula") final String matricula);

}