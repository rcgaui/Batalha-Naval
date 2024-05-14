package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PintarArmamentos extends JPanel {
	public int x = -1;
	public int y = -1;
	
	public String armamentoSelecionado;
	private boolean booleanArmamentoSelecionado;
	
	private boolean armamentosPosicionados = false;
	
	private boolean hidroAviao1Posicionado = false;
	private boolean hidroAviao2Posicionado = false;
	private boolean hidroAviao3Posicionado = false;
	private boolean hidroAviao4Posicionado = false;
	private boolean hidroAviao5Posicionado = false;
	private boolean submarino1Posicionado = false;
	private boolean submarino2Posicionado = false;
	private boolean submarino3Posicionado = false;
	private boolean submarino4Posicionado = false;
	private boolean destroyer1Posicionado = false;
	private boolean destroyer2Posicionado = false;
	private boolean destroyer3Posicionado = false;
	private boolean cruzador1Posicionado = false;
	private boolean cruzador2Posicionado = false;
	private boolean couracado1Posicionado = false;
	private int[] arrayArmamentosPosicionados = new int[15];
	private int[] arrayArmamentosNaMatriz = new int[225];
	// arrayArmamentosNaMatriz[index] recebe:
	// 0 --> Nada posicionado
	// 1 --> Hidro Avião posicionado
	// 2 --> Submarino posicionado
	// 3 --> Destroyer Avião posicionado
	// 4 --> Cruzador Avião posicionado
	// 5 --> Couracado Avião posicionado
	
	private boolean mouseEsquerdo = false;
	private boolean mouseDireito = false;
	
	JButton botaoComecarJogo = new JButton("Tabuleiro pronto!");
	
	public PintarArmamentos() {
	    this.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            x = e.getX();
	            y = e.getY();

	            if (SwingUtilities.isLeftMouseButton(e)) {
	            	mouseEsquerdo = true;
	            	mouseDireito = false;
	            } else if (SwingUtilities.isRightMouseButton(e)) {
	            	mouseDireito = true;
	            	mouseEsquerdo = false;
	            }

	            repaint();
	        }
	    });
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBackground(Color.lightGray);
		
		double topY = 125.0;
		double largura = 300.0;
		double altura = 300.0;
		
		Graphics2D g2d = (Graphics2D) g;
		
		desenhoArmamentos(g2d, largura, altura, arrayArmamentosPosicionados);
		desenhoTabuleiro(g2d, topY, largura, altura, g, arrayArmamentosNaMatriz);
		
		verificarClick(g2d);
		verificarTudoPosicionado(g);
	}
	
	private void desenhoArmamentos(Graphics2D g2d, double largura, double altura, int[] arrayArmamentosPosicionados) {
		g2d.setPaint(new Color(0, 80, 0));
		
		int contador = 0;
		
		for (int i = 0; i < 5; i++) {
			if(arrayArmamentosPosicionados[contador] == 1) {
				g2d.setPaint(Color.lightGray);
			}
			
			Rectangle2D hidroAviaoBase = new Rectangle2D.Double(100.0 + i * (20.0 * 4.0), 100.0, largura / 15.0, altura / 15.0);
			g2d.fill(hidroAviaoBase);
			Rectangle2D hidroAviaoEsquerda = new Rectangle2D.Double(80.0 + i * (20.0 * 4.0), 120.0, largura / 15.0, altura / 15.0);
			g2d.fill(hidroAviaoEsquerda);
			Rectangle2D hidroAviaoDireita = new Rectangle2D.Double(120.0 + i * (20.0 * 4.0), 120.0, largura / 15.0, altura / 15.0);
			g2d.fill(hidroAviaoDireita);
			
			g2d.setPaint(new Color(0, 80, 0));
			
			contador++;
		}
		
		g2d.setPaint(new Color(75, 0, 130));
		
		for (int i = 0; i < 4; i++) {
			if(arrayArmamentosPosicionados[contador] == 1) {
				g2d.setPaint(Color.lightGray);
			}
			
			Rectangle2D submarino = new Rectangle2D.Double(80.0 + i * (20.0 * 2.0), 180.0, largura / 15.0, altura / 15.0);
			g2d.fill(submarino);
			
			g2d.setPaint(new Color(75, 0, 130));

			contador++;
		}
		
		g2d.setPaint(new Color(255, 255, 0));
		
		for (int i = 0; i < 3; i++) {
			if(arrayArmamentosPosicionados[contador] == 1) {
				g2d.setPaint(Color.lightGray);
			}
			
			Rectangle2D destroyerPosicao1 = new Rectangle2D.Double(80.0 + i * (20.0 * 3.0), 240.0, largura / 15.0, altura / 15.0);
			g2d.fill(destroyerPosicao1);
			Rectangle2D destroyerPosicao2 = new Rectangle2D.Double(100.0 + i * (20.0 * 3.0), 240.0, largura / 15.0, altura / 15.0);
			g2d.fill(destroyerPosicao2);
			
			g2d.setPaint(new Color(255, 255, 0));

			contador++;
		}
		
		g2d.setPaint(new Color(255, 165, 0));
		
		for (int i = 0; i < 2; i++) {
			if(arrayArmamentosPosicionados[contador] == 1) {
				g2d.setPaint(Color.lightGray);
			}
			
			Rectangle2D cruzadorPosicao1 = new Rectangle2D.Double(80.0 + i * (20.0 * 5.0), 300.0, largura / 15.0, altura / 15.0);
			g2d.fill(cruzadorPosicao1);
			Rectangle2D cruzadorPosicao2 = new Rectangle2D.Double(100.0 + i * (20.0 * 5.0), 300.0, largura / 15.0, altura / 15.0);
			g2d.fill(cruzadorPosicao2);
			Rectangle2D cruzadorPosicao3 = new Rectangle2D.Double(120.0 + i * (20.0 * 5.0), 300.0, largura / 15.0, altura / 15.0);
			g2d.fill(cruzadorPosicao3);
			Rectangle2D cruzadorPosicao4 = new Rectangle2D.Double(140.0 + i * (20.0 * 5.0), 300.0, largura / 15.0, altura / 15.0);
			g2d.fill(cruzadorPosicao4);
			
			g2d.setPaint(new Color(255, 165, 0));

			contador++;
		}
		
		g2d.setPaint(new Color(160, 82, 45));
		
		if(arrayArmamentosPosicionados[contador] == 1) {
			g2d.setPaint(Color.lightGray);
		}
		
		Rectangle2D couracadoPosicao1 = new Rectangle2D.Double(80.0, 360.0, largura / 15.0, altura / 15.0);
		g2d.fill(couracadoPosicao1);
		Rectangle2D couracadoPosicao2 = new Rectangle2D.Double(100.0, 360.0, largura / 15.0, altura / 15.0);
		g2d.fill(couracadoPosicao2);
		Rectangle2D couracadoPosicao3 = new Rectangle2D.Double(120.0, 360.0, largura / 15.0, altura / 15.0);
		g2d.fill(couracadoPosicao3);
		Rectangle2D couracadoPosicao4 = new Rectangle2D.Double(140.0, 360.0, largura / 15.0, altura / 15.0);
		g2d.fill(couracadoPosicao4);
		Rectangle2D couracadoPosicao5 = new Rectangle2D.Double(160.0, 360.0, largura / 15.0, altura / 15.0);
		g2d.fill(couracadoPosicao5);

		contador++;
		
		g2d.setPaint(Color.black);
	}
	
	private void desenhoTabuleiro(Graphics2D g2d, double topY, double largura, double altura, Graphics g, int[] arrayArmamentosNaMatriz) {
		Rectangle2D tabuleiroJogador = new Rectangle2D.Double(575.0, topY, largura, altura);
		g2d.draw(tabuleiroJogador);
		
		int contador = 0;
		
		for (int i = 0; i < 15; i++) {
			char casa = (char)('A' + i);
			
			g.drawString(Character.toString(casa), 560, 140 + 20 * i);
			
			for (int j = 0; j < 15; j++) {
				int num = 1 + j;
				
				g.drawString(Integer.toString(num), 580 + 20 * j, 120);
				
				Rectangle2D retangulosTabuleiro = new Rectangle2D.Double(575.0 + 20.0 * i, topY + 20.0 * j, largura / 15.0, altura / 15.0);
				inserirCorMatriz(g2d, retangulosTabuleiro, contador);
				
				// ERRO AQUI
				contador = i + 15 * j;
			}
		}
	}
	
	private void inserirCorMatriz(Graphics2D g2d, Rectangle2D retangulosTabuleiro, int contador) {
		if(arrayArmamentosNaMatriz[contador] == 0) {
			g2d.setPaint(Color.black);
			g2d.draw(retangulosTabuleiro);
		}
		else if(arrayArmamentosNaMatriz[contador] == 1) {
			g2d.setPaint(new Color(0, 80, 0));
			g2d.fill(retangulosTabuleiro);
		}
		else if(arrayArmamentosNaMatriz[contador] == 2) {
			g2d.setPaint(new Color(75, 0, 130));
			g2d.fill(retangulosTabuleiro);
		}
		else if(arrayArmamentosNaMatriz[contador] == 3) {
			g2d.setPaint(new Color(255, 255, 0));
			g2d.fill(retangulosTabuleiro);
		}
		else if(arrayArmamentosNaMatriz[contador] == 4) {
			g2d.setPaint(new Color(255, 165, 0));
			g2d.fill(retangulosTabuleiro);
		}
		else if(arrayArmamentosNaMatriz[contador] == 5) {
			g2d.setPaint(new Color(160, 82, 45));
			g2d.fill(retangulosTabuleiro);
		}
	}

	private void verificarClick(Graphics2D g2d) {
		Stroke stroke = new BasicStroke(3f);
		g2d.setStroke(stroke);
		
		if (mouseEsquerdo && hidroAviao1Posicionado == false && x >= 100 && x <= 120 && y >= 100 && y <= 120 || x >= 80 && x <= 100 && y >= 120 && y <= 140 || x >= 120 && x <= 140 && y >= 120 && y <= 140) {			
			Rectangle2D hidroAviao1Selecionado = new Rectangle2D.Double(80.0, 100.0, 60, 40);
			g2d.draw(hidroAviao1Selecionado);
			
			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Hidro Avião 1";
        }
        else if (mouseEsquerdo && hidroAviao2Posicionado == false && x >= 180 && x <= 200 && y >= 100 && y <= 120 || x >= 160 && x <= 180 && y >= 120 && y <= 140 || x >= 200 && x <= 220 && y >= 120 && y <= 140) {
        	Rectangle2D hidroAviao2Selecionado = new Rectangle2D.Double(160.0, 100.0, 60, 40);
			g2d.draw(hidroAviao2Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Hidro Avião 2";
        }
        else if (mouseEsquerdo && hidroAviao3Posicionado == false && x >= 260 && x <= 280 && y >= 100 && y <= 120 || x >= 240 && x <= 260 && y >= 120 && y <= 140 || x >= 280 && x <= 300 && y >= 120 && y <= 140) {
        	Rectangle2D hidroAviao3Selecionado = new Rectangle2D.Double(240.0, 100.0, 60, 40);
			g2d.draw(hidroAviao3Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Hidro Avião 3";
        }
        else if (mouseEsquerdo && hidroAviao4Posicionado == false && x >= 340 && x <= 360 && y >= 100 && y <= 120 || x >= 320 && x <= 340 && y >= 120 && y <= 140 || x >= 360 && x <= 380 && y >= 120 && y <= 140) {
        	Rectangle2D hidroAviao4Selecionado = new Rectangle2D.Double(320.0, 100.0, 60, 40);
			g2d.draw(hidroAviao4Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Hidro Avião 4";
        }
        else if (mouseEsquerdo && hidroAviao5Posicionado == false && x >= 420 && x <= 440 && y >= 100 && y <= 120 || x >= 400 && x <= 420 && y >= 120 && y <= 140 || x >= 440 && x <= 460 && y >= 120 && y <= 140) {
        	Rectangle2D hidroAviao5Selecionado = new Rectangle2D.Double(400.0, 100.0, 60, 40);
			g2d.draw(hidroAviao5Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Hidro Avião 5";
        }
        else if (mouseEsquerdo && submarino1Posicionado == false && x >= 80 && x <= 100 && y >= 180 && y <= 200) {
        	Rectangle2D submarino1Selecionado = new Rectangle2D.Double(80.0, 180.0, 20, 20);
			g2d.draw(submarino1Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Submarino 1";
        }
        else if (mouseEsquerdo && submarino2Posicionado == false && x >= 120 && x <= 140 && y >= 180 && y <= 200) {
        	Rectangle2D submarino2Selecionado = new Rectangle2D.Double(120.0, 180.0, 20, 20);
			g2d.draw(submarino2Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Submarino 2";
        }
        else if (mouseEsquerdo && submarino3Posicionado == false && x >= 160 && x <= 180 && y >= 180 && y <= 200) {
        	Rectangle2D submarino3Selecionado = new Rectangle2D.Double(160.0, 180.0, 20, 20);
			g2d.draw(submarino3Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Submarino 3";
        }
        else if (mouseEsquerdo && submarino4Posicionado == false && x >= 200 && x <= 220 && y >= 180 && y <= 200) {
        	Rectangle2D submarino4Selecionado = new Rectangle2D.Double(200.0, 180.0, 20, 20);
			g2d.draw(submarino4Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Submarino 4";
        }
        else if (mouseEsquerdo && destroyer1Posicionado == false && x >= 80 && x <= 120 && y >= 240 && y <= 260) {
        	Rectangle2D destroyer1Selecionado = new Rectangle2D.Double(80.0, 240.0, 40, 20);
			g2d.draw(destroyer1Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Destroyer 1";
        }
        else if (mouseEsquerdo && destroyer2Posicionado == false && x >= 140 && x <= 180 && y >= 240 && y <= 260) {
        	Rectangle2D destroyer2Selecionado = new Rectangle2D.Double(140.0, 240.0, 40, 20);
			g2d.draw(destroyer2Selecionado);
			
			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Destroyer 2";
        }
        else if (mouseEsquerdo && destroyer3Posicionado == false && x >= 200 && x <= 240 && y >= 240 && y <= 260) {
        	Rectangle2D destroyer3Selecionado = new Rectangle2D.Double(200.0, 240.0, 40, 20);
			g2d.draw(destroyer3Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Destroyer 3";
        }
        else if (mouseEsquerdo && cruzador1Posicionado == false && x >= 80 && x <= 160 && y >= 300 && y <= 320) {
        	Rectangle2D cruzador1Selecionado = new Rectangle2D.Double(80.0, 300.0, 80, 20);
			g2d.draw(cruzador1Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Cruzador 1";
        }
        else if (mouseEsquerdo && cruzador2Posicionado == false && x >= 180 && x <= 260 && y >= 300 && y <= 320) {
        	Rectangle2D cruzador2Selecionado = new Rectangle2D.Double(180.0, 300.0, 80, 20);
			g2d.draw(cruzador2Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Cruzador 2";
        }
        else if (mouseEsquerdo && couracado1Posicionado == false && x >= 80 && x <= 180 && y >= 360 && y <= 380) {
        	Rectangle2D couracado1Selecionado = new Rectangle2D.Double(80.0, 360.0, 100, 20);
			g2d.draw(couracado1Selecionado);

			booleanArmamentoSelecionado = true;
			armamentoSelecionado = "Couraçado 1";
        }
        else {
        	int[] retornoClick = ClickTabuleiro.VerificarClickTabuleiro(x, y, armamentoSelecionado);
    		
        	// retornoClick[0] é a coluna e retornoClick[1] a linha
    		if (retornoClick[0] >= 0 && retornoClick[0] <= 15 && retornoClick[1] >= 0 && retornoClick[1] <= 15) {
    			if(booleanArmamentoSelecionado) {
    				if(armamentoSelecionado.equals("Hidro Avião 1")) {
                    	arrayArmamentosPosicionados[0] = 1;
        				hidroAviao1Posicionado = true;
        				
        				int index = retornoClick[0] + 15 * retornoClick[1];
        				arrayArmamentosNaMatriz[index] = 1;
        				index = retornoClick[0] + 15 * retornoClick[1] + 14;
        				arrayArmamentosNaMatriz[index] = 1;
        				index = retornoClick[0] + 15 * retornoClick[1] + 16;
        				arrayArmamentosNaMatriz[index] = 1;
        				
        				repaint();
                    }
        			else if(armamentoSelecionado.equals("Hidro Avião 2")) {
                    	// ...
                    }
                    // ...
    			}
            }
    		else {
    			booleanArmamentoSelecionado = false;
    			armamentoSelecionado = "vazio";
    		}
        }
	}
	
	private void verificarTudoPosicionado(Graphics g) {
		if(hidroAviao1Posicionado && hidroAviao2Posicionado && hidroAviao3Posicionado && 
				hidroAviao4Posicionado && hidroAviao5Posicionado && submarino1Posicionado && 
				submarino2Posicionado && submarino3Posicionado && submarino4Posicionado && 
				destroyer1Posicionado && destroyer2Posicionado && destroyer3Posicionado && 
				cruzador1Posicionado && cruzador2Posicionado && couracado1Posicionado) {
			armamentosPosicionados = true;
			
			g.drawString("Armamentos selecionados", 435, 470);
			botaoComecarJogo.setBounds(420, 490, 160, 40);
			add(botaoComecarJogo);	
		}
		else {
			g.drawString("Selecione os armamentos", 435, 470);
		}
	}
}
