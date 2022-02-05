package com.lucianoortizsilva.professor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lucianoortizsilva.commom.Professor;

@Repository
class ProfessorRepository {

	private static List<Professor> professores = new ArrayList<>(3);

	static {
		professores.add(new Professor(1L, "Albert Einstein"));
		professores.add(new Professor(2L, "Johann Heinrich Pestalozzi"));
		professores.add(new Professor(3L, "Marie Curie"));
	}

	Collection<Professor> findAll() {
		return professores;
	}

}