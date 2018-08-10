package mainGame;
import java.awt.*;

import java.awt.event.*;
import java.io.File;
import java.sql.Time;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import gameEntities.Player;
import gameEntities.ScoreBoard;

public class Qbert {
	private Level currentLevel;
	private GamePanel gamePanel;
	private Clip clip;
	private int levelNumber;
	private ScoreBoard scoreBoard;
	private QBertListener listener;
	private boolean shouldRestart;
	private MenuBar menu;
	
	public Qbert() {
		this.gamePanel = new GamePanel();
		
        menu = new MenuBar();
		
		this.levelNumber = 0;
		this.scoreBoard = new ScoreBoard();
		scoreBoard.getScore();
		this.currentLevel = new Level(levelNumber, gamePanel, scoreBoard);
		listener = new QBertListener(this, currentLevel.getModel(), gamePanel , menu);
		this.listener.updateListenerData(currentLevel.getModel());
		gamePanel.setEntityModel(currentLevel.getModel());
		shouldRestart = false;
		
		playMusic("TheFatRat - Unity.wav");

	}

	public String getState() {
		return gamePanel.getState();
	}

	public JMenuBar getMenuBar() {
		return this.menu;
	}

	public void setState(String state) {
		gamePanel.setState(state);
	}
	
	public void showEndGame() {
		System.out.println("end!");
	   //this.setState("ending");	    
	}

	public void updateLevel() {
		this.currentLevel.dismantle();
		this.currentLevel = new Level(levelNumber, gamePanel, scoreBoard);
		if (listener != null) {
			this.listener.updateListenerData(currentLevel.getModel());
		}
	}
	
	public boolean gameOver() {
		return this.currentLevel.gameOver();			

	}
	
	public boolean levelComplete() {
		return this.currentLevel.levelComplete();
	}
	
	public void nextLevel() {
		levelNumber++;
		updateLevel();
		gamePanel.setEntityModel(currentLevel.getModel());
	}
	
	public GamePanel getGamePanel() {
		return this.gamePanel;
	}
	
	public void start() {
		setUpGame();
		this.setState("playing");
		gamePanel.requestFocus();
	}
	
	public void playMusic(String fileName) {
		// https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
		try {
			File soundFile = new File(fileName); // Open an audio input stream from a wave File
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip(); // Get a sound clip resource.
			clip.open(audioIn); // Open audio clip and load samples from the audio input stream.
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY);
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}
	
	public void setUpGame() {
		//playMusic("TheFatRat - Unity.wav");
		currentLevel.setUp();
	}

	public void restart() {
		this.start();
		this.setState("playing");
		levelNumber = 0;
		scoreBoard.setLives(scoreBoard.MAX_LIVES);
		scoreBoard.setPoints(0);
	}
	
	public boolean shouldRestart() {
		return shouldRestart;
	}

}
