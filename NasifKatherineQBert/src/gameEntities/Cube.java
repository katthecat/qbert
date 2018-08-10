package gameEntities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Cube extends Platform {
	private boolean isOn;
	private boolean allowToBeTurnedOn;

	public Cube(int x, int y) {
		super(x, y, "Non-Lit Cube.png" , 45 , 25);
		isOn = false;
		allowToBeTurnedOn = true;
	}
	
	public boolean lightUp() {
		// return true if should add points
		if (allowToBeTurnedOn && !isOn) {
			this.isOn = true;
			super.setImage("Lit Cube.png");
			return true;
		}
		return false;
	}

	public boolean isLit() {
		return isOn;
	}
	
	public String toString() {
		String out = "| " + this.getX() + " , " + this.getY() + " |";	
		return out;
		
	}
	
	public void setImage(String img) { // for the action listener
		super.setImage(img);
	}
	
	public void powerDown() {
		isOn = false;
		setImage("Non-Lit Cube.png");
	}
	
	public void freeze() {
	  	allowToBeTurnedOn = false;
	  	if (isOn) {
	  		super.setImage("Lit Cube - Iced.png");
	  	} else {
	  		super.setImage("Non-Lit Cube - Iced.png");
	  	}

		  ActionListener stopFreeze = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  	allowToBeTurnedOn = true;
		    	  	if (isOn) {
		    	  		setImage("Lit Cube.png");
		    	  	} else {
		    	  		setImage("Non-Lit Cube.png");
		    	  	}
		      }
		  };
		  
		Timer a = new Timer(5000, stopFreeze);
		a.setRepeats(false);
		a.start();

	}
	
}
