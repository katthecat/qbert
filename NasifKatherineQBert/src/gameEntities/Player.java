package gameEntities;

import java.awt.Image;

import javax.swing.ImageIcon;

import enemyBehaviours.FallOffPyramidBehaviour;
import enemyBehaviours.LightUpCubeBehaviour;
import enemyBehaviours.PlayerCollisionBehaviour;
import mainGame.EntityModel;

public class Player extends Character {
	private Image liftImage;
	public Player(int x, int y, EntityModel model) {
		super(x, y , 21 , 4, "QBert - Bottom Right.png" , "QBert - Bottom Left.png" , "QBert - Top Left.png" ,  
				"QBert - Top Right.png", new LightUpCubeBehaviour(), new FallOffPyramidBehaviour(), model);
		this.setCollisionBehaviour(new PlayerCollisionBehaviour());
		this.setCollisonImage("QBert - Swearing.png");
		this.liftImage = new ImageIcon("Qbert - Whee.png").getImage();
		
	}
	
	public void kill() {
		this.getModel().killPlayer();
	}

	public Image getLiftImage() {
		// TODO Auto-generated method stub
		return this.liftImage;
	}
}
