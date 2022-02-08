package com.lucianoortizsilva.aluno.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.commom.Professor;

@Service
public class ProfessorService {

	@Autowired
	@SuppressWarnings("rawtypes")
	private CircuitBreakerFactory circuitBreakerFactory;

	@Autowired
	private ProfessorClient professorClient;

	
	
	public String getNomeProfessorBy(final Long idProfessor) {
		final CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker-professor");
		final Optional<Professor> professor = circuitBreaker.run(() -> this.professorClient.getProfessorById(idProfessor), throwable -> getProfessorDefault());
		if(professor.isPresent()) {
			return professor.get().getNome();
		}
		return null;
	}
	
	
	
	private static Optional<Professor> getProfessorDefault() {
		final Professor professor = new Professor();
		professor.setNome("Indisponível no momento");
		return Optional.of(professor);
	}

}