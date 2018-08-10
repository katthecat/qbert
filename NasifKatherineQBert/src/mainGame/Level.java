package mainGame;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import gameEntities.ScoreBoard;

public class Level {
    private EntityModel model;
    private GamePanel gamePanel;
	

	public Level(int level, GamePanel gamePanel, ScoreBoard scoreBoard) {
		this.gamePanel = gamePanel;
		this.model = new EntityModel(gamePanel, level, scoreBoard);
	}
	
	
	public boolean levelComplete() {
		return model.levelComplete();
	}
	
	public boolean gameOver() {
		return model.gameOver();
	}
	
	

	public EntityModel getModel() {
		return this.model;
	}

	public void setPoints(int points) {
		// TODO Auto-generated method stub
		model.setPoints(points);
	}

	public int getPoints() {
		return model.getPoints();
	}

	public void dismantle() {
		// TODO Auto-generated method stub
		this.model.dismantle();
		this.model = null;
	}


	public void setUp() {
		this.model.getPyramid().lightAllUp();
	}
	
}
