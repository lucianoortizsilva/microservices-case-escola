package com.lucianoortizsilva.aluno;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.aluno.service.AvaliacaoService;
import com.lucianoortizsilva.commom.Aluno;
import com.lucianoortizsilva.commom.Avaliacao;
import com.lucianoortizsilva.commom.Boletim;

@Service
class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private AvaliacaoService avaliacaoService;

	List<Boletim> getBoletimByAluno(final Long idAluno) {
		Optional<List<Avaliacao>> avaliacoes = this.avaliacaoService.getAvaliacoesPor(idAluno);
		if (avaliacoes.isPresent()) {
			String nomeAluno = getNomeAlunoById(idAluno);
			return avaliacaoService.createBoletimTo(nomeAluno, avaliacoes.get());
		} else {
			throw new AlunoException("Boletim não encontrado, para o aluno informado.");
		}
	}

	private String getNomeAlunoById(final Long idAluno) {
		return alunoRepository.findAll()
				.stream()
				.filter(aluno -> aluno.getId().equals(idAluno))
				.map(Aluno::getNome)
				.findFirst().orElse(StringUtils.EMPTY);
	}

	Aluno getAlunoById(final Long idAluno) {
		return alunoRepository.findAll()
				.stream()
				.filter(aluno -> aluno.getId().equals(idAluno))
				.findFirst().orElse(null);
	}

}