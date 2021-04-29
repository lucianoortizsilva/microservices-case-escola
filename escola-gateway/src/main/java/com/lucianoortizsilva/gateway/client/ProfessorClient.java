package com.lucianoortizsilva.gateway.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lucianoortizsilva.commom.Professor;

@FeignClient(name = "ms-professor")
public interface ProfessorClient {

	@GetMapping(value = "/professores/{id}")
	public Optional<Professor> getById(@PathVariable(name = "id") final Long idProfessor);

}