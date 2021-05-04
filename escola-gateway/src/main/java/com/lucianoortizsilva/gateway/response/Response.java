package com.lucianoortizsilva.gateway.response;

import java.util.HashSet;
import java.util.Set;

import com.lucianoortizsilva.commom.Disciplina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Response {

	@Getter
	@Setter
	private String nomeAluno;

	@Getter
	@Setter
	private String periodo;

	@Getter
	private Set<Lancamento> lancamentos = new HashSet<>();

	@Getter
	@Setter
	@AllArgsConstructor
	public static class Lancamento {
		private String nomeProfessor;
		private Disciplina disciplina;
		private String valor;
	}

}