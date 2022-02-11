package com.lucianoortizsilva.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Professor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/professores")
public class ProfessorController {

	@Autowired
	private ProfessorService service;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{codigo}")
	public Professor getByCodigo(@PathVariable(name = "codigo") final String codigo) {
		log.info("[ms-professor] GET > /professores/{}", codigo);
		return service.getProfessorByCodigo(codigo);
	}

}