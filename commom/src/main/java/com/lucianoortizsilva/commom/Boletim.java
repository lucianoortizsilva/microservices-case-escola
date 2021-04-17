package com.lucianoortizsilva.commom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Boletim {

	private Long id;
	private Long idAluno;
	private String ano;
	private String semestre;
	private Double notaMatematica;
	private Double notaPortugues;
	private Double notaQuimica;

}