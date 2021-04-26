package com.lucianoortizsilva.commom;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nota {

	private Long idAluno;

	private Long idProfessor;

	private Disciplina disciplina;

	private String valor;

	private LocalDate dtLancamento;

}