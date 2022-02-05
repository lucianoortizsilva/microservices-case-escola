package com.lucianoortizsilva.aluno.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lucianoortizsilva.commom.Avaliacao;

/**
 * 
 * @see ms-avaliacao
 *
 */
@FeignClient(name = "ms-avaliacao")
interface AvaliacaoClient {

	@GetMapping(value = "/avaliacoes/aluno/{id}")
	Optional<List<Avaliacao>> getAvaliacoesByAlunoId(@PathVariable(name = "id") final Long idAluno);

}