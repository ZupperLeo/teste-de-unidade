package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class TesteDoAvaliador {


	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose; 
	private Usuario ana;
	
	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.ana = new Usuario("Maria");
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		Leilao leilao = new Leilao("Playstation 5 novo");
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 250.0));
		leilao.propoe(new Lance(ana, 300.0));
		
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		leiloeiro.avalia(leilao);
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
/*	@Test
	public void deveCalcularMedia() {
		
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
		
	}*/

	@Test
	public void deveEncontrarOsTresMaiores() {
		Leilao leilao = new Leilao("Cubo Mágico");
		
        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(ana, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(ana, 400.0));
        
        leiloeiro.avalia(leilao);
        
        List<Lance> maiores = leiloeiro.getTresMaiores();
        
        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
		
	}

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(ana,450.0));
        leilao.propoe(new Lance(joao,120.0));
        leilao.propoe(new Lance(ana,700.0));
        leilao.propoe(new Lance(joao,630.0));
        leilao.propoe(new Lance(ana,230.0));

        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescente() { 
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(ana,300.0));
        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(ana,100.0));

        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {        
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
        		.lance(joao, 100.0)
        		.lance(ana, 200.00)
        		.lance(joao, 300)
        		.lance(ana, 400)
        		.constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(300, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(ana, 200.0));

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(2, maiores.size());
        assertEquals(200, maiores.get(0).getValor(), 0.00001);
        assertEquals(100, maiores.get(1).getValor(), 0.00001);
    }

    @Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
    
    @Test
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
        try {
            Leilao leilao = new CriadorDeLeilao()
                .para("Playstation 3 Novo")
                .constroi();

            leiloeiro.avalia(leilao);
            Assert.fail();
        }
        catch(RuntimeException e) {
            // deu certo!
        }
    }
    
}
