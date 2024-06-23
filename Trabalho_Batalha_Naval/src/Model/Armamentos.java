package Model;

import java.util.ArrayList;

abstract class Armamentos {
	protected String casaTabuleiro;
	protected String sentido;
	protected ArrayList<String> posicoes = new ArrayList<>();
	protected int tamanho;
	protected boolean armamentoPosicionado;

	public boolean isArmamentoPosicionado() {
		return armamentoPosicionado;
	}

	public void setArmamentoPosicionado(boolean armamentoPosicionado) {
		this.armamentoPosicionado = armamentoPosicionado;
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
            if(tabuleiro.getCasas()[letra][numero].getPosicao() != "*") {
            	contaPosicoes++;
            }
        }
		if(contaPosicoes == this.tamanho) return this.posicoes;
		else return null;
	}
	
	protected boolean verificarSintaxe(char letra, int numero)
	{
		if (letra >= 'A' && letra <= 'O' && numero >= 0 && numero <= 14) return true;
		else return false;	
	}
	
	public boolean verificarSentido(Tabuleiro tabuleiro, String sentido, int letra, int numero)
	{
		switch (sentido) {
			case "Leste-Oeste":
				if((numero - tamanho + 1) < 0) {
					return false;
				}
				for (int i = 0; i < tamanho; i++) {
					if(!verificarCasas(tabuleiro, letra, numero - i)) {
						return false;
					}
				}
				return true;
			case "Norte-Sul":
				if((letra + tamanho - 1) > 14) {
					return false;
				}
				for (int i = 0; i < tamanho; i++) {
					if(!verificarCasas(tabuleiro, letra + i, numero)) {
						return false;
					}
				}
			    return true;
			case "Sul-Norte":
				if((letra - tamanho + 1) < 0) {
					return false;
				}
				for (int i = 0; i < tamanho; i++) {
					if(!verificarCasas(tabuleiro, letra - i, numero)) {
						return false;
					}
				}
			    return true;
			case "Oeste-Leste":
				if((numero + tamanho - 1) > 14) {
					return false;
				}
				for (int i = 0; i < tamanho; i++) {
					if(!verificarCasas(tabuleiro, letra, numero + i)) {
						return false;
					}
				}
			    return true;
			default:
				return false;
		}
	}
	
	public boolean verificarSentidoHidroAviao(Tabuleiro tabuleiro, String sentido, int letra, int numero)
	{
		switch (sentido) {
			case "Oeste-Leste":
				return (verificarCasas(tabuleiro, letra, numero) && 
						verificarCasas(tabuleiro, letra + 1, numero + 1) && 
						verificarCasas(tabuleiro, letra - 1, numero + 1)) ? true : false;
			case "Leste-Oeste":
				return (verificarCasas(tabuleiro, letra, numero) && 
						verificarCasas(tabuleiro, letra + 1, numero - 1) && 
						verificarCasas(tabuleiro, letra - 1, numero - 1)) ? true : false;
			case "Norte-Sul":
				return (verificarCasas(tabuleiro, letra, numero) && 
						verificarCasas(tabuleiro, letra + 1, numero + 1) && 
						verificarCasas(tabuleiro, letra + 1, numero - 1)) ? true : false;
			case "Sul-Norte":
				return (verificarCasas(tabuleiro, letra, numero) && 
						verificarCasas(tabuleiro, letra - 1, numero + 1) && 
						verificarCasas(tabuleiro, letra - 1, numero - 1)) ? true : false;
			default:
				return false;
		}
	}
	
	protected boolean verificarCasas(Tabuleiro tabuleiro, int letra, int numero) {
	    int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	    int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	    if(letra < 0 || letra > 14 || numero < 0 || numero > 14) {
	    	return false;
	    }
	    
	    if(!tabuleiro.getCasas()[letra][numero].getEstadoCasa().equals("?")) {
	        return false;
	    }

	    for(int i = 0; i < 8; i++) {
	    	int novaLetra = letra + dx[i];
	        int novoNumero = numero + dy[i];
	        
	        if(novaLetra >= 0 && novaLetra <= 14 && novoNumero >= 0 && novoNumero <= 14) {
	            if(!tabuleiro.getCasas()[novaLetra][novoNumero].getEstadoCasa().equals("?")) {
	            	// System.out.println("A embarcação conflita com outras embarcacoes\n");
	                return false;
	            }
	        }
	    }

	    return true;
	}
	
	protected boolean inserirArmamento(Tabuleiro tabuleiro, int letra, int numero, String sentido)
	{
		String coordenada;
		switch (sentido) {
			case "Leste-Oeste":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra][numero - i].setEstadoCasa("!");
					char letraLinha = (char) ('A' + letra);
			        int numeroColuna = numero + 1;
					coordenada = "" + letraLinha + numeroColuna;
					posicoes.add(coordenada);
				}
				return true;
			case "Oeste-Leste":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra][numero + i].setEstadoCasa("!");
					char letraLinha = (char) ('A' + letra);
			        int numeroColuna = numero + 1;
					coordenada = "" + letraLinha + numeroColuna;
					posicoes.add(coordenada);
				}
				return true;
			case "Sul-Norte":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra - i][numero].setEstadoCasa("!");
					char letraLinha = (char) ('A' + letra);
			        int numeroColuna = numero + 1;
					coordenada = "" + letraLinha + numeroColuna;
					posicoes.add(coordenada);
				}
				return true;
			case "Norte-Sul":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra + i][numero].setEstadoCasa("!");
					char letraLinha = (char) ('A' + letra);
			        int numeroColuna = numero + 1;
					coordenada = "" + letraLinha + numeroColuna;
					posicoes.add(coordenada);
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
				tabuleiro.getCasas()[letra + 1][numero + 1].setEstadoCasa("!");
				tabuleiro.getCasas()[letra - 1][numero + 1].setEstadoCasa("!");
				return true;
			case "Leste-Oeste":
				tabuleiro.getCasas()[letra][numero].setEstadoCasa("!");
				tabuleiro.getCasas()[letra + 1][numero - 1].setEstadoCasa("!");
				tabuleiro.getCasas()[letra - 1][numero - 1].setEstadoCasa("!");			
				return true;
			case "Norte-Sul":
				tabuleiro.getCasas()[letra][numero].setEstadoCasa("!");
				tabuleiro.getCasas()[letra + 1][numero + 1].setEstadoCasa("!");
				tabuleiro.getCasas()[letra + 1][numero - 1].setEstadoCasa("!");
				return true;
			case "Sul-Norte":
				tabuleiro.getCasas()[letra][numero].setEstadoCasa("!");
				tabuleiro.getCasas()[letra - 1][numero + 1].setEstadoCasa("!");
				tabuleiro.getCasas()[letra - 1][numero - 1].setEstadoCasa("!");
				return true;
			default:
				return false;
		}
	}
}