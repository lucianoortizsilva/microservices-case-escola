package com.lucianoortizsilva.aluno.msaluno;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Aluno;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

	private static List<Aluno> alunos = new ArrayList<>();
	
	static {
		alunos.add(new Aluno(1L, "Luciano"));
		alunos.add(new Aluno(2L, "Mariana"));
		alunos.add(new Aluno(3L, "Vanessa"));
	}

	@GetMapping(value = "/{id}")
	public Aluno getById(@PathVariable(name = "id") final Long id) {
		return findBy(id);
	}

	private Aluno findBy(final Long id) {
		return alunos.stream().filter(aluno -> aluno.getId().equals(id)).findFirst().orElse(null);
	}

}