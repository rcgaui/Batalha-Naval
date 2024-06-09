package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controller.Control;
import Model.ObservadoAtaqueIF;

public class PainelAtaque extends JPanel implements ObservadorAtaqueIF{
	JButton botaoComecarJogo = new JButton("Começar Jogo!");
	private Color corCasasJ1 [][];
	private Color corCasasJ2 [][];
	int qntNumeros = 15;
	int qntLetras = 15;
	
	PainelAtaque()
	{
		Control.getController().registra(this);
		corCasasJ1 = new Color[qntLetras][qntNumeros];
		corCasasJ2 = new Color[qntLetras][qntNumeros];
		for (int i = 0; i < qntLetras; i++) {
            for (int j = 0; j < qntNumeros; j++) {
                corCasasJ1[i][j] = Color.darkGray;
                corCasasJ2[i][j] = Color.darkGray;
            }
        }
	}
	
	public void notify(ObservadoAtaqueIF observado)
	{
		String casa = observado.get(0);
		String estadoCasa = observado.get(1);
		converteCasa(casa, estadoCasa);
		repaint();
	}
	
	
	
	public void converteCasa(String casa, String estadocasa)
	{
		int letra = casa.charAt(0) - 'A';
		int numero = Integer.parseInt(casa.substring(1)) - 1;
		
		if(Control.getController().isJ1())
		{
			if(estadocasa.equals("*")) //Atingido
			{
				this.corCasasJ1[letra][numero] = Color.red;
			}
			else if(estadocasa.equals("~")) //Agua
			{
				this.corCasasJ2[letra][numero] = Color.blue;
			}
		}
		else
		{
			if(estadocasa.equals("*")) //Atingido
			{
				this.corCasasJ2[letra][numero] = Color.red;
			}
			else if(estadocasa.equals("~")) //Agua
			{
				this.corCasasJ2[letra][numero] = Color.blue;
			}
		}
			
	}
	
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		setBackground(Color.lightGray);
		
		Graphics2D g2d = (Graphics2D) g;
		

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
				g2d.setPaint(corCasasJ1[i][j]);
				g2d.fill(retangulosTabuleiro1);
				g2d.setPaint(Color.black);
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
				g2d.setPaint(corCasasJ2[i][j]);
				g2d.fill(retangulosTabuleiro2);
				g2d.setPaint(Color.black);
				g2d.draw(retangulosTabuleiro2);
			}
		}
		
		g.drawString("Visão bloqueada", 455, 470);
		
		botaoComecarJogo.setBounds(440, 490, 120, 40);
		add(botaoComecarJogo);
	}
}
