package refactoring.objects;

import java.awt.Graphics;
import java.awt.Image;

import refactoring.point.Point;

public class Bird extends AbstractObject {
	private static final long serialVersionUID = 1L;

	private Point velocity;

	public Bird() {
		super();
		this.velocity = new Point(0, 0);
	}

	public Bird(Point position, Image image, boolean isGravitational, int typeObject, Point velocity) {
		super(position, image, isGravitational, typeObject);
		this.velocity = velocity;
	}

	public Point getVelocity() {
		return velocity;
	}

	public void setVelocity(Point velocity) {
		this.velocity = velocity;
	}
/*
	public void paint(Graphics g) {
		System.out.println("Méthode paint");
		super.paint(g);
		switch (typeObject) {
		case 1:
			System.out.println("type = " + 1);
			image = getToolkit().getImage("./res/red.png");
			break;
		case 2:
			System.out.println("type = " + 2);
			image = getToolkit().getImage("./res/chuck.png");
			break;
		default:
			break;
		}
		g.drawImage(image, 100, 100, this);
	}
	
	public void update(Graphics g){
		System.out.println("Méthode update");
		paint(g);
	}
*/
	@Override
	protected void paintComponent(Graphics g) {
		System.out.println("Méthode paint");
		super.paintComponent(g);
		switch (typeObject) {
		case 1:
			System.out.println("type = " + 1);
			image = getToolkit().getImage("./res/red.png");
			break;
		case 2:
			System.out.println("type = " + 2);
			image = getToolkit().getImage("./res/chuck.png");
			break;
		default:
			break;
		}
		g.drawImage(image, 100, 100, this);
	}
}
