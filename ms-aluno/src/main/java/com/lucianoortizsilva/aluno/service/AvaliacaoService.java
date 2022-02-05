package com.lucianoortizsilva.aluno.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.aluno.config.CacheConstant;
import com.lucianoortizsilva.commom.Avaliacao;
import com.lucianoortizsilva.commom.Boletim;
import com.lucianoortizsilva.commom.Boletim.Lancamento;

@Service
public class AvaliacaoService {

	@Autowired
	@SuppressWarnings("rawtypes")
	private CircuitBreakerFactory circuitBreakerFactory;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private AvaliacaoClient avaliacaoClient;

	@Cacheable(cacheNames = CacheConstant.CACHE_AVALIACOES, key = "#idAluno", unless = "#result?.size() == 0")
	public Optional<List<Avaliacao>> getAvaliacoesPor(final Long idAluno) {
		final CircuitBreaker circuitBreaker = this.circuitBreakerFactory.create("circuitbreaker-avaliacao");
		return circuitBreaker.run(() -> this.avaliacaoClient.getAvaliacoesByAlunoId(idAluno), throwable -> getAvaliacoesDefault());
	}

	private Optional<List<Avaliacao>> getAvaliacoesDefault() {
		return Optional.of(Collections.emptyList());
	}

	public List<Boletim> createBoletimTo(final String nomeAluno, final List<Avaliacao> avaliacoes) {
		Map<String, List<Avaliacao>> avaliacoesAgrupadasPorTrimestre = agruparAvaliacoesPorTrimestre(avaliacoes);
		List<Boletim> response = new ArrayList<>();
		for (Entry<String, List<Avaliacao>> map : avaliacoesAgrupadasPorTrimestre.entrySet()) {
			final String trimestre = map.getKey();
			final List<Avaliacao> avaliacoesPorTrimestre = map.getValue();
			final Boletim boletim = createBoletimWith(nomeAluno, trimestre, avaliacoesPorTrimestre);
			response.add(boletim);
		}
		return response;
	}

	private Boletim createBoletimWith(final String nomeAluno, final String trimestre, final List<Avaliacao> avaliacoesPorPerido) {
		final Boletim response = new Boletim();
		response.setPeriodo(trimestre);
		response.setNomeAluno(nomeAluno);
		response.getLancamentos().addAll(getLancamentos(avaliacoesPorPerido));
		return response;
	}

	private List<Lancamento> getLancamentos(final List<Avaliacao> avaliacoesPorPerido) {
		final Iterator<Avaliacao> it = avaliacoesPorPerido.iterator();
		final List<Lancamento> lancamentos = new ArrayList<>();
		while (it.hasNext()) {
			final Avaliacao avaliacao = it.next();
			final String nomeProfessor = professorService.getNomeProfessorBy(avaliacao.getIdProfessor());
			lancamentos.add(criarLancamentoPara(nomeProfessor, avaliacao));
		}
		return lancamentos;
	}

	private static Lancamento criarLancamentoPara(final String nomeProfessor, final Avaliacao avaliacao) {
		return Lancamento.builder().disciplina(avaliacao.getDisciplina()).nomeProfessor(nomeProfessor).valor(avaliacao.getNota()).build();

	}

	private static Map<String, List<Avaliacao>> agruparAvaliacoesPorTrimestre(final List<Avaliacao> avaliacoes) {
		return avaliacoes.stream().collect(Collectors.groupingBy(Avaliacao::getTrimestre, Collectors.mapping(Function.identity(), Collectors.toList())));
	}
	
}