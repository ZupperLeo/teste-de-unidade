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
	
	@Test
	public void naoDeveAceitarMaisQue5Lances() {
		Leilao leilao = new Leilao("PC de super mercado");
		Usuario jose = new Usuario("Jose");
		Usuario ana = new Usuario("Ana");
		
		leilao.propoe(new Lance(jose, 130.60));
		leilao.propoe(new Lance(ana, 200.00));

		leilao.propoe(new Lance(jose, 230.60));
		leilao.propoe(new Lance(ana, 300.0));
		
		leilao.propoe(new Lance(jose, 330.60));
		leilao.propoe(new Lance(ana, 400.0));
		
		leilao.propoe(new Lance(jose, 430.60));
		leilao.propoe(new Lance(ana, 500.60));

		leilao.propoe(new Lance(jose, 530.60));
		leilao.propoe(new Lance(ana, 600.60));

		//Deve ser ignorado
		leilao.propoe(new Lance(ana, 1000.60));
		
		assertEquals(10, leilao.getLances().size());//verifica se apenas 10 lances foram aceitos, no caso, 5 por usuario
		assertEquals(600.60, leilao.getLances().get(leilao.getLances().size() - 1)
				.getValor(), 0.00001);//Verifica se o utlimo lance dado foi 600.60
	}
	
    @Test
    public void deveDobrarOUltimoLanceDado() {
		Leilao leilao = new Leilao("PC de super mercado");
		Usuario jose = new Usuario("Jose");
		Usuario ana = new Usuario("Ana");

        leilao.propoe(new Lance(jose, 2000));
        leilao.propoe(new Lance(ana, 3000));
        leilao.dobraLance(jose);

        assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
    }
}
















