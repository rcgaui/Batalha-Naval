package Model;

class Destroyer extends Armamentos {
	public Destroyer()
	{
		this.armamentoPosicionado = false;
		this.tamanho = 2;	
	}
	
	public boolean posicionarArmamento(Tabuleiro tabuleiro, String casaTabuleiro, String sentido, Jogador jogador) {
		int letra = casaTabuleiro.charAt(0) - 'A';
		char letraCh = casaTabuleiro.charAt(0);
		int numero = Integer.parseInt(casaTabuleiro.substring(1)) - 1;
		
		if(casaTabuleiro.length() < 2 || casaTabuleiro.length() > 3 || !verificarSintaxe(letraCh, numero) || jogador.getQntDestroyers() >= 3) {
			return false;
		}
		else {
			if(inserirArmamento(tabuleiro, casaTabuleiro, letra, numero, sentido))
			{
				jogador.addDestroyer();
				if (jogador.getQntDestroyers() == 3) {
					this.armamentoPosicionado = true;
				}
				return true;
			}
			else return false;
		}
	}
}