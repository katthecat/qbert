package enemies;

import enemyBehaviours.DoNothingOnCubeBehaviour;
import enemyBehaviours.KillCollisionBehaviour;
import gameEntities.Enemy;
import gameEntities.Player;
import gameEntities.Pyramid;
import mainGame.EntityModel;

public class RedBall extends Enemy {
	public RedBall(int x, int y, Pyramid pyramid, Player player, EntityModel model) {
		super (x, y, 10 , 4, "RedBall.png" , "RedBall.png" , 
				"RedBall.png" , "RedBall.png" , pyramid, player, 
				new DoNothingOnCubeBehaviour(), model, false);
		this.setCollisionBehaviour(new KillCollisionBehaviour());
	}
}
