package Controller;

import View.*;
import Model.ModelFacade;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Control {
	//Singleton
    private static Control controller = null;
    public static Control getController()
    {
    	if(controller == null) {
    		controller = new Control();
    	}
    	return controller;
    }
    
    //Parte Grafica
    private JFrame criaPartida;
    private JFrame janelaInicial;
    private JFrame telaAtaque;
    private JFrame posicionarArmamento;
    private ModelFacade facade;
	
    Control()
    {
    	janelaInicial = JanelaInicial.getInstance();
    	criaPartida = janelaInicial = posicionarArmamento = null;    	
    	facade =  new ModelFacade();
    }
    
    public void comecarAtaque()
    {
    	telaAtaque = new FrameAtaque();
    	telaAtaque.setVisible(true);
    }
    
	public void irParaCriarPartida(JFrame frame)
	{
		frame.setVisible(false);
		criaPartida = CriaPartida.getInstance();
		criaPartida.setVisible(true);
	}
	
	public String getJogadorName(int i)
	{
		return facade.getName(i);
	}
	
	public boolean isJ1() //Verifica se está na vez do jogador 1
	{
		return facade.isJ1();
	}
	
	public ArrayList<String> getDestroyed()
	{
		return facade.getDestroyed();
	}
	
	public boolean VerificaPosicao(String nomeBarco, int numeroBarco, String sentido, String casa) {
		return facade.VerificaPosicao(nomeBarco, numeroBarco, sentido, casa);
	}
	
	public void PosicionaEmbarcacao(String nomeBarco, int numeroBarco, String sentido, String casa) {
		facade.PosicionaEmbarcacao(nomeBarco, numeroBarco, sentido, casa);
	}
	
	public void irParaTelaInicial(JFrame frame)
	{
		frame.setVisible(false);
		janelaInicial = JanelaInicial.getInstance();
		janelaInicial.setVisible(true);
	}
	
	public void irParaPosicionarArmamento(JFrame frame, String jogador1, String jogador2)
	{
		frame.setVisible(false);
		facade.novaPartida(jogador1, jogador2);
		posicionarArmamento = new PainelPosicionarArmamento(jogador1, jogador2);
	}
	
	public void registra(ObservadorAtaqueIF observador)
	{
		facade.registra(observador);
	}
	
	public void carregarPartida(JFrame frame, JFileChooser file)
	{
		frame.dispose();
		facade.carregaPartida(file);
	}
	
	public boolean salvarPartida(File file)
	{
		return facade.salvarPartida(file);
	}
	
	public static void main(String[] args) {
		Control controllerControl = Control.getController();
	}
}