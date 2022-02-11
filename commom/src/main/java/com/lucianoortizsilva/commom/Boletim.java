package com.lucianoortizsilva.commom;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Boletim {

	@Getter
	@Setter
	private String aluno;

	@Getter
	@Setter
	private String curso;

	@Getter
	private List<Trimestre> trimestres = new ArrayList<>();

	
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Trimestre {
		@Getter
		@Setter
		private String descricao;
		@Getter
		private List<Detalhe> detalhes = new ArrayList<>();
	}

	
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Detalhe {
		private String professor;
		private String disciplina;
		private Double nota;
	}

	
	
}