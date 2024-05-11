package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class HidroaviaoTeste {
	
	@Test
	public void PosicionaDentroTabuleiro() {
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Hidroaviao hidroaviao = new Hidroaviao();
		assertTrue(hidroaviao.posicionarArmamento(tab, "A2", "Norte-Sul", jg)); // A1
	}
	
	@Test
	public void PosicionaForaTabuleiro()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Hidroaviao hidroaviao = new Hidroaviao();
		assertFalse(hidroaviao.posicionarArmamento(tab, "Z0", "Norte-Sul", jg));
	}
	
	@Test
	public void SentidoCorreto()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Hidroaviao hidroaviao = new Hidroaviao();
		assertTrue(hidroaviao.posicionarArmamento(tab, "A2", "Norte-Sul", jg));
		assertTrue(hidroaviao.posicionarArmamento(tab, "F1", "Oeste-Leste", jg));
	}
	
	@Test
	public void SentidoIncorreto()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Hidroaviao hidroaviao = new Hidroaviao();
		assertFalse(hidroaviao.posicionarArmamento(tab, "A1", "Norte-Sul", jg));
	}
	
	@Test
	public void colisao()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Hidroaviao hidroaviao = new Hidroaviao();
		assertTrue(hidroaviao.posicionarArmamento(tab, "B1", "Oeste-Leste", jg)); //Insere barco 1
		assertFalse(hidroaviao.posicionarArmamento(tab, "B3", "Leste-Oeste", jg)); // Nao insere barco 2
	}
}
