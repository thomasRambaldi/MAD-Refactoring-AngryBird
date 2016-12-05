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

	public ObjectOfLevel(Point position, Image image, boolean isGravitational, int typeObject)  {
		super(position, image, isGravitational, typeObject);
	}

	/*
	public void paint(Graphics g) {
		super.paint(g);
		image = getToolkit().getImage("./res/tuyau.png");
		g.drawImage(image, 100, 100, this);
	}
	 */

	@Override
	protected void paintComponent(Graphics g) {
		System.out.println("Méthode paint");
		super.paintComponent(g);
		super.paint(g);
		image = getToolkit().getImage("./res/tuyau.png");
		g.drawImage(image, 100, 100, this);
	}
}
