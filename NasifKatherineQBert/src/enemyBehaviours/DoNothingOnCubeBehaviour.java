package enemyBehaviours;

import gameEntities.Cube;
import gameEntities.Platform;

public class DoNothingOnCubeBehaviour implements JumpingOnCubeBehaviour {

	public boolean jumpOnCube(Platform cubeToJumpTo) {

		return false;
	}
	
}
