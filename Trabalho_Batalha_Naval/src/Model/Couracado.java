package Model;

public class Couracado extends Armamentos {
	public Couracado()
	{
		this.armamentoPosicionado = false;
		this.tamanho = 5;	
	}
	
	public static void addCouracado()
	{
		qntCouracados++;
	}
	
	public boolean posicionarArmamento(Tabuleiro tabuleiro, String casaTabuleiro, String sentido) {
		int letra = casaTabuleiro.charAt(0) - 'A'; // Pegar o primeiro caracter da string
		char letraCh = casaTabuleiro.charAt(0);
		int numero = Integer.parseInt(casaTabuleiro.substring(1)) - 1; // Pegar o segundo caracter da string
		
		if(casaTabuleiro.length() < 2 || casaTabuleiro.length() > 3 || !verificarSintaxe(letraCh, numero) || qntCouracados >= 1) {
			System.out.println("Erro ao posicionar\n");
			return false;
		}
		else {
			if(verificarSentido(tabuleiro, sentido, letra, numero))
			{
				System.out.println("Sentido Verificado\n");
				if(inserirArmamento(tabuleiro, letra, numero, sentido))
				{
					System.out.println("Couracado Inserido com Sucesso!\n");
					addCouracado();
					if(qntCouracados == 1) {
						this.armamentoPosicionado = true;
					}
					return true;
				}
				else return false;
			}
			else 
			{
				System.out.println("Couracado nao pode ser inserido nesse sentido\n");
				return false;
			}
		}
	}
}