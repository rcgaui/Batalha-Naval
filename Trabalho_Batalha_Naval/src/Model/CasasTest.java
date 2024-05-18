package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CasasTest {
    private Casas casa;
    
    @Before
    public void setUp() {
    	casa = new Casas(1,'A');
    }
    
    
    
    @Test
    public void testGetEstadoCasa() {
    	assertEquals("O estado inicial da casa deve ser '?'", "?", casa.getEstadoCasa());
    }
    
    @Test
    public void testSetEstadoCasa() {
    	casa.setEstadoCasa("!");
    	assertEquals("O estado da casa deve ser '!'", "!", casa.getEstadoCasa());
    }
    
    @Test
    public void testAtacarCasaArmada() {
        casa.setEstadoCasa("!");
        assertEquals("Deve-se retornar 'Embarcação atingida!', quando tem um armamento na casa atacada", "Embarcação atingida!", casa.atacarCasa());
        assertEquals("O estado da casa deve ser '*' após uma casa com armamento ser atingida", "*", casa.getEstadoCasa());
    }
    
    @Test
    public void testAtacarCasaAgua() {
        casa.setEstadoCasa("?");
        assertEquals("Deve-se retornar 'Água!', quando não tem nenhum armamento na casa atacada", "Água!", casa.atacarCasa());
        assertEquals("O estado da casa deve ser '~' após uma casa sem armamento ser atingida", "~", casa.getEstadoCasa());
    }
    
    public void testAtacarCasaAguaJaAtingida() {
    	casa.setEstadoCasa("~");
    	assertEquals("Deve-se retornar 'Água!', quando não tem nenhum armamento na casa atacada", "Água!", casa.atacarCasa());
    }
    
    @Test
    public void testAtacarCasaArmadaJaAtingida() {
    	casa.setEstadoCasa("*");
    	assertEquals("Deve-se retornar 'Já atingido!', quando a casa já tiver sido atacada anteriormente e conter um armamento", "Já atingido!", casa.atacarCasa());
    }
    
    @Test
    public void testAtacarCasaInvalida() {
        casa.setEstadoCasa("X");
        assertEquals("Deve retornar 'Ataque inválido!' quando o estado da casa é inválido", "Ataque inválido!", casa.atacarCasa());
    }
 

}