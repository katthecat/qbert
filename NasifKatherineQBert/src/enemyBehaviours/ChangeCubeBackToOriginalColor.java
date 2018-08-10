package enemyBehaviours;

import gameEntities.Cube;
import gameEntities.Platform;

public class ChangeCubeBackToOriginalColor implements JumpingOnCubeBehaviour {

	public boolean jumpOnCube(Platform cubeToJumpTo) {
		if (cubeToJumpTo instanceof Cube) {
			if (((Cube) cubeToJumpTo).isLit()) {
				((Cube) cubeToJumpTo).powerDown();
			}
		}
		return false;
	}

}
