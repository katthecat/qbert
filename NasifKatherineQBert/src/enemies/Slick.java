package enemies;

import enemyBehaviours.ChangeCubeBackToOriginalColor;
import enemyBehaviours.DoNothingOnCubeBehaviour;
import enemyBehaviours.KillCollisionBehaviour;
import gameEntities.Enemy;
import gameEntities.Player;
import gameEntities.Pyramid;
import mainGame.EntityModel;

public class Slick extends Enemy {
	public Slick(int x, int y, Pyramid pyramid, Player player, EntityModel model) {
		super (x, y, 15 , 10, "Slick - Bottom Left.png" , "Slick - Bottom Right.png" , 
				"Slick - Upper Left.png" , "Slick - Upper Right.png" , pyramid, player, 
				new ChangeCubeBackToOriginalColor(), model, false);
		this.setCollisionBehaviour(new KillCollisionBehaviour());
		this.setJumpSpeed(750);
	}
}
