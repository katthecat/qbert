package enemyBehaviours;

import enemies.GreenBall;
import enemies.Sam;
import enemies.Slick;
import gameEntities.Character;
import gameEntities.Enemy;
import mainGame.EntityModel;

public class PlayerCollisionBehaviour implements CollisionBehaviour {
	public void collide(Character character, EntityModel model) {
		// TODO Auto-generated method stub
		if (character instanceof Slick || character instanceof Sam) {	
			model.getScoreBoard().addSlickPointsToScore();
			model.removeCharacter(character);
		} else if (character instanceof GreenBall) {
			model.removeCharacter(character);
		} else if (character instanceof Enemy) {
			model.killPlayer();
		}
	}

}
