package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import Controller.Control;
import View.ObservadorAtaqueIF;

class Casas implements ObservadoAtaqueIF{ 
	private int numero;
	private char letra;
	private String posicao;
	private String estadoCasa;
	private List<ObservadorAtaqueIF> lst = new ArrayList<ObservadorAtaqueIF>();
	
	protected Casas(int numero, char letra){
		this.numero = numero;
		this.letra = letra;
		this.posicao = letra + Integer.toString(numero);
		estadoCasa = "?";
	}
	
	public void add(ObservadorAtaqueIF observador)
	{
		lst.add(observador);
	}
	
	private void atualiza()
	{
		ListIterator<ObservadorAtaqueIF> li = lst.listIterator();
		
		while(li.hasNext()) {
			li.next().notify(this);
		}
	}
	
	public String getPosicao()
	{
		return this.posicao;
	}
	
	public String get(int n)
	{
		if(n == 0)
		{
			String casa = this.letra + String.valueOf(this.numero);
			return casa;
		}
		else if(n == 1)
		{
			return estadoCasa;
		}
		else
		{
			return "";
		}
	}

	public String getEstadoCasa() {
		return estadoCasa;
	}

	public void setEstadoCasa(String estadoCasa) {
		this.estadoCasa = estadoCasa;
		atualiza();
	}

	public String atacarCasa() {

		if (estadoCasa.equals("!")) {
            estadoCasa = "*";
            atualiza();
            return "Embarcação atingida!";
        } else if(estadoCasa.equals("?")) {
        	estadoCasa = "~";
        	atualiza();
        	return "Água atingida!";
        } else if (estadoCasa.equals("~")) {
            atualiza();
            return "Já atingido! (Água)";
        } else if (estadoCasa.equals("*")){
        	atualiza();
            return "Já atingido! (Embarcação)";
        } else {
        	return "Ataque inválido!";
        }
    }
}