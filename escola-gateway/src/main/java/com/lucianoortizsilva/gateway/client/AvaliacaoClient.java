package com.lucianoortizsilva.gateway.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lucianoortizsilva.commom.Avaliacao;

@FeignClient(name = "ms-avaliacao")
public interface AvaliacaoClient {

	@GetMapping(value = "/avaliacoes/aluno/{id}")
	public Optional<List<Avaliacao>> getById(@PathVariable(name = "id") final Long idAluno);

}