package com.lucianoortizsilva.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Professor;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorController {

	@Autowired
	private ProfessorService service;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Professor getById(@PathVariable(name = "id") final Long id) {
		return service.getProfessorById(id);
	}

}