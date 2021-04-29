package com.lucianoortizsilva.commom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {

	private Long idAluno;
	private Long idProfessor;
	private Disciplina disciplina;
	private String nota;
	private String periodo;

}