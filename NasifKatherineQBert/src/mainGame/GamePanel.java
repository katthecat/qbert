package mainGame;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;

import javax.swing.*;

import gameEntities.GameEntity;
import gameEntities.Player;
import gameEntities.Pyramid;

public class GamePanel extends JPanel {
	private EntityModel entityModel;
	private boolean gameIsRunning;
	private String gameState;
	private Image instructions;
	private int finalScore;
	
	public GamePanel() {
		this.setBackground(Color.BLACK);
		gameState = "playing";
		instructions = new ImageIcon("Instructions.png").getImage();
	}
	
	public void setEntityModel(EntityModel e) {
		this.entityModel = e;
	}
	
    protected void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		
    		if (gameState.equals("loading")) {
	    		g.setColor(Color.yellow);
	    		g.setFont(new Font("TimesRoman" , Font.BOLD, 30));
    			g.drawString("Loading...", 10, 10);
    			
    		} else if (gameState.equals("playing")) {
    		
	    		g.setColor(Color.yellow);
	    		g.setFont(new Font("TimesRoman" , Font.BOLD, 30));
	    		g.drawString("Q BERT SPEED", 20, 40);
	    		g.setFont(new Font("TimesRoman" , Font.PLAIN, 14));
	    		g.drawString("Tips for a successful Qbert:", 20, 150);
	    		g.drawString("1. Avoid the enemies!", 20, 165);
	    		g.drawString("2. Catch the green guys!", 20, 180);
	    		g.drawString("3. Light up the pyramid!", 20, 195);
	    		
	    		g.drawImage(instructions, 400, 25, 250, 200, null);
	    		
	    		if (entityModel.getPlayerFalling()) {
	    			entityModel.getPlayer().draw(g);
	        		entityModel.getPyramid().drawPyramid(g);
	    		} else {
	        		entityModel.getPyramid().drawPyramid(g);
	        		if (entityModel.getPlayer() != null) {
	        			entityModel.getPlayer().draw(g);
	        		}
	
	    		}
	
	    		for (GameEntity gameEntity : entityModel.getEntities()) {
	    			if (!(gameEntity instanceof Player)) {
	        			gameEntity.draw(g);
	
	    			}
	    		}
	
	    		g.setColor(Color.YELLOW);
	    		g.setFont(new Font("TimesRoman" , Font.PLAIN , 14));
	    		g.drawString("Score: " + entityModel.getScoreBoard().getScore(), 20, 75);
	    		g.drawString("Lives: " + entityModel.getScoreBoard().getLives() , 20, 90);
	    		g.drawString("Level: " + entityModel.getCurrentLevel(), 20, 105);
	    		
    		} else if (gameState.equals("ending")) {
    			showEndGame(g);
    		}
    		
    }
    
    public void showEndGame(Graphics g) {
    		g.setFont(new Font("TimesRoman", Font.BOLD, 50));
    		g.setColor(Color.red);
    		g.drawString("GAME OVER", 100, 100);
    		g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
    		g.setColor(Color.yellow);
    		g.drawString("Your score: " + finalScore, 100, 200);
    }
    
    public void changeState(String x) {
    		this.gameState = x;
    }

	public void setState(String state) {
		this.gameState = state;
		if (state.equals("ending")) {
    			finalScore = entityModel.getScoreBoard().getScore();
		}
	}

	public String getState() {
		return this.gameState;
	}
    	
}
