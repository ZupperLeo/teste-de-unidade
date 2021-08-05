package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
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
		
		assertEquals(400, leiloeiro.getMedia(), 0.00001);
	}
	
	@Test
	public void testaMediaZeroLances() {
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Playstation 5 novo");
		Avaliador leiloeiro = new Avaliador();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(0, leiloeiro.getMedia(), 0.00001);
		
	}

	@Test
	public void deveEncontrarOsTresMaiores() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Cubo Mágico");
		
        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));
        
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        
        List<Lance> maiores = leiloeiro.getTresMaiores();
        
        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
		
	}

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
        Usuario joao = new Usuario("Joao"); 
        Usuario maria = new Usuario("Maria"); 
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,450.0));
        leilao.propoe(new Lance(joao,120.0));
        leilao.propoe(new Lance(maria,700.0));
        leilao.propoe(new Lance(joao,630.0));
        leilao.propoe(new Lance(maria,230.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {
        Usuario joao = new Usuario("Joao"); 
        Usuario maria = new Usuario("Maria"); 
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,100.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
    }
    
}
