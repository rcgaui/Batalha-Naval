package Model;

class Submarino extends Armamentos {
	public Submarino()
	{
		this.armamentoPosicionado = false;
		this.tamanho = 1;	
	}

	
	public boolean posicionarArmamento(Tabuleiro tabuleiro, String casaTabuleiro, String sentido, Jogador jogador) {
		int letra = casaTabuleiro.charAt(0) - 'A'; // Pegar o primeiro caracter da string
		char letraCh = casaTabuleiro.charAt(0);
		int numero = Integer.parseInt(casaTabuleiro.substring(1)) - 1; // Pegar o segundo caracter da string
		
		if(casaTabuleiro.length() < 2 || casaTabuleiro.length() > 3 || !verificarSintaxe(letraCh, numero) || jogador.getQntSubmarinos() >= 4) {
			System.out.println("Erro ao posicionar\n");
			return false;
		}
		else {
			if(verificarCasas(tabuleiro, letra, numero))
			{
				System.out.println("Sentido Verificado\n");
				if(inserirArmamento(tabuleiro, letra, numero, sentido))
				{
					System.out.println("Submarino Inserido com Sucesso!\n");
					jogador.addSubmarino();
					if (jogador.getQntSubmarinos() == 4) {
						this.armamentoPosicionado = true;
					}
					return true;
				}
				else return false;
			}
			else 
			{
				return false;
			}
		}
	}
}
