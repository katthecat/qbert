package gameEntities;

import java.awt.Image;

import javax.swing.ImageIcon;

import enemies.Coily;
import enemies.PurpleBall;
import enemyBehaviours.CollisionBehaviour;
import enemyBehaviours.JumpOffPyramidBehaviour;
import enemyBehaviours.JumpingOnCubeBehaviour;
import mainGame.EntityModel;

public class Character extends GameEntity {
	private boolean isFacingLeft;
	private boolean isFacingDown;
	private Image bottomRightImage;
	private Image bottomLeftImage;
	private Image upperRightImage;
	private Image upperLeftImage;
	private JumpingOnCubeBehaviour cubeBehaviour;
	private EntityModel model;
	private CollisionBehaviour collisionBehaviour;
	private JumpOffPyramidBehaviour jumpOffPyramidBehaviour;
	private Image collisionImage;
	private boolean isCurrentlyFalling;
	
	public Character(int x, int y, int height, int width, String bottomRightImageName, 
			String bottomLeftImageName, String higherLeftImageName, String higherRightImageName, 
			JumpingOnCubeBehaviour cubeBehaviour, JumpOffPyramidBehaviour jumpOffPyramidBehaviour, EntityModel model) {
		super(x, y, bottomRightImageName, height, width);
		this.bottomRightImage = new ImageIcon(bottomRightImageName).getImage();
		this.bottomLeftImage = new ImageIcon(bottomLeftImageName).getImage();
		this.upperRightImage = new ImageIcon(higherRightImageName).getImage();
		this.upperLeftImage = new ImageIcon(higherLeftImageName).getImage();
		this.model = model;
		
		isFacingLeft = false;
		isFacingDown = true;
		
		this.jumpOffPyramidBehaviour = jumpOffPyramidBehaviour;
		this.cubeBehaviour = cubeBehaviour;
		
		this.collisionImage = bottomRightImage; // just to standardize
		isCurrentlyFalling = false;
	}
	
	public Image getBottomRightImage() {
		return bottomRightImage;
	}

	public void setBottomRightImage(Image bottomRightImage) {
		this.bottomRightImage = bottomRightImage;
	}

	public Image getBottomLeftImage() {
		return bottomLeftImage;
	}

	public void setBottomLeftImage(Image bottomLeftImage) {
		this.bottomLeftImage = bottomLeftImage;
	}

	public Image getUpperRightImage() {
		return upperRightImage;
	}

	public void setUpperRightImage(Image upperRightImage) {
		this.upperRightImage = upperRightImage;
	}

	public Image getUpperLeftImage() {
		return upperLeftImage;
	}

	public void setUpperLeftImage(Image upperLeftImage) {
		this.upperLeftImage = upperLeftImage;
	}

	public void jump(Pyramid pyramid) {
		try {
			if (isFacingLeft && isFacingDown) {
				this.setImage(bottomLeftImage);
				jumpBottomLeft(pyramid);
			} else if (isFacingLeft && !isFacingDown) {
				this.setImage(upperLeftImage);
				jumpHigherLeft(pyramid);
			} else if (!isFacingLeft && isFacingDown) {
				this.setImage(bottomRightImage);
				jumpBottomRight(pyramid);
			} else {
				this.setImage(upperRightImage);
				jumpHigherRight(pyramid);
			}
		} catch (IndexOutOfBoundsException e) { // jumps out of bounds
			if (this instanceof PurpleBall) {
				model.generateEnemyAt(model.getPyramid().getCubeAtIndexEnemySitsOn(this), new Coily(model.getPyramid().getCubeAtIndexEnemySitsOn(this).getX() , model.getPyramid().getCubeAtIndexEnemySitsOn(this).getY() - 20 , pyramid, model.getPlayer(), getModel()));
				model.removeCharacter(this);
			} else {
				if (!this.isAlreadyFalling()) {
					this.jumpOffPyramidBehaviour.jumpOffPyramid(this);
				}
			}
		}
		
	}
	
	private boolean isAlreadyFalling() {
		return this.isCurrentlyFalling;
	}

	private void jumpFromCubeToOtherCube(Platform cubeCurrentEnemyIsSittingOn, Platform cubeToJumpTo) {
		this.setX(cubeToJumpTo.getX()+this.getWidth());
		this.setY(cubeToJumpTo.getY() - this.getHeight()*2);
		// can add animation once finished
		if (cubeToJumpTo instanceof Cube) {		
			if (cubeBehaviour.jumpOnCube(cubeToJumpTo)) { // needs to add points to scoreboard
				model.getScoreBoard().addCubePointsToScore();
				model.getGamePanel().repaint();
			}
		} else if (cubeToJumpTo instanceof Lift) {
			try {
				model.performLift(this,  (Lift) cubeToJumpTo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //IMPLEMENT THIS SCRUB
		}
		
	}
	
	public void jumpBottomLeft(Pyramid pyramid) {
		Platform cubeCurrentEnemyIsSittingOn = pyramid.getCubeAtIndexEnemySitsOn(this);
		int currentRow = pyramid.getRowOfCube(cubeCurrentEnemyIsSittingOn);
		int currentCol = pyramid.getColOfCube(cubeCurrentEnemyIsSittingOn);

		Platform cubeToJumpTo = pyramid.getCubeAtIndex(currentRow + 1 , currentCol);
		pyramid.addCharacterToPyramid(this , pyramid.getRowOfCube(cubeToJumpTo) , pyramid.getColOfCube(cubeToJumpTo));
		pyramid.removeCharacterAtCube(cubeCurrentEnemyIsSittingOn, this);

		jumpFromCubeToOtherCube(cubeCurrentEnemyIsSittingOn, cubeToJumpTo);		
	}
	
	public void jumpBottomRight(Pyramid pyramid) {
		Platform cubeCurrentEnemyIsSittingOn = pyramid.getCubeAtIndexEnemySitsOn(this);
		int currentRow = pyramid.getRowOfCube(cubeCurrentEnemyIsSittingOn);
		int currentCol = pyramid.getColOfCube(cubeCurrentEnemyIsSittingOn);
		Platform cubeToJumpTo = pyramid.getCubeAtIndex(currentRow + 1, currentCol + 1);
		pyramid.addCharacterToPyramid(this , pyramid.getRowOfCube(cubeToJumpTo) , pyramid.getColOfCube(cubeToJumpTo));
		pyramid.removeCharacterAtCube(cubeCurrentEnemyIsSittingOn, this);
		
		jumpFromCubeToOtherCube(cubeCurrentEnemyIsSittingOn, cubeToJumpTo);
	}
	
	public void jumpHigherLeft(Pyramid pyramid) {
		Platform cubeCurrentEnemyIsSittingOn = pyramid.getCubeAtIndexEnemySitsOn(this);
		int currentRow = pyramid.getRowOfCube(cubeCurrentEnemyIsSittingOn);
		int currentCol = pyramid.getColOfCube(cubeCurrentEnemyIsSittingOn);
		Platform cubeToJumpTo = pyramid.getCubeAtIndex(currentRow - 1 , currentCol-1);
		pyramid.addCharacterToPyramid(this , pyramid.getRowOfCube(cubeToJumpTo) , pyramid.getColOfCube(cubeToJumpTo));
		pyramid.removeCharacterAtCube(cubeCurrentEnemyIsSittingOn ,this);

		jumpFromCubeToOtherCube(cubeCurrentEnemyIsSittingOn, cubeToJumpTo);
	}
	
	public void jumpHigherRight(Pyramid pyramid) {
		Cube cubeCurrentEnemyIsSittingOn = pyramid.getCubeAtIndexEnemySitsOn(this);
		int currentRow = pyramid.getRowOfCube(cubeCurrentEnemyIsSittingOn);
		int currentCol = pyramid.getColOfCube(cubeCurrentEnemyIsSittingOn);

		Platform cubeToJumpTo = pyramid.getCubeAtIndex(currentRow - 1, currentCol);

		pyramid.addCharacterToPyramid(this , pyramid.getRowOfCube(cubeToJumpTo) , pyramid.getColOfCube(cubeToJumpTo));
		pyramid.removeCharacterAtCube(cubeCurrentEnemyIsSittingOn, this);

		jumpFromCubeToOtherCube(cubeCurrentEnemyIsSittingOn, cubeToJumpTo);
	}
	
	public void makeFaceLeft() {
		isFacingLeft = true;
	}
	
	public void makeFaceRight() {
		isFacingLeft = false;
	}
	
	public void makeFaceUp() {
		isFacingDown = false;
	}
	
	public void makeFaceDown() {
		isFacingDown = true;
	}

	public boolean isFacingLeft() {
		return isFacingLeft;
	}

	public boolean isFacingDown() {
		return isFacingDown;
	}
	
	public void faceRandomDownwardsDirection() {
		int x = (int)(Math.random()*2);
		if (x == 0) {
			this.makeFaceDown();
			this.makeFaceLeft();
		} else {
			this.makeFaceDown();
			this.makeFaceRight();
		}

	}
	
	public EntityModel getModel() {
		return this.model;
	}

	public void collideWith(Character character, EntityModel model) {
		collisionBehaviour.collide(character, model);
	}
	
	public void setCollisionBehaviour(CollisionBehaviour b) {
		this.collisionBehaviour = b;
	}
	
	public JumpOffPyramidBehaviour getJumpOffPyramidBehaviour() {
		return this.jumpOffPyramidBehaviour;
	}

	public void setModel(EntityModel model) {
		this.model = model;
	}
	
	public void setCollisonImage(String imageName) {
		this.collisionImage = new ImageIcon(imageName).getImage();
	}
	
	public Image getCollisionImage() {
		return this.collisionImage;
	}
	
	public void setJumpOffPyramidBehaviour(JumpOffPyramidBehaviour a) {
		this.jumpOffPyramidBehaviour = a;
	}
	
	public void setFalling(boolean f) {
		isCurrentlyFalling = f;
	}
	
}
