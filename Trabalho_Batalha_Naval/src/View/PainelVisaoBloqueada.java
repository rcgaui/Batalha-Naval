package View;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PainelVisaoBloqueada extends JFrame {
	private final int LARGURA_PADRAO = 1000;
	private final int ALTURA_PADRAO = 600;
	
	JPanel painel = new JPanel();
	
	// Singleton PainelVisaoBloqueada
		private static PainelVisaoBloqueada instance = null;
	    public static PainelVisaoBloqueada getInstance() {
	        if (instance == null) {
	            instance = new PainelVisaoBloqueada();
	        }
	        return instance;
	    }
	
	public PainelVisaoBloqueada() {
		setTitle("Batalha Naval");
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension resolucaoTela = toolkit.getScreenSize();
		int larguraDaResolucao = resolucaoTela.width;
		int alturaDaResolucao = resolucaoTela.height;
		int x = (larguraDaResolucao / 2) - (LARGURA_PADRAO / 2);
		int y = (alturaDaResolucao / 2) - (ALTURA_PADRAO / 2);
		setBounds(x, y, LARGURA_PADRAO, ALTURA_PADRAO);
		
		PintarVisaoBloqueada pintarVisaoBloqueada = new PintarVisaoBloqueada();
		getContentPane().add(pintarVisaoBloqueada);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
