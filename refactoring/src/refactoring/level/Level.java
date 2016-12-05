package refactoring.level;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import refactoring.objects.Bird;
import refactoring.objects.Pig;

public class Level extends Panel{
	private static final long serialVersionUID = 1L;
	
	private Difficulties difficulty;
	private List<Bird> listBirds;
	private List<Pig> listPigs;
	private List<Object> listObjects;
	
	private Image background;
	private int gravity;
	private boolean isFinish;
	private boolean isWin;
	
	public Level(){
		super();
		this.difficulty = Difficulties.EASY;
		this.listBirds = new ArrayList <>();
		this.listPigs = new ArrayList <>();
		this.listObjects = new ArrayList <>();
//		this.background = ;
		this.gravity = 1;
	}
	
	public Level(Difficulties difficulty, List<Bird> listBirds, List<Pig> listPigs, List<Object> listObjects, Image background, int gravity) {
		super();

		this.difficulty = difficulty;
		this.listBirds = listBirds;
		this.listPigs = listPigs;
		this.listObjects = listObjects;
		this.background = background;
		this.gravity = gravity;
	}
	
	public void init(){
		System.out.println("Constructeur de level");
		this.isFinish = false;
		this.isWin = false;
	}
	
	public void paint(Graphics g) {
		System.out.println("Paint Level");
		super.paint(g);
//		background = getToolkit().getImage("./res/nature.jpg");
		g.drawImage(background, 0, 0, this);
		
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

	public List<Object> getListObjects() {
		return listObjects;
	}

	public void setListObjects(List<Object> listObjects) {
		this.listObjects = listObjects;
	}

//	public Image getBackground() {
//		return background;
//	}

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

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
	
}
