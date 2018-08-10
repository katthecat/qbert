package enemyBehaviours;

import gameEntities.Cube;
import gameEntities.Platform;

public interface JumpingOnCubeBehaviour {
	public boolean jumpOnCube(Platform cubeToJumpTo); // returns true if should add points
}
