package com.lucianoortizsilva.boletim.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lucianoortizsilva.commom.Aluno;

@FeignClient(name = "ms-aluno", url = "http://localhost:8080")
public interface AlunoServiceClient {

	@GetMapping(value = "/alunos/{id}")
	public Aluno getAluno(@PathVariable("id") final Long in);

}