package Model;

import java.util.ArrayList;

abstract class Armamentos {
	protected String casaTabuleiro;
	protected String sentido;
	protected boolean destroyed = false;
	protected ArrayList<String> posicoes = new ArrayList<>();
	protected int tamanho;
	protected boolean armamentoPosicionado;

	public boolean isArmamentoPosicionado() {
		return armamentoPosicionado;
	}

	public void setArmamentoPosicionado(boolean armamentoPosicionado) {
		this.armamentoPosicionado = armamentoPosicionado;
	}
	
	public boolean getIfDestroyed()
	{
		return destroyed;
	}
	
	protected abstract boolean posicionarArmamento(Tabuleiro tabuleiro, String casaTabuleiro, String sentido, Jogador jogador);
	
	public String getCasa()
	{
		return this.casaTabuleiro;
	}
	
	public String getSentido()
	{
		return this.sentido;
	}
	
	protected ArrayList<String> isDestroyed(Tabuleiro tabuleiro)
	{
		int contaPosicoes = 0;
		for (String posicao : posicoes) {
			int letra = posicao.charAt(0) - 'A'; 
			int numero = Integer.parseInt(posicao.substring(1)) - 1;
			System.out.printf("Posicoes: %s", posicao);
            if(tabuleiro.getCasas()[letra][numero].getEstadoCasa() == "*") {
            	contaPosicoes++;
            }
        }
		System.out.printf("Conta posicoes: %d\n", contaPosicoes);
		if(contaPosicoes == this.tamanho) 
			{
				System.out.println("Is destoyed retorna vetor com destruidos\n");
				this.destroyed = true;
				return this.posicoes;
			}
		else return null;
	}
	
	protected boolean verificarSintaxe(char letra, int numero)
	{
		if (letra >= 'A' && letra <= 'O' && numero >= 0 && numero <= 14) return true;
		else return false;	
	}
	
	public int verificarSentido(Tabuleiro tabuleiro, String sentido, int letra, int numero)
	{
		switch (sentido) {
			case "Leste-Oeste":
				if((numero - tamanho + 1) < 0) {
					return 2;
				}
				for (int i = 0; i < tamanho; i++) {
					if(verificarCasas(tabuleiro, letra, numero - i) == 1) {
						return 1;
					}
					else if (verificarCasas(tabuleiro, letra, numero - i) == 2) {
						return 2;
					}
				}
				return 0;
			case "Norte-Sul":
				if((letra + tamanho - 1) > 14) {
					return 2;
				}
				for (int i = 0; i < tamanho; i++) {
					if(verificarCasas(tabuleiro, letra + i, numero) == 1) {
						return 1;
					}
					else if (verificarCasas(tabuleiro, letra + i, numero) == 2) {
						return 2;
					}
				}
			    return 0;
			case "Sul-Norte":
				if((letra - tamanho + 1) < 0) {
					return 2;
				}
				for (int i = 0; i < tamanho; i++) {
					if(verificarCasas(tabuleiro, letra - i, numero) == 1) {
						return 1;
					}
					else if(verificarCasas(tabuleiro, letra - i, numero) == 1) {
						return 2;
					}
				}
			    return 0;
			case "Oeste-Leste":
				if((numero + tamanho - 1) > 14) {
					return 2;
				}
				for (int i = 0; i < tamanho; i++) {
					if(verificarCasas(tabuleiro, letra, numero + i) == 1) {
						return 1;
					}
					else if(verificarCasas(tabuleiro, letra, numero + i) == 1) {
						return 2;
					}
				}
			    return 0;
			default:
				return 2;
		}
	}
	
	public int verificarSentidoHidroAviao(Tabuleiro tabuleiro, String sentido, int letra, int numero)
	{
		switch (sentido) {
			case "Oeste-Leste":
				if (verificarCasas(tabuleiro, letra, numero) == 0 && 
					verificarCasas(tabuleiro, letra + 1, numero + 1) == 0 && 
					verificarCasas(tabuleiro, letra - 1, numero + 1) == 0) {
					return 0;
				}
				else if (verificarCasas(tabuleiro, letra, numero) == 2 || 
						 verificarCasas(tabuleiro, letra + 1, numero + 1) == 2 || 
						 verificarCasas(tabuleiro, letra - 1, numero + 1) == 2) {
					return 2;
				}
				else {
					return 1;
				}
			case "Leste-Oeste":
				if (verificarCasas(tabuleiro, letra, numero) == 0 && 
					verificarCasas(tabuleiro, letra + 1, numero - 1) == 0 && 
					verificarCasas(tabuleiro, letra - 1, numero - 1) == 0) {
					return 0;
				}
				else if (verificarCasas(tabuleiro, letra, numero) == 2 || 
						 verificarCasas(tabuleiro, letra + 1, numero - 1) == 2 || 
						 verificarCasas(tabuleiro, letra - 1, numero - 1) == 2) {
					return 2;
				}
				else {
					return 1;
				}
			case "Norte-Sul":
				if (verificarCasas(tabuleiro, letra, numero) == 0 && 
					verificarCasas(tabuleiro, letra + 1, numero + 1) == 0 && 
					verificarCasas(tabuleiro, letra + 1, numero - 1) == 0) {
					return 0;
				}
				else if (verificarCasas(tabuleiro, letra, numero) == 2 || 
						 verificarCasas(tabuleiro, letra + 1, numero + 1) == 2 || 
						 verificarCasas(tabuleiro, letra + 1, numero - 1) == 2) {
					return 2;
				}
				else {
					return 1;
				}
			case "Sul-Norte":
				if (verificarCasas(tabuleiro, letra, numero) == 0 && 
					verificarCasas(tabuleiro, letra - 1, numero + 1) == 0 && 
					verificarCasas(tabuleiro, letra - 1, numero - 1) == 0) {
					return 0;
				}
				else if (verificarCasas(tabuleiro, letra, numero) == 2 || 
						 verificarCasas(tabuleiro, letra - 1, numero + 1) == 2 || 
						 verificarCasas(tabuleiro, letra - 1, numero - 1) == 2) {
					return 2;
				}
				else {
					return 1;
				}
			default:
				return 2;
		}
	}

	protected int verificarCasas(Tabuleiro tabuleiro, int letra, int numero) {
	    int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	    int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	    if(letra < 0 || letra > 14 || numero < 0 || numero > 14) {
	    	return 2;
	    }
	    
	    if(!tabuleiro.getCasas()[letra][numero].getEstadoCasa().equals("?")) {
	        return 1;
	    }

	    for(int i = 0; i < 8; i++) {
	    	int novaLetra = letra + dx[i];
	        int novoNumero = numero + dy[i];
	        
	        if(novaLetra >= 0 && novaLetra <= 14 && novoNumero >= 0 && novoNumero <= 14) {
	            if(!tabuleiro.getCasas()[novaLetra][novoNumero].getEstadoCasa().equals("?")) {
	                return 1;
	            }
	        }
	    }

	    return 0;
	}
	
	protected boolean inserirArmamento(Tabuleiro tabuleiro, int letra, int numero, String sentido)
	{
		switch (sentido) {
			case "Leste-Oeste":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra][numero - i].setEstadoCasa("!");
					posicoes.add(tabuleiro.getCasas()[letra][numero - 1].getPosicao());
				}
				return true;
			case "Oeste-Leste":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra][numero + i].setEstadoCasa("!");
					posicoes.add(tabuleiro.getCasas()[letra][numero + i].getPosicao());
				}
				return true;
			case "Sul-Norte":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra - i][numero].setEstadoCasa("!");
					posicoes.add(tabuleiro.getCasas()[letra - i][numero].getPosicao());
				}
				return true;
			case "Norte-Sul":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra + i][numero].setEstadoCasa("!");
					posicoes.add(tabuleiro.getCasas()[letra + i][numero].getPosicao());
				}
				return true;
			default:
				return false;
		}
	}
	
	protected boolean inserirArmamentoHidroAviao(Tabuleiro tabuleiro, int letra, int numero, String sentido)
	{
		switch (sentido) {
			case "Oeste-Leste":
				tabuleiro.getCasas()[letra][numero].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra][numero].getPosicao());
				tabuleiro.getCasas()[letra + 1][numero + 1].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra + 1][numero + 1].getPosicao());
				tabuleiro.getCasas()[letra - 1][numero + 1].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra - 1][numero + 1].getPosicao());
				
				return true;
			case "Leste-Oeste":
				tabuleiro.getCasas()[letra][numero].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra][numero].getPosicao());
				tabuleiro.getCasas()[letra + 1][numero - 1].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra + 1][numero - 1].getPosicao());
				tabuleiro.getCasas()[letra - 1][numero - 1].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra - 1][numero - 1].getPosicao());
				return true;
			case "Norte-Sul":
				tabuleiro.getCasas()[letra][numero].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra][numero].getPosicao());
				tabuleiro.getCasas()[letra + 1][numero + 1].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra + 1][numero + 1].getPosicao());
				tabuleiro.getCasas()[letra + 1][numero - 1].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra + 1][numero - 1].getPosicao());
				return true;
			case "Sul-Norte":
				tabuleiro.getCasas()[letra][numero].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra][numero].getPosicao());
				tabuleiro.getCasas()[letra - 1][numero + 1].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra - 1][numero + 1].getPosicao());
				tabuleiro.getCasas()[letra - 1][numero - 1].setEstadoCasa("!");
				posicoes.add(tabuleiro.getCasas()[letra - 1][numero - 1].getPosicao());
				return true;
			default:
				return false;
		}
	}
}