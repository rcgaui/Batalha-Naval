package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;


public class ArmamentosTest{
	private Couracado couracado;
	private Submarino submarino;
	private Cruzador cruzador;
	private Hidroaviao hidroaviao;
	private Destroyer destroyer;
	private Tabuleiro tabuleiro;
	
	@Before
	public void setUp() {
		couracado = new Couracado();
		submarino = new Submarino();
		cruzador = new Cruzador();
		hidroaviao = new Hidroaviao();
		destroyer= new Destroyer();
		tabuleiro= new Tabuleiro();
	}
	
	@Test
	public void testIsArmamentoPosicionado() {
		assertFalse("Por padrão, o armamento não deve estar posicionado", couracado.isArmamentoPosicionado());
	}
	
	@Test
	public void testSetArmamentoPosicionado() {
		submarino.setArmamentoPosicionado(true);
		assertTrue("O armamento deve estar posicionado após setArmamentoPosicionado(true)", submarino.isArmamentoPosicionado());
	}
	
	@Test
    public void testGetIfDestroyed() {
        assertFalse("Por padrão, o armamento não deve estar destruído", couracado.getIfDestroyed());
    }
	
	@Test
    public void testGetCasa() {
        couracado.casaTabuleiro = "A1";
        assertEquals("A1", couracado.getCasa());
    }
	
	@Test
    public void testGetSentido() {
        couracado.sentido = "Norte-Sul";
        assertEquals("Norte-Sul", couracado.getSentido());
    }
	
	@Test
    public void testIsDestroyed() {
        couracado.posicoes.add("A1");
        couracado.posicoes.add("A2");
        couracado.posicoes.add("A3");
        couracado.posicoes.add("A4");
        tabuleiro.getCasas()[0][0].setEstadoCasa("*");
        tabuleiro.getCasas()[0][1].setEstadoCasa("*");
        tabuleiro.getCasas()[0][2].setEstadoCasa("*");
        tabuleiro.getCasas()[0][3].setEstadoCasa("*");
        couracado.tamanho = 4;
        ArrayList<String> destroyedPositions = couracado.isDestroyed(tabuleiro);
        assertNotNull("O armamento deve estar destruído", destroyedPositions);
        assertEquals(4, destroyedPositions.size());
    }

	@Test
	public void testVerificarSintaxe() {
		 assertTrue("A sintaxe deve ser válida para 'A', 0", submarino.verificarSintaxe('A', 0));
	     assertFalse("A sintaxe deve ser inválida para 'P', 0", submarino.verificarSintaxe('P', 0));
	     assertFalse("A sintaxe deve ser inválida para 'A', 15", submarino.verificarSintaxe('A', 15));
	}
	
	@Test
	public void testVerificarSentido() {
	    assertEquals("Deve retornar 2 para índice fora da matriz", 2, cruzador.verificarSentido(tabuleiro, "Norte-Sul", -1, -1));
	    assertEquals("Deve retornar 0 para uma posição válida", 0, cruzador.verificarSentido(tabuleiro, "Norte-Sul", 0, 1));
	    tabuleiro.getCasas()[1][1].setEstadoCasa("!");
	    assertEquals("Deve retornar 1 para um conflito de embarcações", 1, cruzador.verificarSentido(tabuleiro, "Norte-Sul", 0, 1));
	    tabuleiro.getCasas()[1][1].setEstadoCasa("?");
	}
	
	@Test
	public void testVerificarSentidoHidroAviao() {
	    assertEquals("Deve retornar 2 para índice fora da matriz", 2, hidroaviao.verificarSentidoHidroAviao(tabuleiro, "Oeste-Leste", -1, -1));
	    assertEquals("Deve retornar 2 para posição inválida no tabuleiro", 2, hidroaviao.verificarSentidoHidroAviao(tabuleiro, "Oeste-Leste", 14, 14)); 
	    assertEquals("Deve retornar 0 para uma posição válida", 0, hidroaviao.verificarSentidoHidroAviao(tabuleiro, "Oeste-Leste", 1, 1));
	    tabuleiro.getCasas()[0][0].setEstadoCasa("!");
	    assertEquals("Deve retornar 1 para um conflito de embarcações", 1, hidroaviao.verificarSentidoHidroAviao(tabuleiro, "Oeste-Leste", 1, 1));
	    tabuleiro.getCasas()[0][0].setEstadoCasa("?");
	}

	@Test
	public void testVerificarCasa() {
	    tabuleiro.getCasas()[0][0].setEstadoCasa("?");
	    tabuleiro.getCasas()[0][1].setEstadoCasa("?");
	    assertEquals("Deve retornar 0 para uma casa válida para inserção do destroyer", 0, destroyer.verificarCasas(tabuleiro, 0, 0));
	    tabuleiro.getCasas()[0][1].setEstadoCasa("!");
	    assertEquals("Deve retornar 1 para conflito de embarcações", 1, destroyer.verificarCasas(tabuleiro, 0, 0));
	    assertEquals("Deve retornar 2 para índice fora da matriz", 2, destroyer.verificarCasas(tabuleiro, 15, 15));
	}
	
	@Test
    public void testInserirArmamento() {
        assertTrue("O armamento deve ser inserido após inserirArmamento() com parâmetros corretos", cruzador.inserirArmamento(tabuleiro, "G8", 6, 8, "Norte-Sul"));
        assertEquals("!", tabuleiro.getCasas()[6][8].getEstadoCasa());
    }
	
	@Test
    public void testInserirArmamentoHidroAviao() {
        assertTrue("O armamento deve ser inserido após inserirArmamentoHidroAviao() com parâmetros corretos", hidroaviao.inserirArmamentoHidroAviao(tabuleiro, "G8", 6, 8, "Oeste-Leste"));
        assertEquals("!", tabuleiro.getCasas()[6][8].getEstadoCasa());
        assertEquals("!", tabuleiro.getCasas()[7][9].getEstadoCasa());
        assertEquals("!", tabuleiro.getCasas()[5][9].getEstadoCasa());
    }
	
}