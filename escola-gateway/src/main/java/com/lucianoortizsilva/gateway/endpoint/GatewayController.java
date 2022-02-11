package com.lucianoortizsilva.gateway.endpoint;

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

	@GetMapping(value = "/alunos/v1/{matricula}/boletim")
	public ResponseEntity<Boletim> gerarBoletimByMatriculaAluno(@PathVariable(name = "matricula") final String matricula) {
		log.info("[escola-gateway] GET > /alunos/v1/{}/boletim", matricula);
		return ResponseEntity.ok().body(alunoClientV1.getBoletimByMatriculaAluno(matricula));
	}

}