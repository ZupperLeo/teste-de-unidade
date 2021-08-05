package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;// pega o menor valor do tipo double
	private double menorDeTodos = Double.POSITIVE_INFINITY;// pega o maior valor do tipo double
	private double media = 0;
	private List<Lance> maiores;

	public void avalia(Leilao leilao) {
		double total = 0;
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorDeTodos)
				maiorDeTodos = lance.getValor();
			if (lance.getValor() < menorDeTodos)
				menorDeTodos = lance.getValor();
			total += lance.getValor();
		}

		if (total == 0) {
			media = 0;
			return;
		}

		media = Math.ceil(total / leilao.getLances().size());

		maiores = new ArrayList<Lance>(leilao.getLances());
		
		Collections.sort(maiores, new Comparator<Lance>() {

			public int compare(Lance o1, Lance o2) {
				if (o1.getValor() < o2.getValor())
					return 1;
				if (o1.getValor() > o2.getValor())
					return -1;
				return 0;
			}
		});
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());

	}

	public double getMaiorLance() {
		return maiorDeTodos;
	}

	public double getMenorLance() {
		return menorDeTodos;
	}

	public double getMedia() {
		return media;
	}

	public List<Lance> getTresMaiores() {
		return this.maiores;
	}
}
