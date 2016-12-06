package refactoring.objects;

import java.awt.Graphics;
import java.awt.Image;

import refactoring.point.Point;

public class ObjectOfLevel extends AbstractObject{
	private static final long serialVersionUID = 1L;

	public ObjectOfLevel() {
		super();
	}

	public ObjectOfLevel(Point position, int width, int heigth, Image image, boolean isGravitational, int typeObject, double gravity)  {
		super(position,width, heigth,  image, isGravitational, typeObject, gravity);
	}

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
