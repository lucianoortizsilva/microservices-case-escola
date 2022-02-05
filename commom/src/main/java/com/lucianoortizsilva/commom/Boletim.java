package com.lucianoortizsilva.commom;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Boletim {

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
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Lancamento {
		private String nomeProfessor;
		private Disciplina disciplina;
		private String valor;
	}

}