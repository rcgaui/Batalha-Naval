package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PintarVisaoBloqueada extends JPanel{
	JButton botaoComecarJogo = new JButton("Começar Jogo!");
	
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		setBackground(Color.lightGray);
		
		Graphics2D g2d = (Graphics2D) g;
		
		int qntNumeros = 15;
		int qntLetras = 15;

		double topY = 125.0;
		double largura = 300.0;
		double altura = 300.0;
		
		g.drawString("Tabuleiro Jogador 1", 225, 60);
		
		Rectangle2D tabuleiroJogador1 = new Rectangle2D.Double(125.0, topY, largura, altura);
		g2d.setPaint(Color.darkGray);
		g2d.fill(tabuleiroJogador1);
		g2d.setPaint(Color.black);
		
		for (int i = 0; i < qntLetras; i++) {
			char casa = (char)('A' + i);
			g.drawString(Character.toString(casa), 110, 140 + 20 * i);
			for (int j = 0; j < qntNumeros; j++) {
				int num = 1 + j;
				g.drawString(Integer.toString(num), 130 + 20 * j, 120);
				Rectangle2D retangulosTabuleiro1 = new Rectangle2D.Double(125.0 + 20 * i, topY + 20 * j, largura / 15, altura / 15);
				g2d.draw(retangulosTabuleiro1);
			}
		}
		
		g.drawString("Tabuleiro Jogador 2", 675, 60);
		
		Rectangle2D tabuleiroJogador2 = new Rectangle2D.Double(575.0, topY, largura, altura);
		g2d.setPaint(Color.darkGray);
		g2d.fill(tabuleiroJogador2);
		g2d.setPaint(Color.black);
		
		for (int i = 0; i < qntLetras; i++) {
			char casa = (char)('A' + i);
			g.drawString(Character.toString(casa), 560, 140 + 20 * i);
			for (int j = 0; j < qntNumeros; j++) {
				int num = 1 + j;
				g.drawString(Integer.toString(num), 580 + 20 * j, 120);
				Rectangle2D retangulosTabuleiro2 = new Rectangle2D.Double(575.0 + 20 * i, topY + 20 * j, largura / 15, altura / 15);
				g2d.draw(retangulosTabuleiro2);
			}
		}
		
		g.drawString("Visão bloqueada", 455, 470);
		
		botaoComecarJogo.setBounds(440, 490, 120, 40);
		add(botaoComecarJogo);
	}
}
