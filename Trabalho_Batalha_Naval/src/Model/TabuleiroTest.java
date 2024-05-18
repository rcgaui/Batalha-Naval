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
    public void testImprimeTabuleiro() {
        try {
            tabuleiro.imprimeTabuleiro(true);
            tabuleiro.imprimeTabuleiro(false);
        } catch (Exception e) {
            fail("O método imprimeTabuleiro não deve lançar exceções");
        }
    }

    
    @Test
    public void testRealizarTiro() {
        String resultado = tabuleiro.realizarTiro(0,0);
        assertNotNull("O resultado do tiro não deve ser nulo", resultado);
        assertTrue("O resultado deve ser 'Embarcação atingida!', 'Água!', 'Já atingido!' ou 'Ataque inválido!'",
        		resultado.equals("Embarcação atingida!") || resultado.equals("Água!") || resultado.equals("Já atingido!") || resultado.equals("Ataque inválido!"));
    }
    
    @Test
    public void testRealizarTiroErrado() {
        String resultado = tabuleiro.realizarTiro(15,15);
        assertNotNull("O resultado do tiro não deve ser nulo", resultado);
        assertTrue("O resultado deve ser 'Coordenadas inválidas, tente novamente'", resultado.equals("Coordenadas inválidas, tente novamente"));
    }
}