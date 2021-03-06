package com.lucianoortizsilva.avaliacao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.commom.Avaliacao;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository respository;

	List<Avaliacao> findAvaliacoesByMatriculaAluno(final String matricula) {
		final List<Avaliacao> avaliacoesByAluno = new ArrayList<>();
		final List<Avaliacao> avaliacoesAll = this.respository.findAll().stream().collect(Collectors.toList());
		for (final Avaliacao avaliacao : avaliacoesAll) {
			if (avaliacao.getMatriculaAluno().equalsIgnoreCase(matricula)) {
				avaliacoesByAluno.add(avaliacao);
			}
		}
		return avaliacoesByAluno;
	}

}