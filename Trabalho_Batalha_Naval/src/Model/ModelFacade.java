package Model;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Controller.Control;
import View.ObservadorAtaqueIF;

public class ModelFacade {
	//Ambos
	private Turno turno;
	
	//Montagem Jogador 1
	private Jogador J1;
	private Tabuleiro tabuleiroJ1;
	private ArrayList<Armamentos> armamentosJ1;
	private ArrayList<String> destruidosJ1;
	private Submarino submarino1J1;
	private Submarino submarino2J1;
	private Submarino submarino3J1;
	private Submarino submarino4J1;
	private Hidroaviao hidroaviao1J1;
	private Hidroaviao hidroaviao2J1;
	private Hidroaviao hidroaviao3J1;
	private Hidroaviao hidroaviao4J1;
	private Hidroaviao hidroaviao5J1;
	private Cruzador cruzador1J1;
	private Cruzador cruzador2J1;
	private Destroyer destroyer1J1;
	private Destroyer destroyer2J1;
	private Destroyer destroyer3J1;
	private Couracado couracado1J1;
	
	//Montagem Jogador 2
	private Jogador J2;
	private Tabuleiro tabuleiroJ2;
	private ArrayList<Armamentos> armamentosJ2;
	private ArrayList<String> destruidosJ2;
	private Submarino submarino1J2;
	private Submarino submarino2J2;
	private Submarino submarino3J2;
	private Submarino submarino4J2;
	private Hidroaviao hidroaviao1J2;
	private Hidroaviao hidroaviao2J2;
	private Hidroaviao hidroaviao3J2;
	private Hidroaviao hidroaviao4J2;
	private Hidroaviao hidroaviao5J2;
	private Cruzador cruzador1J2;
	private Cruzador cruzador2J2;
	private Destroyer destroyer1J2;
	private Destroyer destroyer2J2;
	private Destroyer destroyer3J2;
	private Couracado couracado1J2;
	
	public void novaPartida(String jogador1, String jogador2)
	{
		J1 = new Jogador(jogador1);
		J2 = new Jogador(jogador2);
		tabuleiroJ1 = new Tabuleiro();
		tabuleiroJ2 = new Tabuleiro();
		armamentosJ1 = new ArrayList<>();
		armamentosJ2 = new ArrayList<>();
		turno = new Turno(J1, J2);
		inicializarBarcos();
	}
	
	public boolean salvarPartida(File file)
	{
		return turno.salvaJogo(file, tabuleiroJ1, tabuleiroJ2, armamentosJ1, armamentosJ1);
	}
	
	public void carregaPartida(JFileChooser file)
	{
		J1 = new Jogador();
		J2 = new Jogador();
		tabuleiroJ1 = new Tabuleiro();
		tabuleiroJ2 = new Tabuleiro();
		armamentosJ1 = new ArrayList<>();
		armamentosJ2 = new ArrayList<>();
		turno = new Turno(J1, J2);
		Control.getController().comecarAtaque();
		inicializarBarcos();
		turno.carregarJogo(file.getSelectedFile().getAbsolutePath(), tabuleiroJ1, tabuleiroJ2,armamentosJ1, armamentosJ2);
	}
	
	private void inicializarBarcos()
	{
        // Inicializando os submarinos do Jogador 1
        submarino1J1 = new Submarino();
        submarino2J1 = new Submarino();
        submarino3J1 = new Submarino();
        submarino4J1 = new Submarino();
        armamentosJ1.add(submarino1J1);
        armamentosJ1.add(submarino2J1);
        armamentosJ1.add(submarino3J1);
        armamentosJ1.add(submarino4J1);

        // Inicializando os hidroaviões do Jogador 1
        hidroaviao1J1 = new Hidroaviao();
        hidroaviao2J1 = new Hidroaviao();
        hidroaviao3J1 = new Hidroaviao();
        hidroaviao4J1 = new Hidroaviao();
        hidroaviao5J1 = new Hidroaviao();
        armamentosJ1.add(hidroaviao1J1);
        armamentosJ1.add(hidroaviao2J1);
        armamentosJ1.add(hidroaviao3J1);
        armamentosJ1.add(hidroaviao4J1);
        armamentosJ1.add(hidroaviao5J1);

        // Inicializando os cruzadores do Jogador 1
        cruzador1J1 = new Cruzador();
        cruzador2J1 = new Cruzador();
        armamentosJ1.add(cruzador1J1);
        armamentosJ1.add(cruzador2J1);

        // Inicializando os destroyers do Jogador 1
        destroyer1J1 = new Destroyer();
        destroyer2J1 = new Destroyer();
        destroyer3J1 = new Destroyer();
        armamentosJ1.add(destroyer1J1);
        armamentosJ1.add(destroyer2J1);
        armamentosJ1.add(destroyer3J1);

        // Inicializando o couraçado do Jogador 1
        couracado1J1 = new Couracado();
        armamentosJ1.add(couracado1J1);

        // Inicializando os submarinos do Jogador 2
        submarino1J2 = new Submarino();
        submarino2J2 = new Submarino();
        submarino3J2 = new Submarino();
        submarino4J2 = new Submarino();
        armamentosJ2.add(submarino1J2);
        armamentosJ2.add(submarino2J2);
        armamentosJ2.add(submarino3J2);
        armamentosJ2.add(submarino4J2);

        // Inicializando os hidroaviões do Jogador 2
        hidroaviao1J2 = new Hidroaviao();
        hidroaviao2J2 = new Hidroaviao();
        hidroaviao3J2 = new Hidroaviao();
        hidroaviao4J2 = new Hidroaviao();
        hidroaviao5J2 = new Hidroaviao();
        armamentosJ2.add(hidroaviao1J2);
        armamentosJ2.add(hidroaviao2J2);
        armamentosJ2.add(hidroaviao3J2);
        armamentosJ2.add(hidroaviao4J2);
        armamentosJ2.add(hidroaviao5J2);

        // Inicializando os cruzadores do Jogador 2
        cruzador1J2 = new Cruzador();
        cruzador2J2 = new Cruzador();
        armamentosJ2.add(cruzador1J2);
        armamentosJ2.add(cruzador2J2);

        // Inicializando os destroyers do Jogador 2
        destroyer1J2 = new Destroyer();
        destroyer2J2 = new Destroyer();
        destroyer3J2 = new Destroyer();
        armamentosJ2.add(destroyer1J2);
        armamentosJ2.add(destroyer2J2);
        armamentosJ2.add(destroyer3J2);

        // Inicializando o couraçado do Jogador 2
        couracado1J2 = new Couracado();
        armamentosJ2.add(couracado1J2);
    }
	
	public boolean anyDestroyed()
	{
		ArrayList<String> posicoes;
		if(isJ1())
		{
			for(Armamentos barco: armamentosJ1)
			{
				posicoes = barco.isDestroyed(tabuleiroJ1);
				if(posicoes != null)
				{
					destruidosJ1.addAll(posicoes);
					return true;
				}
				else {}
			}
			return false;
		}
		else
		{
			for(Armamentos barco: armamentosJ2)
			{
				posicoes = barco.isDestroyed(tabuleiroJ2);
				if(posicoes != null)
				{
					destruidosJ2.addAll(posicoes);
					return true;
				}
				else {}
			}
			return false;
		}
	}
	
	public ArrayList<String> getDestroyed()
	{
		if(anyDestroyed())
		{
			if(isJ1())
			{
				return destruidosJ2;
			}
			else
			{
				return destruidosJ1;
			}
		}
		else
		{
			return null;
		}

	}
	
	public String getName(int i)
	{
		if(i == 1) return this.J1.getNome();
		else if(i==2) return this.J2.getNome();
		else return "";
	}
	
	public boolean isJ1()
	{
		if(turno.getVezJogar() == J1) return true;
		else return false;
	}
	
	public void trocaTurno()
	{
		turno.trocaTurno();
	}
	
	public String converteCoordenada(double x, double y)
	{
        if (y < 125.0 || y > 425.0) {
            return "Fora do tabuleiro";
        }

        int indexY = (int) ((y - 125) / 20);
        char casaY;
        if (indexY >= 0 && indexY <= 14) {
            casaY = (char) ('A' + indexY);
        } else {
            return "Fora do tabuleiro";
        }

        String casaX;
        if (isJ1()) {
            if (x < 125.0 || x > 425.0) {
                return "Fora do tabuleiro";
            }
            int indexX = (int) ((x - 125) / 20);
            if (indexX >= 0 && indexX <= 14) {
            	casaX = String.valueOf(1 + indexX);
            } else {
                return "Fora do tabuleiro";
            }
        } else {
            if (x < 575.0 || x > 875.0) {
                return "Fora do tabuleiro";
            }
            int indexX = (int) ((x - 575) / 20);
            if (indexX >= 0 && indexX <= 14) {
            	casaX = String.valueOf(1 + indexX);
            } else {
                return "Fora do tabuleiro";
            }
        }

        return casaY + casaX;
    }
	
	public int[] converteCoordenadaPosicionarArmamentos(int x, int y)
	{
		int posX = -1;
		int posY = -1;

		for (int i = 0; i < 15; i++) {
		    for (int j = 0; j < 15; j++) {
		        double minX = 575.0 + i * 20.0;
		        double maxX = minX + 20.0;
		        double minY = 125.0 + j * 20.0;
		        double maxY = minY + 20.0;

		        if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
		            posX = i;
		            posY = j;
		            break;
		        }
		    }
		    if (posX != -1 && posY != -1) {
		        break;
		    }
		}

		return new int[]{posX, posY};
	}
	
	public int verificaPosicao(String nomeBarco, int numeroBarco, String sentido, String casa)
	{
		int letra = casa.charAt(0) - 'A';
		int numero = Integer.parseInt(casa.substring(1)) - 1;
		
		numeroBarco--;
		switch (nomeBarco) {
			case "submarino":
				if(turno.getVezJogar() == J1) {
					return armamentosJ1.get(numeroBarco).verificarSentido(tabuleiroJ1, sentido, letra, numero);	
				}
				else if (turno.getVezJogar() == J2) {
					return armamentosJ1.get(numeroBarco).verificarSentido(tabuleiroJ2, sentido, letra, numero);
				}
			case "hidroaviao":
				numeroBarco += 4;
				if(turno.getVezJogar() == J1) {
					return armamentosJ1.get(numeroBarco).verificarSentidoHidroAviao(tabuleiroJ1, sentido, letra, numero);
				}
				else if (turno.getVezJogar() == J2) {
					return armamentosJ1.get(numeroBarco).verificarSentidoHidroAviao(tabuleiroJ2, sentido, letra, numero);	
				}
			case "cruzador":
				numeroBarco += 9;
				if(turno.getVezJogar() == J1) {
					return armamentosJ1.get(numeroBarco).verificarSentido(tabuleiroJ1, sentido, letra, numero);
				}
				else if (turno.getVezJogar() == J2) {
					return armamentosJ1.get(numeroBarco).verificarSentido(tabuleiroJ2, sentido, letra, numero);
				}
			case "destroyer":
				numeroBarco += 11;
				if(turno.getVezJogar() == J1) {
					return armamentosJ1.get(numeroBarco).verificarSentido(tabuleiroJ1, sentido, letra, numero);	
				}
				else if (turno.getVezJogar() == J2) {
					return armamentosJ1.get(numeroBarco).verificarSentido(tabuleiroJ2, sentido, letra, numero);	
				}
			case "couracado":
				numeroBarco += 14;
				if(turno.getVezJogar() == J1) {
					return armamentosJ1.get(numeroBarco).verificarSentido(tabuleiroJ1, sentido, letra, numero);	
				}
				else if (turno.getVezJogar() == J2) {
					return armamentosJ1.get(numeroBarco).verificarSentido(tabuleiroJ2, sentido, letra, numero);
				}
			default:
				return 2;
		}
	}
	
	public boolean posicionaEmbarcacao(String nomeBarco, int numeroBarco, String sentido, String casa)
	{
		numeroBarco--;
		switch (nomeBarco) {
			case "submarino":
				if(turno.getVezJogar() == J1) {
					return armamentosJ1.get(numeroBarco).posicionarArmamento(tabuleiroJ1, casa, sentido, J1);
				}
				else if (turno.getVezJogar() == J2) {
					return armamentosJ2.get(numeroBarco).posicionarArmamento(tabuleiroJ2, casa, sentido, J2);
				}
				else return false;
			case "hidroaviao":
				numeroBarco += 4;
				if(turno.getVezJogar() == J1) {
					return armamentosJ1.get(numeroBarco).posicionarArmamento(tabuleiroJ1, casa, sentido, J1);
				}
				else if (turno.getVezJogar() == J2) {
					return armamentosJ2.get(numeroBarco).posicionarArmamento(tabuleiroJ2, casa, sentido, J2);
				}
				else return false;
			case "cruzador":
				numeroBarco += 9;
				if(turno.getVezJogar() == J1) {
					return armamentosJ1.get(numeroBarco).posicionarArmamento(tabuleiroJ1, casa, sentido, J1);
				}
				else if (turno.getVezJogar() == J2) {
					return armamentosJ2.get(numeroBarco).posicionarArmamento(tabuleiroJ2, casa, sentido, J2);
				}
				else return false;
			case "destroyer":
				numeroBarco += 11;
				if(turno.getVezJogar() == J1) {
					return armamentosJ1.get(numeroBarco).posicionarArmamento(tabuleiroJ1, casa, sentido, J1);
				}
				else if (turno.getVezJogar() == J2) {
					return armamentosJ2.get(numeroBarco).posicionarArmamento(tabuleiroJ2, casa, sentido, J2);
				}
				else return false;
			case "couracado":
				numeroBarco += 14;
				if(turno.getVezJogar() == J1) {
					return armamentosJ1.get(numeroBarco).posicionarArmamento(tabuleiroJ1, casa, sentido, J1);
				}
				else if (turno.getVezJogar() == J2) {
					return armamentosJ2.get(numeroBarco).posicionarArmamento(tabuleiroJ2, casa, sentido, J2);
				}
				else return false;
			default:
				return false;
		}
	}
	
	public void atacar(int letra, int numero)
	{
		if(turno.getVezJogar() == J1) {
			tabuleiroJ2.realizarTiro(letra, numero);	
		}
		else if(turno.getVezJogar() == J2) {
			tabuleiroJ1.realizarTiro(letra,numero);
		}
	}
	
	public void registra(ObservadorAtaqueIF observador)
	{
		tabuleiroJ1.registraObservador(observador);
		tabuleiroJ2.registraObservador(observador);
	}
}