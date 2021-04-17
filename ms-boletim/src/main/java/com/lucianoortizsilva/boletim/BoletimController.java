package com.lucianoortizsilva.boletim;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucianoortizsilva.boletim.clients.AlunoServiceClient;
import com.lucianoortizsilva.commom.Aluno;
import com.lucianoortizsilva.commom.Boletim;

@RestController
@RequestMapping(value = "/boletins")
public class BoletimController {

	@Autowired
	private AlunoServiceClient alunoServiceClient;

	private static List<Boletim> boletins = new ArrayList<>();

	static {
		boletins.add(new Boletim(1L, 1L, "2020", "1", Double.valueOf(9.7), Double.valueOf(6.7), Double.valueOf(7)));
		boletins.add(new Boletim(2L, 1L, "2020", "2", Double.valueOf(7.7), Double.valueOf(4.7), Double.valueOf(8.4)));
		boletins.add(new Boletim(3L, 2L, "2020", "1", Double.valueOf(8.7), Double.valueOf(3.7), Double.valueOf(2.9)));
		boletins.add(new Boletim(4L, 3L, "2020", "1", Double.valueOf(4.7), Double.valueOf(9.9), Double.valueOf(10)));
		boletins.add(new Boletim(5L, 3L, "2020", "2", Double.valueOf(8.7), Double.valueOf(10), Double.valueOf(4.9)));
	}

	@GetMapping(value = "/aluno/{id}")
	public List<Boletim> getById(@PathVariable(name = "id") final Long id) {
		final Aluno aluno = this.alunoServiceClient.getAluno(id);
		if (aluno == null) {
			return new ArrayList<>();
		} else {
			return findBy(id);
		}
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

}