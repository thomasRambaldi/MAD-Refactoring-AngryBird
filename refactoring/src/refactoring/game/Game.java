package refactoring.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import refactoring.level.Difficulties;
import refactoring.level.Level;
import refactoring.objects.Bird;
import refactoring.objects.ObjectOfLevel;
import refactoring.objects.Pig;
import refactoring.point.Point;



public class Game extends JPanel implements Runnable, MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;

	private Level[] levels;
	boolean selecting = false;

	private int mouseX, mouseY, score;
	private boolean gameOver;
	private int windowWidth, windowHeight;

	public Game(int windowWidth, int windowHeight) throws IOException {
		super();
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		init();
	}

	public void init() throws IOException{
		Level[] levels = new Level[2];

		List<Bird> listBirds = new ArrayList<>();
		
		Bird red = new Bird(new Point(windowWidth/6 - 30, windowHeight/1.3 - 70), 40, 40, ImageIO.read(new File("./res/red.png")), true, 0, new Point(5, 5));
		Bird chuck = new Bird(new Point(windowWidth/6 - 50, windowHeight/1.3 + 30), 40, 40, ImageIO.read(new File("./res/chuck.png")), true, 0, new Point(5, 5));
		listBirds.add(red);
		listBirds.add(chuck);

	
		List<Pig> listPigs = new ArrayList<>();
		List<ObjectOfLevel> listObjects = new ArrayList<>();
		ObjectOfLevel lancePierre = new ObjectOfLevel(new Point(windowWidth/6, windowHeight/1.3), 60, 150, ImageIO.read(new File("./res/lance-pierre.png")), false, 0);
		listObjects.add(lancePierre);
		
		
		
		levels[0] = new Level(Difficulties.EASY, listBirds, listPigs, listObjects, ImageIO.read(new File("./res/nature.jpg")), 1);
		levels[1] = new Level(Difficulties.EASY, listBirds, listPigs, listObjects, ImageIO.read(new File("./res/marais.jpg")), 1);

		this.levels=levels;
		this.score = 0;
		gameOver = false;
		addMouseListener(this);
		addMouseMotionListener(this);
		new Thread(this).start();
	}

	public void paint(Graphics g) {
		super.paint(g);

		// Draw the background image.
		Level level = levels[0];
		level.paint(g);
		
		
		List<Bird> birds = levels[0].getListBirds();
		for(Bird b : birds)
			b.paint(g);
		//		g.drawImage(b.getImage(), (int) b.getPosition().getX() - 20, (int) b.getPosition().getY() - 20,40,40, this);
		
		
		List<ObjectOfLevel> objectsOfLevel = levels[0].getListObjects();
		for(ObjectOfLevel object : objectsOfLevel)
			object.paint(g);
	}

	public void update(Graphics g){
		paint(g);
	}

	// boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
	public void run() {
		while(true) {
			// un pas de simulation toutes les 10ms
			try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }
			//			System.out.println("hello");
			//
			//            if(!gameOver && !selecting) {
			//
			//                // moteur physique
			//                birdX += velocityX;
			//                birdY += velocityY;
			//                velocityY += gravity;
			//
			//                // conditions de victoire
			//                if(distance(birdX, birdY, pigX, pigY) < 35) {
			//                    stop();
			//                    message = "Gagn� : cliquez pour recommencer.";
			//                    score++;
			//                } else if(birdX < 20 || birdX > 780 || birdY < 0 || birdY > 480) {
			//                    stop();
			//                    message = "Perdu : cliquez pour recommencer.";
			//                }
			//
			// redessine
			//			                repaint();
			//            }
		}
	}

	// fin de partie
	void stop() {
		//		velocityX = 0;
		//		velocityY = 0;
		gameOver = true;
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
				try {
					init();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		} else if(selecting) {
//			velocityX = (birdX - mouseX) / 20.0;
//			velocityY = (birdY - mouseY) / 20.0;
//			message = "L'oiseau prend sont envol";
			selecting = false;
		}
		repaint();		
	}

	@Override
	public void mouseClicked(MouseEvent e) {	}

	@Override
	public void mousePressed(MouseEvent e) {	}

	@Override
	public void mouseEntered(MouseEvent e) {	}

	@Override
	public void mouseExited(MouseEvent e) {	}


}
