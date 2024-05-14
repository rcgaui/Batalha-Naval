package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JogadorTest {
    private Jogador jogador;

    @Before
    public void setUp() {
    	jogador = new Jogador("Jogador Teste");
    }
    
    @Test
    public void testGetsJogador() {
        assertEquals("Jogador Teste", jogador.getNome());
        assertEquals(0, jogador.getQntSubmarinos());
        assertEquals(0, jogador.getQntCruzadores());
        assertEquals(0, jogador.getQntDestroyers());
        assertEquals(0, jogador.getQntHidroavioes());
        assertEquals(0, jogador.getQntCouracados());
    }
    
    @Test
    public void testAddSubmarino() {
        jogador.addSubmarino();
        assertEquals(1, jogador.getQntSubmarinos());
    }

    @Test
    public void testAddCruzador() {
        jogador.addCruzador();
        jogador.addCruzador();
        assertEquals(2, jogador.getQntCruzadores());
    }

    @Test
    public void testAddDestroyer() {
        jogador.addDestroyer();
        jogador.addDestroyer();
        jogador.addDestroyer();
        assertEquals(3, jogador.getQntDestroyers());
    }

    @Test
    public void testAddHidroaviao() {
        jogador.addHidroaviao();
        assertEquals(1, jogador.getQntHidroavioes());
    }

    @Test
    public void testAddCouracado() {
        jogador.addCouracado();
        assertEquals(1, jogador.getQntCouracados());
    }
    
}