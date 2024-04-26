package Model;

public class Submarino extends Armamentos {
	public Submarino()
	{
		this.armamentoPosicionado = false;
		this.tamanho = 1;	
	}
	
	public static void addSubmarino()
	{
		qntSubmarinos++;
	}
	
	public boolean posicionarArmamento(Tabuleiro tabuleiro, String casaTabuleiro, String sentido) {
		int letra = casaTabuleiro.charAt(0) - 'A'; // Pegar o primeiro caracter da string
		char letraCh = casaTabuleiro.charAt(0);
		int numero = Integer.parseInt(casaTabuleiro.substring(1)) - 1; // Pegar o segundo caracter da string
		
		if(casaTabuleiro.length() < 2 || casaTabuleiro.length() > 3 || !verificarSintaxe(letraCh, numero) || qntSubmarinos >= 4) {
			System.out.println("Erro ao posicionar\n");
			return false;
		}
		else {
			if(verificarCasas(tabuleiro, letra, numero))
			{
				System.out.println("Sentido Verificado\n");
				if(inserirArmamento(tabuleiro, letra, numero, sentido))
					{
						System.out.println("Submarino Inserido com Sucesso!\n");
						addSubmarino();
						return true;
					}
				else return false;
			}
			else 
			{
				System.out.println("Submarino nao pode ser inserido nesse sentido\n");
				return false;
			}
		}
	}
}
