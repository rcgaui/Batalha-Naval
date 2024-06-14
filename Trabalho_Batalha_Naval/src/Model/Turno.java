package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class Turno { 
	private boolean jogoRodando;
	private Jogador vezJogar;
	private Jogador jogador1;
	private Jogador jogador2;
	private int tentativas;
	
	public Turno(Jogador jogador1, Jogador jogador2) {
		jogoRodando = true;
		this.tentativas = 3;
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
	
	public void tentativaTiro()
	{
		if(tentativas>0)
			this.tentativas--;
	}
	
	public int getTentativas()
	{
		return this.tentativas;
	}
	
	public void setVezJogar(Jogador vezJogar) {
		this.vezJogar = vezJogar;
	}
	
	public void trocaTurno()
	{
		if(vezJogar == jogador1)
		{
			this.vezJogar = jogador2;
			this.tentativas = 3;
		}
		else
		{
			vezJogar = jogador1;
			this.tentativas = 3;
		}
	}
	
	public boolean salvaJogo(File file, Tabuleiro tabuleiroj1, Tabuleiro tabuleiroj2, ArrayList<Armamentos> armamentosJ1, ArrayList<Armamentos> armamentosJ12 )
	{
		PrintWriter writer = null;
		try
		{
			File arq = file;
			if(arq.createNewFile())
			{
				System.out.println("Arquivo Criado: " + arq.getName() + "\n");
			}
			else
			{
				System.out.println("Arquivo existente, sobreescrevendo: " + arq.getName() + "\n");
			}
			
			writer = new PrintWriter(new FileWriter(arq, false));
			
			for(int i = 0; i < 15; i++) //Escreve posicoes armamentos J1
			{
				String casa = armamentosJ1.get(i).getCasa();
				String sentido = armamentosJ1.get(i).getSentido(); 
				writer.write(casa);
				writer.flush();
				writer.write(" ");
				writer.flush();
				writer.write(sentido);
				writer.flush();
				writer.write("\n");
				writer.flush();
			}
			
			writer.write("\n");
			writer.flush();
			
			for(int i = 0; i < 15; i++)//Escreve posicoes armamentos J2
			{
				String casa = armamentosJ1.get(i).getCasa();
				String sentido = armamentosJ1.get(i).getSentido(); 
				writer.write(casa);
				writer.flush();
				writer.write(" ");
				writer.flush();
				writer.write(sentido);
				writer.flush();
				writer.write("\n");
				writer.flush();
			}
			
			writer.write("\n");
			writer.flush();
			
			for(int i = 0; i < 15; i++) //Escreve atacados tabuleiro 1
			{
				for(int j = 0; j < 15; j++)
				{
					if(tabuleiroj1.getCasas()[i][j].getEstadoCasa() == "*")
					{
						writer.write(tabuleiroj1.getCasas()[i][j].getPosicao());
						writer.flush();
						writer.write("\n");
						writer.flush();
					}
				}
			}
			
			writer.write("-"); //Identificador de mudanca de atacados do j1 pra j2
			writer.flush();
			writer.write("\n");
			writer.flush();
			
			for(int i = 0; i < 15; i++) //Escreve atacados tabuleiro 2
			{
				for(int j = 0; j < 15; j++)
				{
					if(tabuleiroj2.getCasas()[i][j].getEstadoCasa() == "*")
					{
						writer.write(tabuleiroj2.getCasas()[i][j].getPosicao());
						writer.flush();
						writer.write("\n");
						writer.flush();
					}
				}
			}
			writer.write("-"); //Identificador de mudanca de atacados do j2 para nomes
			writer.flush();
			writer.write("\n");
			writer.flush();

			writer.write(vezJogar.getNome());
			writer.flush();
			writer.write("\n");
			writer.flush();
			if(vezJogar == jogador1) writer.write(jogador2.getNome());
			else writer.write(jogador1.getNome());
			
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
	
	public boolean carregarJogo(String nome, Tabuleiro tabuleiroJogador1, Tabuleiro tabuleiroJogador2, ArrayList<Armamentos> armamentosj1, ArrayList<Armamentos> armamentosj2) {
		try {
			FileReader buffer= new FileReader(nome);
			
			BufferedReader buffered = new BufferedReader(buffer);
			String linha;
			String casa;
			String sentido;
			int letra;
			int numero;
			
			for(int i = 0; i < 15; i++) //insere as embarcacoes j1
			{
				linha = buffered.readLine();
				casa = linha.trim().split("\\s+")[0];
				sentido =  linha.trim().split("\\s+")[1];
				letra = casa.charAt(0) - 'A'; 
				numero = Integer.parseInt(casa.substring(1)) - 1;
				armamentosj1.get(i).inserirArmamento(tabuleiroJogador1, letra, numero, sentido);
			}
			buffered.readLine();
			for(int i = 0; i < 15; i++) //Insere as embarcacoes j2
			{
				linha = buffered.readLine();
				casa = linha.trim().split("\\s+")[0];
				sentido =  linha.trim().split("\\s+")[1];
				letra = casa.charAt(0) - 'A'; 
				numero = Integer.parseInt(casa.substring(1)) - 1;
				armamentosj2.get(i).inserirArmamento(tabuleiroJogador2, letra, numero, sentido);
			}
			buffered.readLine();
			
			linha = buffered.readLine(); 
			
			while(linha != "-")
			{
				letra = linha.charAt(0) - 'A';
				numero = Integer.parseInt(linha.substring(1)) - 1;
				tabuleiroJogador1.realizarTiro(letra, numero);
				linha = buffered.readLine();
			}
			
			linha = buffered.readLine(); 
			while(linha != "-")
			{
				letra = linha.charAt(0) - 'A';
				numero = Integer.parseInt(linha.substring(1)) - 1;
				tabuleiroJogador1.realizarTiro(letra, numero);
				linha = buffered.readLine();
			}

			this.jogador1.setNome(buffered.readLine());
			this.jogador2.setNome(buffered.readLine());
			
			
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
