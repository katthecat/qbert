package mainGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import gameEntities.Player;
import gameEntities.Pyramid;

public class QBertListener implements KeyListener, ActionListener {
	private Player player;
	private Pyramid pyramid;
	private JPanel view;
	private JButton restartButton;
	private JButton closeButton;
	private Qbert game;
	
	public QBertListener(Qbert game, EntityModel model, JPanel view, MenuBar menu) {
		this.game = game;
		this.player = model.getPlayer();
		this.pyramid = model.getPyramid();
		this.view = view;
		restartButton = menu.getRestartButton();
		closeButton = menu.getCloseButton();
		restartButton.addActionListener(this);
		closeButton.addActionListener(this);
		
		view.addKeyListener(this);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// do nothing
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// do nothing
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyChar() == 'q'
				|| arg0.getKeyChar() == 'w'
				|| arg0.getKeyChar() == 'a'
				|| arg0.getKeyChar() == 's') { // is an arrow key
			if (arg0.getKeyChar() == 'q') { // top left
				player.makeFaceUp();
				player.makeFaceLeft();
				player.setImage(player.getUpperLeftImage());
			} else if (arg0.getKeyChar() == 'w') { // top right
				player.makeFaceUp();
				player.makeFaceRight();
				player.setImage(player.getUpperRightImage());
			} else if (arg0.getKeyChar() == 'a') { // bottom left
				player.makeFaceLeft();
				player.makeFaceDown();
				player.setImage(player.getBottomLeftImage());
			} else if (arg0.getKeyChar() == 's') { // bottom right
				player.makeFaceRight();
				player.makeFaceDown();
				player.setImage(player.getBottomRightImage());
			}
			player.jump(pyramid);
		}
		view.repaint();
		view.requestFocus();
	}
	
	public void updateListenerData(EntityModel model) {
		this.player = model.getPlayer();
		this.pyramid = model.getPyramid();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(restartButton)) {
			game.restart();
		} else if (e.getSource().equals(closeButton)) {
			System.exit(0);
		}
	}


}
