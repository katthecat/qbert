package gameEntities;

import java.awt.Graphics;
import java.util.ArrayList;

import dataStructures.CharacterArray;
import dataStructures.CollisionPair;
import mainGame.Qbert;

public class Pyramid {
	private int startingX, startingY;
	private Platform[][] cubes;
	private boolean[][] occupied;
	private CharacterArray[][] characters;
	private int numberOfCubesOnBottomRow;
	private int verticalSpacingBetweenCubes;
	private int horizontalSpacingBetweenCubes;
	
	public Pyramid(int startingX , int startingY, int levelNumber) {
		this.startingX = startingX;
		this.startingY = startingY;
		int verticalSpacingBetweenCubes = 45;
		int horizontalSpacingBetweenCubes = 52;
		numberOfCubesOnBottomRow = 12;
		int tempX = startingX;
		int tempY = startingY;
		
		int numberOfLiftsMax = 5 - levelNumber;
		if (numberOfLiftsMax <= 0) {
			numberOfLiftsMax = 1;
		}
		int liftCount = 0;
		
		cubes = new Platform[numberOfCubesOnBottomRow][];
		occupied = new boolean[numberOfCubesOnBottomRow][];
		characters = new CharacterArray[numberOfCubesOnBottomRow][];
		
		for (int i = 0 ; i < numberOfCubesOnBottomRow ; i++) {
			cubes[i] = new Platform[i+1];
			occupied[i] = new boolean[i+1];
			characters[i] = new CharacterArray[i+1];
			for (int x = 0 ; x < cubes[i].length ; x++) {
				if (x == 0 || x == cubes[i].length-1) { // adding in lifts
					int a = (int)(Math.random()*15);
					if (a <= 2 && liftCount < numberOfLiftsMax && i >= 5 && i < cubes.length-2) {
						liftCount++;
						cubes[i][x] = new Lift(tempX + horizontalSpacingBetweenCubes*x , tempY);
						characters[i][x] = new CharacterArray();
					}
				} else {
					cubes[i][x] = new Cube(tempX + horizontalSpacingBetweenCubes*x , tempY);
					occupied[i][x] = false;
					characters[i][x] = new CharacterArray();
				}
			}
			tempX -= 0.5*horizontalSpacingBetweenCubes;
			tempY += verticalSpacingBetweenCubes;
		}
	}
	
	public void drawPyramid(Graphics g) {
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x < cubes[i].length ; x++) {
				if (cubes[i][x] != null) {
					cubes[i][x].draw(g);
				}
			}
		}
	}
	
	public int getNumberOfCubesOnBottomRow() {
		return this.numberOfCubesOnBottomRow;
	}
	
	public Cube getFirstUnoccupiedCube() {
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x <cubes[i].length ; x++) {
				if (cubes[i][x] != null && cubes[i][x] instanceof Cube && occupied[i][x] == false) {
					return (Cube) cubes[i][x];
				}
			}
		}
		return null;
	}
	
	public boolean addCharacterToGivenCubeInPyramid(Platform cube, Character character) {
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x <cubes[i].length ; x++) {
				if (characters[i][x] != null && !occupied[i][x]) {
					occupied[i][x] = true;
					characters[i][x].addCharacter(character);
					character.setX(cubes[i][x].getX() + character.getWidth());
					character.setY(cubes[i][x].getY() - character.getHeight());
					return true;
				}
			}
		}
		return false;
	}
	
	public Cube getCubeAtIndexEnemySitsOn(Character character) {
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x <cubes[i].length ; x++) {
				if (cubes[i][x] != null && !(cubes[i][x] instanceof Lift) && characters[i][x].getIfContainsCharacter(character) && cubes[i][x] instanceof Cube) {
					return (Cube) cubes[i][x]; 
				}
			}
		}
		return null; // didn't find a valid cube
	}
	
	public Platform getCubeAtIndex(int row, int col) {
		return cubes[row][col];
	}
	
	
	public Platform[][] getCubeArray() {
		return cubes;
	}
	
	public int getColOfCube(Platform cubeCurrentEnemyIsSittingOn) {
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x <cubes[i].length ; x++) {
				if (cubes[i][x] != null && cubes[i][x].equals(cubeCurrentEnemyIsSittingOn)) {
					return x;
				}
			}
		}
		return -1; // didn't find cube!
	}
	
	public int getRowOfCube(Platform platform) {
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x <cubes[i].length ; x++) {
				if (cubes[i][x] != null && cubes[i][x].equals(platform)) {
					return i;
				}
			}
		}
		return -1; // didn't find cube!
	}
	
	public void addCharacterToPyramid(Character character, int row, int col) {
		characters[row][col].addCharacter(character);
		occupied[row][col] = true;
	}
	
	public void removeCharacterAtCube(Platform cubeCurrentEnemyIsSittingOn, Character character) {
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x <cubes[i].length ; x++) {
				if (cubes[i][x] != null && cubes[i][x].equals(cubeCurrentEnemyIsSittingOn)) {
					characters[i][x].removeCharacter(character);
					occupied[i][x] = false;
				}
			}
		}
	}
	
	public void removeCharacter(Character character) {
		removeCharacterAtCube((Cube) this.getCubeAtIndexEnemySitsOn(character), character);
	}

	public void removeAll() {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x <cubes[i].length ; x++) {
				characters[i][x] = new CharacterArray();
			}
		}
	}

	public CollisionPair updateCollisions() { // returns collison pair for model to deal with
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x <cubes[i].length ; x++) {
				if (cubes[i][x] != null && characters[i][x] != null) {
					if (characters[i][x].size() >= 2) { // more than one character on a cube
						Player player = null;
						Enemy enemy = null;
						Enemy enemy2 = null;
						for (int a = 0 ; player == null && a < characters[i][x].size() ; a++) { // find player
							if (characters[i][x].get(a) instanceof Player) {
								player = (Player) characters[i][x].get(a);
							}
						}
						for (int a = 0 ; enemy == null && a < characters[i][x].size() ; a++) { // find enemy
							if (characters[i][x].get(a) instanceof Enemy) {
								enemy = (Enemy) characters[i][x].get(a);
							}
						}
						
						for (int a = 0 ; enemy2 == null && a < characters[i][x].size() ; a++) { // find enemy2
							if (characters[i][x].get(a) instanceof Enemy && !(enemy.equals(characters[i][x].get(a)))) {
								enemy2 = (Enemy) characters[i][x].get(a);
							}
						}
						
						if (player != null) { // enemy only collision
							return new CollisionPair(player, enemy);
						} else if (enemy != null && enemy2 != null){
							return new CollisionPair(enemy, enemy2);
						}
					}
				}
			}
		}
		return null;
	}
	
	public boolean allLitUp() {
		boolean out = true;
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x < cubes[i].length ; x++) {
				if (cubes[i][x] instanceof Cube) {
					if (!(((Cube) cubes[i][x]).isLit())) {
						out = false;
					} 
				}
			}
		}
		return out;
	}
	
	public void generate() {
		int tempX = this.startingX;
		int tempY = this.startingY;
		cubes = new Cube[numberOfCubesOnBottomRow][];
		occupied = new boolean[numberOfCubesOnBottomRow][];
		characters = new CharacterArray[numberOfCubesOnBottomRow][];
		for (int i = 0 ; i < numberOfCubesOnBottomRow ; i++) {
			cubes[i] = new Cube[i+1];
			occupied[i] = new boolean[i+1];
			characters[i] = new CharacterArray[i+1];
			for (int x = 0 ; x < cubes[i].length ; x++) {
				cubes[i][x] = new Cube(tempX + horizontalSpacingBetweenCubes*x , tempY);
				occupied[i][x] = false;
				characters[i][x] = new CharacterArray();
			}
			tempX -= 0.5*horizontalSpacingBetweenCubes;
			tempY += verticalSpacingBetweenCubes;
		}
	}

	public void reset() {
		// TODO Auto-generated method stub
		generate();
	}
	
	public String toString() {
		String out = "";
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x <cubes[i].length ; x++) {
				if (cubes[i][x] != null) {
					out += cubes[i][x].toString();
				}
			}
			out += '\n';
		}
		out += '\n';
		out += '\n';

		/*for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x <cubes[i].length ; x++) {
				if (characters[i][x] != null) {
					out += cubes[i][x].toString();
				}
			}
			out += '\n';
		} */
		return out;
		
	}

	public Platform liftMe(Lift lift, Character character) {
		Lift temp = lift;
		try {
			int row = this.getRowOfCube(lift);
			int col = this.getColOfCube(lift);
			if (this.getColOfCube(lift) == 0) {
				cubes[row][col] = temp;
			} else {
				cubes[row-1][col-1] = temp;
			}
			this.removeCharacter(character);
			this.addCharacterToGivenCubeInPyramid(temp, character);
			cubes[row][col] = null;
			if (character instanceof Player) { // make it go wheeheeeee
				character.setImage(((Player)character).getLiftImage());
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			temp = null;
		}
		return temp;
	}
	
	public void lightAllUp() {
		for (int i = 0 ; i < cubes.length ; i++) {
			for (int x = 0 ; x <cubes[i].length ; x++) {
				if (cubes[i][x] instanceof Cube && cubes[i][x] != null) {
					(((Cube) cubes[i][x])).lightUp();
				}
			}
		}
	}
	
}