package com.lucianoortizsilva.boletim;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.commom.Boletim;
import com.lucianoortizsilva.commom.Disciplina;
import com.lucianoortizsilva.commom.Nota;

@RestController
@RequestMapping(value = "/boletins")
public class BoletimController {

	private static List<Boletim> boletins = new ArrayList<>();

	static {
		boletins.add(new Boletim(1L, criarNotasPrimeiroSemestrePara(1L)));
		boletins.add(new Boletim(1L, criarNotasSegundoSemestrePara(1L)));
		boletins.add(new Boletim(1L, criarNotasTerceiroSemestrePara(1L)));
		boletins.add(new Boletim(1L, criarNotasQuartoSemestrePara(1L)));
	}

	
	
	@GetMapping(value = "/aluno/{id}")
	public List<Boletim> getById(@PathVariable(name = "id") final Long id) {
		return findBy(id);
	}
	
	
	
	private List<Boletim> findBy(final Long idAluno) {
		final List<Boletim> list = new ArrayList<>();
		for (final Boletim b : boletins) {
			if (b.getIdAluno().equals(idAluno)) {
				list.add(b);
			}
		}
		return list;
	}
	
	
	private static Set<Nota> criarNotasPrimeiroSemestrePara(final long idAluno) {
		final HashSet<Nota> notas = new HashSet<>();
		notas.add(new Nota(idAluno, 1L, Disciplina.FISICA, "A", LocalDate.of(2020, 3, 30)));
		notas.add(new Nota(idAluno, 2L, Disciplina.PEDAGOGIA, "B", LocalDate.of(2020, 3, 30)));
		notas.add(new Nota(idAluno, 3L, Disciplina.QUIMICA, "C", LocalDate.of(2020, 3, 30)));
		return notas;
	}

	private static Set<Nota> criarNotasSegundoSemestrePara(final long idAluno) {
		final HashSet<Nota> notas = new HashSet<>();
		notas.add(new Nota(idAluno, 1L, Disciplina.FISICA, "B", LocalDate.of(2020, 6, 30)));
		notas.add(new Nota(idAluno, 2L, Disciplina.PEDAGOGIA, "C", LocalDate.of(2020, 6, 30)));
		notas.add(new Nota(idAluno, 3L, Disciplina.QUIMICA, "A", LocalDate.of(2020, 6, 30)));
		return notas;
	}

	private static Set<Nota> criarNotasTerceiroSemestrePara(final long idAluno) {
		final HashSet<Nota> notas = new HashSet<>();
		notas.add(new Nota(idAluno, 1L, Disciplina.FISICA, "B", LocalDate.of(2020, 9, 30)));
		notas.add(new Nota(idAluno, 2L, Disciplina.PEDAGOGIA, "B", LocalDate.of(2020, 9, 30)));
		notas.add(new Nota(idAluno, 3L, Disciplina.QUIMICA, "B", LocalDate.of(2020, 9, 30)));
		return notas;
	}

	private static Set<Nota> criarNotasQuartoSemestrePara(final long idAluno) {
		final HashSet<Nota> notas = new HashSet<>();
		notas.add(new Nota(idAluno, 1L, Disciplina.FISICA, "A", LocalDate.of(2020, 12, 30)));
		notas.add(new Nota(idAluno, 2L, Disciplina.PEDAGOGIA, "C", LocalDate.of(2020, 12, 30)));
		notas.add(new Nota(idAluno, 3L, Disciplina.QUIMICA, "A", LocalDate.of(2020, 12, 30)));
		return notas;
	}

}