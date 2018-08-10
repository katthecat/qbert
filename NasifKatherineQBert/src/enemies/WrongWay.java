package enemies;

import enemyBehaviours.DoNothingOnCubeBehaviour;
import enemyBehaviours.KillCollisionBehaviour;
import gameEntities.Enemy;
import gameEntities.Player;
import gameEntities.Pyramid;
import mainGame.EntityModel;

public class WrongWay extends Enemy {
	public WrongWay(int x, int y, Pyramid pyramid, Player player, EntityModel model) {
		super (x, y, 10 , 0, "Wrong Way - Bottom Left.png" , "Wrong Way - Bottom Right.png" , 
				"Wrong Way - Top Left.png" , "Wrong Way - Top Right.png" , pyramid, player, 
				new DoNothingOnCubeBehaviour(), model, true);
		this.setCollisionBehaviour(new KillCollisionBehaviour());
	}
}
