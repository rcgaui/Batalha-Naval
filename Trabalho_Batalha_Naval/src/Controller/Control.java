package Controller;

import View.*;
import Model.ModelFacade;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Control {
	//Singleton
    private static Control controller = null;
    public static Control getController()
    {
    	if(controller == null)
    		controller = new Control();
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
		criaPartida = CriaPartida.singleCriaPartida();
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
	
	public void irParaTelaInicial(JFrame frame)
	{
		frame.setVisible(false);
		janelaInicial = JanelaInicial.getInstance();
		janelaInicial.setVisible(true);
	}
	
	public void irParaPosicionarArmamento(JFrame frame, String jogador1, String jogador2)
	{
		facade.novaPartida(jogador1, jogador2);
		frame.setVisible(false);
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
	
	public static void main(String[] args) {
		Control controllerControl = Control.getController();

	}
}
