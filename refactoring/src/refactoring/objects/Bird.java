package refactoring.objects;

import java.awt.Graphics;
import java.awt.Image;

import refactoring.point.Point;

public class Bird extends AbstractObject {
	private static final long serialVersionUID = 1L;

	private Point velocity;
	boolean stop = false;

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
		g.drawImage(image, (int) getPosition().getX(), (int)getPosition().getY(),  width, height, this);
	}
	
	public void updatePosition(int windowWidth, int windowHeight){
		if( stop)
			return;
		
		if(velocity.getY() > 0 && velocity.getY() < 1 && position.getY()+2 >  windowHeight - 150) {
			velocity.setY(0);
			velocity.setX(0);
			stop = true;
			return;
		}
		
		if( isHitingFloor(windowHeight) )
			velocity.setY(velocity.getY()*-0.8);

		if(isHitingCeiling())
			velocity.setY(velocity.getY()*-0.9);
		
		if(isHitingRightWall(windowWidth) || isHitingLeftWall())
			velocity.setX(velocity.getX()*-0.9);
		
		position.setX(position.getX() + velocity.getX());
		position.setY(position.getY() + velocity.getY());
		velocity.setY(velocity.getY() + gravity);
	}

	private boolean isHitingCeiling(){
		return position.getY() < 0  && velocity.getY() < -0.001;
	}
	
	private boolean isHitingFloor(int windowHeight){
		return position.getY() >  windowHeight - 150 && (velocity.getY() > 0.001);
	}
	
	private boolean isHitingLeftWall(){
		return position.getX() < 0 && velocity.getX() < -0.001;
	}
	
	private boolean isHitingRightWall(int windowWidth){
		return position.getX() + getWidth() > windowWidth && velocity.getX() > 0.001;
	}

	public void acceleratePosition(double acceleration) {
		velocity.setX(velocity.getX()*acceleration);
		velocity.setY(velocity.getY()*acceleration);
		position.setX(position.getX() + velocity.getX());
		position.setY(position.getY() + velocity.getY());
	}
	
}
