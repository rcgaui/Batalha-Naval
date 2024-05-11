package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CouracadoTeste {

	@Test
	public void PosicionaDentroTabuleiro() {
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Couracado couracado = new Couracado();
		assertTrue(couracado.posicionarArmamento(tab, "A1", "Norte-Sul", jg)); // A1
	}
	
	@Test
	public void PosicionaForaTabuleiro()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Couracado couracado = new Couracado();
		assertFalse(couracado.posicionarArmamento(tab, "Z0", "Norte-Sul", jg));
	}
	
	@Test
	public void SentidoCorreto()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Couracado couracado = new Couracado();
		assertTrue(couracado.posicionarArmamento(tab, "E1", "Sul-Norte", jg));
	}
	
	@Test
	public void SentidoIncorreto()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Couracado couracado = new Couracado();
		assertFalse(couracado.posicionarArmamento(tab, "A1", "Sul-Norte", jg));
	}
	
	@Test
	public void colisao()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Couracado couracado = new Couracado();
		Submarino submarino = new Submarino();
		assertTrue(submarino.posicionarArmamento(tab, "A1", "Norte-Sul", jg)); //Insere barco 1
		assertFalse(couracado.posicionarArmamento(tab, "A5", "Leste-Oeste", jg)); // Nao insere barco 2
	}
}
