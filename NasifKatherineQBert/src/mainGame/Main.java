package mainGame;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
        JFrame f = new JFrame();
        Qbert game = new Qbert();
        f.setJMenuBar(game.getMenuBar());
        
        f.add(game.getGamePanel());
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
        f.setSize(700,700);
                
  	  	game.start();
	  	game.setState("playing");
	  	
	  	while (true) {
	  		if (game.getState().equals("playing")) {
			  	while (true) {
					while (!(game.gameOver())) {
						        if (game.levelComplete()) {
						        		game.nextLevel();
						        }
					 }
					break;
			  	}
				game.setState("ending");
	  		}
	  		game.showEndGame();
	  	}
		    
    }
}
