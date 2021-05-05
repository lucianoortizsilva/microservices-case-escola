package com.lucianoortizsilva.gateway.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.commom.Avaliacao;
import com.lucianoortizsilva.gateway.config.CacheConstant;

@Service
public class AvaliacaoService {

	@Autowired
	@SuppressWarnings("rawtypes")
	private CircuitBreakerFactory circuitBreakerFactory;

	@Autowired
	private AvaliacaoClient avaliacaoClient;

	
	
	@Cacheable(cacheNames = CacheConstant.CACHE_AVALIACOES, key = "#idAluno", unless = "#result?.size() == 0")
	public List<Avaliacao> getAvaliacoesBy(final Long idAluno) {
		final CircuitBreaker circuitBreaker = this.circuitBreakerFactory.create("circuitbreaker-avaliacao");
		final Optional<List<Avaliacao>> optional = circuitBreaker.run(() -> this.avaliacaoClient.getById(idAluno), throwable ->  getAvaliacoesDefault());
		return optional.get();
	}
	
	
	
	private Optional<List<Avaliacao>> getAvaliacoesDefault() {
		return Optional.of(Collections.emptyList());
	}
	
}