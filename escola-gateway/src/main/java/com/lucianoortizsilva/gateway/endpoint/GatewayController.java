package com.lucianoortizsilva.gateway.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.gateway.response.BoletimResponse;

@RestController
public class GatewayController {

	@Autowired
	private GatewayBoletim gatewayBoletim;

	@GetMapping(value = "/boletins/aluno/{id}")
	public ResponseEntity<List<BoletimResponse>> gerarBoletimPara(@PathVariable(name = "id") final Long idAluno) {
		return ResponseEntity.ok().body(this.gatewayBoletim.gerarBoletimPara(idAluno));
	}

}