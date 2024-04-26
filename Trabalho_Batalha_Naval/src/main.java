
import java.util.Scanner; // TEMPORARIAMENTE PARA TESTES

import Model.Armamentos; // TEMPORARIAMENTE PARA TESTES
import Model.Couracado;
import Model.Destroyer;
import Model.Hidroaviao;
import Model.Jogador; // TEMPORARIAMENTE PARA TESTES
import Model.Submarino;
import Model.Tabuleiro; // TEMPORARIAMENTE PARA TESTES
import Model.Turno; // TEMPORARIAMENTE PARA TESTES

public class main {
	public static void main(String[] args) {
		// Jogador 1
		Jogador jogador1 = new Jogador("Joao");
		Tabuleiro tabuleiroJogador1 = new Tabuleiro();
		System.out.println("Tabuleiro do jogador 1 atualmente:");
		tabuleiroJogador1.imprimeTabuleiro();
		Hidroaviao armamentosJogador1 = new Hidroaviao();
		
		System.out.println("");
		
		// Jogador 2
		Jogador jogador2 = new Jogador("Maria");
		Tabuleiro tabuleiroJogador2 = new Tabuleiro();
		System.out.println("Tabuleiro do jogador 2 atualmente:");
		tabuleiroJogador2.imprimeTabuleiro();
		Destroyer armamentosJogador2 = new Destroyer();
		
		System.out.println("");
		
		Turno turno = new Turno();
		
		Scanner scanner = new Scanner(System.in);
		String inputTeclado;
		String inputSentido;
		
		// Isso provavelmente será passado para o pacote Controller em outra interação
		while(turno.isJogoRodando()) {
			if(turno.getVezJogar() == "Jogador 1") {
				if(!armamentosJogador1.isArmamentoPosicionado()) {
					// posicionar armamentos
					System.out.println("Selecione onde o barco sera posicionado: ");
					inputTeclado = scanner.nextLine();
					if(inputTeclado.equals("Sair")) {
						turno.setJogoRodando(false);
					}
					else
					{
						System.out.println("Qual sera o sentido? ");
						inputSentido = scanner.nextLine();						
						if(!armamentosJogador1.posicionarArmamento(tabuleiroJogador1, inputTeclado, inputSentido))
							{
								System.out.println("Jogue Novamente! \n");
							}
						else
							{
								turno.setVezJogar("Jogador 2");
								tabuleiroJogador1.imprimeTabuleiro();
							}
						
					}
				}
				else { // Jogar
					System.out.println("Digite 'Sair' para parar de executar o programa.");
					System.out.printf("Digite onde deseja atacar %s, por exemplo 'A1'.\n", jogador2.getNome());
					System.out.printf("Tabuleiro de %s:\n", jogador2.getNome());
					tabuleiroJogador2.imprimeTabuleiro();
					
					inputTeclado = scanner.nextLine();
					
					// realizar a jogada do jogador 1
					
					if(inputTeclado.equals("Sair")) {
						turno.setJogoRodando(false);
					}
					
					turno.setVezJogar("Jogador 2");
				}
			}
			else if(turno.getVezJogar() == "Jogador 2") {
				if(!armamentosJogador2.isArmamentoPosicionado()) {
					// posicionar armamentos
					System.out.println("Selecione onde o barco sera posicionado: ");
					inputTeclado = scanner.nextLine();
					if(inputTeclado.equals("Sair")) {
						turno.setJogoRodando(false);
					}
					else
					{
						System.out.println("Qual sera o sentido? ");
						inputSentido = scanner.nextLine();						
						if(!armamentosJogador2.posicionarArmamento(tabuleiroJogador2, inputTeclado, inputSentido))
							{
								System.out.println("Jogue Novamente! \n");
							}
						else
							{
								turno.setVezJogar("Jogador 1");
								tabuleiroJogador2.imprimeTabuleiro();
							}
						
					}
				}
				else {
					System.out.println("Digite 'Sair' para parar de executar o programa.");
					System.out.printf("Digite onde deseja atacar %s, por exemplo 'A1'.\n", jogador1.getNome());
					System.out.printf("Tabuleiro de %s:\n", jogador1.getNome());
					tabuleiroJogador1.imprimeTabuleiro();
					
					inputTeclado = scanner.nextLine();
					
					// realizar a jogada do jogador 2
					
					if(inputTeclado.equals("Sair")) {
						turno.setJogoRodando(false);
					}
					
					turno.setVezJogar("Jogador 1");	
				}
			}
		}
		
		scanner.close();
	}
}
