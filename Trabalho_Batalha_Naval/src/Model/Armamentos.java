package Model;

import java.util.Iterator;

public abstract class Armamentos { // PUBLIC TEMPORARIAMENTE PARA TESTES NA MAIN
	protected String casaTabuleiro;
	protected String sentido;
	protected int tamanho;
	protected boolean armamentoPosicionado;
	protected static int qntCouracados = 0;
	protected static int qntSubmarinos = 0;
	protected static int qntCruzadores = 0;
	protected static int qntHidroAvioes = 0;
	protected static int qntDestroyers = 0;
	
	public Armamentos() {
		this.armamentoPosicionado = false;
	}
	
	public boolean isArmamentoPosicionado() {
		return armamentoPosicionado;
	}

	public void setArmamentoPosicionado(boolean armamentoPosicionado) {
		this.armamentoPosicionado = armamentoPosicionado;
	}
	
	public abstract boolean posicionarArmamento(Tabuleiro tabuleiro, String casaTabuleiro, String sentido, Jogador jogador);
	
	public boolean inserirArmamento(Tabuleiro tabuleiro, int letra, int numero, String sentido)
	{
		switch (sentido) {
			case "Leste-Oeste":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra][numero - i].setEstadoCasa("!");
				}
				return true;
			case "Oeste-Leste":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra][numero + i].setEstadoCasa("!");
				}
				return true;
			case "Sul-Norte":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra - i][numero].setEstadoCasa("!");
				}
				return true;
			case "Norte-Sul":
				for (int i = 0; i < this.tamanho; i++) {
					tabuleiro.getCasas()[letra + i][numero].setEstadoCasa("!");
				}
				return true;
			default:
				return false;
		}
	}
	
	public boolean verificarSintaxe(char letra, int numero)
	{
		if (letra >= 'A' && letra <= 'O' && numero >= 0 && numero <= 14)
			return true;
		else 
			return false;	
	}
	
	public boolean verificarSentido(Tabuleiro tabuleiro, String sentido,int letra, int numero)
	{
		switch (sentido) {
			case "Leste-Oeste":
				// Verificar se posição com esse sentido sai do conjunto (A-O, 1-15)
				if((numero  - tamanho) < -1) {
					System.out.println("A embarcação nao pode ser inserida nesse sentido\n");
					return false;
				}
				// Verificar casa escolhida e casas adjacentes
				for (int i = 0; i < tamanho; i++) {
					if(!verificarCasas(tabuleiro, letra, numero - i)) {
						return false; // Se em algum momento da verificação encontrou uma casa que já está ocupada
					}
				}
				return true;
			case "Norte-Sul":
				if((letra + tamanho) > 15) {
					System.out.println("A embarcação nao pode ser inserida nesse sentido\n");
					return false;
				}
				for (int i = 0; i < tamanho; i++) {
					if(!verificarCasas(tabuleiro, letra + i, numero)) {
						return false;
					}
				}
			    return true;
			case "Sul-Norte":
				if((letra - tamanho) < -1) {
					System.out.println("A embarcação nao pode ser inserida nesse sentido\n");
					return false;
				}
				for (int i = 0; i < tamanho; i++) {
					if(!verificarCasas(tabuleiro, letra - i, numero)) {
						return false;
					}
				}
			    return true;
			case "Oeste-Leste":
				if((numero + tamanho) > 15) {
					System.out.println("A embarcação nao pode ser inserida nesse sentido\n");
					return false;
				}
				for (int i = 0; i < tamanho; i++) {
					if(!verificarCasas(tabuleiro, letra, numero + i)) {
						return false;
					}
				}
			    return true;
			default:
				System.out.println("Sentido Inválido\n");
				return false;
		}
	}
	
	public boolean verificarCasas(Tabuleiro tabuleiro, int letra, int numero) {
	    // Define os deslocamentos para as 8 direções possíveis
		// Respectivamente de acordo com os índices dos vetores abaixo:
		// (Norte, Nordeste, Leste, Sudeste, Sul, Sudoeste, Oeste, Noroeste)
	    int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	    int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	    
	    // Verifica se a casa pode ser acessada
	    if(letra < 0 || letra > 14 || numero < 0 || numero > 14)
	    {
	    	return false;
	    }
	    // Verifica a casa escolhida
	    if(!tabuleiro.getCasas()[letra][numero].getEstadoCasa().equals("?")) {
	        return false;
	    }

	    // Verifica as casas adjacentes
	    for(int i = 0; i < 8; i++) {
	        int novaLetra = letra + dx[i];
	        int novoNumero = numero + dy[i];

	        // Verifica se a nova posição está dentro do tabuleiro
	        if(novaLetra >= 0 && novaLetra < 15 && novoNumero >= 0 && novoNumero < 15) {
	            if(!tabuleiro.getCasas()[novaLetra][novoNumero].getEstadoCasa().equals("?")) {
	            	System.out.println("A embarcação conflita com outras embarcacoes\n");
	                return false;
	            }
	        }
	    }

	    return true;
	}
}
