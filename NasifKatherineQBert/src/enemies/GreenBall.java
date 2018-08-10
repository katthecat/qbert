package enemies;

import enemyBehaviours.FreezeCubeBehaviour;
import enemyBehaviours.KillCollisionBehaviour;
import gameEntities.Enemy;
import gameEntities.Player;
import gameEntities.Pyramid;
import mainGame.EntityModel;

public class GreenBall extends Enemy {
	public GreenBall(int x, int y, Pyramid pyramid, Player player, EntityModel model) {
		super (x, y, 10 , 4, "Green Ball.png" , "Green Ball.png" , 
				"Green Ball.png" , "Green Ball.png" , pyramid, player, 
				new FreezeCubeBehaviour(), model, false);
		this.setCollisionBehaviour(new KillCollisionBehaviour());
		this.setJumpSpeed(500);
	}
}
