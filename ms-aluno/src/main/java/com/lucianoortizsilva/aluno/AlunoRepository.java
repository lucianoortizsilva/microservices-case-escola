package com.lucianoortizsilva.aluno;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lucianoortizsilva.commom.Aluno;

@Repository
class AlunoRepository {

	private static List<Aluno> alunos = new ArrayList<>();

	static {
		alunos.add(new Aluno(1L, "Ortiz"));
		alunos.add(new Aluno(2L, "Mariana"));
		alunos.add(new Aluno(3L, "Fernanda"));
	}

	Collection<Aluno> findAll() {
		return alunos;
	}

}