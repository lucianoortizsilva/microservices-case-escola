package com.lucianoortizsilva.gateway.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.commom.Aluno;
import com.lucianoortizsilva.gateway.config.CacheConstant;

@Service
public class AlunoService {

	@Autowired
	@SuppressWarnings("rawtypes")
	private CircuitBreakerFactory circuitBreakerFactory;

	@Autowired
	private AlunoClient alunoClient;
	
	
	
	@Cacheable(cacheNames = CacheConstant.CACHE_ALUNO, key = "#idAluno", unless = "#result == null")
	public String getNomeAlunoBy(final Long idAluno) {
		final CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker-aluno");
		final Optional<Aluno> optional = circuitBreaker.run(() -> this.alunoClient.getAluno(idAluno), throwable -> getAlunoDefault());
		return optional.get().getNome();
	}
	
	
	
	private static Optional<Aluno> getAlunoDefault() {
		final Aluno aluno = new Aluno();
		aluno.setNome("Indisponivel no momento");
		return Optional.of(aluno);
	}

}