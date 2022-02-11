package com.lucianoortizsilva.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.commom.Professor;

@Service
class ProfessorService {

	@Autowired
	private ProfessorRepository repository;

	Professor getProfessorByCodigo(String codigo) {
		return repository.findAll().stream()
				.filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
				.findFirst().orElse(null);
	}

}