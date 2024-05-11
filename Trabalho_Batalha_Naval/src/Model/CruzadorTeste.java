package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CruzadorTeste {

	@Test
	public void PosicionaDentroTabuleiro() {
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Cruzador cruzador = new Cruzador();
		assertTrue(cruzador.posicionarArmamento(tab, "A1", "Norte-Sul", jg)); // A1
		assertTrue(cruzador.posicionarArmamento(tab, "O15", "Sul-Norte", jg)); //O15
	}
	
	@Test
	public void PosicionaForaTabuleiro()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Cruzador cruzador = new Cruzador();
		assertFalse(cruzador.posicionarArmamento(tab, "Z0", "Norte-Sul", jg));
	}
	
	@Test
	public void SentidoCorreto()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Cruzador cruzador = new Cruzador();
		assertTrue(cruzador.posicionarArmamento(tab, "A1", "Norte-Sul", jg));
		assertTrue(cruzador.posicionarArmamento(tab, "O15", "Leste-Oeste", jg));
	}
	
	@Test
	public void SentidoIncorreto()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Cruzador cruzador = new Cruzador();
		assertFalse(cruzador.posicionarArmamento(tab, "A1", "Sul-Norte", jg));
		assertFalse(cruzador.posicionarArmamento(tab, "O15", "Oeste-Leste", jg));
	}
	
	@Test
	public void colisao()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Cruzador cruzador = new Cruzador();
		assertTrue(cruzador.posicionarArmamento(tab, "A1", "Norte-Sul", jg)); //Insere barco 1
		assertFalse(cruzador.posicionarArmamento(tab, "B1", "Norte-Sul", jg)); // Nao insere barco 2
	}
}
