package refactoring.objects;

import java.awt.Graphics;
import java.awt.Image;

import refactoring.point.Point;

public class Pig extends AbstractObject {
	private static final long serialVersionUID = 1L;

	private int life;

	public Pig() {
		super();
		life = 1;
	}

	public Pig(Point position, int width, int heigth, Image image, boolean isGravitational, int typeObject, int life)  {
		super(position, width , heigth, image, isGravitational, typeObject);
		this.life = life;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	} 
	
/*
	public void paint(Graphics g) {
		super.paint(g);
		switch (typeObject) {
		case 1:
			image = getToolkit().getImage("./res/pig_1.png");
			break;
		case 2:
			image = getToolkit().getImage("./res/pig_2.png");
			break;
		default:
			break;
		}
		g.drawImage(image, 100, 100, this);
	}
*/
	@Override
	protected void paintComponent(Graphics g) {
		System.out.println("Méthode paint");
		super.paintComponent(g);
		switch (typeObject) {
		case 1:
			image = getToolkit().getImage("./res/pig_1.png");
			break;
		case 2:
			image = getToolkit().getImage("./res/pig_2.png");
			break;
		default:
			break;
		}
		g.drawImage(image, 100, 100, this);
	}
}
