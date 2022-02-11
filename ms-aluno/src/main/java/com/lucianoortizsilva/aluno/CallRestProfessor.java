package com.lucianoortizsilva.aluno;

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
interface CallRestProfessor {

	@GetMapping(value = "/professores/{codigo}")
	Optional<Professor> getProfessorByCodigo(@PathVariable(name = "codigo") final String codigo);

}