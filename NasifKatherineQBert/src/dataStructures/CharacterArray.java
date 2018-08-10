package dataStructures;

import java.util.ArrayList;

import gameEntities.Character;

public class CharacterArray {
	private ArrayList<Character> characters;
	
	public CharacterArray() {
		this.characters = new ArrayList<Character>();
	}
	
	public void addCharacter(Character character) {
		characters.add(character);
	}
	
	public void removeCharacter(Character character) {
		characters.remove(character);
	}
	
	public boolean checkIfCollision(Character c1, Character c2) {
		boolean hasC1 = false;
		boolean hasC2 = false;
		for (int x  = 0 ; x<characters.size() ; x++) {
			if (characters.get(x).equals(c1)) {
				hasC1 = true;
			}
			if (characters.get(x).equals(c2)) {
				hasC2 = true;
			}
		}
		return hasC1 && hasC2;
	}
	
	public boolean getIfContainsCharacter(Character character) {
		boolean x = false;
		for (int i = 0 ; i<characters.size() ; i++) {
			if (character != null && character.equals(characters.get(i))) {
				x = true;
			}
		}
		return x;
	}
	
	public Character get(int i) {
		return characters.get(i);
	}
	
	public int size() {
		return characters.size();
	}

}
