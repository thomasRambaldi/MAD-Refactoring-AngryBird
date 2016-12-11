package refactoring.level;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import refactoring.objects.Bird;
import refactoring.objects.ObjectOfLevel;
import refactoring.objects.Pig;

public class Level extends Panel{
	private static final long serialVersionUID = 1L;
	
	private Difficulties difficulty;
	private List<Bird> listBirds;
	private List<Pig> listPigs;
	private List<ObjectOfLevel> listObjects;
	
	private Image background;
	private int gravity;
	private boolean isFinish;
	
	public Level(){
		super();
		this.difficulty = Difficulties.EASY;
		this.listBirds = new ArrayList <>();
		this.listPigs = new ArrayList <>();
		this.listObjects = new ArrayList <>();
//		this.background = ;
		this.gravity = 1;
	}
	
	public Level(Difficulties difficulty, List<Bird> listBirds, List<Pig> listPigs, List<ObjectOfLevel> listObjects, Image background, int gravity) {
		super();
		this.difficulty = difficulty;
		this.listBirds = listBirds;
		this.listPigs = listPigs;
		this.listObjects = listObjects;
		this.background = background;
		this.gravity = gravity;
	}
	
	public void init(){
		this.isFinish = false;
	}
	
	public void paint(Graphics g) {
		//super.paint(g);
		g.drawImage(background, 0, 0, 1300, 800, this);
	}
	
	public Difficulties getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulties difficulty) {
		this.difficulty = difficulty;
	}

	public List<Bird> getListBirds() {
		return listBirds;
	}

	public void setListBirds(List<Bird> listBirds) {
		this.listBirds = listBirds;
	}

	public List<Pig> getListPigs() {
		return listPigs;
	}

	public void setListPigs(List<Pig> listPigs) {
		this.listPigs = listPigs;
	}

	public List<ObjectOfLevel> getListObjects() {
		return listObjects;
	}

	public void setListObjects(List<ObjectOfLevel> listObjects) {
		this.listObjects = listObjects;
	}

	public Image getImageBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public boolean isFinish() {
		return isFinish;
	}

	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}
}
