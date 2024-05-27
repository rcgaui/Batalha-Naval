package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PainelPosicionarArmamento extends JFrame {
	private final int LARGURA_PADRAO = 1000;
	private final int ALTURA_PADRAO = 600;
	
	JPanel painel = new JPanel();
	
	
	public PainelPosicionarArmamento() {
		setTitle("Batalha Naval");
		
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
