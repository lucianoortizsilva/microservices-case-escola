package com.lucianoortizsilva.gateway.endpoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucianoortizsilva.commom.Avaliacao;
import com.lucianoortizsilva.gateway.exception.http.ErroInesperadoException;
import com.lucianoortizsilva.gateway.response.BoletimResponse;
import com.lucianoortizsilva.gateway.response.BoletimResponse.Lancamento;
import com.lucianoortizsilva.gateway.service.AlunoService;
import com.lucianoortizsilva.gateway.service.AvaliacaoService;
import com.lucianoortizsilva.gateway.service.ProfessorService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
class GatewayBoletim {

	@Autowired
	private AvaliacaoService avaliacaoService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private AlunoService alunoService;

	
	
	public List<BoletimResponse> gerarBoletimPara(final Long idAluno) {
		final List<Avaliacao> avaliacoes = this.avaliacaoService.getAvaliacoesPor(idAluno);
		final List<BoletimResponse> response = new ArrayList<>();
		try {
			final String nomeAluno = this.alunoService.getNomeAlunoPor(idAluno);
			final Map<String, List<Avaliacao>> avaliacoesPorPeriodo = agruparAvaliacoesPorPeriodo(avaliacoes);
			for (final Entry<String, List<Avaliacao>> map : avaliacoesPorPeriodo.entrySet()) {
				response.add(this.criarBoletimResponsePara(nomeAluno, map.getKey(), map.getValue()));
			}
		} catch (final Exception e) {
			log.error(e.getMessage());
			throw new ErroInesperadoException();
		}
		return response;
	}

	
	
	private static Map<String, List<Avaliacao>> agruparAvaliacoesPorPeriodo(final List<Avaliacao> avaliacoes) {
		return avaliacoes.stream().collect(Collectors.groupingBy(Avaliacao::getPeriodo, Collectors.mapping(Function.identity(), Collectors.toList())));
	}
	
	
	
	private BoletimResponse criarBoletimResponsePara(final String nomeAluno, final String periodo, final List<Avaliacao> avaliacoesPorPerido) {
		final BoletimResponse response = new BoletimResponse();
		response.setPeriodo(periodo);
		response.setNomeAluno(nomeAluno);
		response.getLancamentos().addAll(this.getLancamentos(avaliacoesPorPerido));
		return response;
	}
	
	
	
	private List<Lancamento> getLancamentos(final List<Avaliacao> avaliacoesPorPerido) {
		final Iterator<Avaliacao> it = avaliacoesPorPerido.iterator();
		final List<Lancamento> lancamentos = new ArrayList<>();
		while (it.hasNext()) {
			final Avaliacao avaliacao = it.next();
			final String nomeProfessor = this.professorService.getNomeProfessorBy(avaliacao.getIdProfessor());
			lancamentos.add(criarLancamentoPara(nomeProfessor, avaliacao));
		}
		return lancamentos;
	}
	

	
	private static Lancamento criarLancamentoPara(final String nomeProfessor, final Avaliacao avaliacao) {
		return Lancamento.builder()
				.disciplina(avaliacao.getDisciplina())
				.nomeProfessor(nomeProfessor)
				.valor(avaliacao.getNota())
				.build();
		
	}

}