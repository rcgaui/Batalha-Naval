package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TurnoTest{
	private Turno turno;
	private Tabuleiro tabuleiroJogador1;
    private Tabuleiro tabuleiroJogador2;
	
	@Before
    public void setUp() {
        turno = new Turno();
        tabuleiroJogador1 = new Tabuleiro();
        tabuleiroJogador2 = new Tabuleiro();
    }
	
	@After
	public void tearDown() throws IOException {
        File arquivo = new File("testSave.txt");
        if (arquivo.exists()) {
            arquivo.delete();
        }
    }
	
	@Test
	public void testIsJogoRodando() {
		assertTrue("Por padrão o jogo deve estar rodando ao iniciar um turno", turno.isJogoRodando());
	}
	
	@Test
	public void testGetVezJogar() {
		assertEquals("Por padrão a vez deve ser do 'Jogador 1", "Jogador 1", turno.getVezJogar());
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
		turno.setVezJogar("Jogador 2");
		assertEquals("Após setVeJogar('Jogador 2') a vez deve ser do 'Jogador 2", "Jogador 2", turno.getVezJogar());
		
		turno.setVezJogar("Jogador 1");
		assertEquals("Após setVeJogar('Jogador 1') a vez deve ser do 'Jogador 1", "Jogador 1", turno.getVezJogar());
	}
	
	@Test
	public void testSalvaJogo() {
		tabuleiroJogador1 = new Tabuleiro();
        tabuleiroJogador2 = new Tabuleiro();
        
        boolean resultado = turno.salvaJogo("testSave", tabuleiroJogador1, tabuleiroJogador2);

        assertTrue("O jogo deve ser salvo corretamente", resultado);

        File arquivo = new File("testSave.txt");
        assertTrue("O arquivo de salvamento deve existir", arquivo.exists());

        try {
            String conteudo = new String(Files.readAllBytes(arquivo.toPath()));
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    assertTrue("O arquivo deve conter o estado das casas do jogador 1", conteudo.contains(tabuleiroJogador1.getCasas()[i][j].getEstadoCasa()));
                    assertTrue("O arquivo deve conter o estado das casas do jogador 2", conteudo.contains(tabuleiroJogador2.getCasas()[i][j].getEstadoCasa()));
                }
            }
        } catch (IOException e) {
            fail("Erro ao ler o arquivo de salvamento");
        }
	}
}