package View;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameAtaque extends JFrame {
	private final int LARGURA_PADRAO = 1000;
	private final int ALTURA_PADRAO = 600;
	
	public FrameAtaque() {
		setTitle("Batalha Naval");
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension resolucaoTela = toolkit.getScreenSize();
		int larguraDaResolucao = resolucaoTela.width;
		int alturaDaResolucao = resolucaoTela.height;
		int x = (larguraDaResolucao / 2) - (LARGURA_PADRAO / 2);
		int y = (alturaDaResolucao / 2) - (ALTURA_PADRAO / 2);
		setBounds(x, y, LARGURA_PADRAO, ALTURA_PADRAO);
		
		PainelAtaque painelAtaque = new PainelAtaque();
		getContentPane().add(painelAtaque);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}