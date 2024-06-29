package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.Control;
import Model.ObservadoAtaqueIF;

public class PainelAtaque extends JPanel implements ObservadorAtaqueIF{
	JButton botaoComecarJogo = new JButton("Liberar Visão!");
	JButton botaoTrocaTurno = new JButton("Trocar Turno");
	JLabel textoAcao = new JLabel();
	JFrame pai;
	JPanel panel = this;
	JMenuBar barra = new JMenuBar();
	JMenu menu = new JMenu("Menu");
    JMenuItem salvarItem = new JMenuItem("Salvar Partida");
	JFileChooser filechooser;
	private Color corCasasJ1 [][];
	private Color corCasasJ2 [][];
	int qntNumeros = 15;
	int qntLetras = 15;
	protected boolean telaBloqueio = true;
	
	PainelAtaque(JFrame framePai)
	{
		pai = framePai;
		Control.getController().registra(this);
		corCasasJ1 = new Color[qntLetras][qntNumeros];
		corCasasJ2 = new Color[qntLetras][qntNumeros];
		for (int i = 0; i < qntLetras; i++) {
            for (int j = 0; j < qntNumeros; j++) {
                corCasasJ1[i][j] = Color.darkGray;
                corCasasJ2[i][j] = Color.darkGray;
            }
        }
		
		salvarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filechooser = new JFileChooser();
                filechooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
                int selecionado = filechooser.showSaveDialog(panel);
                
                if(selecionado == JFileChooser.APPROVE_OPTION) {
                    File file = filechooser.getSelectedFile();
                    if (!file.getAbsolutePath().endsWith(".txt")) {
                        file = new File(file.getAbsolutePath() + ".txt");
                    }
                    Control.getController().salvarPartida(file);
                } 
            }
        });
		
		menu.add(salvarItem);
		barra.add(menu);
		barra.setBounds(440, 50, 120, 40);
		add(barra);
		
		botaoTrocaTurno.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	remove(textoAcao);
		    	Control.getController().TrocaTurno();    	
		    	telaBloqueio = true;
		    	add(botaoComecarJogo);
		    	remove(botaoTrocaTurno);
		    	repaint();
		    }
		});
		
		botaoComecarJogo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	remove(textoAcao);
		    	telaBloqueio = false;
		    	remove(botaoComecarJogo);
		    	repaint();
		    }
		});
		
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if(Control.getController().getTentativas() > 0)
            	{     
            		int escolha;
            		int x = e.getX();
            		int y = e.getY();
            		String coordenadaConvertida = Control.getController().converteCoordenadaAtaque(x, y);
            		
            		if(coordenadaConvertida != null)
            		{
            			int letra = coordenadaConvertida.charAt(0) - 'A';
            			int numero = Integer.parseInt(coordenadaConvertida.substring(1)) - 1;
            			Control.getController().atacar(letra, numero);
            			if(Control.getController().isGameOver())
            			{
            				if(Control.getController().isJ1())
            				{
            					String vencedorPartida = "Vencedor da partida: " + Control.getController().getJogadorName(1);
            					JOptionPane.showMessageDialog(null, vencedorPartida, "Partida Terminada", JOptionPane.INFORMATION_MESSAGE);            					
            				}
            				else
            				{
            					String vencedorPartida = "Vencedor da partida: " + Control.getController().getJogadorName(2);
            					JOptionPane.showMessageDialog(null, vencedorPartida, "Partida Terminada", JOptionPane.INFORMATION_MESSAGE);    
            				}
            				
            				escolha = JOptionPane.showConfirmDialog(null, "Deseja começar uma nova partida?", "Confirmação", JOptionPane.YES_NO_OPTION);
        				  if (escolha == JOptionPane.YES_OPTION) {
        			            JOptionPane.showMessageDialog(null, "Você escolheu continuar.");
        			            Control.getController().irParaPosicionarArmamento(framePai, Control.getController().getJogadorName(1), Control.getController().getJogadorName(2));
        			            pai.dispose();
        			        } else {
        			        	JOptionPane.showMessageDialog(null, "Encerrando Programa!");
        			        	System.exit(0);
        			        }
            			}
            		}
            	}
            }
        });
	}
	
	public void notify(ObservadoAtaqueIF observado)
	{
  		String casa = observado.get(0);
		String estadoCasa = observado.get(1);
		converteCasa(casa, estadoCasa);
		pintaDestroyed();
		repaint();
	}
	
	public void pintaDestroyed()
	{
		ArrayList<String> destroyed = Control.getController().getDestroyed();
		if(destroyed == null) {}
		else
		{
			textoAcao.setText("Embarcação Destruida!");
			textoAcao.setForeground(Color.red);
			panel.add(textoAcao);
			for(String posicao: destroyed)
			{
				destroiBarco(posicao);
			}
		}
	}
	
	public void converteCasa(String casa, String estadocasa)
	{
		int letra = casa.charAt(0) - 'A';
		int numero = Integer.parseInt(casa.substring(1)) - 1;
		if(Control.getController().isJ1())
		{
			if(estadocasa.equals("*")) //Atingido
			{
				this.corCasasJ2[letra][numero] = Color.orange;
				textoAcao.setText("Embarcação Atingida!");
				textoAcao.setForeground(Color.orange);
				panel.add(textoAcao);
			}
			else if(estadocasa.equals("~")) //Agua
			{
				textoAcao.setText("Água Atingida!");
				textoAcao.setForeground(Color.blue);
				panel.add(textoAcao);
				this.corCasasJ2[letra][numero] = Color.blue;
			}
			else {}
		}
		else
		{
			if(estadocasa.equals("*")) //Atingido
			{
				textoAcao.setText("Embarcação Atingida!");
				textoAcao.setForeground(Color.orange);
				panel.add(textoAcao);
				this.corCasasJ1[letra][numero] = Color.orange;
			}
			else if(estadocasa.equals("~")) //Agua
			{
				textoAcao.setText("Água Atingida!");
				textoAcao.setForeground(Color.blue);
				panel.add(textoAcao);
				this.corCasasJ1[letra][numero] = Color.blue;
			}
			else {}
		}		
	}
	
	public void destroiBarco(String casa)
	{
		int letra = casa.charAt(0) - 'A';
		int numero = Integer.parseInt(casa.substring(1)) - 1;
		
		if(Control.getController().isJ1())
		{
			this.corCasasJ2[letra][numero] = Color.red;
		}
		else
		{
			this.corCasasJ1[letra][numero] = Color.red;
		}
		repaint();
	}
	
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
		setBackground(Color.lightGray);
		
		Graphics2D g2d = (Graphics2D) g;
		
		double topY = 125.0;
		double largura = 300.0;
		double altura = 300.0;
		
		textoAcao.setBounds(430, 0, 200, 200);
		String nomeJ1 = "Tabuleiro de " + Control.getController().getJogadorName(1);
		String nomeJ2 = "Tabuleiro de " + Control.getController().getJogadorName(2);
		
		// Tabuleiro J1
		g.drawString(nomeJ1, 225, 60);
		
		Rectangle2D tabuleiroJogador1 = new Rectangle2D.Double(125.0, topY, largura, altura);
		g2d.setPaint(Color.darkGray);
		g2d.fill(tabuleiroJogador1);
		g2d.setPaint(Color.black);
		
		for (int i = 0; i < qntLetras; i++) {
			char casa = (char)('A' + i);
			g.drawString(Character.toString(casa), 110, 140 + 20 * i);
			for (int j = 0; j < qntNumeros; j++) {
				int num = 1 + j;
				double posX = 125.0 + (20*j);
				double posY = topY + (20*i);
				g.drawString(Integer.toString(num), 130 + 20 * j, 120);
				Rectangle2D retangulosTabuleiro1 = new Rectangle2D.Double(posX, posY, largura / 15, altura / 15);
				Rectangle2D bordaQuadrados1 = new Rectangle2D.Double(posX, posY, largura / 15, altura / 15);
				g2d.setPaint(this.corCasasJ1[i][j]);
				g2d.fill(retangulosTabuleiro1);
				g2d.setPaint(Color.black);
				g2d.draw(bordaQuadrados1);
			}
		}
		
		// Tabuleiro J2
		g.drawString(nomeJ2, 675, 60);
		
		Rectangle2D tabuleiroJogador2 = new Rectangle2D.Double(575.0, topY, largura, altura);
		g2d.setPaint(Color.darkGray);
		g2d.fill(tabuleiroJogador2);
		g2d.setPaint(Color.black);
		
		for (int i = 0; i < qntLetras; i++) {
			char casa = (char)('A' + i);
			g.drawString(Character.toString(casa), 560, 140 + 20 * i);
			for (int j = 0; j < qntNumeros; j++) {
				int num = 1 + j;
				double posX = 575.0 + (20*j);
				double posY = topY + 20*i;
				g.drawString(Integer.toString(num), 580 + 20 * j, 120);
				Rectangle2D retangulosTabuleiro2 = new Rectangle2D.Double(posX, posY, largura / 15, altura / 15);
				Rectangle2D bordaQuadrados2 = new Rectangle2D.Double(posX, posY, largura / 15, altura / 15);
				g2d.setPaint(this.corCasasJ2[i][j]);
				g2d.fill(retangulosTabuleiro2);
				g2d.setPaint(Color.black);
				g2d.draw(bordaQuadrados2);
			}
		}

		// Bloqueios de visao
		if(telaBloqueio == true)
		{
			g2d.setPaint(Color.darkGray);
			g2d.fill(tabuleiroJogador2);
			g2d.fill(tabuleiroJogador1);
			g.setColor(Color.white);
			g.drawString("Visão bloqueada", 225, 275);
			g.drawString("Visão bloqueada", 675, 275);
			botaoComecarJogo.setBounds(440, 490, 120, 40);
			add(botaoComecarJogo);
		}
		else if(Control.getController().isJ1()) // Vez do J1, bloqueia Tabuleiro dele
		{
			g2d.setPaint(Color.darkGray);
			g2d.fill(tabuleiroJogador1);
			g.setColor(Color.black);
			g.drawString("Visão bloqueada", 225, 275);
		}
		else // Vez do J2, bloqueia tabuleiro dele
		{
			g2d.setPaint(Color.darkGray);
			g2d.fill(tabuleiroJogador2);
			g.setColor(Color.black);
			g.drawString("Visão bloqueada", 675, 275);
		}
		
		if(Control.getController().getTentativas() < 1)
		{
			botaoTrocaTurno.setBounds(440, 490, 120, 40);
			add(botaoTrocaTurno);
		}
		else if(Control.getController().getTentativas() == 3 && telaBloqueio == false)
		{
			menu.add(salvarItem);
			barra.add(menu);
			barra.setBounds(0, 0, 50, 20);
			add(barra);
		}
		else
		{
			remove(barra);
		}
	}
}