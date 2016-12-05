package refactoring.objects;

import java.awt.Graphics;
import java.awt.Image;

import refactoring.point.Point;

public class ObjectOfLevel extends AbstractObject{
	private static final long serialVersionUID = 1L;


	
	public ObjectOfLevel() {
		super();
		//		this.objectType = 0;
	}

	public ObjectOfLevel(Point position, int width, int heigth, Image image, boolean isGravitational, int typeObject)  {
		super(position,width, heigth,  image, isGravitational, typeObject);
	}

	/*
	public void paint(Graphics g) {
		super.paint(g);
		image = getToolkit().getImage("./res/tuyau.png");
		g.drawImage(image, 100, 100, this);
	}
	 */

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		switch (typeObject) {
		case 1:
			image = getToolkit().getImage("./res/lance-pierre.png");
			break;
		default:
			break;
		}
		g.drawImage(image, (int) getPosition().getX() - width/2, (int)getPosition().getY()-heigth/2, width, heigth, this);
	}
}
