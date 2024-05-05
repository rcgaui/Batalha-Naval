
// TEMPORARIAMENTE PARA TESTES
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

// TEMPORARIAMENTE PARA TESTES
import Model.Armamentos;
import Model.Couracado;
import Model.Cruzador;
import Model.Destroyer;
import Model.Hidroaviao;
import Model.Jogador;
import Model.Submarino;
import Model.Tabuleiro;
import Model.Turno;

public class main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// Jogador 1
		Jogador jogador1 = new Jogador("Joao");
		Tabuleiro tabuleiroJogador1 = new Tabuleiro();
		
		System.out.println("Tabuleiro do jogador 1 atualmente:\n\n");
		tabuleiroJogador1.imprimeTabuleiro(true);
		Submarino submarinoArmamentoJogador1 = new Submarino();
		Destroyer destroyerArmamentoJogador1 = new Destroyer();
		Hidroaviao hidroAviaoArmamentoJogador1 = new Hidroaviao();
		Cruzador cruzadoreArmamentoJogador1 = new Cruzador();
		Couracado couracadoArmamentoJogador1 = new Couracado();
		
		System.out.println("");
		
		// Jogador 2
		Jogador jogador2 = new Jogador("Maria");
		Tabuleiro tabuleiroJogador2 = new Tabuleiro();
		System.out.println("Tabuleiro do jogador 2 atualmente:\n\n");
		tabuleiroJogador2.imprimeTabuleiro(true);
		Submarino submarinoArmamentoJogador2 = new Submarino();
		Destroyer destroyerArmamentoJogador2 = new Destroyer();
		Hidroaviao hidroAviaoArmamentoJogador2 = new Hidroaviao();
		Cruzador cruzadoreArmamentoJogador2 = new Cruzador();
		Couracado couracadoArmamentoJogador2 = new Couracado();
		
		System.out.println("");
		
		Turno turno = new Turno();
		turno.setVezJogar(jogador1.getNome());	
		
		turno.carregarJogo("Save da Partida", tabuleiroJogador1, tabuleiroJogador2);
		tabuleiroJogador1.imprimeTabuleiro(true);
		tabuleiroJogador2.imprimeTabuleiro(true);
		
		// Isso provavelmente será passado para o pacote Controller em outra interação
		while(turno.isJogoRodando()) {
			if(turno.getVezJogar() == jogador1.getNome()) {
				
				turno.salvaJogo("Save da Partida", tabuleiroJogador1, tabuleiroJogador2);
				
				if(!submarinoArmamentoJogador1.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, submarinoArmamentoJogador1, "submarino", tabuleiroJogador1, scanner, jogador2.getNome(), jogador1);
                }
                /*else if(!destroyerArmamentoJogador1.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, destroyerArmamentoJogador1, "destroyer", tabuleiroJogador1, scanner, jogador2.getNome(), jogador1);
                }*/
                else if(!hidroAviaoArmamentoJogador1.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, hidroAviaoArmamentoJogador1, "hidro aviao", tabuleiroJogador1, scanner, jogador2.getNome(), jogador1);
                }
                /*else if(!cruzadoreArmamentoJogador1.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, cruzadoreArmamentoJogador1, "cruzador", tabuleiroJogador1, scanner, jogador2.getNome(), jogador1);
                }
                else if(!couracadoArmamentoJogador1.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, couracadoArmamentoJogador1, "couracado", tabuleiroJogador1, scanner, jogador2.getNome(), jogador1);
                }*/
                else { // Jogar
                	System.out.println("\n===============================================");
                	System.out.println("Digite 'Sair' para parar de executar o programa.");
					System.out.printf("Digite onde deseja atacar %s, por exemplo 'A1'.\n", jogador2.getNome());
					System.out.printf("Tabuleiro de %s:\n\n", jogador2.getNome());
					tabuleiroJogador2.imprimeTabuleiro(false);
					
					String inputTeclado = scanner.nextLine();
					
					// realizar a jogada do jogador 1
					
					if(inputTeclado.equals("Sair")) {
						turno.setJogoRodando(false);
					}
					
					else {
						int linha = inputTeclado.charAt(0) - 'A';
				        int coluna = Integer.parseInt(inputTeclado.substring(1)) - 1;

				        String resultado = tabuleiroJogador2.realizarTiro(linha, coluna);
				        System.out.println("Resultado do tiro: " + resultado);
				        
				        System.out.println("Tabuleiro do jogador adversário:\n");
				        
				        tabuleiroJogador2.imprimeTabuleiro(false);
					}
					
					turno.setVezJogar(jogador2.getNome());
                }
			}
			else if(turno.getVezJogar() == jogador2.getNome()) {

				if(!submarinoArmamentoJogador2.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, submarinoArmamentoJogador2, "submarino", tabuleiroJogador2, scanner, jogador1.getNome(), jogador2);
                }
                /*else if(!destroyerArmamentoJogador2.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, destroyerArmamentoJogador2, "destroyer", tabuleiroJogador2, scanner, jogador1.getNome(), jogador2);
                }*/
                else if(!hidroAviaoArmamentoJogador2.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, hidroAviaoArmamentoJogador2, "hidro aviao", tabuleiroJogador2, scanner, jogador1.getNome(), jogador2);
                }
                /*else if(!cruzadoreArmamentoJogador2.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, cruzadoreArmamentoJogador2, "cruzador", tabuleiroJogador2, scanner, jogador1.getNome(), jogador2);
                }
                else if(!couracadoArmamentoJogador2.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, couracadoArmamentoJogador2, "couracado", tabuleiroJogador2, scanner, jogador1.getNome(), jogador2);
                }*/
				else {
					System.out.println("Digite 'Sair' para parar de executar o programa.");
					System.out.printf("Digite onde deseja atacar %s, por exemplo 'A1'.\n", jogador1.getNome());
					System.out.printf("Tabuleiro de %s:\n", jogador1.getNome());
					tabuleiroJogador1.imprimeTabuleiro(false);
					
					String inputTeclado = scanner.nextLine();
					
					// realizar a jogada do jogador 2
					
					if(inputTeclado.equals("Sair")) {
						turno.setJogoRodando(false);
					}
					
					else {
						int linha = inputTeclado.charAt(0) - 'A';
				        int coluna = Integer.parseInt(inputTeclado.substring(1)) - 1;

				        String resultado = tabuleiroJogador1.realizarTiro(linha, coluna);
				        System.out.println("Resultado do tiro: " + resultado);
				        
				        System.out.println("Tabuleiro do jogador adversário:\n");
				        
				        tabuleiroJogador1.imprimeTabuleiro(false);
					}
					
					turno.setVezJogar(jogador1.getNome());
				}
			}
		}
			
	}
	
	private static void posicionarArmamento(Turno turno, Armamentos armamento, String nomeArmamento, Tabuleiro tabuleiro, Scanner scanner, String nomeProximoTurno, Jogador jogador) {
		System.out.println("===============================================\n");
		tabuleiro.imprimeTabuleiro(true);
		System.out.println("\nSelecione onde um " + nomeArmamento + " de " + turno.getVezJogar() + " sera posicionado: ");
        String inputTeclado = scanner.nextLine();
        if(inputTeclado.equals("Sair")) {
            turno.setJogoRodando(false);
        }
        else {
            System.out.println("Qual sera o sentido? ");
            String inputSentido = scanner.nextLine();
            if(!armamento.posicionarArmamento(tabuleiro, inputTeclado, inputSentido, jogador)) {
                System.out.println("Jogue Novamente! \n");
            }
            else {
                turno.setVezJogar(nomeProximoTurno);
                tabuleiro.imprimeTabuleiro(true);
            }
        }
    }
}
