package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JanelaInicial extends JFrame{
	public final int LARG_DEFAULT = 400;
	public final int ALT_DEFAULT = 300;
	JPanel PainelInicio = new JPanel();
	JButton NovaPartida = new JButton("Nova Partida");
	JButton CarregaPartida = new JButton("Carregar Partida");
	JFileChooser fileChooser = new JFileChooser();
	JLabel label = new JLabel("Bem Vindo ao Batalha Naval!");
	
	
	
	public JanelaInicial() {
		setVisible(true);
		setTitle("Batalha Naval");
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2; 
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		
		
		PainelInicio.setLayout(new BoxLayout(PainelInicio, BoxLayout.Y_AXIS));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		NovaPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
		NovaPartida.setAlignmentY(Component.CENTER_ALIGNMENT);
		CarregaPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
		CarregaPartida.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		PainelInicio.add(label);
		PainelInicio.add(Box.createRigidArea(new Dimension(0 ,10)));
		PainelInicio.add(NovaPartida);
		PainelInicio.add(Box.createRigidArea(new Dimension(0 ,5)));
		PainelInicio.add(CarregaPartida);
		getContentPane().add(PainelInicio);
		CarregaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
			}
		});
		PainelInicio.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
