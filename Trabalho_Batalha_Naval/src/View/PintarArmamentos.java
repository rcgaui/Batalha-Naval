package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities; 

import Controller.Control;
import Model.ObservadoAtaqueIF;

public class PintarArmamentos extends JPanel implements ObservadorAtaqueIF {
	public int x = -1;
	public int y = -1;
	
	private String nomeArmamentoSelecionado = "";
	private int numeroArmamentoSelecionado = 1;
	private boolean booleanArmamentoSelecionado = false;
	private boolean existeErroParaConfirmarPosicionamento = false;
	private boolean confirmarPosicionamento = false;
	
	private boolean armamentosJogador1Posicionado = false;
	private boolean armamentosJogador2Posicionado = false;
	
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
	// 0 --> Não posicionado
	// 1 --> Posicionado
	
	private int[][] matrizArmamentos = new int[15][15];
	// private int[][] matrizArmamentosJ2 = new int[15][15];
	
	private int[][] matrizArmamentosTemp = new int[25][25];
	// arrayArmamentosNaMatriz[index] recebe:
	// 0 --> Nada posicionado
	// 1 --> Hidro Avião posicionado
	// 2 --> Submarino posicionado
	// 3 --> Destroyer Avião posicionado
	// 4 --> Cruzador Avião posicionado
	// 5 --> Couracado Avião posicionado
	
	private int[] retornoClick;
	private String coordenada;
	private int[] clickSalvoAteInserir;
	
	private int numRotaçãoGeral = 0;  // Começa "Oeste-Leste" --> "Norte-Sul" --> "Leste-Oeste" --> "Sul-Norte"
	private int numRotaçãoHidroAviao = 0; // Começa "Norte-Sul" --> "Leste-Oeste" --> "Sul-Norte" --> "Oeste-Leste"
	
	private boolean mouseEsquerdo = false;
	private boolean mouseDireito = false;
	
	JLabel textNomeJogador1;
	JLabel textNomeJogador2;
	JLabel textAuxiliar;
	JButton botaoConfirmarArmamento = new JButton("Confirmar posição de armamento");
	JButton botaoProximoJogador = new JButton("Próximo jogador");
	JButton botaoComecarJogo = new JButton("Começar jogo");
	
	PintarArmamentos(String nomeJogador1, String nomeJogador2, JFrame framePai) {
		Control.getController().registra(this);
		
		textNomeJogador1 = new JLabel("Posicionar armamentos " + nomeJogador1);
		textNomeJogador2 = new JLabel("Posicionar armamentos " + nomeJogador2);
		
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
	        }
	    });
		
		botaoConfirmarArmamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	if(existeErroParaConfirmarPosicionamento == false && confirmarPosicionamento) {
            		confirmarPosicionamento = false;
            		
            		if(nomeArmamentoSelecionado == "hidroaviao") {
            			if(numRotaçãoHidroAviao == 1) {
                			Control.getController().PosicionaEmbarcacao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Norte-Sul", coordenada);	
                		}
    					else if(numRotaçãoHidroAviao == 2) {
    						Control.getController().PosicionaEmbarcacao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Leste-Oeste", coordenada);
    					}
    					else if(numRotaçãoHidroAviao == 3) {
    						Control.getController().PosicionaEmbarcacao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Sul-Norte", coordenada);
    					}
    					else if(numRotaçãoHidroAviao == 4) {
    						Control.getController().PosicionaEmbarcacao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Oeste-Leste", coordenada);	
    					}
					}
					else {
						if(numRotaçãoGeral == 1) {
	            			Control.getController().PosicionaEmbarcacao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Oeste-Leste", coordenada);
    					}
    					else if(numRotaçãoGeral == 2) {
    						Control.getController().PosicionaEmbarcacao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Norte-Sul", coordenada);
    					}
    					else if(numRotaçãoGeral == 3) {
    						Control.getController().PosicionaEmbarcacao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Leste-Oeste", coordenada);
    					}
    					else if(numRotaçãoGeral == 4) {
    						Control.getController().PosicionaEmbarcacao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Sul-Norte", coordenada);
    					}
					}
            	}
            }
        });
		
		botaoProximoJogador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	redefinirPainelProximoJogador();
            	
            	Control.getController().TrocaTurno();
            }
        });
		
		botaoComecarJogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	framePai.dispose();
            	
            	Control.getController().ComecarAtaque();
            }
        });
	}
	
	public void notify(ObservadoAtaqueIF observado)
	{
		if(nomeArmamentoSelecionado == "hidroaviao") {
			inserirArmamentoSelecionado(clickSalvoAteInserir, numRotaçãoHidroAviao);
		}
		else {
			inserirArmamentoSelecionado(clickSalvoAteInserir, numRotaçãoGeral);
		}
		
		limparMatrizArmamentosTemp();
		booleanArmamentoSelecionado = false;
		nomeArmamentoSelecionado = "";
		numeroArmamentoSelecionado = -1;
		coordenada = "";
		clickSalvoAteInserir = null;
		numRotaçãoGeral = 0;
		numRotaçãoHidroAviao = 0;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBackground(Color.lightGray);
		
		if(armamentosJogador1Posicionado == false && armamentosJogador2Posicionado == false) {
			textNomeJogador1.setBounds(400, 20, 220, 30);
			add(textNomeJogador1);
		}
		else if(armamentosJogador1Posicionado == true && armamentosJogador2Posicionado == false) {
			textNomeJogador2.setBounds(400, 20, 220, 30);
			add(textNomeJogador2);
		}
		
		botaoConfirmarArmamento.setBounds(615, 430, 220, 40);
        add(botaoConfirmarArmamento);
		
		double topY = 125.0;
		double largura = 300.0;
		double altura = 300.0;
		
		Graphics2D g2d = (Graphics2D) g;
		
		desenhoArmamentos(g2d, largura, altura, arrayArmamentosPosicionados);
		desenhoTabuleiro(g2d, topY, largura, altura, g, matrizArmamentos);
		desenhoTabuleiroTemp(g2d, topY, largura, altura, g, matrizArmamentos);
		
		Stroke stroke = new BasicStroke(3f);
		g2d.setStroke(stroke);
		
		verificarClick(g2d);
		selecionarArmamento(g2d);
		
		if(booleanArmamentoSelecionado && confirmarPosicionamento) {
			g.drawString("Altere, rotacione ou confirme o posicionamento", 600, 100);
		}
		else if(booleanArmamentoSelecionado) {
			g.drawString("Clique na matriz onde deseja posicionar o armamento", 585, 100);
		}
		else {
			g.drawString("Selecione um armamento", 660, 100);
		}
		
		verificarTudoPosicionado(g);
		
		repaint();
	}
	
	private void redefinirPainelProximoJogador() {
		nomeArmamentoSelecionado = "";
		numeroArmamentoSelecionado = -1;
    	booleanArmamentoSelecionado = false;
    	
    	hidroAviao1Posicionado = false;
    	hidroAviao2Posicionado = false;
    	hidroAviao3Posicionado = false;
    	hidroAviao4Posicionado = false;
    	hidroAviao5Posicionado = false;
    	submarino1Posicionado = false;
    	submarino2Posicionado = false;
    	submarino3Posicionado = false;
    	submarino4Posicionado = false;
    	destroyer1Posicionado = false;
    	destroyer2Posicionado = false;
    	destroyer3Posicionado = false;
    	cruzador1Posicionado = false;
    	cruzador2Posicionado = false;
    	couracado1Posicionado = false;
    	
    	limparMatrizArmamentosTemp();
    	
    	for(int i = 0; i < arrayArmamentosPosicionados.length; i++) {
    		arrayArmamentosPosicionados[i] = 0;
    	}

    	for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
            	matrizArmamentos[i][j] = 0;
            }
        }
    	
    	remove(textNomeJogador1);
    	remove(botaoProximoJogador);
    	
    	armamentosJogador1Posicionado = true;
    	
    	repaint();
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
	
	private void desenhoTabuleiro(Graphics2D g2d, double topY, double largura, double altura, Graphics g, int[][] arrayArmamentosNaMatriz) {
		for (int i = 0; i < 15; i++) {
			char casa = (char)('A' + i);
			
			g2d.setPaint(Color.black);
			g.drawString(Character.toString(casa), 560, 140 + 20 * i);
			
			for (int j = 0; j < 15; j++) {
				int num = 1 + j;
				
				g2d.setPaint(Color.black);
				g.drawString(Integer.toString(num), 580 + 20 * j, 120);
				
				Rectangle2D retangulosTabuleiro = new Rectangle2D.Double(575.0 + 20.0 * j, topY + 20.0 * i, largura / 15.0, altura / 15.0);
				inserirCorMatriz(g2d, retangulosTabuleiro, i, j);
			}
		}
	}
	
	private void desenhoTabuleiroTemp(Graphics2D g2d, double topY, double largura, double altura, Graphics g, int[][] arrayArmamentosNaMatriz) {
		for (int i = 0; i < 25; i++) {
			g2d.setPaint(Color.black);
			for (int j = 0; j < 25; j++) {
				g2d.setPaint(Color.black);
				Rectangle2D retangulosTabuleiro = new Rectangle2D.Double(575.0 - 100.0 + 20.0 * j, topY - 100.0 + 20.0 * i, largura / 15.0, altura / 15.0);
				inserirCorMatrizTemp(g2d, retangulosTabuleiro, i, j);
			}
		}
	}
	
	private void inserirCorMatriz(Graphics2D g2d, Rectangle2D retangulosTabuleiro, int i, int j) {
		if(matrizArmamentos[i][j] == 0) {
			g2d.setPaint(Color.lightGray);
			g2d.fill(retangulosTabuleiro);
			g2d.setPaint(Color.black);
			g2d.draw(retangulosTabuleiro);
		}
		else if(matrizArmamentos[i][j] == 1) {
			g2d.setPaint(new Color(0, 80, 0));
			g2d.fill(retangulosTabuleiro);
		}
		else if(matrizArmamentos[i][j] == 2) {
			g2d.setPaint(new Color(75, 0, 130));
			g2d.fill(retangulosTabuleiro);
		}
		else if(matrizArmamentos[i][j] == 3) {
			g2d.setPaint(new Color(255, 255, 0));
			g2d.fill(retangulosTabuleiro);
		}
		else if(matrizArmamentos[i][j] == 4) {
			g2d.setPaint(new Color(255, 165, 0));
			g2d.fill(retangulosTabuleiro);
		}
		else if(matrizArmamentos[i][j] == 5) {
			g2d.setPaint(new Color(160, 82, 45));
			g2d.fill(retangulosTabuleiro);
		}
	}
	
	private void inserirCorMatrizTemp(Graphics2D g2d, Rectangle2D retangulosTabuleiro, int i, int j) {
		if(matrizArmamentosTemp[i][j] == 6) {
			g2d.setPaint(new Color(100, 100, 120));
			g2d.fill(retangulosTabuleiro);
		}
		else if(matrizArmamentosTemp[i][j] == 7) {
			g2d.setPaint(new Color(225, 80, 80));
			g2d.fill(retangulosTabuleiro);
		}
	}
	
	private void limparMatrizArmamentosTemp() {
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				matrizArmamentosTemp[i][j] = 0;
			}
		}
	}

	private void selecionarArmamento(Graphics2D g2d) {
		if(nomeArmamentoSelecionado.equals("submarino")) {
			if(numeroArmamentoSelecionado == 1) {
				Rectangle2D submarino1Selecionado = new Rectangle2D.Double(80.0, 180.0, 20, 20);
				g2d.draw(submarino1Selecionado);
			}
			else if(numeroArmamentoSelecionado == 2) {
				Rectangle2D submarino2Selecionado = new Rectangle2D.Double(120.0, 180.0, 20, 20);
				g2d.draw(submarino2Selecionado);
	        }
			else if(numeroArmamentoSelecionado == 3) {
				Rectangle2D submarino3Selecionado = new Rectangle2D.Double(160.0, 180.0, 20, 20);
				g2d.draw(submarino3Selecionado);
	        }
			else if(numeroArmamentoSelecionado == 4) {
				Rectangle2D submarino4Selecionado = new Rectangle2D.Double(200.0, 180.0, 20, 20);
				g2d.draw(submarino4Selecionado);
	        }
        }
		else if(nomeArmamentoSelecionado.equals("hidroaviao")) {
			if(numeroArmamentoSelecionado == 1) {
				Rectangle2D hidroAviao1Selecionado = new Rectangle2D.Double(80.0, 100.0, 60, 40);
				g2d.draw(hidroAviao1Selecionado);
	        }
			else if(numeroArmamentoSelecionado == 2) {
				Rectangle2D hidroAviao2Selecionado = new Rectangle2D.Double(160.0, 100.0, 60, 40);
				g2d.draw(hidroAviao2Selecionado);
	        }
			else if(numeroArmamentoSelecionado == 3) {
				Rectangle2D hidroAviao3Selecionado = new Rectangle2D.Double(240.0, 100.0, 60, 40);
				g2d.draw(hidroAviao3Selecionado);
	        }
			else if(numeroArmamentoSelecionado == 4) {
				Rectangle2D hidroAviao4Selecionado = new Rectangle2D.Double(320.0, 100.0, 60, 40);
				g2d.draw(hidroAviao4Selecionado);
	        }
			else if(numeroArmamentoSelecionado == 5) {
				Rectangle2D hidroAviao5Selecionado = new Rectangle2D.Double(400.0, 100.0, 60, 40);
				g2d.draw(hidroAviao5Selecionado);
	        }
		}
		else if(nomeArmamentoSelecionado.equals("destroyer")) {
			if(numeroArmamentoSelecionado == 1) {
				Rectangle2D destroyer1Selecionado = new Rectangle2D.Double(80.0, 240.0, 40, 20);
				g2d.draw(destroyer1Selecionado);
			}
			else if(numeroArmamentoSelecionado == 2) {
				Rectangle2D destroyer2Selecionado = new Rectangle2D.Double(140.0, 240.0, 40, 20);
				g2d.draw(destroyer2Selecionado);
	        }
			else if(numeroArmamentoSelecionado == 3) {
				Rectangle2D destroyer3Selecionado = new Rectangle2D.Double(200.0, 240.0, 40, 20);
				g2d.draw(destroyer3Selecionado);
	        }
        }
		else if(nomeArmamentoSelecionado.equals("cruzador")) {
			if(numeroArmamentoSelecionado == 1) {
				Rectangle2D cruzador1Selecionado = new Rectangle2D.Double(80.0, 300.0, 80, 20);
				g2d.draw(cruzador1Selecionado);
			}
			else if(numeroArmamentoSelecionado == 2) {
				Rectangle2D cruzador2Selecionado = new Rectangle2D.Double(180.0, 300.0, 80, 20);
				g2d.draw(cruzador2Selecionado);
	        }
        }
		else if(nomeArmamentoSelecionado.equals("couracado") && numeroArmamentoSelecionado == 1) {
			Rectangle2D couracado1Selecionado = new Rectangle2D.Double(80.0, 360.0, 100, 20);
			g2d.draw(couracado1Selecionado);
        }
	}
	
	private void inserirTempArmamento(int[] retornoClick, boolean posicionamentoPermitido, int numRotação) {
		if(posicionamentoPermitido) {
			if(nomeArmamentoSelecionado.equals("submarino")) {
				matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
	        }
			else if(nomeArmamentoSelecionado.equals("hidroaviao")) {
				if(numRotação == 1) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] - 1 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] + 1 + 5] = 6;
				}
				else if(numRotação == 2) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] - 1 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] - 1 + 5] = 6;
				}
				else if(numRotação == 3) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] - 1 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] + 1 + 5] = 6;
				}
				else if(numRotação == 4) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] + 1 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] + 1 + 5] = 6;
				}
			}
			else if(nomeArmamentoSelecionado.equals("destroyer")) {
				if(numRotação == 1) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 1 + 5] = 6;
				}
				else if(numRotação == 2) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] + 5] = 6;
				}
				else if(numRotação == 3) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 1 + 5] = 6;
				}
				else if(numRotação == 4) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] + 5] = 6;	
				}
	        }
			else if(nomeArmamentoSelecionado.equals("cruzador")) {
				if(numRotação == 1) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 1 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 2 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 3 + 5] = 6;
				}
				else if(numRotação == 2) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 2 + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 3 + 5][retornoClick[0] + 5] = 6;
				}
				else if(numRotação == 3) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 1 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 2 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 3 + 5] = 6;
				}
				else if(numRotação == 4) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 2 + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 3 + 5][retornoClick[0] + 5] = 6;
				}
	        }
			else if(nomeArmamentoSelecionado.equals("couracado")) {
				if(numRotação == 1) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 1 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 2 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 3 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 4 + 5] = 6;
				}
				else if(numRotação == 2) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 2 + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 3 + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 4 + 5][retornoClick[0] + 5] = 6;
				}
				else if(numRotação == 3) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 1 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 2 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 3 + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 4 + 5] = 6;
				}
				else if(numRotação == 4) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 2 + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 3 + 5][retornoClick[0] + 5] = 6;
					matrizArmamentosTemp[retornoClick[1] - 4 + 5][retornoClick[0] + 5] = 6;
				}
	        }
		}
		else if(posicionamentoPermitido == false) {
			if(nomeArmamentoSelecionado.equals("submarino")) {
				matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
	        }
			else if(nomeArmamentoSelecionado.equals("hidroaviao")) {
				if(numRotação == 1) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] - 1 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] + 1 + 5] = 7;
				}
				else if(numRotação == 2) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] - 1 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] - 1 + 5] = 7;
				}
				else if(numRotação == 3) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] - 1 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] + 1 + 5] = 7;
				}
				else if(numRotação == 4) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] + 1 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] + 1 + 5] = 7;
				}
			}
			else if(nomeArmamentoSelecionado.equals("destroyer")) {
				if(numRotação == 1) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 1 + 5] = 7;
				}
				else if(numRotação == 2) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] + 5] = 7;
				}
				else if(numRotação == 3) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 1 + 5] = 7;
				}
				else if(numRotação == 4) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] + 5] = 7;	
				}
	        }
			else if(nomeArmamentoSelecionado.equals("cruzador")) {
				if(numRotação == 1) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 1 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 2 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 3 + 5] = 7;
				}
				else if(numRotação == 2) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 2 + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 3 + 5][retornoClick[0] + 5] = 7;
				}
				else if(numRotação == 3) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 1 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 2 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 3 + 5] = 7;
				}
				else if(numRotação == 4) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 2 + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 3 + 5][retornoClick[0] + 5] = 7;
				}
	        }
			else if(nomeArmamentoSelecionado.equals("couracado")) {
				if(numRotação == 1) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 1 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 2 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 3 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 4 + 5] = 7;
				}
				else if(numRotação == 2) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 1 + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 2 + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 3 + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 4 + 5][retornoClick[0] + 5] = 7;
				}
				else if(numRotação == 3) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 1 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 2 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 3 + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] - 4 + 5] = 7;
				}
				else if(numRotação == 4) {
					matrizArmamentosTemp[retornoClick[1] + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 1 + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 2 + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 3 + 5][retornoClick[0] + 5] = 7;
					matrizArmamentosTemp[retornoClick[1] - 4 + 5][retornoClick[0] + 5] = 7;
				}
	        }
		}
	}
	
	private void inserirArmamentoSelecionado(int[] retornoClick, int numRotação) {
		if(nomeArmamentoSelecionado.equals("submarino")) {
			matrizArmamentos[retornoClick[1]][retornoClick[0]] = 2;
			
			if(numeroArmamentoSelecionado == 1) {
				arrayArmamentosPosicionados[5] = 1;
				submarino1Posicionado = true;	
			}
			else if(numeroArmamentoSelecionado == 2) {
				arrayArmamentosPosicionados[6] = 1;
				submarino2Posicionado = true;
	        }
			else if(numeroArmamentoSelecionado == 3) {
				arrayArmamentosPosicionados[7] = 1;
				submarino3Posicionado = true;
	        }
			else if(numeroArmamentoSelecionado == 4) {
				arrayArmamentosPosicionados[8] = 1;
				submarino4Posicionado = true;
	        }
        }
		else if(nomeArmamentoSelecionado.equals("hidroaviao")) {
			if(numRotação == 1) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 1;
				matrizArmamentos[retornoClick[1] + 1][retornoClick[0] - 1] = 1;
				matrizArmamentos[retornoClick[1] + 1][retornoClick[0] + 1] = 1;
			}
			else if(numRotação == 2) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 1;
				matrizArmamentos[retornoClick[1] - 1][retornoClick[0] - 1] = 1;
				matrizArmamentos[retornoClick[1] + 1][retornoClick[0] - 1] = 1;
			}
			else if(numRotação == 3) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 1;
				matrizArmamentos[retornoClick[1] - 1][retornoClick[0] - 1] = 1;
				matrizArmamentos[retornoClick[1] - 1][retornoClick[0] + 1] = 1;
			}
			else if(numRotação == 4) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 1;
				matrizArmamentos[retornoClick[1] - 1][retornoClick[0] + 1] = 1;
				matrizArmamentos[retornoClick[1] + 1][retornoClick[0] + 1] = 1;
			}
			
			if(numeroArmamentoSelecionado == 1) {
	        	arrayArmamentosPosicionados[0] = 1;
				hidroAviao1Posicionado = true;
	        }
			else if(numeroArmamentoSelecionado == 2) {
				arrayArmamentosPosicionados[1] = 1;
				hidroAviao2Posicionado = true;
	        }
			else if(numeroArmamentoSelecionado == 3) {
				arrayArmamentosPosicionados[2] = 1;
				hidroAviao3Posicionado = true;
	        }
			else if(numeroArmamentoSelecionado == 4) {
				arrayArmamentosPosicionados[3] = 1;
				hidroAviao4Posicionado = true;
	        }
			else if(numeroArmamentoSelecionado == 5) {
				arrayArmamentosPosicionados[4] = 1;
				hidroAviao5Posicionado = true;
	        }
		}
		else if(nomeArmamentoSelecionado.equals("destroyer")) {
			if(numRotação == 1) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 3;
				matrizArmamentos[retornoClick[1]][retornoClick[0] + 1] = 3;
			}
			else if(numRotação == 2) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 3;
				matrizArmamentos[retornoClick[1] + 1][retornoClick[0]] = 3;
			}
			else if(numRotação == 3) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 3;
				matrizArmamentos[retornoClick[1]][retornoClick[0] - 1] = 3;
			}
			else if(numRotação == 4) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 3;
				matrizArmamentos[retornoClick[1] - 1][retornoClick[0]] = 3;	
			}
			
			if(numeroArmamentoSelecionado == 1) {
				arrayArmamentosPosicionados[9] = 1;
				destroyer1Posicionado = true;	
			}
			else if(numeroArmamentoSelecionado == 2) {
				arrayArmamentosPosicionados[10] = 1;
				destroyer2Posicionado = true;
	        }
			else if(numeroArmamentoSelecionado == 3) {
				arrayArmamentosPosicionados[11] = 1;
				destroyer3Posicionado = true;
	        }
        }
		else if(nomeArmamentoSelecionado.equals("cruzador")) {
			if(numRotação == 1) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 4;
				matrizArmamentos[retornoClick[1]][retornoClick[0] + 1] = 4;
				matrizArmamentos[retornoClick[1]][retornoClick[0] + 2] = 4;
				matrizArmamentos[retornoClick[1]][retornoClick[0] + 3] = 4;
			}
			else if(numRotação == 2) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 4;
				matrizArmamentos[retornoClick[1] + 1][retornoClick[0]] = 4;
				matrizArmamentos[retornoClick[1] + 2][retornoClick[0]] = 4;
				matrizArmamentos[retornoClick[1] + 3][retornoClick[0]] = 4;
			}
			else if(numRotação == 3) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 4;
				matrizArmamentos[retornoClick[1]][retornoClick[0] - 1] = 4;
				matrizArmamentos[retornoClick[1]][retornoClick[0] - 2] = 4;
				matrizArmamentos[retornoClick[1]][retornoClick[0] - 3] = 4;
			}
			else if(numRotação == 4) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 4;
				matrizArmamentos[retornoClick[1] - 1][retornoClick[0]] = 4;
				matrizArmamentos[retornoClick[1] - 2][retornoClick[0]] = 4;
				matrizArmamentos[retornoClick[1] - 3][retornoClick[0]] = 4;
			}
			
			if(numeroArmamentoSelecionado == 1) {
				arrayArmamentosPosicionados[12] = 1;
				cruzador1Posicionado = true;
			}
			else if(numeroArmamentoSelecionado == 2) {
				arrayArmamentosPosicionados[13] = 1;
				cruzador2Posicionado = true;
	        }
        }
		else if(nomeArmamentoSelecionado.equals("couracado")) {
			if(numRotação == 1) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 5;
				matrizArmamentos[retornoClick[1]][retornoClick[0] + 1] = 5;
				matrizArmamentos[retornoClick[1]][retornoClick[0] + 2] = 5;
				matrizArmamentos[retornoClick[1]][retornoClick[0] + 3] = 5;
				matrizArmamentos[retornoClick[1]][retornoClick[0] + 4] = 5;
			}
			else if(numRotação == 2) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 5;
				matrizArmamentos[retornoClick[1] + 1][retornoClick[0]] = 5;
				matrizArmamentos[retornoClick[1] + 2][retornoClick[0]] = 5;
				matrizArmamentos[retornoClick[1] + 3][retornoClick[0]] = 5;
				matrizArmamentos[retornoClick[1] + 4][retornoClick[0]] = 5;
			}
			else if(numRotação == 3) {
				matrizArmamentos[retornoClick[1]][retornoClick[0] + 5] = 5;
				matrizArmamentos[retornoClick[1]][retornoClick[0] - 1 + 5] = 5;
				matrizArmamentos[retornoClick[1]][retornoClick[0] - 2 + 5] = 5;
				matrizArmamentos[retornoClick[1]][retornoClick[0] - 3 + 5] = 5;
				matrizArmamentos[retornoClick[1]][retornoClick[0] - 4 + 5] = 5;
			}
			else if(numRotação == 4) {
				matrizArmamentos[retornoClick[1]][retornoClick[0]] = 5;
				matrizArmamentos[retornoClick[1] - 1][retornoClick[0]] = 5;
				matrizArmamentos[retornoClick[1] - 2][retornoClick[0]] = 5;
				matrizArmamentos[retornoClick[1] - 3][retornoClick[0]] = 5;
				matrizArmamentos[retornoClick[1] - 4][retornoClick[0]] = 5;
			}
			
			arrayArmamentosPosicionados[14] = 1;
			couracado1Posicionado = true;
        }
	}
	
	private void verificarClick(Graphics2D g2d) {
		boolean selecionarArmamentoTemp = false;
		
		if(mouseEsquerdo) {
			if(hidroAviao1Posicionado == false && confirmarPosicionamento == false) {
				if(x >= 100 && x <= 120 && y >= 100 && y <= 120 || x >= 80 && x <= 100 && y >= 120 && y <= 140 || x >= 120 && x <= 140 && y >= 120 && y <= 140) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "hidroaviao";
					numeroArmamentoSelecionado = 1;
				}
			}
			
			if (hidroAviao2Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 180 && x <= 200 && y >= 100 && y <= 120 || x >= 160 && x <= 180 && y >= 120 && y <= 140 || x >= 200 && x <= 220 && y >= 120 && y <= 140) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "hidroaviao";
					numeroArmamentoSelecionado = 2;
		        }
			}
			
			if (hidroAviao3Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 260 && x <= 280 && y >= 100 && y <= 120 || x >= 240 && x <= 260 && y >= 120 && y <= 140 || x >= 280 && x <= 300 && y >= 120 && y <= 140) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "hidroaviao";
					numeroArmamentoSelecionado = 3;
		        }
			}
			
			if (hidroAviao4Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 340 && x <= 360 && y >= 100 && y <= 120 || x >= 320 && x <= 340 && y >= 120 && y <= 140 || x >= 360 && x <= 380 && y >= 120 && y <= 140) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "hidroaviao";
					numeroArmamentoSelecionado = 4;
		        }
			}
			
			if (hidroAviao5Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 420 && x <= 440 && y >= 100 && y <= 120 || x >= 400 && x <= 420 && y >= 120 && y <= 140 || x >= 440 && x <= 460 && y >= 120 && y <= 140) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "hidroaviao";
					numeroArmamentoSelecionado = 5;
		        }
			}
			
			if (submarino1Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 80 && x <= 100 && y >= 180 && y <= 200) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "submarino";
					numeroArmamentoSelecionado = 1;
		        }
			}
			
			if (submarino2Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 120 && x <= 140 && y >= 180 && y <= 200) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "submarino";
					numeroArmamentoSelecionado = 2;
		        }
			}
			
			if (submarino3Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 160 && x <= 180 && y >= 180 && y <= 200) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "submarino";
					numeroArmamentoSelecionado = 3;
		        }
			}
			
			if (submarino4Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 200 && x <= 220 && y >= 180 && y <= 200) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "submarino";
					numeroArmamentoSelecionado = 4;
		        }
			}
			
			if (destroyer1Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 80 && x <= 120 && y >= 240 && y <= 260) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "destroyer";
					numeroArmamentoSelecionado = 1;
		        }
			}
			
			if (destroyer2Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 140 && x <= 180 && y >= 240 && y <= 260) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "destroyer";
					numeroArmamentoSelecionado = 2;
		        }
			}
			
			if (destroyer3Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 200 && x <= 240 && y >= 240 && y <= 260) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "destroyer";
					numeroArmamentoSelecionado = 3;
		        }
			}
			
			if (cruzador1Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 80 && x <= 160 && y >= 300 && y <= 320) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "cruzador";
					numeroArmamentoSelecionado = 1;
		        }
			}
			
			if (cruzador2Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 180 && x <= 260 && y >= 300 && y <= 320) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "cruzador";
					numeroArmamentoSelecionado = 2;
		        }
			}
			
			if (couracado1Posicionado == false && confirmarPosicionamento == false) {
				if (x >= 80 && x <= 180 && y >= 360 && y <= 380) {
					selecionarArmamentoTemp = true;
					booleanArmamentoSelecionado = true;
					nomeArmamentoSelecionado = "couracado";
					numeroArmamentoSelecionado = 1;
		        }
			}
			
			retornoClick = Control.getController().ConverteCoordenadaPosicionarArmamentos(x, y);
			
			// Armamento estava selecionado mas click foi fora da matriz e des-selecionou o armamento
			if(selecionarArmamentoTemp == false && booleanArmamentoSelecionado == true && retornoClick[0] == -1 && retornoClick[1] == -1 && confirmarPosicionamento == false) {
				booleanArmamentoSelecionado = false;
				nomeArmamentoSelecionado = "";
				numeroArmamentoSelecionado = -1;
			}
			
			if (booleanArmamentoSelecionado && retornoClick[0] >= 0 && retornoClick[0] <= 15 && retornoClick[1] >= 0 && retornoClick[1] <= 15) {
				int verificaPosicaoNovaPosicao = -1;
				char letraLinha = (char) ('A' + retornoClick[1]);
		        int numeroColuna = retornoClick[0] + 1;
		        coordenada =  "" + letraLinha + numeroColuna;
				
				if (nomeArmamentoSelecionado == "hidroaviao") {
					verificaPosicaoNovaPosicao = Control.getController().VerificaPosicao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Norte-Sul", coordenada);
					numRotaçãoHidroAviao = 1;
				}
				else {
					verificaPosicaoNovaPosicao = Control.getController().VerificaPosicao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Oeste-Leste", coordenada);
					numRotaçãoGeral = 1;
				}
				
				confirmarPosicionamento = true;
				
				if (verificaPosicaoNovaPosicao == 0) {
					clickSalvoAteInserir = retornoClick;
					
					limparMatrizArmamentosTemp();
					
					if (nomeArmamentoSelecionado == "hidroaviao") {
						inserirTempArmamento(retornoClick, true, numRotaçãoHidroAviao);
					}
					else {
						inserirTempArmamento(retornoClick, true, numRotaçãoGeral);
					}
					
					existeErroParaConfirmarPosicionamento = false;
				}
				else {
					limparMatrizArmamentosTemp();
					
					if (nomeArmamentoSelecionado == "hidroaviao") {
						inserirTempArmamento(retornoClick, false, numRotaçãoHidroAviao);
					}
					else {
						inserirTempArmamento(retornoClick, false, numRotaçãoGeral);
					}
					
					existeErroParaConfirmarPosicionamento = true;
				}
	        }
		}
		else if(mouseDireito) {
			if(confirmarPosicionamento) {
				int verificaPosicaoNovaPosicao = -1;
				
				if (nomeArmamentoSelecionado == "hidroaviao") {
					numRotaçãoHidroAviao += 1;
					if(numRotaçãoHidroAviao == 5) {
						numRotaçãoHidroAviao = 1;
					}
					
					if(numRotaçãoHidroAviao == 1) {
						verificaPosicaoNovaPosicao = Control.getController().VerificaPosicao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Norte-Sul", coordenada);
					}
					else if(numRotaçãoHidroAviao == 2) {
						verificaPosicaoNovaPosicao = Control.getController().VerificaPosicao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Leste-Oeste", coordenada);
					}
					else if(numRotaçãoHidroAviao == 3) {
						verificaPosicaoNovaPosicao = Control.getController().VerificaPosicao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Sul-Norte", coordenada);
					}
					else if(numRotaçãoHidroAviao == 4) {
						verificaPosicaoNovaPosicao = Control.getController().VerificaPosicao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Oeste-Leste", coordenada);	
					}
				}
				else {
					numRotaçãoGeral += 1;
					if(numRotaçãoGeral == 5) {
						numRotaçãoGeral = 1;
					}
					
					if(numRotaçãoGeral == 1) {
						verificaPosicaoNovaPosicao = Control.getController().VerificaPosicao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Oeste-Leste", coordenada);
					}
					else if(numRotaçãoGeral == 2) {
						verificaPosicaoNovaPosicao = Control.getController().VerificaPosicao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Norte-Sul", coordenada);
					}
					else if(numRotaçãoGeral == 3) {
						verificaPosicaoNovaPosicao = Control.getController().VerificaPosicao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Leste-Oeste", coordenada);
					}
					else if(numRotaçãoGeral == 4) {
						verificaPosicaoNovaPosicao = Control.getController().VerificaPosicao(nomeArmamentoSelecionado, numeroArmamentoSelecionado, "Sul-Norte", coordenada);	
					}
				}
				
				if (verificaPosicaoNovaPosicao == 0) {
					confirmarPosicionamento = true;
					
					limparMatrizArmamentosTemp();
					clickSalvoAteInserir = retornoClick;
					
					if (nomeArmamentoSelecionado == "hidroaviao") {
						inserirTempArmamento(retornoClick, true, numRotaçãoHidroAviao);
					}
					else {
						inserirTempArmamento(retornoClick, true, numRotaçãoGeral);
					}
					
					existeErroParaConfirmarPosicionamento = false;
				}
				else {
					limparMatrizArmamentosTemp();
					
					if (nomeArmamentoSelecionado == "hidroaviao") {
						inserirTempArmamento(retornoClick, false, numRotaçãoHidroAviao);
					}
					else {
						inserirTempArmamento(retornoClick, false, numRotaçãoGeral);
					}
					
					existeErroParaConfirmarPosicionamento = true;
				}
			}
		}
		
		mouseEsquerdo = false;
		mouseDireito = false;
	}
	
	private void verificarTudoPosicionado(Graphics g) {
		if(hidroAviao1Posicionado && hidroAviao2Posicionado && hidroAviao3Posicionado && 
				hidroAviao4Posicionado && hidroAviao5Posicionado && submarino1Posicionado && 
				submarino2Posicionado && submarino3Posicionado && submarino4Posicionado && 
				destroyer1Posicionado && destroyer2Posicionado && destroyer3Posicionado && 
				cruzador1Posicionado && cruzador2Posicionado && couracado1Posicionado) {
			g.drawString("Armamentos selecionados", 435, 470);
			
			if(armamentosJogador1Posicionado == false && armamentosJogador2Posicionado == false) {
				botaoProximoJogador.setBounds(420, 490, 160, 40);
				add(botaoProximoJogador);
			}
			else if(armamentosJogador1Posicionado == true && armamentosJogador2Posicionado == false) {
				armamentosJogador2Posicionado = true;
				
				botaoComecarJogo.setBounds(420, 490, 160, 40);
				add(botaoComecarJogo);
			}
		}
		else {
			g.drawString("Selecione os armamentos", 435, 470);
		}
	}
}