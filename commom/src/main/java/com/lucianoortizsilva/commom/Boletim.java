package com.lucianoortizsilva.commom;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Boletim {

	private Long idAluno;
	private Set<Nota> notas;

}