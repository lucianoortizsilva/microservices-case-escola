package com.lucianoortizsilva.gateway.endpoint;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Boletim;
import com.lucianoortizsilva.gateway.service.AlunoClientV1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GatewayController {

	@Autowired
	private AlunoClientV1 alunoClientV1;

	@GetMapping(value = "/alunos/v1/{id}/boletim")
	public ResponseEntity<List<Boletim>> gerarBoletimPara(@PathVariable(name = "id") final Long idAluno) {
		log.info("GET > /alunos/v1/{}/boletim", idAluno);
		return ResponseEntity.ok().body(alunoClientV1.getBoletimByAlunoId(idAluno).stream().collect(Collectors.toList()));
	}

}