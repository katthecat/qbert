package gameEntities;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class GameEntity {
	private int x,y;
	private Image activeImage;
	
	int height, width;
	
	public GameEntity(int x, int y, String imageName, int height, int width) {
		this.x = x;
		this.y = y;
		this.activeImage = new ImageIcon(imageName).getImage();
		this.height = height;
		this.width = width;
	}
	
	public void setImage(String imageName) {
		this.activeImage = new ImageIcon(imageName).getImage();
	}
	
	public Image getImage() {
		return this.activeImage;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void draw(Graphics g) {
		g.drawImage(this.activeImage, this.x, this.y, null);
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}

	public void setImage(Image image) {
		// TODO Auto-generated method stub
		this.activeImage = image;
		
	}

}
