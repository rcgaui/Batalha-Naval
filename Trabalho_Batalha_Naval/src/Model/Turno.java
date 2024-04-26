package Model;

public class Turno { // PUBLIC TEMPORARIAMENTE PARA TESTES NA MAIN
	private boolean jogoRodando;
	private String vezJogar;

	public Turno() {
		jogoRodando = true;
		vezJogar = "Jogador 1"; // Por padrão é "Jogador 1" pois o salvamento do jogo sempre acontecerá quando for a vez do jogador 1
	}
	
	public boolean isJogoRodando() {
		return jogoRodando;
	}

	public void setJogoRodando(boolean jogoRodando) {
		this.jogoRodando = jogoRodando;
	}
	
	public String getVezJogar() {
		return vezJogar;
	}

	public void setVezJogar(String vezJogar) {
		this.vezJogar = vezJogar;
	}
}
