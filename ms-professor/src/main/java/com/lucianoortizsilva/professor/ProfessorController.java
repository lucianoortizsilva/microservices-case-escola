package com.lucianoortizsilva.professor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Professor;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorController {

	private static List<Professor> professores = new ArrayList<>(3);

	
	
	static {
		professores.add(new Professor(1L, "Albert Einstein"));
		professores.add(new Professor(2L, "Johann Heinrich Pestalozzi"));
		professores.add(new Professor(3L, "Marie Curie"));
	}

	
	
	@GetMapping(value = "/{id}")
	public Professor getById(@PathVariable(name = "id") final Long id) {
		return findBy(id);
	}
	
	
	
	private Professor findBy(final Long id) {
		return professores.stream().filter(professor -> professor.getId().equals(id)).findFirst().orElse(null);
	}

}