package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Turno { 
	private boolean jogoRodando;
	private Jogador vezJogar;
	private Jogador jogador1;
	private Jogador jogador2;

	public Turno(Jogador jogador1, Jogador jogador2) {
		jogoRodando = true;
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.vezJogar = jogador1;
	}
	
	public boolean isJogoRodando() {
		return jogoRodando;
	}

	public void setJogoRodando(boolean jogoRodando) {
		this.jogoRodando = jogoRodando;
	}
	
	public Jogador getVezJogar() {
		return vezJogar;
	}

	public void setVezJogar(Jogador vezJogar) {
		this.vezJogar = vezJogar;
	}
	
	public void trocaTurno()
	{
		if(vezJogar == jogador1)
		{
			vezJogar = jogador2;
		}
		else
		{
			vezJogar = jogador1;
		}
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
