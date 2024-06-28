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
			writer = new PrintWriter(new FileWriter(file, false));
			
			for(int i = 0; i < 15; i++) // Escreve posicoes armamentos J1
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
			
			for(int i = 0; i < 15; i++) // Escreve posicoes armamentos J2
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
			
			for(int i = 0; i < 15; i++) // Escreve tabuleiro 1
			{
				for(int j = 0; j < 15; j++)
				{
					if(tabuleiroj1.getCasas()[i][j].getEstadoCasa() == "*" || tabuleiroj1.getCasas()[i][j].getEstadoCasa() == "~")
					{
						writer.write(tabuleiroj1.getCasas()[i][j].getPosicao());
						writer.flush();
						writer.write("\n");
						writer.flush();
					}
				}
			}
			
			writer.write("-"); // Identificador de mudanca de atacados do j1 pra j2
			writer.flush();
			writer.write("\n");
			writer.flush();
			
			for(int i = 0; i < 15; i++) // Escreve tabuleiro 2
			{
				for(int j = 0; j < 15; j++)
				{
					if(tabuleiroj2.getCasas()[i][j].getEstadoCasa() == "*" || tabuleiroj2.getCasas()[i][j].getEstadoCasa() == "~")
					{
						writer.write(tabuleiroj2.getCasas()[i][j].getPosicao());
						writer.flush();
						writer.write("\n");
						writer.flush();
					}
				}
			}
			writer.write("-"); // Identificador de mudanca de atacados do j2 para nomes
			writer.flush();
			writer.write("\n");
			writer.flush();

			writer.write(vezJogar.getNome());
			writer.flush();
			writer.write("\n");
			writer.flush();
			
			if(vezJogar == jogador1) {
				writer.write("J1");
				writer.flush();
				writer.write("\n");
				writer.flush();
				writer.write(jogador2.getNome());
			}
			else {
				writer.write("J2");
				writer.flush();
				writer.write("\n");
				writer.flush();
				writer.write(jogador1.getNome());
			}
			
			writer.flush();
			writer.write("\n");
			writer.flush();
			
			return true;
			
		}
		catch(IOException e)
		{
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
			BufferedReader buffered = new BufferedReader(new FileReader(nome));
			String linha;
			String casa;
			String sentido;
			int letra;
			int numero;
			char letraLinha;
			String coordenada;
			
			for(int i = 0; i < 15; i++) // 15 embarcações J1
			{
				linha = buffered.readLine();
				casa = linha.trim().split("\\s+")[0];
				sentido =  linha.trim().split("\\s+")[1];
				letra = casa.charAt(0) - 'A'; 
				numero = Integer.parseInt(casa.substring(1)) - 1;
				letraLinha = (char) ('A' + letra);
		        coordenada =  "" + letraLinha + numero;
		        
		        if(i >= 4 && i <= 8) {
		        	armamentosj1.get(i).inserirArmamentoHidroAviao(tabuleiroJogador1, coordenada, letra, numero, sentido);	
		        }
		        else {
		        	armamentosj1.get(i).inserirArmamento(tabuleiroJogador1, coordenada, letra, numero, sentido);
		        }
			}
			
			buffered.readLine();
			
			for(int i = 0; i < 15; i++) // 15 embarcações J2
			{
				linha = buffered.readLine();
				casa = linha.trim().split("\\s+")[0];
				sentido =  linha.trim().split("\\s+")[1];
				letra = casa.charAt(0) - 'A'; 
				numero = Integer.parseInt(casa.substring(1)) - 1;
				letraLinha = (char) ('A' + letra);
		        coordenada =  "" + letraLinha + numero;
		        
		        if(i >= 4 && i <= 8) {
		        	armamentosj2.get(i).inserirArmamentoHidroAviao(tabuleiroJogador2, coordenada, letra, numero, sentido);	
		        }
		        else {
		        	armamentosj2.get(i).inserirArmamento(tabuleiroJogador2, coordenada, letra, numero, sentido);
		        }
			}
			
			buffered.readLine();
			
			while(true) // J1 atirando no J2
			{
				linha = buffered.readLine();
				if(linha.equals("-")) {
					break;
				}
				letra = linha.charAt(0) - 'A';
				numero = Integer.parseInt(linha.substring(1)) - 1;
				tabuleiroJogador2.realizarTiro(letra, numero);
			}
			
			while(true) // J2 atirando no J1
			{
				linha = buffered.readLine();
				if(linha.equals("-")) {
					break;
				}
				letra = linha.charAt(0) - 'A';
				numero = Integer.parseInt(linha.substring(1)) - 1;
				tabuleiroJogador1.realizarTiro(letra, numero);
			}
			
			String primeiroNome = buffered.readLine();
			String vezDoJogador = buffered.readLine();
			
			if(vezDoJogador.equals("J1")) {
				jogador1.setNome(primeiroNome);
				jogador2.setNome(buffered.readLine());
				vezJogar = jogador1;
			}
			else {
				jogador2.setNome(primeiroNome);
				jogador1.setNome(buffered.readLine());
				vezJogar = jogador2;
			}
			
			return true;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}