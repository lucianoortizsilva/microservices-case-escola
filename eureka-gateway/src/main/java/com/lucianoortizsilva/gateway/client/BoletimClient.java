package com.lucianoortizsilva.gateway.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lucianoortizsilva.commom.Boletim;

@FeignClient(name = "ms-boletim")
public interface BoletimClient {

	@GetMapping(value = "/boletins/aluno/{id}")
	public List<Boletim> getById(@PathVariable(name = "id") final Long idAluno);

}