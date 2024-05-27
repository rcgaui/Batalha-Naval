package Model;

class Casas { // PUBLIC TEMPORARIAMENTE PARA TESTES NA MAIN
	private int numero;
	private char letra;
	private String estadoCasa;
	
	protected Casas(int numero, char letra){
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
            estadoCasa = "*";
            return "Embarcação atingida!";
        } else if (estadoCasa.equals("?") || estadoCasa.equals("~")) {
            estadoCasa = "~";
            return "Água!";
        } else if (estadoCasa.equals("*")){
            return "Já atingido!";
        }else {
        	return "Ataque inválido!";
        }
    }
}
