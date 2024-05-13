package View;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CriaPartida extends JFrame{
	public final int LARG_DEFAULT = 400;
	public final int ALT_DEFAULT = 400;
	JPanel Painel = new JPanel();
	JPanel Inputs = new JPanel();
	JLabel Jogador1 = new JLabel("Jogador 1: ");
	JLabel Jogador2 = new JLabel("Jogador 2: ");
	JTextField inputJ1 = new JTextField("Jogador 1");
	JTextField inputJ2 = new JTextField("Jogador 2");
	JButton comecar = new JButton("Começar");
	
	public CriaPartida()
	{
		setTitle("Seleção de Jogadores");
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2; 
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		
		Inputs.setLayout(new BoxLayout(Inputs, BoxLayout.Y_AXIS));
		Inputs.add(Jogador1);
		Inputs.add(inputJ1);

		Inputs.add(Box.createRigidArea(new Dimension(0 ,10)));
		Inputs.add(Jogador2);
		Inputs.add(inputJ2);
		Inputs.add(Box.createRigidArea(new Dimension(0 ,10)));
		Inputs.add(comecar);
		Painel.add(Inputs);
		getContentPane().add(Painel);
		Painel.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		setVisible(true);
	}
}
