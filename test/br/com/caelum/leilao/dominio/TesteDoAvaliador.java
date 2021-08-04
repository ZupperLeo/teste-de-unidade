package br.com.caelum.leilao.dominio;

import org.junit.Assert;
import org.junit.Test;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 5 novo");
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 250.0));
		leilao.propoe(new Lance(maria, 300.0));
		
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveCalcularMedia() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 5 novo");
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 500.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(400, leiloeiro.getMedia(), 0.00001);
	}
	
	@Test
	public void testaMediaZeroLances() {
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Playstation 5 novo");
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(0, leiloeiro.getMedia(), 0.00001);
		
	}
}
