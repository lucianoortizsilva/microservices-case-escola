package com.lucianoortizsilva.gateway.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBoletim {

	private String aluno;
	private String periodo;
	private String notaMatematica;
	private String notaPortugues;
	private String notaQuimica;

}