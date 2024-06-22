package Model;

import View.ObservadorAtaqueIF;

class Tabuleiro {
	private Casas casas[][];
	private int qntNumeros;
	private int qntLetras;
	
	public Tabuleiro() {
		char casa;
		this.qntNumeros = 15; // Por padrão é 15
		this.qntLetras = 15; // Por padrão é 15
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
	
	public boolean gameOver()
	{
		int qtdDestruidos = 0;
		
		for (int i = 0; i < qntLetras; i++) {
            for (int j = 0; j < qntNumeros; j++) {
            	if(casas[i][j].getEstadoCasa().equals("*")) qtdDestruidos++;
            }
        }
		
		if(qtdDestruidos == 38) return true;
		else return false;
	}
	
	public void registraObservador(ObservadorAtaqueIF observador)
	{
		for (int i = 0; i < qntLetras; i++) {
            for (int j = 0; j < qntNumeros; j++) {
                casas[i][j].add(observador);
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
}