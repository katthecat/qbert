package gameEntities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import enemyBehaviours.CollisionBehaviour;
import enemyBehaviours.FallOffPyramidBehaviour;
import enemyBehaviours.JumpingOnCubeBehaviour;
import enemyBehaviours.NeverJumpOffPyramidBehaviour;
import mainGame.EntityModel;

public abstract class Enemy extends Character implements ActionListener {
	private Timer timer;
	private Pyramid pyramid;
	private Player player;
	private boolean followsThePlayer;
	private int jumpSpeed;
	
	public Enemy(int x, int y, int height, int width, String bottomLeftImageName, String bottomRightImageName, 
			String upperLeftImageName , String upperRightImageName, Pyramid pyramid, Player player,  
			JumpingOnCubeBehaviour cubeBehaviour, EntityModel model, boolean followsThePlayer) {
		super(x, y, height, width,  bottomRightImageName,  bottomLeftImageName,  upperLeftImageName,  upperRightImageName, cubeBehaviour, new NeverJumpOffPyramidBehaviour(),  model);
		this.pyramid = pyramid;
		this.followsThePlayer = followsThePlayer;
		this.player = player;
		
		this.setJumpOffPyramidBehaviour(new FallOffPyramidBehaviour());
		
		jumpSpeed = 1500; // default
		timer = new Timer(jumpSpeed , this);
		timer.start();
	}
	
	public void jumpTowardCharacter(Character player) {
		// fill in here
		if (this.followsThePlayer) {
			this.faceCharacter(player);
		} else {
			this.faceRandomDownwardsDirection();
		}
		this.jump(pyramid);
	}
	
	public void faceCharacter(Character player) {
		if (player != null) {
			if (player.getX() < this.getX()) {
				this.makeFaceLeft();
			} else {
				this.makeFaceRight();
			}
			
			if (player.getY() > this.getY()) {
				this.makeFaceDown();
			} else {
				this.makeFaceUp();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.jumpTowardCharacter(player);
		
	}
	
	public void dismantle() {
		timer.stop();
	}
	
	public void setJumpSpeed(int mils) {
		timer.setDelay(mils);
	}
	
	
}
