package Model;

import javax.swing.JFileChooser;

public class modelFacade {
	private Jogador J1;
	private Jogador J2;
	private Tabuleiro tabuleiroJ1;
	private Tabuleiro tabuleiroJ2;
	private Turno turno;
	
	//Pecas jogador 1
	private Submarino submarinoJ1;
	private Couracado couracadoJ1;
	private Hidroaviao hidroaviaoJ1;
	private Cruzador cruzadorJ1;
	private Destroyer destroyerJ1;
	
	//pecas jogador 2
	private Submarino submarinoJ2;
	private Couracado couracadoJ2;
	private Hidroaviao hidroaviaoJ2;
	private Cruzador cruzadorJ2;
	private Destroyer destroyerJ2;
	
	
	
	public void novaPartida(String jogador1, String jogador2)
	{
		J1 = new Jogador(jogador1);
		J2 = new Jogador(jogador2);
		tabuleiroJ1 = new Tabuleiro();
		tabuleiroJ2 = new Tabuleiro();
		turno = new Turno(J1, J2);
		submarinoJ1 = new Submarino();
		couracadoJ1 = new Couracado();
		hidroaviaoJ1 = new Hidroaviao();
		cruzadorJ1 = new Cruzador();
		destroyerJ1 = new Destroyer();
		
		//pecas jogador 2
		submarinoJ2 = new Submarino();
		couracadoJ2 = new Couracado();
		hidroaviaoJ2 = new Hidroaviao();
		cruzadorJ2 = new Cruzador();
		destroyerJ2 = new Destroyer();
	}
	
	public void converteCoordenada(float x, float y)
	{
		//Preencher Dps
	}
	
	public boolean VerificaPosicao(String nomeBarco, String sentido, String casa) //Adicionar coordenadas da tela como parâmetro
	{
		
		//Converter aqui a casa para letra e numero
	
		switch (nomeBarco) {
			case "submarino":
				if(turno.getVezJogar() == J1)
					return submarinoJ1.verificarSentido(tabuleiroJ1, sentido, 0, 0); //Usar o conversor pra inserir a casa correta com base na coordenada
				else if (turno.getVezJogar() == J2)
					return submarinoJ2.verificarSentido(tabuleiroJ1, sentido, 0, 0);
				else return false;
			case "couracado":
				if(turno.getVezJogar() == J1)
					return couracadoJ1.verificarSentido(tabuleiroJ1, sentido, 0, 0); //Usar o conversor pra inserir a casa correta com base na coordenada
				else if (turno.getVezJogar() == J2)
					return couracadoJ2.verificarSentido(tabuleiroJ1, sentido, 0, 0);
				else return false;
			case "destroyer":
				if(turno.getVezJogar() == J1)
					return destroyerJ1.verificarSentido(tabuleiroJ1, sentido, 0, 0); //Usar o conversor pra inserir a casa correta com base na coordenada
				else if (turno.getVezJogar() == J2)
					return destroyerJ2.verificarSentido(tabuleiroJ1, sentido, 0, 0);
				else return false;
			case "hidroaviao":
				if(turno.getVezJogar() == J1)
					return hidroaviaoJ1.verificarSentido(tabuleiroJ1, sentido, 0, 0); //Usar o conversor pra inserir a casa correta com base na coordenada
				else if (turno.getVezJogar() == J2)
					return hidroaviaoJ2.verificarSentido(tabuleiroJ1, sentido, 0, 0);
				else return false;
			case "cruzador":
				if(turno.getVezJogar() == J1)
					return cruzadorJ1.verificarSentido(tabuleiroJ1, sentido, 0, 0); //Usar o conversor pra inserir a casa correta com base na coordenada
				else if (turno.getVezJogar() == J2)
					return cruzadorJ2.verificarSentido(tabuleiroJ1, sentido, 0, 0);
				else return false;
			default:
				return false;
		}
	}
		
	public void trocaTurno()
	{
		turno.trocaTurno();
	}
	
	public void PosicionaEmbarcacao(String nomeBarco, String sentido, String casa) //Adicionar coordenadas da tela como parâmetro
	{
		switch (nomeBarco) {
		case "submarino":
			if(turno.getVezJogar() == J1)
				 submarinoJ1.posicionarArmamento(tabuleiroJ1, casa, sentido, J1);
			else if (turno.getVezJogar() == J2)
				 submarinoJ2.posicionarArmamento(tabuleiroJ2, casa, sentido, J2);
		case "couracado":
			if(turno.getVezJogar() == J1)
				 couracadoJ1.posicionarArmamento(tabuleiroJ1, casa, sentido, J1); 
			else if (turno.getVezJogar() == J2)
				 couracadoJ2.posicionarArmamento(tabuleiroJ2, casa, sentido, J2);
		case "destroyer":
			if(turno.getVezJogar() == J1)
				 destroyerJ1.posicionarArmamento(tabuleiroJ1, casa, sentido, J1);
			else if (turno.getVezJogar() == J2)
				 destroyerJ2.posicionarArmamento(tabuleiroJ2, casa, sentido, J2);
		case "hidroaviao":
			if(turno.getVezJogar() == J1)
				 hidroaviaoJ1.posicionarArmamento(tabuleiroJ1, casa, sentido, J1);
			else if (turno.getVezJogar() == J2)
				 hidroaviaoJ2.posicionarArmamento(tabuleiroJ2, casa, sentido, J2);

		case "cruzador":
			if(turno.getVezJogar() == J1)
				 cruzadorJ1.posicionarArmamento(tabuleiroJ1, casa, sentido, J1); 
			else if (turno.getVezJogar() == J2)
				 cruzadorJ2.posicionarArmamento(tabuleiroJ2, casa, sentido, J2);
		}	
	}
	
	public void atacar(int letra, int numero)
	{
		if(turno.getVezJogar() == J1) 
			tabuleiroJ2.realizarTiro(letra, numero);
		else if(turno.getVezJogar() == J2) 
			tabuleiroJ1.realizarTiro(letra,numero);
	}
	
	
	public void carregaPartida(JFileChooser file)
	{
		
	}
	
	public void salvarPartida()
	{
		
	}

}
