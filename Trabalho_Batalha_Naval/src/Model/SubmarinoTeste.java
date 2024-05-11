package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubmarinoTeste {

	@Test
	public void PosicionaDentroTabuleiro() {
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Submarino submarino = new Submarino();
		assertTrue(submarino.posicionarArmamento(tab, "A1", "Norte-Sul", jg)); 
		assertTrue(submarino.posicionarArmamento(tab, "O15", "Sul-Norte", jg)); 
	}
	
	@Test
	public void PosicionaForaTabuleiro()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Submarino submarino = new Submarino();
		assertFalse(submarino.posicionarArmamento(tab, "Z0", "Norte-Sul", jg));
	}
	
	@Test
	public void colisao()
	{
		Tabuleiro tab = new Tabuleiro();
		Jogador jg = new Jogador("Jogador 1");
		Submarino submarino = new Submarino();
		assertTrue(submarino.posicionarArmamento(tab, "A1", "Norte-Sul", jg)); //Insere barco 1
		assertFalse(submarino.posicionarArmamento(tab, "A1", "Norte-Sul", jg)); // Nao insere barco 2
	}
}
