package com.lucianoortizsilva.gateway.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lucianoortizsilva.commom.Aluno;

@FeignClient(name = "ms-aluno")
interface AlunoClient {

	@GetMapping(value = "/alunos/{id}")
	Optional<Aluno> getAluno(@PathVariable("id") final Long in);

}