package enemyBehaviours;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import gameEntities.Player;

public class FallOffPyramidBehaviour implements JumpOffPyramidBehaviour {


	@Override
	public void jumpOffPyramid(gameEntities.Character c) {
		int jumpLength = 50;
		if (c.isFacingDown()) {
			c.setY(c.getY() + jumpLength);
		} else {
			c.setY(c.getY() - jumpLength);
		}
		
		if (c.isFacingLeft()) {
			c.setX(c.getX() - jumpLength);
		} else {
			c.setX(c.getX() + jumpLength);
		}
		//c.setImage(c.getCollisionImage());
		  ActionListener fallPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  	c.setFalling(true);
		  		c.setY(c.getY() + 10);
		  		if (c instanceof Player) {
	    	  			c.getModel().makeViewPaintPyramidOnTopOfPlayer(true);
		  		}
		      }
		  };
		  
		  Timer x = new Timer(10, fallPerformer);
		  x.start();
		
		// from Javadoc
		int delay = 500; //milliseconds
		  ActionListener killPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  	c.setFalling(false);
		    	  	x.stop(); // stop falling
		    	  	c.getModel().makeViewPaintPyramidOnTopOfPlayer(false);
		    	  	if (c instanceof Player) {
		    	  		c.getModel().killPlayer();
		    	  	} else {
		    	  		c.getModel().removeCharacter(c);
		    	  	}
		      }
		  };
		  Timer t = new Timer(delay, killPerformer);
		  t.setRepeats(false);
		  t.start();
		  

	}
}
