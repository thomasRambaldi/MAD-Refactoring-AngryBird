package refactoring.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

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

	public void looseLife(){
		life --;
		typeObject--;
	}

	public void changePigs(){
		switch (typeObject) {
		case 1:
			image = getToolkit().getImage("./res/armor_pig3.png");
			break;
		case 2:
			image = getToolkit().getImage("./res/armor_pig.png");
			break;
		case 3:
			image = getToolkit().getImage("./res/king_pig4.png");
			break;
		case 4 :
			image = getToolkit().getImage("./res/king_pig2.png");
			break;
		case 5:
			image = getToolkit().getImage("./res/king_pig1.png");
			break;
		default:
			break;
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, (int) getPosition().getX()-width/2, (int)getPosition().getY()-height/2,  width, height, this);
	}

	@Override
	public void actionCollision() {
		looseLife();
		changePigs();
	}
}
