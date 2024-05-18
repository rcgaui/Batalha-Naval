package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


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
	public void testInserirArmamento() {
		assertTrue("O armamento deve ser inserido após inserirArmamento() com parâmetros corretos", cruzador.inserirArmamento(tabuleiro, 6, 8, "Norte-Sul"));
	}
	
	@Test
	public void testVerificarSintaxe() {
		 assertTrue("A sintaxe deve ser válida para 'A', 0", submarino.verificarSintaxe('A', 0));
	     assertFalse("A sintaxe deve ser inválida para 'P', 0", submarino.verificarSintaxe('P', 0));
	     assertFalse("A sintaxe deve ser inválida para 'A', 15", submarino.verificarSintaxe('A', 15));
	}
	
	@Test
	public void testVerificarSentido() {
		assertFalse("O sentido deve ser inválido para posicionar o hidroaviao dessa forma", hidroaviao.verificarSentido(tabuleiro, "Norte-Sul", 0, 0));
		assertTrue("O sentido deve ser válido para posicionar o hidroaviao dessa forma", hidroaviao.verificarSentido(tabuleiro, "Norte-Sul", 0, 1));
	}
	
	@Test
	public void testVerificarCasa() {
		tabuleiro.getCasas()[0][0].setEstadoCasa("?");
        tabuleiro.getCasas()[0][1].setEstadoCasa("?");
        assertTrue("A casa deve estar válida para inserção do destroyer", destroyer.verificarCasas(tabuleiro, 0, 0));
        tabuleiro.getCasas()[0][1].setEstadoCasa("!");
        assertFalse("A casa deve estar inválida para inserção do destroyer", destroyer.verificarCasas(tabuleiro, 0, 0));
        
	}
	
	
}

