package gameEntities;

public class ScoreBoard {
	public final int MAX_LIVES = 3;
	private int score;
	private int lives;
	public ScoreBoard() {
		score = 0;
		lives = this.MAX_LIVES;
	}
	
	public int subtractLife() {
		if (lives > 0) {
			lives --;
		} else {
			// is out of lives
		}
		return lives;
	}
	
	public void addCubePointsToScore() {
		score += 50;
	}
	
	public void addLevelUpPointsToScore() {
		score += 1000;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getLives() {
		return this.lives;
	}

	public void addSlickPointsToScore() {
		score += 300;
	}

	public void setPoints(int points) {
		this.score = points;
	}

	public void setLives(int startingLives) {
		this.lives = startingLives;
	}
}
