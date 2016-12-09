package refactoring.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import refactoring.level.Difficulties;
import refactoring.level.Level;
import refactoring.objects.Bird;
import refactoring.objects.ObjectOfLevel;
import refactoring.objects.Pig;
import refactoring.point.Point;

/*
 * corriger les problemes de colisions avec les birds plus gros
 */
public class Game extends JPanel implements Runnable, MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;

	private final int SIZE_IMAGE_NORMAL = 40;
	private final int SIZE_IMAGE_LARGE = 50;
	private final int SIZE_IMAGE_HUGE = 60;
	private final int SLINGSHOTW = 60;
	private final int SLINGSHOTH = 150;

	private Level[] levels;
	private int currentLevel;
	private int currentBird;
	private int currentPig;
	private  boolean selecting;

	private int initBirdX, initBirdY;

	private int mouseX, mouseY, score;
	private boolean gameOver;
	private int windowWidth, windowHeight;

	private double gravity;

	private Image buffer;

	public Game(int windowWidth, int windowHeight) throws IOException {
		super();
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.score = 0;
		currentLevel = 0;
		Level[] levels = new Level[2];
		this.levels=levels;
		init();
		new Thread(this).start();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void init() throws IOException{
		currentBird = 0;
		currentPig = 0;
		selecting = true;
		initBirdX = (int) (windowWidth/6 - 30) ;
		initBirdY = (int) (windowHeight/1.3 - 70) ;
		gameOver = false;
		
		List<ObjectOfLevel> listObjects = new ArrayList<>();
		ObjectOfLevel lancePierre = new ObjectOfLevel(new Point(windowWidth/6, windowHeight/1.3), SLINGSHOTW, SLINGSHOTH, ImageIO.read(new File("./res/lance-pierre.png")), false, 0, 0);
		listObjects.add(lancePierre);
		
		/*-----------------------*/
		//--------Level 0---------/
		/*-----------------------*/
		List<Bird> listBirds = addBirdsInLevel0();
		List<Pig> listPigs = addPigsInLevel0();
		levels[0] = new Level(Difficulties.EASY, listBirds, listPigs, listObjects, ImageIO.read(new File("./res/nature.jpg")), 1);


		/*-----------------------*/
		//--------Level 1---------/
		/*-----------------------*/
		List<Bird> listBirds1 = addBirdsInLevel1();
		List<Pig> listPigs1 = addPigsInLevel1();
		levels[1] = new Level(Difficulties.EASY, listBirds1, listPigs1, listObjects, ImageIO.read(new File("./res/marais.jpg")), 1);
	}

/*
	private void initForNextLevel() throws IOException {

		if(currentLevel==0){
			List<Bird> listBirds = addBirdsInLevel0();
			List<Pig> listPigs = addPigsInLevel0();
			levels[0].setListBirds(listBirds);
			levels[0].setListPigs(listPigs);
		}
		if(currentLevel==1){
			List<Pig> listPigs1 = addPigsInLevel1();
			List<Bird> listBirds1 = addBirdsInLevel1();
			levels[1].setListBirds(listBirds1);
			levels[1].setListPigs(listPigs1);
		}

		currentBird = 0;
		currentPig = 0;
		selecting = true;
		initBirdX = (int) (windowWidth/6 - 30) ;
		initBirdY = (int) (windowHeight/1.3 - 70) ;
		gameOver = false;
	}
*/
	
	public List<Bird> addBirdsInLevel0() throws IOException {
		List<Bird> listBirds = new ArrayList<>(); 
		Bird red = new Bird(new Point(initBirdX, initBirdY), SIZE_IMAGE_NORMAL, SIZE_IMAGE_NORMAL, ImageIO.read(new File("./res/red.png")), true, 0, new Point(5, 5), 0.1);
		listBirds.add(red);
		return listBirds;
	}

	public List<Pig> addPigsInLevel0() throws IOException {
		List<Pig> listPigs = new ArrayList<>();
		Pig littlePig = new Pig(new Point( generatePigPosition(300, windowWidth-100), windowHeight - 150), SIZE_IMAGE_NORMAL, SIZE_IMAGE_NORMAL,  ImageIO.read(new File("./res/pig_1.png")), false, 1, 1, 0.0);
		listPigs.add(littlePig);
		return listPigs;
	}

	public List<Bird> addBirdsInLevel1() throws IOException {
		List<Bird> listBirds1 = new ArrayList<>(); 
		Bird red1 = new Bird(new Point(initBirdX, initBirdY), SIZE_IMAGE_NORMAL, SIZE_IMAGE_NORMAL, ImageIO.read(new File("./res/red.png")), true, 0, new Point(5, 5), 0.1);
		Bird chuck1 = new Bird(new Point(windowWidth/6 - 50, windowHeight/1.3 + 30), SIZE_IMAGE_NORMAL, SIZE_IMAGE_NORMAL, ImageIO.read(new File("./res/chuck.png")), true, 0, new Point(5, 5), 0.1);
		Bird bomb1 = new Bird(new Point(windowWidth/6 - 50, windowHeight/1.3 + 30), SIZE_IMAGE_LARGE, SIZE_IMAGE_HUGE, ImageIO.read(new File("./res/bomb.png")), true, 0, new Point(5, 5), 0.2);
		Bird terence1 = new Bird(new Point(windowWidth/6 - 50, windowHeight/1.3 + 30), SIZE_IMAGE_HUGE, SIZE_IMAGE_HUGE, ImageIO.read(new File("./res/terence.png")), true, 0, new Point(5, 5), 0.3);
		listBirds1.add(red1);
		listBirds1.add(chuck1);
		listBirds1.add(bomb1);
		listBirds1.add(terence1);
		return listBirds1;
	}

	public List<Pig> addPigsInLevel1() throws IOException {
		List<Pig> listPigs1 = new ArrayList<>();
		Pig littlePig1 = new Pig(new Point( generatePigPosition(300, windowWidth-100), windowHeight - 150), SIZE_IMAGE_NORMAL, SIZE_IMAGE_NORMAL,  ImageIO.read(new File("./res/armor_pig1.png")), false, 2, 2, 0.0);
		Pig MediumPig1 = new Pig(new Point( generatePigPosition(300, windowWidth-100), windowHeight - 150), SIZE_IMAGE_NORMAL, SIZE_IMAGE_NORMAL,  ImageIO.read(new File("./res/king_pig1.png")), false, 5, 3, 0.0);
		listPigs1.add(littlePig1);
		listPigs1.add(MediumPig1);
		return listPigs1;
	}

	public int generatePigPosition( int valueMin, int valueMax ){
		Random ran = new Random();
		return (valueMin + ran.nextInt(valueMax - valueMin));
	}

	public void paint(Graphics g2) {
		//super.paint(g);
		if(buffer == null) buffer = createImage(1300, 800);
		Graphics2D g = (Graphics2D) buffer.getGraphics();

		drawLevel(g);
		drawBirds(g);
		drawPigs(g);
		drawObjectOfLevel(g);
		
		g.setFont(new Font("TimesNewRoman", Font.BOLD , 30));
		g.drawString("score : " + score, windowWidth - 200, 30);
		
		Bird b = levels[currentLevel].getListBirds().get(currentBird);
		if(selecting){
			b.setPosition(new Point(mouseX-b.getWidth()/2, mouseY-b.getWidth()/2));
			g.setColor(Color.RED);
			g.drawLine( (int)initBirdX + b.getWidth()/2, 
					(int)initBirdY + b.getWidth()/2, mouseX, mouseY);
		}
		g2.drawImage(buffer, 0, 0, null);
	}

	public void drawObjectOfLevel(Graphics g) {
		List<ObjectOfLevel> objectsOfLevel = levels[0].getListObjects();
		for(ObjectOfLevel object : objectsOfLevel)
			object.paint(g);
	}

	public void drawPigs(Graphics g) {
		List<Pig> pigs= levels[currentLevel].getListPigs();
		for(Pig p : pigs)
			p.paint(g);
	}

	public void drawBirds(Graphics g) {
		List<Bird> birds = levels[currentLevel].getListBirds();
		for(Bird b : birds)
			b.paint(g);
	}

	public void drawLevel(Graphics g) {
		Level level = levels[currentLevel];
		level.paint(g);
	}

	public void update(Graphics g){
		paint(g);
	}

	// boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
	public void run() {
		while(true) {
			// un pas de simulation toutes les 10ms
			try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }

			
			if(!selecting && !gameOver) {
				Bird b = levels[currentLevel].getListBirds().get(currentBird);
				b.updatePosition();				
				List<Pig> pigs= levels[currentLevel].getListPigs();

				for(int i = 0 ; i < pigs.size() ; i++){
					if(Point.distance(b.getPosition(), pigs.get(i).getPosition()) <  40){

						pigIsHit(pigs, i);
						nextBird();	
					}
				}

				if(b.getPosition().getX()> initBirdX)
					b.setGravity(gravity);

				if(b.getPosition().getY() >  windowHeight - 150 && (b.getVelocity().getY() > 0.001) ){
					b.getVelocity().setY(b.getVelocity().getY()*-0.8);
				}

				if(b.getPosition().getY() < 0  && b.getVelocity().getY() < -0.001){
					b.getVelocity().setY(b.getVelocity().getY()*-0.9);
				}

				if((b.getPosition().getX() + b.getWidth() > windowWidth && b.getVelocity().getX() > 0.001)
						|| b.getPosition().getX() < 0 && b.getVelocity().getX() < -0.001)
					b.getVelocity().setX(b.getVelocity().getX()*-0.9);

				if(pigs.isEmpty()){
					stop();
				}

			}
			// redessine
			repaint();
		}
	}

	public void nextBird() {
		// Si plus de birds disponible ou le niveau est terminé on stop
		if(currentBird==levels[currentLevel].getListBirds().size()-1)
			stop();
		else{ // sinon on passe au bird suivant
			selecting = true;
			currentBird++;
		}
	}

	public void pigIsHit(List<Pig> pigs, int i) {
		//Le cochons perd de la vie on change d'image
		if( pigs.get(i).getLife() > 1){
			pigs.get(i).looseLife();
			pigs.get(i).changePigs();
		}
		else{ // le cochons disparait car plus de vie
			pigs.remove(pigs.get(i));
			score ++ ;
		}
	}


	// fin de partie
	private void stop() {
		levels[currentLevel].getListBirds().get(0).getVelocity().setX(0);
		levels[currentLevel].getListBirds().get(0).getVelocity().setY(0);
		gameOver = true;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		repaint();		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(gameOver) {

			if(currentLevel+1 == levels.length){
				currentLevel = 0;
				score = 0;
			}
			else
				currentLevel++;

			try {
				init();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if(selecting) {
			Bird b = levels[currentLevel].getListBirds().get(currentBird);
			Point init = new Point(initBirdX+ b.getWidth()/2,initBirdY+ b.getWidth()/2);
			Point mouse = new Point(mouseX,mouseY);
			double x = (((int)initBirdX + b.getWidth()/2) - mouseX)/20;
			double y = (((int)initBirdY + b.getWidth()/2) - mouseY   )/20;
			double distance = Point.distance(init, mouse)/150;
			Point velocity = new Point(x * distance , y * distance);
					
//					new Point((int) ((initBirdX - mouseX) / 20.0)/**Point.distance(init, mouse)/200*/, 
//					(int) ((initBirdY - mouseY) / 20.0)/**Point.distance(init, mouse)/100*/);
			gravity=b.getGravity();
			b.setGravity(0.0001);
			b.setVelocity(velocity);
			selecting = false;
		}
		repaint();		
	}

	public Level[] getLevels() {
		return levels;
	}

	public void setLevels(Level[] levels) {
		this.levels = levels;
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	@Override
	public void mouseClicked(MouseEvent e) {	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) {	}

	@Override
	public void mouseExited(MouseEvent e) {	}

}
