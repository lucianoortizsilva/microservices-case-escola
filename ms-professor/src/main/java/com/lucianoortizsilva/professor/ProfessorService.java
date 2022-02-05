package com.lucianoortizsilva.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.commom.Professor;

@Service
class ProfessorService {

	@Autowired
	private ProfessorRepository repository;

	Professor getProfessorById(Long id) {
		return repository.findAll()
				.stream()
				.filter(professor -> professor.getId().equals(id))
				.findFirst().orElse(null);
	}

}