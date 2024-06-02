package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Control;

public class CriaPartida extends JFrame{
	public final int LARG_DEFAULT = 400;
	public final int ALT_DEFAULT = 400;
	
	JPanel Painel = new JPanel();
	JLabel Jogador1 = new JLabel("Jogador 1: ");
	JLabel Jogador2 = new JLabel("Jogador 2: ");
	JTextField inputJ1 = new JTextField("Jogador 1");
	JTextField inputJ2 = new JTextField("Jogador 2");
	JButton comecar = new JButton("Começar");
	JButton voltar = new JButton("Voltar");
	
	String nomeJogador1;
    String nomeJogador2;
    
    // Singleton CriaPartida
    private static CriaPartida criaPartida = null;
    public static CriaPartida singleCriaPartida() {
        if (criaPartida == null) 
            criaPartida = new CriaPartida(true);
        return criaPartida;
    }
	
	private CriaPartida(boolean visibilidade)
	{
		setTitle("Seleção de Jogadores");
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl / 2 - LARG_DEFAULT / 2;
		int y = sa / 2 - ALT_DEFAULT / 2; 
		setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		
		Painel.setLayout(null);
		
		int margem = 20;
        int largComponente = 100;
        int altComponente = 30;
        int espacamentoVertical = 10;
        
        voltar.setBounds(margem, margem, 70, altComponente);
        Painel.add(voltar);
		
        int posX = ((LARG_DEFAULT - largComponente) / 2) - 5;
        int posY = margem + altComponente + espacamentoVertical;
        
        Jogador1.setBounds(posX, posY, largComponente, altComponente);
        Painel.add(Jogador1);

        posY += altComponente + espacamentoVertical;
        inputJ1.setBounds(posX, posY, largComponente, altComponente);
        Painel.add(inputJ1);

        posY += altComponente + espacamentoVertical;
        Jogador2.setBounds(posX, posY, largComponente, altComponente);
        Painel.add(Jogador2);

        posY += altComponente + espacamentoVertical;
        inputJ2.setBounds(posX, posY, largComponente, altComponente);
        Painel.add(inputJ2);

        posY += altComponente + espacamentoVertical;
        comecar.setBounds(posX, posY, largComponente, altComponente);
        Painel.add(comecar);
        
		getContentPane().add(Painel);
		
        voltar.addActionListener(new ActionListener() { //Classe anônima
            public void actionPerformed(ActionEvent e) {
                Control.getController().irParaTelaInicial(criaPartida);
            }
        });
        
        comecar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nomeJogador1 = inputJ1.getText();
                nomeJogador2 = inputJ2.getText();
                Control.getController().irParaPosicionarArmamento(criaPartida, nomeJogador1, nomeJogador2);
            }
        });
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(visibilidade);
	}
}
