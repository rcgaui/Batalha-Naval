package View;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public abstract class ESC implements KeyListener {
	Component c;
	
	public ESC (Component x) {
		c = x;
	}
	
	public void keyPressed(KeyEvent e) {
		JOptionPane.showMessageDialog(c, "ESC apertado");
    }
}