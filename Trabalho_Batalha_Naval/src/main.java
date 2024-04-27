
// TEMPORARIAMENTE PARA TESTES
import java.util.Scanner;

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
		// Jogador 1
		Jogador jogador1 = new Jogador("Joao");
		Tabuleiro tabuleiroJogador1 = new Tabuleiro();
		System.out.println("Tabuleiro do jogador 1 atualmente:");
		tabuleiroJogador1.imprimeTabuleiro();
		Submarino submarinoArmamentoJogador1 = new Submarino();
		Destroyer destroyerArmamentoJogador1 = new Destroyer();
		Hidroaviao hidroAviaoArmamentoJogador1 = new Hidroaviao();
		Cruzador cruzadoreArmamentoJogador1 = new Cruzador();
		Couracado couracadoArmamentoJogador1 = new Couracado();
		
		System.out.println("");
		
		// Jogador 2
		Jogador jogador2 = new Jogador("Maria");
		Tabuleiro tabuleiroJogador2 = new Tabuleiro();
		System.out.println("Tabuleiro do jogador 2 atualmente:");
		tabuleiroJogador2.imprimeTabuleiro();
		Submarino submarinoArmamentoJogador2 = new Submarino();
		Destroyer destroyerArmamentoJogador2 = new Destroyer();
		Hidroaviao hidroAviaoArmamentoJogador2 = new Hidroaviao();
		Cruzador cruzadoreArmamentoJogador2 = new Cruzador();
		Couracado couracadoArmamentoJogador2 = new Couracado();
		
		System.out.println("");
		
		Turno turno = new Turno();
		turno.setVezJogar(jogador1.getNome());
		
		Scanner scanner = new Scanner(System.in);
		String inputTeclado;
		String inputSentido;
		
		// Isso provavelmente será passado para o pacote Controller em outra interação
		while(turno.isJogoRodando()) {
			if(turno.getVezJogar() == jogador1.getNome()) {
				if(!submarinoArmamentoJogador1.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, submarinoArmamentoJogador1, "submarino", tabuleiroJogador1, scanner, jogador2.getNome());
                }
                else if(!destroyerArmamentoJogador1.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, destroyerArmamentoJogador1, "destroyer", tabuleiroJogador1, scanner, jogador2.getNome());
                }
                else if(!hidroAviaoArmamentoJogador1.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, hidroAviaoArmamentoJogador1, "hidro aviao", tabuleiroJogador1, scanner, jogador2.getNome());
                }
                else if(!cruzadoreArmamentoJogador1.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, cruzadoreArmamentoJogador1, "cruzador", tabuleiroJogador1, scanner, jogador2.getNome());
                }
                else if(!couracadoArmamentoJogador1.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, couracadoArmamentoJogador1, "couracado", tabuleiroJogador1, scanner, jogador2.getNome());
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
					
					turno.setVezJogar(jogador2.getNome());
                }
			}
			else if(turno.getVezJogar() == jogador2.getNome()) {
				if(!submarinoArmamentoJogador2.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, submarinoArmamentoJogador2, "submarino", tabuleiroJogador2, scanner, jogador1.getNome());
                }
                else if(!destroyerArmamentoJogador2.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, destroyerArmamentoJogador2, "destroyer", tabuleiroJogador2, scanner, jogador1.getNome());
                }
                else if(!hidroAviaoArmamentoJogador2.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, hidroAviaoArmamentoJogador2, "hidro aviao", tabuleiroJogador2, scanner, jogador1.getNome());
                }
                else if(!cruzadoreArmamentoJogador2.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, cruzadoreArmamentoJogador2, "cruzador", tabuleiroJogador2, scanner, jogador1.getNome());
                }
                else if(!couracadoArmamentoJogador2.isArmamentoPosicionado()) {
                    posicionarArmamento(turno, couracadoArmamentoJogador2, "couracado", tabuleiroJogador2, scanner, jogador1.getNome());
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
					
					turno.setVezJogar(jogador1.getNome());
				}
			}
		}
		
		scanner.close();
	}
	
	private static void posicionarArmamento(Turno turno, Armamentos armamento, String nomeArmamento, Tabuleiro tabuleiro, Scanner scanner, String nomeProximoTurno) {
		System.out.println("===============================================");
		tabuleiro.imprimeTabuleiro();
		System.out.println("Selecione onde um " + nomeArmamento + " de " + turno.getVezJogar() + " sera posicionado: ");
        String inputTeclado = scanner.nextLine();
        if(inputTeclado.equals("Sair")) {
            turno.setJogoRodando(false);
        }
        else {
            System.out.println("Qual sera o sentido? ");
            String inputSentido = scanner.nextLine();                        
            if(!armamento.posicionarArmamento(tabuleiro, inputTeclado, inputSentido)) {
                System.out.println("Jogue Novamente! \n");
            }
            else {
                turno.setVezJogar(nomeProximoTurno);
                tabuleiro.imprimeTabuleiro();
            }
        }
    }
}
