package enemyBehaviours;

import gameEntities.Cube;
import gameEntities.Platform;

public class LightUpCubeBehaviour implements JumpingOnCubeBehaviour {

	@Override
	public boolean jumpOnCube(Platform cubeToJumpTo) {
		if (cubeToJumpTo instanceof  Cube) {
			if (((Cube) cubeToJumpTo).lightUp()) {
				return true;
			}
		}
		return false;
	}
	
}
