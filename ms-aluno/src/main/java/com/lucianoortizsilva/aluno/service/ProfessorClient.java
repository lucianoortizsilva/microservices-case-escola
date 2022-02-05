package com.lucianoortizsilva.aluno.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lucianoortizsilva.commom.Professor;

/**
 * 
 * @see ms-professor
 *
 */
@FeignClient(name = "ms-professor")
interface ProfessorClient {

	@GetMapping(value = "/professores/{id}")
	Optional<Professor> getProfessorById(@PathVariable(name = "id") final Long idProfessor);

}