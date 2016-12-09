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

	public Bird(Point position, int width, int heigth, Image image, boolean isGravitational, int typeObject, Point velocity, double gravity) {
		super(position, width, heigth, image, isGravitational, typeObject, gravity);
		this.velocity = velocity;
	}

	public Point getVelocity() {
		return velocity;
	}

	public void setVelocity(Point velocity) {
		this.velocity = velocity;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, (int) getPosition().getX(), (int)getPosition().getY(),  width, heigth, this);
	}
	
	public void updatePosition(){
		position.setX(position.getX() + velocity.getX());
		position.setY(position.getY() + velocity.getY());
		velocity.setY(velocity.getY() + gravity);
	}
}
