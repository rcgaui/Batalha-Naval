package View;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JanelaInicial extends JFrame{
	public final int LARG_DEFAULT = 400;
	public final int ALT_DEFAULT = 300;
	
	JPanel PainelInicio = new JPanel();
	JLabel label = new JLabel("Bem Vindo ao Batalha Naval!");
	JButton NovaPartida = new JButton("Nova Partida");
	JButton CarregaPartida = new JButton("Carregar Partida");
	JFileChooser fileChooser = new JFileChooser();
	
	// Singleton JanelaInicial
	private static JanelaInicial instance = null;
    public static JanelaInicial getInstance() {
        if (instance == null) {
            instance = new JanelaInicial();
        }
        return instance;
    }
    
    // Singleton CriaPartida
    private CriaPartida criaPartida = null;
    private void mostrarCriaPartida() {
        if (criaPartida == null) {
            criaPartida = new CriaPartida();
        } else {
            criaPartida.setVisible(true);
        }
    }
	
	public JanelaInicial() {
		setTitle("Batalha Naval");
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl / 2 - LARG_DEFAULT / 2;
		int y = sa / 2 - ALT_DEFAULT / 2; 
		setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		
		PainelInicio.setLayout(null);
		
		int margem = 20;
        int largComponente = 165;
        int altComponente = 30;
        int espacamentoVertical = 10;
        
        int posX = ((LARG_DEFAULT - largComponente) / 2) - 5;
        int posY = margem + altComponente + espacamentoVertical;
        
        label.setBounds(posX, posY, largComponente, altComponente);
        PainelInicio.add(label);

        posY += altComponente + espacamentoVertical;
        NovaPartida.setBounds(posX, posY, largComponente, altComponente);
        PainelInicio.add(NovaPartida);

        posY += altComponente + espacamentoVertical;
        CarregaPartida.setBounds(posX, posY, largComponente, altComponente);
        PainelInicio.add(CarregaPartida);
		
		getContentPane().add(PainelInicio);

		NovaPartida.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	dispose();
		    	
		        mostrarCriaPartida();
		    }
		});
		
		CarregaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
