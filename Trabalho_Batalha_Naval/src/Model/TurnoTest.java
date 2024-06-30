package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TurnoTest{
	private Turno turno;
	private Tabuleiro tabuleiroJogador1;
    private Tabuleiro tabuleiroJogador2;
	private Jogador jogador1;
	private Jogador jogador2;
	private ArrayList<Armamentos> armamentosJ1;
    private ArrayList<Armamentos> armamentosJ2;
    
    
	@Before
    public void setUp() {
        jogador1 = new Jogador("Jogador 1");
        jogador2 = new Jogador("Jogador 2");
		turno = new Turno(jogador1, jogador2);
        tabuleiroJogador1 = new Tabuleiro();
        tabuleiroJogador2 = new Tabuleiro();
        armamentosJ1 = new ArrayList<>();
        armamentosJ2 = new ArrayList<>();
    }
	
	@Test
	public void testIsJogoRodando() {
		assertTrue("Por padrão o jogo deve estar rodando ao iniciar um turno", turno.isJogoRodando());
	}
	
	
	@Test
	public void testSetJogoRodando() {
		turno.setJogoRodando(false);
        assertFalse("O jogo não deve estar rodando após setJogoRodando(false)", turno.isJogoRodando());
        
        turno.setJogoRodando(true);
        assertTrue("O jogo deve estar rodando após setJogoRodando(true)", turno.isJogoRodando());
	}
	
	@Test
	public void testSetVezJogar() {
		turno.setVezJogar(jogador2);
		assertEquals("Após setVeJogar('Jogador 2') a vez deve ser do 'Jogador 2", jogador2, turno.getVezJogar());
		
		turno.setVezJogar(jogador1);
		assertEquals("Após setVeJogar('Jogador 1') a vez deve ser do 'Jogador 1", jogador1, turno.getVezJogar());
	}
	
	@Test
    public void testTrocaTurno() {
        turno.trocaTurno();
        assertEquals("Deve ser a vez do Jogador 2 após a troca de turno", jogador2, turno.getVezJogar());
        turno.trocaTurno();
        assertEquals("Deve ser a vez do Jogador 1 após a segunda troca de turno", jogador1, turno.getVezJogar());
    }
	
	@Test
    public void testTentativaTiro() {
        turno.tentativaTiro();
        assertEquals("Deve haver 2 tentativas restantes após uma tentativa", 2, turno.getTentativas());
        turno.tentativaTiro();
        assertEquals("Deve haver 1 tentativa restante após duas tentativas", 1, turno.getTentativas());
        turno.tentativaTiro();
        assertEquals("Não deve haver tentativas restantes após três tentativas", 0, turno.getTentativas());
    }
}