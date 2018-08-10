package enemies;

import enemyBehaviours.DoNothingOnCubeBehaviour;
import enemyBehaviours.KillCollisionBehaviour;
import gameEntities.Enemy;
import gameEntities.Player;
import gameEntities.Pyramid;
import mainGame.EntityModel;

public class Coily extends Enemy {
	public Coily(int x, int y, Pyramid pyramid, Player player, EntityModel model) {
		super (x, y, 30 , 4, "Snake - Bottom Left.png" , "Snake - Bottom Right.png" , 
				"Snake - Top Left.png" , "Snake - Top Right.png" , pyramid, player, 
				new DoNothingOnCubeBehaviour(), model, true);
		this.setCollisionBehaviour(new KillCollisionBehaviour());
	}
}
