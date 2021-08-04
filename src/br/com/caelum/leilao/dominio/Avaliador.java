package br.com.caelum.leilao.dominio;

public class Avaliador {
	
    private double maiorDeTodos = Double.NEGATIVE_INFINITY;//pega o menor valor do tipo double
    private double menorDeTodos = Double.POSITIVE_INFINITY;//pega o maior valor do tipo double
    private double media = 0;

    public void avalia(Leilao leilao) {
    	double total = 0;
        for(Lance lance : leilao.getLances()) {
            if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
            total += lance.getValor();
        }
        
        if(total == 0) { 
        	media = 0;
        	return;
        }
        
        media = Math.ceil(total / leilao.getLances().size());
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
}
