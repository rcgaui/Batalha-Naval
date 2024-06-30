package Model;

class Submarino extends Armamentos {
	public Submarino()
	{
		this.armamentoPosicionado = false;
		this.tamanho = 1;	
	}

	public boolean posicionarArmamento(Tabuleiro tabuleiro, String casaTabuleiro, String sentido, Jogador jogador) {
		int letra = casaTabuleiro.charAt(0) - 'A';
		char letraCh = casaTabuleiro.charAt(0);
		int numero = Integer.parseInt(casaTabuleiro.substring(1)) - 1;
		
		if(casaTabuleiro.length() < 2 || casaTabuleiro.length() > 3 || !verificarSintaxe(letraCh, numero) || jogador.getQntSubmarinos() >= 4) {
			return false;
		}
		else {
			int verificar = verificarSentido(tabuleiro, sentido, letra, numero);
            if (verificar == 0 && inserirArmamento(tabuleiro, casaTabuleiro, letra, numero, sentido))
			{
				jogador.addSubmarino();
				if (jogador.getQntSubmarinos() == 4) {
					this.armamentoPosicionado = true;
				}
				return true;
			}
			else return false;
		}
	}
}