package com.lucianoortizsilva.avaliacao;

import static com.lucianoortizsilva.commom.Disciplina.FISICA;
import static com.lucianoortizsilva.commom.Disciplina.MATEMATICA;
import static com.lucianoortizsilva.commom.Disciplina.PEDAGOGIA;
import static com.lucianoortizsilva.commom.Disciplina.PORTUGUES;
import static com.lucianoortizsilva.commom.Disciplina.QUIMICA;
import static com.lucianoortizsilva.commom.TrimestreEnum.UM;
import static com.lucianoortizsilva.commom.TrimestreEnum.DOIS;
import static com.lucianoortizsilva.commom.TrimestreEnum.TRES;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lucianoortizsilva.commom.Avaliacao;

@Repository
public class AvaliacaoRepository {

	private static List<Avaliacao> avaliacoes = new ArrayList<>();
	private static final String MATRICULA_ALUNO = "1";
	
	private static final String PROFESSOR_1 = "1";
	private static final String PROFESSOR_2 = "2";
	private static final String PROFESSOR_3 = "3";
	private static final String PROFESSOR_4 = "4";
	private static final String PROFESSOR_5 = "5";
	
	static {
		avaliacoes.add(Avaliacao.builder().trimestre(UM).matriculaAluno(MATRICULA_ALUNO).disciplina(MATEMATICA).codigoProfessor(PROFESSOR_1).nota(9.0).build());
		avaliacoes.add(Avaliacao.builder().trimestre(UM).matriculaAluno(MATRICULA_ALUNO).disciplina(PEDAGOGIA).codigoProfessor(PROFESSOR_2).nota(8.6).build());
		avaliacoes.add(Avaliacao.builder().trimestre(UM).matriculaAluno(MATRICULA_ALUNO).disciplina(PORTUGUES).codigoProfessor(PROFESSOR_3).nota(7.0).build());
		avaliacoes.add(Avaliacao.builder().trimestre(UM).matriculaAluno(MATRICULA_ALUNO).disciplina(QUIMICA).codigoProfessor(PROFESSOR_4).nota(6.0).build());
		avaliacoes.add(Avaliacao.builder().trimestre(UM).matriculaAluno(MATRICULA_ALUNO).disciplina(FISICA).codigoProfessor(PROFESSOR_5).nota(6.1).build());
		
		avaliacoes.add(Avaliacao.builder().trimestre(DOIS).matriculaAluno(MATRICULA_ALUNO).disciplina(MATEMATICA).codigoProfessor(PROFESSOR_1).nota(6.3).build());
		avaliacoes.add(Avaliacao.builder().trimestre(DOIS).matriculaAluno(MATRICULA_ALUNO).disciplina(PEDAGOGIA).codigoProfessor(PROFESSOR_2).nota(5.6).build());
		avaliacoes.add(Avaliacao.builder().trimestre(DOIS).matriculaAluno(MATRICULA_ALUNO).disciplina(PORTUGUES).codigoProfessor(PROFESSOR_3).nota(9.1).build());
		avaliacoes.add(Avaliacao.builder().trimestre(DOIS).matriculaAluno(MATRICULA_ALUNO).disciplina(QUIMICA).codigoProfessor(PROFESSOR_4).nota(4.4).build());
		avaliacoes.add(Avaliacao.builder().trimestre(DOIS).matriculaAluno(MATRICULA_ALUNO).disciplina(FISICA).codigoProfessor(PROFESSOR_5).nota(6.2).build());
		
		avaliacoes.add(Avaliacao.builder().trimestre(TRES).matriculaAluno(MATRICULA_ALUNO).disciplina(MATEMATICA).codigoProfessor(PROFESSOR_1).nota(8.7).build());
		avaliacoes.add(Avaliacao.builder().trimestre(TRES).matriculaAluno(MATRICULA_ALUNO).disciplina(PEDAGOGIA).codigoProfessor(PROFESSOR_2).nota(6.5).build());
		avaliacoes.add(Avaliacao.builder().trimestre(TRES).matriculaAluno(MATRICULA_ALUNO).disciplina(PORTUGUES).codigoProfessor(PROFESSOR_3).nota(5.2).build());
		avaliacoes.add(Avaliacao.builder().trimestre(TRES).matriculaAluno(MATRICULA_ALUNO).disciplina(QUIMICA).codigoProfessor(PROFESSOR_4).nota(9.0).build());
		avaliacoes.add(Avaliacao.builder().trimestre(TRES).matriculaAluno(MATRICULA_ALUNO).disciplina(FISICA).codigoProfessor(PROFESSOR_5).nota(6.6).build());
	}

	Collection<Avaliacao> findAll() {
		return avaliacoes;
	}

}