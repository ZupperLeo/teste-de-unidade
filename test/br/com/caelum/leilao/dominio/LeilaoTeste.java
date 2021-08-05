package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTeste {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("PC de super mercado");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Jose"), 200.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(200.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("PC de super mercado");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Jose"), 130.60));
		leilao.propoe(new Lance(new Usuario("Ana"), 750.0));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(130.60, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(750, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("PC de super mercado");
		Usuario user = new Usuario("Jose");
		
		leilao.propoe(new Lance(user, 130.60));
		leilao.propoe(new Lance(user, 200.60));
		
		assertEquals(1, leilao.getLances().size());
	}
}
