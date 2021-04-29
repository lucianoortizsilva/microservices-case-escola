package com.lucianoortizsilva.avaliacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Avaliacao;
import com.lucianoortizsilva.commom.Disciplina;

@RestController
@RequestMapping(value = "/avaliacoes")
public class AvaliacaoController {

	private static List<Avaliacao> avaliacoes = new ArrayList<>();
	
	private static final String _1_TRIMESTRE_2020 = "Trimestre 1 - 2020";
	private static final String _2_TRIMESTRE_2020 = "Trimestre 2 - 2020";
	private static final String _3_TRIMESTRE_2020 = "Trimestre 3 - 2020";
	private static final String _4_TRIMESTRE_2020 = "Trimestre 4 - 2020";

	static {
		avaliacoes.add(new Avaliacao(1L, 1L, Disciplina.FISICA, "A", _1_TRIMESTRE_2020));
		avaliacoes.add(new Avaliacao(1L, 2L, Disciplina.PEDAGOGIA, "B", _1_TRIMESTRE_2020));
		avaliacoes.add(new Avaliacao(1L, 3L, Disciplina.QUIMICA, "C", _1_TRIMESTRE_2020));
		
		avaliacoes.add(new Avaliacao(1L, 1L, Disciplina.FISICA, "B", _2_TRIMESTRE_2020));
		avaliacoes.add(new Avaliacao(1L, 2L, Disciplina.PEDAGOGIA, "B", _2_TRIMESTRE_2020));
		avaliacoes.add(new Avaliacao(1L, 3L, Disciplina.QUIMICA, "A", _2_TRIMESTRE_2020));
		
		avaliacoes.add(new Avaliacao(1L, 1L, Disciplina.FISICA, "C", _3_TRIMESTRE_2020));
		avaliacoes.add(new Avaliacao(1L, 2L, Disciplina.PEDAGOGIA, "A", _3_TRIMESTRE_2020));
		avaliacoes.add(new Avaliacao(1L, 3L, Disciplina.QUIMICA, "A", _3_TRIMESTRE_2020));
		
		avaliacoes.add(new Avaliacao(1L, 1L, Disciplina.FISICA, "A", _4_TRIMESTRE_2020));
		avaliacoes.add(new Avaliacao(1L, 2L, Disciplina.PEDAGOGIA, "A", _4_TRIMESTRE_2020));
		avaliacoes.add(new Avaliacao(1L, 3L, Disciplina.QUIMICA, "B", _4_TRIMESTRE_2020));
	}

	
	
	@GetMapping(value = "/aluno/{id}")
	public Optional<List<Avaliacao>> getById(@PathVariable(name = "id") final Long id) {
		return Optional.of(findBy(id));
	}
	
	
	
	private List<Avaliacao> findBy(final Long idAluno) {
		final List<Avaliacao> list = new ArrayList<>();
		for (final Avaliacao b : avaliacoes) {
			if (b.getIdAluno().equals(idAluno)) {
				list.add(b);
			}
		}
		return list;
	}
	
}