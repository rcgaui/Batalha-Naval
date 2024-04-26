package Model;

public class Casas { // PUBLIC TEMPORARIAMENTE PARA TESTES NA MAIN
	private int numero;
	private char letra;
	private String estadoCasa;
	
	public Casas(int numero, char letra){
		this.numero = numero;
		this.letra = letra;
		estadoCasa = "?";
	}

	public String getEstadoCasa() {
		return estadoCasa;
	}

	public void setEstadoCasa(String estadoCasa) {
		this.estadoCasa = estadoCasa;
	}

	public void atacarCasa() {
        if (estadoCasa == "?") {
        	// estadoCasa pode virar um armamento destruido ou água
        }
    }
}
