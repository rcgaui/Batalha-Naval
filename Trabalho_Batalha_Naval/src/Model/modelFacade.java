package Model;

import javax.swing.JFileChooser;

public class modelFacade {
	public void novaPartida(String jogador1, String jogador2)
	{
		//PReencher depois
	}
	
	public void converteCoordenada(float x, float y)
	{
		//Preencher Dps
	}
	
	public void VerificaPosicao(Tabuleiro tabuleiro, Armamentos barco, String sentido) //Adicionar coordenadas da tela como parâmetro
	{
		if(barco.verificarSentido(tabuleiro, sentido, 0, 0)) //Sendo true, desenha verde na tela
		{
			
		}
		else
		{
			//Desenha vermelho
		}
	}
	
	public void PosicionaEmbarcacao(Tabuleiro tabuleiro, Armamentos barco, String sentido, String casa) //Adicionar coordenadas da tela como parâmetro
	{
		if(barco.posicionarArmamento(tabuleiro, casa, sentido, null)) //Sendo true, insere no tabuleiro e confirma a pintura verde
		{
			
		}
		else
		{
			//Desenha vermelho
		}
	}
	
	public void atacar(Tabuleiro tabuleiro, int letra, int numero)
	{
		tabuleiro.realizarTiro(letra, numero);
	}
	
	
	public void carregaPartida(JFileChooser file)
	{
		
	}
	
	public void salvarPartida()
	{
		
	}

}
