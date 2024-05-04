package Model;

public class Hidroaviao extends Armamentos {
	public Hidroaviao()
	{
		this.armamentoPosicionado = false;
		this.tamanho = 3;	
	}

	
	public boolean posicionarArmamento(Tabuleiro tabuleiro, String casaTabuleiro, String sentido, Jogador jogador) {
		int letra = casaTabuleiro.charAt(0) - 'A'; // Pegar o primeiro caracter da string
		char letraCh = casaTabuleiro.charAt(0);
		int numero = Integer.parseInt(casaTabuleiro.substring(1)) - 1; // Pegar o segundo caracter da string
		
		if(casaTabuleiro.length() < 2 || casaTabuleiro.length() > 3  || !verificarSintaxe(letraCh, numero) || jogador.getQntHidroavioes() >= 5) {
			System.out.println("Erro de verificacao\n");
			return false;
		}
		else {
			if(verificarSentido(tabuleiro, sentido, letra, numero))
			{
				System.out.println("Sentido Verificado\n");
				if(inserirArmamento(tabuleiro, letra, numero, sentido))
				{
					System.out.println("HidroAviao Inserido com Sucesso!\n");
					jogador.addHidroaviao();
					if (jogador.getQntHidroavioes() == 5) {
						this.armamentoPosicionado = true;
					}
					return true;
				}
				else return false;
			}
			else 
			{
				System.out.println("HidroAviao nao pode ser inserido nesse sentido\n");
				return false;
			}
		}
	}
	
	public boolean verificarSentido(Tabuleiro tabuleiro, String sentido,int letra, int numero)
	{
		switch (sentido) {
			case "Leste-Oeste":
				return (verificarCasas(tabuleiro, letra, numero) && verificarCasas(tabuleiro, letra - 1, numero - 1) && 
						verificarCasas(tabuleiro, letra + 1, numero - 1)) ? true : false;
			case "Norte-Sul":
				return (verificarCasas(tabuleiro, letra, numero) && verificarCasas(tabuleiro, letra + 1, numero - 1) && 
						verificarCasas(tabuleiro, letra + 1, numero + 1)) ? true : false;
			case "Sul-Norte":
				return (verificarCasas(tabuleiro, letra, numero) && verificarCasas(tabuleiro, letra - 1, numero + 1) && 
						verificarCasas(tabuleiro, letra - 1, numero - 1)) ? true : false;
			case "Oeste-Leste":
				return (verificarCasas(tabuleiro, letra, numero) && verificarCasas(tabuleiro, letra + 1, numero + 1) && 
						verificarCasas(tabuleiro, letra - 1, numero + 1)) ? true : false;
			default:
				return false;
		}
				
	}
	
	public boolean inserirArmamento(Tabuleiro tabuleiro, int letra, int numero, String sentido)
	{
		switch (sentido) {
			case "Leste-Oeste":
				tabuleiro.getCasas()[letra][numero].setEstadoCasa("!");
				tabuleiro.getCasas()[letra + 1][numero - 1].setEstadoCasa("!");
				tabuleiro.getCasas()[letra - 1][numero - 1].setEstadoCasa("!");			
				return true;
			case "Oeste-Leste":
				tabuleiro.getCasas()[letra][numero].setEstadoCasa("!");
				tabuleiro.getCasas()[letra + 1][numero + 1].setEstadoCasa("!");
				tabuleiro.getCasas()[letra - 1][numero + 1].setEstadoCasa("!");
				return true;
			case "Sul-Norte":
				tabuleiro.getCasas()[letra][numero].setEstadoCasa("!");
				tabuleiro.getCasas()[letra - 1][numero + 1].setEstadoCasa("!");
				tabuleiro.getCasas()[letra - 1][numero - 1].setEstadoCasa("!");
				return true;
			case "Norte-Sul":
				tabuleiro.getCasas()[letra][numero].setEstadoCasa("!");
				tabuleiro.getCasas()[letra + 1][numero - 1].setEstadoCasa("!");
				tabuleiro.getCasas()[letra + 1][numero + 1].setEstadoCasa("!");
				return true;
			default:
				return false;
		}
	}
}
