package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TabuleiroTest {
    private Tabuleiro tabuleiro;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro();
    }

    @Test
    public void testTabuleiroInicializacao() {
        Casas[][] casas = tabuleiro.getCasas();
        assertNotNull("O array de casas não deve ser nulo", casas);
        assertEquals("Deve haver 15 linhas", 15, casas.length);
        assertEquals("Deve haver 15 colunas", 15, casas[0].length);
    }
    
    @Test
    public void testRealizarTiro() {
        String resultado = tabuleiro.realizarTiro(0,0);
        assertEquals("Água atingida!", resultado);
        resultado = tabuleiro.realizarTiro(0, 0);
        assertEquals("Já atingido! (Água)", resultado);
    }
    
    @Test
    public void testRealizarTiroErrado() {
        String resultado = tabuleiro.realizarTiro(15,15);
        assertTrue("O resultado deve ser 'Coordenadas inválidas, tente novamente'", resultado.equals("Coordenadas inválidas, tente novamente"));
    }
    
    @Test
    public void testRealizarTiroEmbarcacao() {
        tabuleiro.getCasas()[0][0].setEstadoCasa("!");
        String resultado = tabuleiro.realizarTiro(0, 0);
        assertEquals("Embarcação atingida!", resultado);
        resultado = tabuleiro.realizarTiro(0, 0);
        assertEquals("Já atingido! (Embarcação)", resultado);
    }
}