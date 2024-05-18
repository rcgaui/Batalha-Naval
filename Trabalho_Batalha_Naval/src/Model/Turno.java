package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Turno { // PUBLIC TEMPORARIAMENTE PARA TESTES NA MAIN
	private boolean jogoRodando;
	private String vezJogar;

	public Turno() {
		jogoRodando = true;
		vezJogar = "Jogador 1"; // Por padrão é "Jogador 1" pois o salvamento do jogo sempre acontecerá quando for a vez do jogador 1
	}
	
	public boolean isJogoRodando() {
		return jogoRodando;
	}

	public void setJogoRodando(boolean jogoRodando) {
		this.jogoRodando = jogoRodando;
	}
	
	public String getVezJogar() {
		return vezJogar;
	}

	public void setVezJogar(String vezJogar) {
		this.vezJogar = vezJogar;
	}
	
	public boolean salvaJogo(String nome, Tabuleiro tabuleiroJogador1, Tabuleiro tabuleiroJogador2)
	{
		PrintWriter writer = null;
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
			
			writer = new PrintWriter(new FileWriter(arq, false));
			
			for(int i = 0; i < 15; i++)
			{
				for(int j = 0; j < 15; j++)
				{
					writer.write( tabuleiroJogador1.getCasas()[i][j].getEstadoCasa());
					writer.flush();
				}
			
				writer.flush();
			}
			
			writer.write("\n");
			writer.flush();
			
			for(int i = 0; i < 15; i++)
			{
				for(int j = 0; j < 15; j++)
				{
					writer.write(tabuleiroJogador2.getCasas()[i][j].getEstadoCasa());
					writer.flush();
				}
				
				writer.flush();
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
		finally {
            if (writer != null) {
                writer.close();
            }
        }
	}
	
	public boolean carregarJogo(String nome, Tabuleiro tabuleiroJogador1, Tabuleiro tabuleiroJogador2) {
		try {
			FileReader buffer= new FileReader(nome + ".txt");
			int c = 0;
			
			for(int i = 0; i < 15; i++)
			{
				for(int j = 0; j < 15; j++)
				{
					c = buffer.read();
					char character = (char) c;
					tabuleiroJogador1.getCasas()[i][j].setEstadoCasa(Character.toString(character));
					
				}
			}
			
			c = buffer.read();
			
			for(int i = 0; i < 15; i++)
			{
				for(int j = 0; j < 15; j++)
				{
					c = buffer.read();
					char character = (char) c;
					tabuleiroJogador2.getCasas()[i][j].setEstadoCasa(Character.toString(character));
				}
				
			}
			
			System.out.println("Partida Carregada!\n");
			return true;
		}
		catch(IOException e)
		{
			System.out.println("Erro ao Carregar arquivo \n");
			e.printStackTrace();
			return false;
		}
	}
}
