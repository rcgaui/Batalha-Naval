package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Janela extends JFrame {
	private final int LARGURA_PADRAO = 1000;
	private final int ALTURA_PADRAO = 600;
	
	public Janela() {
		setTitle("Batalha Naval");
		
//		PainelVisaoBloqueada painelVisaoBloqueada = new PainelVisaoBloqueada();
//		getContentPane().add(painelVisaoBloqueada);
		
		PainelPosicionarArmamento painelPosicionarArmamento = new PainelPosicionarArmamento();
		getContentPane().add(painelPosicionarArmamento);

		painelPosicionarArmamento.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x >= 100 && x <= 120 && y >= 100 && y <= 120 || x >= 80 && x <= 100 && y >= 120 && y <= 140 || x >= 120 && x <= 140 && y >= 120 && y <= 140) {
                    System.out.println("Click Efetuado no hidro avião 1");
                }
                else if (x >= 180 && x <= 200 && y >= 100 && y <= 120 || x >= 160 && x <= 180 && y >= 120 && y <= 140 || x >= 200 && x <= 220 && y >= 120 && y <= 140) {
                    System.out.println("Click Efetuado no hidro avião 2");
                }
                else if (x >= 260 && x <= 280 && y >= 100 && y <= 120 || x >= 240 && x <= 260 && y >= 120 && y <= 140 || x >= 280 && x <= 300 && y >= 120 && y <= 140) {
                    System.out.println("Click Efetuado no hidro avião 3");
                }
                else if (x >= 340 && x <= 360 && y >= 100 && y <= 120 || x >= 320 && x <= 340 && y >= 120 && y <= 140 || x >= 360 && x <= 380 && y >= 120 && y <= 140) {
                    System.out.println("Click Efetuado no hidro avião 4");
                }
                else if (x >= 420 && x <= 440 && y >= 100 && y <= 120 || x >= 400 && x <= 420 && y >= 120 && y <= 140 || x >= 440 && x <= 460 && y >= 120 && y <= 140) {
                    System.out.println("Click Efetuado no hidro avião 5");
                }
                else if (x >= 80 && x <= 100 && y >= 180 && y <= 200) {
                    System.out.println("Click Efetuado no submarino 1");
                }
                else if (x >= 120 && x <= 140 && y >= 180 && y <= 200) {
                    System.out.println("Click Efetuado no submarino 2");
                }
                else if (x >= 160 && x <= 180 && y >= 180 && y <= 200) {
                    System.out.println("Click Efetuado no submarino 3");
                }
                else if (x >= 200 && x <= 220 && y >= 180 && y <= 200) {
                    System.out.println("Click Efetuado no submarino 4");
                }
                else if (x >= 80 && x <= 120 && y >= 240 && y <= 260) {
                    System.out.println("Click Efetuado no destroyer 1");
                }
                else if (x >= 140 && x <= 180 && y >= 240 && y <= 260) {
                    System.out.println("Click Efetuado no destroyer 2");
                }
                else if (x >= 200 && x <= 240 && y >= 240 && y <= 260) {
                    System.out.println("Click Efetuado no destroyer 3");
                }
                else if (x >= 80 && x <= 160 && y >= 300 && y <= 320) {
                    System.out.println("Click Efetuado no cruzador 1");
                }
                else if (x >= 180 && x <= 260 && y >= 300 && y <= 320) {
                    System.out.println("Click Efetuado no cruzador 2");
                }
                else if (x >= 80 && x <= 180 && y >= 360 && y <= 380) {
                    System.out.println("Click Efetuado no couracado 1");
                }
            }
        });
		
		// Centralizar janela em qualquer tela
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension resolucaoTela = toolkit.getScreenSize();
		int larguraDaResolucao = resolucaoTela.width;
		int alturaDaResolucao = resolucaoTela.height;
		int x = (larguraDaResolucao / 2) - (LARGURA_PADRAO / 2);
		int y = (alturaDaResolucao / 2) - (ALTURA_PADRAO / 2);
		setBounds(x, y, LARGURA_PADRAO, ALTURA_PADRAO);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
	}
}
