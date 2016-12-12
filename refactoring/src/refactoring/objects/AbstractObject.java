package refactoring.objects;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.JComponent;

import refactoring.point.Point;

public abstract class AbstractObject extends JComponent{
	private static final long serialVersionUID = 1L;

	protected Point position;
	protected Image image;
	protected boolean isGravitational;
	protected Graphics g;
	protected int typeObject;
	protected int width, height;
	protected double gravity;


	public AbstractObject(){
		position = new Point(0, 0);
		image = null;
		isGravitational = false;
		typeObject = 1;
		gravity = 0.1;
	}

	public AbstractObject(Point position, int width , int height, Image image, boolean isGravitational, int typeObject, double gravity){
		this.position = position;
		this.image = image;
		this.isGravitational = isGravitational;
		this.typeObject = typeObject;
		this.width = width;
		this.height = height;
		this.gravity = gravity;
	}
	//public abstract void paint ( Graphics g );

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isGravitational() {
		return isGravitational;
	}

	public void setGravitational(boolean isGravitational) {
		this.isGravitational = isGravitational;
	}

	public int getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(int typeObject) {
		this.typeObject = typeObject;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	
	
	public boolean isCollided(AbstractObject o/*Point center, int width, int height*/){
		double highY = position.getY()-height/2;
		double bottomY = position.getY() + height/2;
		double leftX = position.getX()-width/2;
		double rightX = position.getX() + width/2;
		return o.getPosition().getY() + o.getHeight()/2 > highY && 
			   o.getPosition().getY() - o.getHeight()/2 < bottomY && 
			   o.getPosition().getX() + o.getWidth()/2 > leftX && 
			   o.getPosition().getX()  < rightX;
	}
	
	public abstract void actionCollision();

}
