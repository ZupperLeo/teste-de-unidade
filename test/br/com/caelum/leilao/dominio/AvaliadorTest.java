package br.com.caelum.leilao.dominio;


public class AvaliadorTest {
	
    private double maiorDeTodos = Double.NEGATIVE_INFINITY;//pega o menor valor do tipo double
    private double menorDeTodos = Double.POSITIVE_INFINITY;

    public void avalia(Leilao leilao) {
        for(Lance lance : leilao.getLances()) {
            if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            else if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
        }
    }

    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorLance() {
        return menorDeTodos;
    }
}
