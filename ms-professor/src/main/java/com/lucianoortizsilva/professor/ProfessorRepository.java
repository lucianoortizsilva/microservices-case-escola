package com.lucianoortizsilva.professor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lucianoortizsilva.commom.Professor;

@Repository
class ProfessorRepository {

	private static List<Professor> professores = new ArrayList<>(5);

	static {
		professores.add(Professor.builder().codigo("1").nome("João").build());
		professores.add(Professor.builder().codigo("2").nome("Maicon").build());
		professores.add(Professor.builder().codigo("3").nome("Leticia").build());
		professores.add(Professor.builder().codigo("4").nome("Alfredo").build());
		professores.add(Professor.builder().codigo("5").nome("Fernanda").build());
	}

	Collection<Professor> findAll() {
		return professores;
	}

}