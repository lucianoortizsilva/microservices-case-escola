package com.lucianoortizsilva.commom;

import lombok.Getter;

@Getter
public enum Curso {

	ENSINO_FUNDAMENTAL("Ensino Fundamental");

	private String descricao;

	Curso(final String descricao) {
		this.descricao = descricao;
	}

}