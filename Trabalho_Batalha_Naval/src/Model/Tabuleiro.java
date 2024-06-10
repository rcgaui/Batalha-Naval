package Model;

import View.ObservadorAtaqueIF;

class Tabuleiro {
	private Casas casas[][];
	private int qntNumeros;
	private int qntLetras;
	
	public Tabuleiro() {
		char casa;
		qntNumeros = 15; // Por padrão é 15
		qntLetras = 15; // Por padrão é 15
		casas = new Casas[qntLetras][qntNumeros]; // Letras referem-se à linhas e números à colunas
		
		for (int i = 0; i < qntLetras; i++) {
            for (int j = 0; j < qntNumeros; j++) {
                casa = (char)('A' + i);
                casas[i][j] = new Casas(j + 1, casa);
            }
        }
	}
	
	public Casas[][] getCasas() {
		return casas;
	}
	
	public void registraObservador(ObservadorAtaqueIF observador)
	{
		for (int i = 0; i < qntLetras; i++) {
            for (int j = 0; j < qntNumeros; j++) {
                casas[i][j].add(observador);;
            }
        }
	}
	
	public String realizarTiro(int letra, int numero) {
		if (letra < 0 || letra >= casas.length || numero < 0 || numero >= casas[letra].length) {
	        return "Coordenadas inválidas, tente novamente";
	    }
        Casas casaAlvo = casas[letra][numero];
        return casaAlvo.atacarCasa();
    }

	public void imprimeTabuleiro(boolean mostrarDetalhes) {
	    System.out.print("   ");
	    
	    for (int i = 1; i <= qntLetras; i++) {
	    	System.out.print(String.format("%02d", i) + " ");
	    }
	    
	    System.out.println();
	    
	    for (int i = 1; i <= qntNumeros; i++) {
	    	System.out.print((char)('A' + i - 1) + " ");
	    	
	    	if (mostrarDetalhes) {
		        for (int j = 1; j <= qntLetras; j++) {
		        	
		            if (casas[i - 1][j - 1].getEstadoCasa().equals("?")) {
		                System.out.print(" ? ");
		            } else if(casas[i - 1][j - 1].getEstadoCasa().equals("!")) {
		                System.out.print(" ! ");
		            }
		            else if(casas[i - 1][j - 1].getEstadoCasa().equals("*")) {
		                System.out.print(" * ");
		            }
		            else if(casas[i - 1][j - 1].getEstadoCasa().equals("~")) {
		                System.out.print(" ~ ");
		            }
		            else {
		            	System.out.print(" # ");
		            }
		        }
	    	}
	    	else {
	    		for (int j = 1; j <= qntLetras; j++) {

		            if(casas[i - 1][j - 1].getEstadoCasa().equals("*")) {
		                System.out.print(" * ");
		            }
		            else if(casas[i - 1][j - 1].getEstadoCasa().equals("~")) {
		                System.out.print(" ~ ");
		            }
		            else {
		            	System.out.print(" # ");
		            }
		        }
	    	}
	        
	        System.out.println();
	    }
	}
	
	
}
