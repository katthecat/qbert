package mainGame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.color.*;

public class MenuBar extends JMenuBar {
	private JButton restart;
	private JButton close;

	public MenuBar() {
		this.setBackground(Color.RED);
		this.setForeground(Color.WHITE);
		this.setFont(new Font("TimesRoman", Font.ITALIC, 16));
		this.setLayout(new FlowLayout());
		restart = new JButton("Restart");
		close = new JButton("Close");
		
		
		this.add(new JLabel("Q-bert!"));
		this.add(restart);
		this.add(close);
	}
	
	public JButton getRestartButton() {
		return this.restart;
	}
	
	public JButton getCloseButton() {
		return this.close;
	}
}
