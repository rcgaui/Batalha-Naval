package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelPosicionarArmamento extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.lightGray);
		
		Graphics2D g2d = (Graphics2D) g;
		
		int qntNumeros = 15;
		int qntLetras = 15;

		double topY = 125.0;
		double largura = 300.0;
		double altura = 300.0;
		
		g2d.setPaint(new Color(0, 80, 0));
		for (int i = 0; i < 5; i++) {
			Rectangle2D hidroAviaoBase = new Rectangle2D.Double(100 + i * (20 * 4), 100, largura / 15, altura / 15);
			g2d.fill(hidroAviaoBase);
			Rectangle2D hidroAviaoEsquerda = new Rectangle2D.Double(80 + i * (20 * 4), 120, largura / 15, altura / 15);
			g2d.fill(hidroAviaoEsquerda);
			Rectangle2D hidroAviaoDireita = new Rectangle2D.Double(120 + i * (20 * 4), 120, largura / 15, altura / 15);
			g2d.fill(hidroAviaoDireita);
		}
		
		g2d.setPaint(new Color(75, 0, 130));
		for (int i = 0; i < 4; i++) {
			Rectangle2D submarino = new Rectangle2D.Double(80 + i * (20 * 2), 180, largura / 15, altura / 15);
			g2d.fill(submarino);
		}
		
		g2d.setPaint(new Color(255, 255, 0));
		for (int i = 0; i < 3; i++) {
			Rectangle2D destroyerPosicao1 = new Rectangle2D.Double(80 + i * (20 * 3), 240, largura / 15, altura / 15);
			g2d.fill(destroyerPosicao1);
			Rectangle2D destroyerPosicao2 = new Rectangle2D.Double(100 + i * (20 * 3), 240, largura / 15, altura / 15);
			g2d.fill(destroyerPosicao2);
		}
		
		g2d.setPaint(new Color(255, 165, 0));
		for (int i = 0; i < 2; i++) {
			Rectangle2D cruzadorPosicao1 = new Rectangle2D.Double(80 + i * (20 * 5), 300, largura / 15, altura / 15);
			g2d.fill(cruzadorPosicao1);
			Rectangle2D cruzadorPosicao2 = new Rectangle2D.Double(100 + i * (20 * 5), 300, largura / 15, altura / 15);
			g2d.fill(cruzadorPosicao2);
			Rectangle2D cruzadorPosicao3 = new Rectangle2D.Double(120 + i * (20 * 5), 300, largura / 15, altura / 15);
			g2d.fill(cruzadorPosicao3);
			Rectangle2D cruzadorPosicao4 = new Rectangle2D.Double(140 + i * (20 * 5), 300, largura / 15, altura / 15);
			g2d.fill(cruzadorPosicao4);
		}
		
		g2d.setPaint(new Color(160, 82, 45));
		Rectangle2D couracadoPosicao1 = new Rectangle2D.Double(80, 360, largura / 15, altura / 15);
		g2d.fill(couracadoPosicao1);
		Rectangle2D couracadoPosicao2 = new Rectangle2D.Double(100, 360, largura / 15, altura / 15);
		g2d.fill(couracadoPosicao2);
		Rectangle2D couracadoPosicao3 = new Rectangle2D.Double(120, 360, largura / 15, altura / 15);
		g2d.fill(couracadoPosicao3);
		Rectangle2D couracadoPosicao4 = new Rectangle2D.Double(140, 360, largura / 15, altura / 15);
		g2d.fill(couracadoPosicao4);
		Rectangle2D couracadoPosicao5 = new Rectangle2D.Double(160, 360, largura / 15, altura / 15);
		g2d.fill(couracadoPosicao5);
		
		g2d.setPaint(Color.black);
		
		Rectangle2D tabuleiroJogador2 = new Rectangle2D.Double(575.0, topY, largura, altura);
		g2d.draw(tabuleiroJogador2);
		
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
		
		g.drawString("Selecione os armamentos", 435, 470);
		JButton botaoComecarJogo = new JButton("Tabuleiro pronto!");
		botaoComecarJogo.setBounds(420, 490, 160, 40);
		// ERRO MULTIPLICANDO OS BOTOES
		add(botaoComecarJogo);
	}
}
