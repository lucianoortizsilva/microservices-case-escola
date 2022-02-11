package com.lucianoortizsilva.commom;

import java.time.LocalDate;

public enum TrimestreEnum {

	UM("1� Trimestre / " + (LocalDate.now().getYear() -1)), //
	DOIS("2� Trimestre / " + (LocalDate.now().getYear() -1)), //
	TRES("3� Trimestre / " + (LocalDate.now().getYear() -1));//

	private String descricao;

	TrimestreEnum(final String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}