package Model;

class Cruzador extends Armamentos {
	public Cruzador()
	{
		this.armamentoPosicionado = false;
		this.tamanho = 4;	
	}
	
	public boolean posicionarArmamento(Tabuleiro tabuleiro, String casaTabuleiro, String sentido, Jogador jogador) {
		int letra = casaTabuleiro.charAt(0) - 'A';
		char letraCh = casaTabuleiro.charAt(0);
		int numero = Integer.parseInt(casaTabuleiro.substring(1)) - 1;
		
		if(casaTabuleiro.length() < 2 || casaTabuleiro.length() > 3 || !verificarSintaxe(letraCh, numero) || jogador.getQntCruzadores() >= 2) {
			return false;
		}
		else {
			if(inserirArmamento(tabuleiro, letra, numero, sentido))
			{
				jogador.addCruzador();
				if (jogador.getQntCruzadores() == 2) {
					this.armamentoPosicionado = true;
				}
				return true;
			}
			else return false;
		}
	}
}