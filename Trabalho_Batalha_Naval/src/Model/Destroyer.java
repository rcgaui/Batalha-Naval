package Model;

public class Destroyer extends Armamentos {
	public Destroyer()
	{
		this.armamentoPosicionado = false;
		this.tamanho = 2;	
	}
	
	
	public boolean posicionarArmamento(Tabuleiro tabuleiro, String casaTabuleiro, String sentido, Jogador jogador) {
		int letra = casaTabuleiro.charAt(0) - 'A'; // Pegar o primeiro caracter da string
		char letraCh = casaTabuleiro.charAt(0);
		int numero = Integer.parseInt(casaTabuleiro.substring(1)) - 1; // Pegar o segundo caracter da string
		
		if(casaTabuleiro.length() < 2 || casaTabuleiro.length() > 3 || !verificarSintaxe(letraCh, numero) || jogador.getQntDestroyers() >= 3) {
			System.out.println("Erro ao posicionar\n");
			return false;
		}
		else {
			if(verificarSentido(tabuleiro, sentido, letra, numero))
			{
				System.out.println("Sentido Verificado\n");
				if(inserirArmamento(tabuleiro, letra, numero, sentido))
				{
					System.out.println("Destroyer Inserido com Sucesso!\n");
					jogador.addDestroyer();
					if (jogador.getQntDestroyers() == 3) {
						this.armamentoPosicionado = true;
					}
					return true;
				}
				else return false;
			}
			else 
			{
				System.out.println("Destroyer nao pode ser inserido nesse sentido\n");
				return false;
			}
		}
	}
}
