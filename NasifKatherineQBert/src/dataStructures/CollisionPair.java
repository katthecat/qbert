package dataStructures;

public class CollisionPair {
	private gameEntities.Character lead;
	private gameEntities.Character follower;
	
	public CollisionPair(gameEntities.Character lead, gameEntities.Character follower) {
		this.lead = lead;
		this.follower = follower;
	}
	
	public gameEntities.Character getLead() {
		return this.lead;
	}
	
	public gameEntities.Character getFollower() {
		return this.follower;
	}
}
