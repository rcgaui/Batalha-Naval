package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tabuleiro { // PUBLIC TEMPORARIAMENTE PARA TESTES NA MAIN
	private Casas casas[][];
	private int qntNumeros;
	private int qntLetras;
	
	public Tabuleiro() {
		char casa;
		qntNumeros = 15; // Por padrão é 15
		qntLetras = 15; // Por padrão é 15
		casas = new Casas[qntLetras][qntNumeros]; // Letras referem-se à linhas e números à colunas
		
		for (int i = 0; i < qntLetras; i++) {
            for (int j = 0; j < qntNumeros; j++) {
                casa = (char)('A' + i);
                casas[i][j] = new Casas(i + 1, casa);
            }
        }
	}
	
	public Casas[][] getCasas() {
		return casas;
	}

	public void imprimeTabuleiro(boolean mostrarDetalhes) {
	    System.out.print("   ");
	    
	    for (int i = 1; i <= qntLetras; i++) {
	    	System.out.print(String.format("%02d", i) + " ");
	    }
	    
	    System.out.println();
	    
	    for (int i = 1; i <= qntNumeros; i++) {
	    	System.out.print((char)('A' + i - 1) + " ");
	    	
	    	if (mostrarDetalhes) {
		        for (int j = 1; j <= qntLetras; j++) {
		        	
		            if (casas[i - 1][j - 1].getEstadoCasa() == "?") {
		                System.out.print(" . ");
		            } else if(casas[i - 1][j - 1].getEstadoCasa() == "!") {
		                System.out.print(" ! ");
		            }
		            else if(casas[i - 1][j - 1].getEstadoCasa() == "*") {
		                System.out.print(" X ");
		            }
		            else if(casas[i - 1][j - 1].getEstadoCasa() == "~") {
		                System.out.print(" ~ ");
		            }
		            else { // Água
		            	System.out.print(" # ");
		            }
		        }
	    	}
	    	else {
	    		for (int j = 1; j <= qntLetras; j++) {

		            if(casas[i - 1][j - 1].getEstadoCasa() == "*") {
		                System.out.print(" X ");
		            }
		            else if(casas[i - 1][j - 1].getEstadoCasa() == "~") {
		                System.out.print(" ~ ");
		            }
		            else { // Água
		            	System.out.print(" . ");
		            }
		        }
	    	}
	        
	        System.out.println();
	    }
	}
	
	
	public boolean salvaTabuleiro(String nome)
	{
		try
		{
			File arq = new File(nome + ".txt");
			if(arq.createNewFile())
			{
				System.out.println("Arquivo Criado: " + arq.getName() + "\n");
			}
			else
			{
				System.out.println("Arquivo existente, sobreescrevendo: " + arq.getName() + "\n");
			}
			
			FileWriter fw = new FileWriter(arq, false);
			
			for(int i = 0; i < qntNumeros; i++)
			{
				for(int j = 0; j < qntLetras; j++)
				{
					fw.write(casas[i][j].getEstadoCasa());
				}
			}
			
			System.out.println("Partida Salva!\n");
			return true;
			
		}
		catch(IOException e)
		{
			System.out.println("Erro ao abrir arquivo \n");
			e.printStackTrace();
			return false;
		}
	}
	
	public String realizarTiro(int letra, int numero) {
        Casas casaAlvo = casas[letra][numero];
        return casaAlvo.atacarCasa();
    }
}
