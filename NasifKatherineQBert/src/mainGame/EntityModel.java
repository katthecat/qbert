package mainGame;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import dataStructures.CollisionPair;
import enemies.Coily;
import enemies.GreenBall;
import enemies.PurpleBall;
import enemies.RedBall;
import enemies.Sam;
import enemies.Slick;
import enemies.WrongWay;
import gameEntities.Character;
import gameEntities.Cube;
import gameEntities.Enemy;
import gameEntities.GameEntity;
import gameEntities.Lift;
import gameEntities.Platform;
import gameEntities.Player;
import gameEntities.Pyramid;
import gameEntities.ScoreBoard;

public class EntityModel implements ActionListener {
	private ArrayList<GameEntity> gameEntities;
	private Pyramid pyramid;
	private Player player;
	private ScoreBoard scoreBoard;
	private GamePanel gamePanel;
	private Timer animationTimer;
	private EnemyGenerator enemyGenerator;
	private int currentLevel;
	private boolean playerIsFalling;
	private int possibleEnemies;
	
	public EntityModel(GamePanel gamePanel, int level, ScoreBoard scoreBoard) {
		pyramid = new Pyramid(250 , 0, level);
		gameEntities = new ArrayList<GameEntity>();
		player = new Player(pyramid.getFirstUnoccupiedCube().getX() , pyramid.getFirstUnoccupiedCube().getY() - pyramid.getFirstUnoccupiedCube().getHeight(), this);
		pyramid.addCharacterToPyramid(player, pyramid.getRowOfCube(pyramid.getFirstUnoccupiedCube()), pyramid.getColOfCube(pyramid.getFirstUnoccupiedCube()));
		((Cube) pyramid.getCubeAtIndex(2, 1)).lightUp();
		gameEntities.add(player);
		this.scoreBoard = scoreBoard;
		this.gamePanel = gamePanel;
		
		enemyGenerator = new EnemyGenerator(this, 5000);
		playerIsFalling = false;
		
		animationTimer = new Timer(50, this);
		animationTimer.start();
		
		currentLevel = level;
		
		possibleEnemies =  7;
	}
	

	public void generateEnemy() {
		// TODO Auto-generated method stub
		int i = (int)(Math.random()*possibleEnemies);
		Cube cubeToAddEnemyTo = pyramid.getFirstUnoccupiedCube();
		Enemy enemyToAdd;
		
		if (i == 0) {
			enemyToAdd = new Sam(cubeToAddEnemyTo.getX() , cubeToAddEnemyTo.getY() - 20 , pyramid, player, this);
		} else if (i== 1) {
			enemyToAdd = new WrongWay(cubeToAddEnemyTo.getX() , cubeToAddEnemyTo.getY() - 20 , pyramid, player, this);
		} else if (i == 2) {
			enemyToAdd = new Slick(cubeToAddEnemyTo.getX() , cubeToAddEnemyTo.getY() - 20 , pyramid, player, this);
		} else if (i==3) {
			enemyToAdd = new GreenBall(cubeToAddEnemyTo.getX() , cubeToAddEnemyTo.getY() - 20 , pyramid, player, this);
		} else if (i== 4) {
			enemyToAdd = new PurpleBall(cubeToAddEnemyTo.getX() , cubeToAddEnemyTo.getY() - 20 , pyramid, player, this);
		} else if (i == 5){
			enemyToAdd = new RedBall(cubeToAddEnemyTo.getX() , cubeToAddEnemyTo.getY() - 20 , pyramid, player, this);
		} else {
			enemyToAdd = null; // don't add enemy
		}
		if (enemyToAdd != null) {
			pyramid.addCharacterToGivenCubeInPyramid(cubeToAddEnemyTo, enemyToAdd);
			gameEntities.add(enemyToAdd);
		}
		gamePanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) { // for animation purposes
		CollisionPair firstCollisionPair = pyramid.updateCollisions();
		if (firstCollisionPair != null) {// found collision
			firstCollisionPair.getLead().collideWith(firstCollisionPair.getFollower(), this);
		}
			
		gamePanel.repaint();
	}

	public void killPlayer() {
		try {
		removeCharacter(this.player);
		resetPlayer();
		player.setImage(player.getCollisionImage());
		this.removeAllEnemies();
		scoreBoard.subtractLife();
		} catch (NullPointerException e) {
			//
		}
	}
	
	public void resetPlayer() {
		player.setX(pyramid.getFirstUnoccupiedCube().getX());
		player.setY(pyramid.getFirstUnoccupiedCube().getY()  - pyramid.getFirstUnoccupiedCube().getHeight());
		gameEntities.add(player);
		pyramid.addCharacterToPyramid(player, pyramid.getRowOfCube(pyramid.getFirstUnoccupiedCube()), pyramid.getColOfCube(pyramid.getFirstUnoccupiedCube()));
	}
	
	public void resetPyramid() {
		pyramid.reset();
	}

	public ScoreBoard getScoreBoard() {
		return this.scoreBoard;
	}
	
	public JPanel getGamePanel() {
		return this.gamePanel;
	}

	public Pyramid getPyramid() {
		return this.pyramid;
	}
	
	public void removeCharacter(gameEntities.Character character) {
		this.pyramid.removeCharacter(character);
		this.gameEntities.remove(character);
		if (character instanceof Enemy) {
			((Enemy) character).dismantle();
		}
	}


	public ArrayList<GameEntity> getEntities() {
		// TODO Auto-generated method stub
		return this.gameEntities;
	}


	public void removeAllEnemies() {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < gameEntities.size() ; i++) {
			if (gameEntities.get(i) instanceof Enemy) {
				((Enemy) gameEntities.get(i)).dismantle();
				pyramid.removeCharacter((Character) gameEntities.get(i));
				gameEntities.remove(i);
				i--;
			}
		}
	}
	
	public boolean gameOver() {
		System.out.println("Lives: " + scoreBoard.getLives()); 
		if ((scoreBoard.getLives()) <= 0) {
			gamePanel.setState("ending");
			return true;
		}
		return false;
	}
	
	public boolean levelComplete() {
		if (pyramid.allLitUp()) {
			return true;
		}
		return false;
	}


	public void setPoints(int points) {
		scoreBoard.setPoints(points);
	}


	public int getPoints() {
		return scoreBoard.getScore();
	}


	public int getPlayerLives() {
		return scoreBoard.getLives();
	}


	public void makeViewPaintPyramidOnTopOfPlayer(boolean b) {
		playerIsFalling = b;
	}


	public boolean getPlayerFalling() {
		return playerIsFalling;
	}
	
	public Player getPlayer() {
		return this.player;
	}


	public void dismantle() {
		this.player = null;
		this.animationTimer.stop();
		for (GameEntity entity : gameEntities) {
			if (entity instanceof Enemy) {
				((Enemy) entity).dismantle();
			}
		}
		this.gameEntities = new ArrayList<GameEntity>();
	}

    public void stopLift(Character characterToRemove) {
	  	characterToRemove.getModel().makeViewPaintPyramidOnTopOfPlayer(false);
		removeCharacter(this.player);
		resetPlayer();
		//this.removeAllEnemies();
		gamePanel.repaint();
    } 

	public void performLift(Character character, Lift lift) throws InterruptedException {
    	  	Platform x = pyramid.liftMe(lift, character);
    	  	while (x != null) {
    	  		Thread.sleep(50);
    	  		x = pyramid.liftMe(lift, character);
    	  		if (x != null) {
    	  			gamePanel.repaint();
    	  		} else {
    	  			stopLift(character);
    	  		}
    	  	}
    	  	gamePanel.repaint();
    	  	
	}


	public void generateEnemyAt(Cube cubeAtIndexEnemySitsOn, Enemy e) {
		pyramid.addCharacterToGivenCubeInPyramid(cubeAtIndexEnemySitsOn, e);
		gameEntities.add(e);
		gamePanel.repaint();
		
	}


	public int getCurrentLevel() {
		return currentLevel;
	}
	
}
