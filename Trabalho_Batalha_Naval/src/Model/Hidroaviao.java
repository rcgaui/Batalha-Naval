package Model;

class Hidroaviao extends Armamentos {
	public Hidroaviao()
	{
		this.armamentoPosicionado = false;
		this.tamanho = 3;	
	}

	public boolean posicionarArmamento(Tabuleiro tabuleiro, String casaTabuleiro, String sentido, Jogador jogador) {
		int letra = casaTabuleiro.charAt(0) - 'A';
		char letraCh = casaTabuleiro.charAt(0);
		int numero = Integer.parseInt(casaTabuleiro.substring(1)) - 1;
		
		if(casaTabuleiro.length() < 2 || casaTabuleiro.length() > 3 || !verificarSintaxe(letraCh, numero) || jogador.getQntHidroavioes() >= 5) {
			return false;
		}
		else {
			if(inserirArmamentoHidroAviao(tabuleiro, casaTabuleiro, letra, numero, sentido))
			{
				jogador.addHidroaviao();
				if (jogador.getQntHidroavioes() == 5) {
					this.armamentoPosicionado = true;
				}
				return true;
			}
			else return false;
		}
	}
}