package mainGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class EnemyGenerator implements ActionListener {
	private EntityModel model;
	private Timer timer;
	
	public EnemyGenerator(EntityModel model, int timeBetweenGeneration) {
		this.model = model;
		timer = new Timer(timeBetweenGeneration, this);
		timer.start();

	}
	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		model.generateEnemy();
	}	
	
}
