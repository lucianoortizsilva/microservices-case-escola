package com.lucianoortizsilva.aluno;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang.StringUtils.EMPTY;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.lucianoortizsilva.commom.Aluno;
import com.lucianoortizsilva.commom.Avaliacao;
import com.lucianoortizsilva.commom.Boletim;
import com.lucianoortizsilva.commom.Boletim.Detalhe;
import com.lucianoortizsilva.commom.Boletim.Trimestre;
import com.lucianoortizsilva.commom.Curso;
import com.lucianoortizsilva.commom.Professor;
import com.lucianoortizsilva.commom.TrimestreEnum;

@Service
class AlunoService {

	@Autowired
	@SuppressWarnings("rawtypes")
	private CircuitBreakerFactory circuitBreakerFactory;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private CallRestProfessor callRestProfessor;

	@Autowired
	private CallRestAvaliacao callRestAvaliacao;

	
	
	Aluno getAlunoByMatricula(final String matricula) {
		return alunoRepository.findAll()
				.stream()
				.filter(aluno -> aluno.getMatricula().equalsIgnoreCase(matricula))
				.findFirst()
				.orElse(null);
	}
	
	
	
	Boletim getBoletimByMatriculaAluno(final String matricula) {
		Optional<List<Avaliacao>> avaliacoes = getAvaliacoesPorMatriculaAluno(matricula);
		if (avaliacoes.isPresent()) {
			String nomeAluno = getNomeAlunoByMatricula(matricula);
			return createBoletimWith(nomeAluno, avaliacoes.get());
		} else {
			throw new AlunoException("Boletim não encontrado, para o aluno informado.");
		}
	}

	
	
	private Optional<List<Avaliacao>> getAvaliacoesPorMatriculaAluno(final String matricula) {
		final CircuitBreaker circuitBreaker = this.circuitBreakerFactory.create("circuitbreaker-avaliacao");
		return circuitBreaker.run(() -> this.callRestAvaliacao.getAvaliacoesByMatriculaAluno(matricula), throwable -> Optional.of(Collections.emptyList()));
	}
	
	
	
	private String getNomeAlunoByMatricula(final String matricula) {
		return alunoRepository.findAll()
				.stream()
				.filter(aluno -> aluno.getMatricula().equalsIgnoreCase(matricula))
				.map(Aluno::getNome)
				.findFirst()
				.orElse(EMPTY);
	}


	private Boletim createBoletimWith(final String nomeAluno, final List<Avaliacao> avaliacoes) {
		final Boletim boletim = new Boletim();
		boletim.setAluno(nomeAluno);
		boletim.setCurso(Curso.ENSINO_FUNDAMENTAL.getDescricao());
		
		final Map<TrimestreEnum, List<Avaliacao>> avaliacoesAgrupadasPorTrimestre = agruparAvaliacoesPorTrimestre(avaliacoes);
		for (final TrimestreEnum trimestreEnum : TrimestreEnum.values()) {
			boletim.getTrimestres().add(createTrimestre(trimestreEnum, avaliacoesAgrupadasPorTrimestre));
		}
		return boletim;
	}
	
	

	private Trimestre createTrimestre(final TrimestreEnum trimestreEnum, final Map<TrimestreEnum, List<Avaliacao>> avaliacoesAgrupadasPorTrimestre) {
		final Trimestre trimestre = new Trimestre();
		trimestre.setDescricao(trimestreEnum.getDescricao());
		
		final List<Avaliacao> avaliacoesPorTrimestre = avaliacoesAgrupadasPorTrimestre.get(trimestreEnum);
		if(isNotEmpty(avaliacoesPorTrimestre)) {
			avaliacoesPorTrimestre.forEach(avaliacao -> {
				final String nomeProfessor = getNomeProfessorByCodigo(avaliacao.getCodigoProfessor());
				if (trimestreEnum.equals(avaliacao.getTrimestre())) {
					trimestre.getDetalhes().add(new Detalhe(nomeProfessor, avaliacao.getDisciplina().name(), avaliacao.getNota()));
				}
			});
		}
		return trimestre;
	}



	private static Map<TrimestreEnum, List<Avaliacao>> agruparAvaliacoesPorTrimestre(final List<Avaliacao> avaliacoes) {
		return avaliacoes.stream().collect(Collectors.groupingBy(Avaliacao::getTrimestre, Collectors.mapping(Function.identity(), Collectors.toList())));
	}

	
	
	private String getNomeProfessorByCodigo(final String codigo) {
		final CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker-professor");
		final Optional<Professor> professor = circuitBreaker.run(() -> this.callRestProfessor.getProfessorByCodigo(codigo), throwable -> Optional.of(Professor.builder().nome("Indisponível no momento").build()));
		String nomeProfessor = EMPTY; 
		if (professor.isPresent()) {
			nomeProfessor = professor.get().getNome();
		}
		return nomeProfessor;
	}

	
}