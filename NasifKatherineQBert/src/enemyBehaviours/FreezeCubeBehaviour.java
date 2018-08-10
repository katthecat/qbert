package enemyBehaviours;

import gameEntities.Cube;
import gameEntities.Platform;

public class FreezeCubeBehaviour implements JumpingOnCubeBehaviour{

	@Override
	public boolean jumpOnCube(Platform cubeToJumpTo) {
		if (cubeToJumpTo instanceof Cube) {
			((Cube) cubeToJumpTo).freeze();
		}
		return false;
	}

}
