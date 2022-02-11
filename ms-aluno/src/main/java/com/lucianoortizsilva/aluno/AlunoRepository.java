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
		alunos.add(Aluno.builder().matricula("1").nome("Fulano De Tal").build());
		alunos.add(Aluno.builder().matricula("2").nome("Beltrano De Tal").build());
		alunos.add(Aluno.builder().matricula("3").nome("Ciclano De Tal").build());
	}

	Collection<Aluno> findAll() {
		return alunos;
	}

}