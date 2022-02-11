package com.lucianoortizsilva.aluno;

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
interface CallRestAvaliacao {

	@GetMapping(value = "/avaliacoes/aluno/{matricula}")
	Optional<List<Avaliacao>> getAvaliacoesByMatriculaAluno(@PathVariable(name = "matricula") final String matricula);

}