package com.lucianoortizsilva.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lucianoortizsilva.commom.Aluno;

@FeignClient(name = "ms-aluno")
public interface AlunoClient {

	@GetMapping(value = "/alunos/{id}")
	public Aluno getAluno(@PathVariable("id") final Long in);

}