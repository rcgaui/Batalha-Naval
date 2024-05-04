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

	public String atacarCasa() {
		if (estadoCasa.equals("!")) {
            estadoCasa = "*";  // Armamento atingido
            return "Embarcação atingida!";
        } else if (estadoCasa.equals("?")) {
            estadoCasa = "~";  // Água atingida
            return "Água!";
        } else {
            return "Já atingido!";
        }
    }
}
