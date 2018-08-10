package enemyBehaviours;

import gameEntities.Character;
import mainGame.EntityModel;

public interface CollisionBehaviour {

	public void collide(Character character, EntityModel model);
}
