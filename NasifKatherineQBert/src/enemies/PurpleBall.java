package enemies;

import enemyBehaviours.DoNothingOnCubeBehaviour;
import enemyBehaviours.FreezeCubeBehaviour;
import enemyBehaviours.KillCollisionBehaviour;
import gameEntities.Enemy;
import gameEntities.Player;
import gameEntities.Pyramid;
import mainGame.EntityModel;

public class PurpleBall extends Enemy {
	public PurpleBall(int x, int y, Pyramid pyramid, Player player, EntityModel model) {
		super (x, y, 10 , 4, "PurpleBall - Bottom Left.png" , "PurpleBall - Bottom Right.png" , 
				"PurpleBall - Top Left.png" , "PurpleBall - Top Right.png" , pyramid, player, 
				new DoNothingOnCubeBehaviour(), model, false);
		this.setCollisionBehaviour(new KillCollisionBehaviour());
	}
}
