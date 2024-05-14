package View;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public abstract class Mouse implements MouseListener{
	Component c;
	
	public Mouse(Component x) {
		c = x;
	}
	
	public void mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDialog(c, "Click Efetuado");
	}
}
