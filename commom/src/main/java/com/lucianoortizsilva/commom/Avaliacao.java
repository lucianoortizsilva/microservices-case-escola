package com.lucianoortizsilva.commom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

	private String matriculaAluno;
	private String codigoProfessor;
	private Disciplina disciplina;
	private TrimestreEnum trimestre;
	private Double nota;

}