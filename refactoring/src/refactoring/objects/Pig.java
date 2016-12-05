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

	public Pig(Point position, int width, int heigth, Image image, boolean isGravitational, int typeObject, int life, double gravity)  {
		super(position, width , heigth, image, isGravitational, typeObject, gravity);
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

	public void looseLife(){
		life --;
	}


	public void changePigs(){
		switch (life) {
		case 1:
			image = getToolkit().getImage("./res/pig_3.png");
			break;
		case 2:
			image = getToolkit().getImage("./res/pig_2.png");
			break;
		case 3:
			image = getToolkit().getImage("./res/pig_1.png");
			break;
		case 4:
			image = getToolkit().getImage("./res/armor_pig.png");
			break;
		default:
			break;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, (int) getPosition().getX(), (int)getPosition().getY(),  width, heigth, this);
	}
}
