package com.lucianoortizsilva.gateway.endpoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucianoortizsilva.gateway.endpoint.dto.BoletimResponse;
import com.lucianoortizsilva.gateway.endpoint.stub.GatewayStub;

@WebMvcTest(controllers = GatewayController.class)
@ExtendWith(value = SpringExtension.class)
public class GatewayControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GatewayBoletim gatewayBoletim;

	@Test
	@DisplayName("DADO QUE pesquiso boletins por id aluno, QUANDO os dados forem encontrados, Então ele deverá ser retornado com sucesso, E status http 200")
	void test_1() throws Exception {
		final List<BoletimResponse> boletimEsperado = GatewayStub.getBoletins();
		Mockito.when(this.gatewayBoletim.gerarBoletimPara(1L)).thenReturn(boletimEsperado);
		final MvcResult mvcResult = this.mockMvc.perform(get("/boletins/aluno/1").contentType("application/json")).andExpect(status().isOk()).andReturn();
		final String boletimRetornado = mvcResult.getResponse().getContentAsString();
		assertThat(boletimRetornado).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(boletimEsperado));
	}

}