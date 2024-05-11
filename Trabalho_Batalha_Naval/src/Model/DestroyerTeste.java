package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DestroyerTeste {

	@Test
	public void PosicionaDentroTabuleiro() {
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Destroyer destroyer = new Destroyer();
		assertTrue(destroyer.posicionarArmamento(tab, "A1", "Norte-Sul", jg)); // A1
		assertTrue(destroyer.posicionarArmamento(tab, "O15", "Sul-Norte", jg)); //O15
	}
	
	@Test
	public void PosicionaForaTabuleiro()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Destroyer destroyer = new Destroyer();
		assertFalse(destroyer.posicionarArmamento(tab, "Z0", "Norte-Sul", jg));
	}
	
	@Test
	public void SentidoCorreto()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Destroyer destroyer = new Destroyer();
		assertTrue(destroyer.posicionarArmamento(tab, "N1", "Norte-Sul", jg));
		assertTrue(destroyer.posicionarArmamento(tab, "O14", "Oeste-Leste", jg));
	}
	
	@Test
	public void SentidoIncorreto()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Destroyer destroyer = new Destroyer();
		assertFalse(destroyer.posicionarArmamento(tab, "A1", "Sul-Norte", jg));
		assertFalse(destroyer.posicionarArmamento(tab, "O15", "Oeste-Leste", jg));
	}
	
	@Test
	public void colisao()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Destroyer destroyer = new Destroyer();
		assertTrue(destroyer.posicionarArmamento(tab, "A1", "Norte-Sul", jg)); //Insere barco 1
		assertFalse(destroyer.posicionarArmamento(tab, "B2", "Leste-Oeste", jg)); // Nao insere barco 2
	}
}
