package com.lucianoortizsilva.gateway;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.commom.Aluno;
import com.lucianoortizsilva.commom.Avaliacao;
import com.lucianoortizsilva.commom.Professor;
import com.lucianoortizsilva.gateway.client.AlunoClient;
import com.lucianoortizsilva.gateway.client.AvaliacaoClient;
import com.lucianoortizsilva.gateway.client.ProfessorClient;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GatewayService {

	private static final String INDISPONIVEL = "Indisponivel no momento";

	@Autowired
	private AlunoClient alunoClient;

	@Autowired
	private AvaliacaoClient avaliacaoClient;

	@Autowired
	private ProfessorClient professorClient;

	
	
	String getNomeProfessorBy(final Long idProfessor) {
		String nomeProfessor = INDISPONIVEL;
		try {
			final Optional<Professor> professor = this.professorClient.getById(idProfessor);
			if (professor.isPresent()) {
				nomeProfessor = professor.get().getNome();
			} else {
				log.warn("############ professor id:{}, inexistente ############", idProfessor);
			}
		} catch (final RetryableException e) {
			log.error("############ ms-professor indisponivel no momento ############");
		} catch (final Exception e) {
			log.error("##################### erro inesperado ####################", e);
		}
		return nomeProfessor;
	}

	
	
	String getNomeAlunoBy(final Long idAluno) {
		String nomeAluno = INDISPONIVEL;
		try {
			final Optional<Aluno> aluno = this.alunoClient.getAluno(idAluno);
			if (aluno.isPresent()) {
				nomeAluno = aluno.get().getNome();
			} else {
				log.warn("############ aluno id:{}, inexistente ############", idAluno);
			}
		} catch (final RetryableException e) {
			log.error("############ ms-aluno indisponivel no momento ############");
		} catch (final Exception e) {
			log.error("##################### erro inesperado ####################", e);
		}
		return nomeAluno;
	}

	
	
	List<Avaliacao> getAvaliacoesBy(final Long idAluno) {
		List<Avaliacao> avaliacoes = null;
		try {
			Optional<List<Avaliacao>> optional = this.avaliacaoClient.getById(idAluno);
			if (optional.isPresent()) {
				avaliacoes = new ArrayList<>();
				avaliacoes.addAll(optional.get());
			} else {
				avaliacoes = Collections.emptyList();
				log.warn("############ avaliacoes do aluno id: {}, inexistente ############", idAluno);
			}
		} catch (final RetryableException e) {
			log.error("############ ms-avaliacao indisponivel no momento ############");
		} catch (final Exception e) {
			log.error("##################### erro inesperado ####################", e);
		}
		return avaliacoes;
	}

}