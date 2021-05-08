package com.lucianoortizsilva.gateway.endpoint.stub;

import java.util.ArrayList;
import java.util.List;

import com.lucianoortizsilva.gateway.endpoint.dto.BoletimResponse;
import com.lucianoortizsilva.gateway.endpoint.dto.BoletimResponse.Lancamento;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GatewayStub {

	public static List<BoletimResponse> getBoletins() {
		final BoletimResponse boletim = new BoletimResponse();
		boletim.setPeriodo(null);
		boletim.setNomeAluno(null);
		boletim.getLancamentos().add(new Lancamento(null, null, null));
		final List<BoletimResponse> boletins = new ArrayList<>(1);
		boletins.add(boletim);
		return boletins;
	}

}