package Controller;

import View.*;
import Model.modelFacade;
import java.awt.*;

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
    private JFrame posicionarArmamento;
    private modelFacade facade;
	
    Control()
    {
    	janelaInicial = JanelaInicial.getInstance();
    	criaPartida = janelaInicial = posicionarArmamento = null;
    }
	
	public void irParaCriarPartida(JFrame frame)
	{
		frame.setVisible(false);
		criaPartida = CriaPartida.singleCriaPartida();
		criaPartida.setVisible(true);
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
		posicionarArmamento = new PainelPosicionarArmamento(jogador1, jogador2);
	}
	
	public void registra(ObservadorAtaqueIF observador)
	{
		facade.registra(observador);
	}
	
	public void carregarPartida(JFrame frame, JFileChooser file)
	{
		
	}
	
	public static void main(String[] args) {
		Control controllerControl = Control.getController();
	}
}
