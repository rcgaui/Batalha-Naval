package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Couracado;
import Model.Cruzador;
import Model.Destroyer;
import Model.Hidroaviao;
import Model.Submarino;
import Model.Tabuleiro;

public class PainelPosicionarArmamento extends JFrame {
	private final int LARGURA_PADRAO = 1000;
	private final int ALTURA_PADRAO = 600;
	
	JPanel painel = new JPanel();
	
	Tabuleiro tabuleiroJogador1 = new Tabuleiro();
	Submarino submarinoArmamentoJogador1 = new Submarino();
	Destroyer destroyerArmamentoJogador1 = new Destroyer();
	Hidroaviao hidroAviaoArmamentoJogador1 = new Hidroaviao();
	Cruzador cruzadoreArmamentoJogador1 = new Cruzador();
	Couracado couracadoArmamentoJogador1 = new Couracado();
	
	Tabuleiro tabuleiroJogador2 = new Tabuleiro();
	Submarino submarinoArmamentoJogador2 = new Submarino();
	Destroyer destroyerArmamentoJogador2 = new Destroyer();
	Hidroaviao hidroAviaoArmamentoJogador2 = new Hidroaviao();
	Cruzador cruzadoreArmamentoJogador2 = new Cruzador();
	Couracado couracadoArmamentoJogador2 = new Couracado();
	
	public PainelPosicionarArmamento() {
		setTitle("Batalha Naval");
		setBackground(Color.lightGray);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension resolucaoTela = toolkit.getScreenSize();
		int larguraDaResolucao = resolucaoTela.width;
		int alturaDaResolucao = resolucaoTela.height;
		int x = (larguraDaResolucao / 2) - (LARGURA_PADRAO / 2);
		int y = (alturaDaResolucao / 2) - (ALTURA_PADRAO / 2);
		setBounds(x, y, LARGURA_PADRAO, ALTURA_PADRAO);
		
		PintarArmamentos pintarArmamentos = new PintarArmamentos();
		getContentPane().add(pintarArmamentos);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
