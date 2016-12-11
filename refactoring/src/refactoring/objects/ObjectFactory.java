package refactoring.objects;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import refactoring.point.Point;

public class ObjectFactory {

	private double initBirdX=0, initBirdY=0;

	public AbstractObject createObject(String objectName){
		if(objectName.equalsIgnoreCase("Little Red Bird")){
			return setBird("red.png", 40,40, 0.1);
		}
		
		if(objectName.equalsIgnoreCase("Yellow Bird")){
			return setBird("chuck.png", 40, 40, 0.1);
		}
		
		if(objectName.equalsIgnoreCase("Bomb Bird")){
			return setBird("bomb.png", 50,60, 0.2);
		}
		
		if(objectName.equalsIgnoreCase("Terence Bird")){
			return setBird("terence.png", 60, 60, 0.3);
		}
		
		if(objectName.equalsIgnoreCase("Normal Pig")){
			return setPig("pig_1.png", 40, 1, 1);
		}
		
		if(objectName.equalsIgnoreCase("Armor Pig")){
			return setPig("armor_pig1.png", 40, 2, 2);
		}
		
		if(objectName.equalsIgnoreCase("King Pig")){
			return setPig("king_pig1.png", 40, 3, 5);
		}
		
		if(objectName.equalsIgnoreCase("Fat Pig")){
			return setPig("Fat_pig.png", 40, 4, 4);
		}
		
		if(objectName.equalsIgnoreCase("Slingshot")){
			return setObjectOfLevel("lance-pierre.png", 60, 150);
		}
		
		if(objectName.equalsIgnoreCase("Pipe")){
			return setObjectOfLevel("tuyau.png", 50, 80);
		}
		return null;
	}
	
	private AbstractObject setBird(String imageRes, int width, int height, double gravity) {
		Bird bird= new Bird();
		bird.setPosition(new Point(initBirdX, initBirdY));
		bird.setWidth(width);
		bird.setHeight(height);
		try {
			bird.setImage(ImageIO.read(new File("./res/"+imageRes)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bird.setGravitational(true);
		bird.setTypeObject(0);
		bird.setVelocity(new Point(5, 5));
		bird.setGravity(gravity);
		return bird;
	}
	
	private AbstractObject setPig(String imageRes, int size, int life, int typeObject) {
		Pig pig= new Pig();
		pig.setPosition(new Point(0, 0));
		pig.setWidth(size);
		pig.setHeight(size);
		try {
			pig.setImage(ImageIO.read(new File("./res/"+imageRes)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pig.setGravitational(true);
		pig.setTypeObject(typeObject);
		pig.setLife(life);
		pig.setGravity(0);
		return pig;
	}
	
	private AbstractObject setObjectOfLevel(String imageRes, int width, int height) {
		ObjectOfLevel ool= new ObjectOfLevel();
		ool.setPosition(new Point(0, 0));
		ool.setWidth(width);
		ool.setHeight(height);
		try {
			ool.setImage(ImageIO.read(new File("./res/"+imageRes)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ool.setGravitational(false);
		return ool;
	}
}
