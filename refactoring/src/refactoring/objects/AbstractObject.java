package refactoring.objects;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import refactoring.point.Point;

public abstract class AbstractObject extends JComponent{
	private static final long serialVersionUID = 1L;

	protected Point position;
	protected Image image;
	protected boolean isGravitational;
	protected Graphics g;
	protected int typeObject;
	protected int width, heigth;
	protected double gravity;


	public AbstractObject(){
		position = new Point(0, 0);
		image = null;
		isGravitational = false;
		typeObject = 1;
		gravity = 0.1;
	}

	public AbstractObject(Point position, int width , int heigth, Image image, boolean isGravitational, int typeObject, double gravity){
		this.position = position;
		this.image = image;
		this.isGravitational = isGravitational;
		this.typeObject = typeObject;
		this.width = width;
		this.heigth = heigth;
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

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

}
