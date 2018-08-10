package enemies;

import enemyBehaviours.ChangeCubeBackToOriginalColor;
import enemyBehaviours.DoNothingOnCubeBehaviour;
import enemyBehaviours.KillCollisionBehaviour;
import gameEntities.Enemy;
import gameEntities.Player;
import gameEntities.Pyramid;
import mainGame.EntityModel;

public class Sam extends Enemy {
	public Sam(int x, int y, Pyramid pyramid, Player player, EntityModel model) {
		super (x, y, 22 , 4, "Sam - Bottom Left.png" , "Sam - Bottom Right.png" , 
				"Sam - Top Left.png" , "Sam - Top Right.png" , pyramid, player, 
				new ChangeCubeBackToOriginalColor(), model, false);
		this.setCollisionBehaviour(new KillCollisionBehaviour());
		this.setJumpSpeed(750);
	}
}
