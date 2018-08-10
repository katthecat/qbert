package enemyBehaviours;

import gameEntities.Player;
import gameEntities.Pyramid;
import mainGame.EntityModel;
import gameEntities.Character;

public class KillCollisionBehaviour implements CollisionBehaviour {
	
	public void collide(Character character, EntityModel model) {
		if (!(character instanceof Player)) {
			Pyramid pyramid = model.getPyramid();
			pyramid.removeCharacterAtCube(pyramid.getCubeAtIndexEnemySitsOn(character), character);
		}
		
	}


}
