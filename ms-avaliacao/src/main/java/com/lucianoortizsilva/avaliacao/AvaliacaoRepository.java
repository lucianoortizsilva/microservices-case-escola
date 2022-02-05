package com.lucianoortizsilva.avaliacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lucianoortizsilva.commom.Avaliacao;
import com.lucianoortizsilva.commom.Disciplina;

@Repository
public class AvaliacaoRepository {

	private static List<Avaliacao> avaliacoes = new ArrayList<>();

	private static final String TRIMESTRE_1_2021 = "Trimestre 1 - 2020";
	private static final String TRIMESTRE_2_2021 = "Trimestre 2 - 2020";
	private static final String TRIMESTRE_3_2021 = "Trimestre 3 - 2020";
	private static final String TRIMESTRE_4_2021 = "Trimestre 4 - 2020";

	static {
		avaliacoes.add(new Avaliacao(1L, 1L, Disciplina.FISICA, "A", TRIMESTRE_1_2021));
		avaliacoes.add(new Avaliacao(1L, 2L, Disciplina.PEDAGOGIA, "B", TRIMESTRE_1_2021));
		avaliacoes.add(new Avaliacao(1L, 3L, Disciplina.QUIMICA, "C", TRIMESTRE_1_2021));

		avaliacoes.add(new Avaliacao(1L, 1L, Disciplina.FISICA, "B", TRIMESTRE_2_2021));
		avaliacoes.add(new Avaliacao(1L, 2L, Disciplina.PEDAGOGIA, "B", TRIMESTRE_2_2021));
		avaliacoes.add(new Avaliacao(1L, 3L, Disciplina.QUIMICA, "A", TRIMESTRE_2_2021));

		avaliacoes.add(new Avaliacao(1L, 1L, Disciplina.FISICA, "C", TRIMESTRE_3_2021));
		avaliacoes.add(new Avaliacao(1L, 2L, Disciplina.PEDAGOGIA, "A", TRIMESTRE_3_2021));
		avaliacoes.add(new Avaliacao(1L, 3L, Disciplina.QUIMICA, "A", TRIMESTRE_3_2021));

		avaliacoes.add(new Avaliacao(1L, 1L, Disciplina.FISICA, "A", TRIMESTRE_4_2021));
		avaliacoes.add(new Avaliacao(1L, 2L, Disciplina.PEDAGOGIA, "A", TRIMESTRE_4_2021));
		avaliacoes.add(new Avaliacao(1L, 3L, Disciplina.QUIMICA, "B", TRIMESTRE_4_2021));
	}

	Collection<Avaliacao> findAll() {
		return avaliacoes;
	}

}