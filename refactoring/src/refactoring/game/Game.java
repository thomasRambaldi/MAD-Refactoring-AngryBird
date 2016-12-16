package refactoring.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp.Factory;
import refactoring.files.ReaderLevels;
import refactoring.level.Difficulties;
import refactoring.level.Level;
import refactoring.objects.AbstractObject;
import refactoring.objects.Bird;
import refactoring.objects.ObjectFactory;
import refactoring.objects.ObjectOfLevel;
import refactoring.objects.Pig;
import refactoring.point.Point;

/*
 * corriger les problemes de colisions avec les birds plus gros
 */
public class Game extends JPanel implements Runnable, MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;

	private ArrayList<Level> levels;
	private int currentLevel;
	private int currentBird;
	private int currentPig;
	private  boolean selecting;
	private final int FLOOR;

	private int initBirdX, initBirdY;

	private int mouseX, mouseY, score;
	private boolean gameOver;
	private int windowWidth, windowHeight;

	private double gravity;

	private Image buffer;

	private ObjectFactory objectFactory;

	private Clip clipWin=null;
	
	private ReaderLevels reader;

	private boolean isOnSlingshot;

	private double acceleration;


	/** Instance unique non pr�initialis�e */
	private static Game INSTANCE = null;

	/** Point d'acc�s pour l'instance unique du singleton */
	public static Game getInstance( int windowWidth, int windowHeight )
	{			
		if (INSTANCE == null)
		{ 	
			try {
				INSTANCE = new Game(windowWidth, windowHeight);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return INSTANCE;
	}

	private void initClip(String soundName) {
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		} catch (UnsupportedAudioFileException | IOException e1) {
			e1.printStackTrace();
		}

		try {
			clipWin = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			clipWin.open(audioInputStream);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private Game(int windowWidth, int windowHeight) throws IOException {
		super();
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.score = 0;
		currentLevel = -1;
		objectFactory = new ObjectFactory();
		FLOOR = windowHeight - 140;
		reader = new ReaderLevels(objectFactory, windowWidth, windowHeight, FLOOR);
		ArrayList<Level> levels = reader.readLevelsXML();
		this.levels=levels;


		initClip("res/yeah.wav");
		init();
		addMouseListener(this);
		addMouseMotionListener(this);
		new Thread(this).start();
	}

	

	

	public void init(){
		currentBird = 0;
		currentPig = 0;
		selecting = true;
		initBirdX = (int) (windowWidth/6 - 30) ;
		initBirdY = (int) (windowHeight/1.3 - 70) ;
		gameOver = false;
		isOnSlingshot=false;

		if(currentLevel+1 == levels.size()){
			currentLevel = 0;
			score = 0;
			levels=reader.readLevelsXML();
		}
		else
			currentLevel++;

		levels.get(currentLevel).getListBirds().get(0).setPosition(new Point(initBirdX, initBirdY));
		mouseX = initBirdX;
		mouseY = initBirdY;
	}

	

	public void paint(Graphics g2) {
		if(buffer == null) buffer = createImage(1300, 800);
		Graphics2D g = (Graphics2D) buffer.getGraphics();

		levels.get(currentLevel).paint(g);
		drawScore(g);
		drawSelectionEvent(g);

		g2.drawImage(buffer, 0, 0, null);
	}

	private void drawScore(Graphics g){
		g.setFont(new Font("TimesNewRoman", Font.BOLD , 30));
		g.drawString("score : " + score, windowWidth - 200, 30);
	}

	private void drawSelectionEvent(Graphics g){
		//		Bird b = levels[currentLevel].getListBirds().get(currentBird);
		Bird b = levels.get(currentLevel).getListBirds().get(currentBird);
		if(selecting){
			b.setPosition(new Point(mouseX, mouseY));
			g.setColor(Color.RED);
			g.drawLine( (int)initBirdX + b.getWidth()/2, 
					(int)initBirdY + b.getWidth()/2, mouseX, mouseY);
		}
	}

	public void update(Graphics g){
		paint(g);
	}

	// boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
	public void run() {
		while(true) {
			// un pas de simulation toutes les 10ms
			try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }


			if(isOnSlingshot){
				Bird b = levels.get(currentLevel).getListBirds().get(currentBird);
				b.acceleratePosition(1.05);
				if(b.getPosition().getX()> initBirdX)
					isOnSlingshot=false;
			}

			if(!selecting && !gameOver) {
				Bird b = levels.get(currentLevel).getListBirds().get(currentBird);
				b.updatePosition(windowWidth, FLOOR);				
				List<Pig> pigs= levels.get(currentLevel).getListPigs();
				List<ObjectOfLevel> objects = levels.get(currentLevel).getListObjects();
				for(int i = 0 ; i < pigs.size() ; i++){
					Pig p = pigs.get(i);
					if(p.isCollided(b)){
						p.actionCollision();
						if(p.getLife()==0){
							pigs.remove(p);
							score ++ ;
						}
						nextBird();	
						break;
					}
				}

				for(int i = 1 ; i < objects.size() ; i++){
					if(objects.get(i).isCollided(b)){	
						nextBird();
					}
				}

				if(b.isStop())
					nextBird();

				if(pigs.isEmpty()){
					clipWin.setFramePosition(0);
					clipWin.setMicrosecondPosition(400000);
					clipWin.start();
					stop();
				}

			}
			// redessine
			repaint();
		}
	}

	public void nextBird() {
		// Si plus de birds disponible ou le niveau est terminé on stop
		//		if(currentBird==levels[currentLevel].getListBirds().size()-1)
		if(currentBird==levels.get(currentLevel).getListBirds().size()-1){
			stop();
		}	
		else{ // sinon on passe au bird suivant
			selecting = true;
			isOnSlingshot=false;
			currentBird++;
			levels.get(currentLevel).getListBirds().get(currentBird).setPosition(new Point(initBirdX, initBirdY));
			mouseX = initBirdX;
			mouseY = initBirdY;
		}
	}


	// fin de partie
	private void stop() {
		//		levels[currentLevel].getListBirds().get(0).getVelocity().setX(0);
		//		levels[currentLevel].getListBirds().get(0).getVelocity().setY(0);
		levels.get(currentLevel).getListBirds().get(0).getVelocity().setX(0);
		levels.get(currentLevel).getListBirds().get(0).getVelocity().setY(0);
		gameOver = true;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		repaint();
		//		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		repaint();		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(gameOver) {
			init();
		} else if(selecting) {
			Bird b = levels.get(currentLevel).getListBirds().get(currentBird);
			Point init = new Point(initBirdX+ b.getWidth()/2,initBirdY+ b.getWidth()/2);
			Point mouse = new Point(mouseX,mouseY);
			double x = (((int)initBirdX + b.getWidth()/2) - mouseX)/20;
			double y = (((int)initBirdY + b.getWidth()/2) - mouseY   )/20;
			double distance = Point.distance(init, mouse)/150;
			acceleration = Point.distance(init, mouse)/150;
			Point velocity = new Point(x * distance , y * distance);

			//					new Point((int) ((initBirdX - mouseX) / 20.0)/**Point.distance(init, mouse)/200*/, 
			//					(int) ((initBirdY - mouseY) / 20.0)/**Point.distance(init, mouse)/100*/);
			gravity=b.getGravity();
			//			b.setGravity(0.0001);
			isOnSlingshot=true;
			b.setVelocity(velocity);
			selecting = false;
		}
		repaint();		
	}

	public ArrayList<Level> getLevels() {
		return levels;
	}

	public void setLevels(ArrayList<Level> levels) {
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
	public void mouseClicked(MouseEvent e) {
		if(!selecting && !gameOver){
			Bird b = levels.get(currentLevel).getListBirds().get(currentBird);
			switch(b.getTypeObject()){
			case 1 : break;
			case 2 : break;
			default : break;
			}

		}

	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) {	}

	@Override
	public void mouseExited(MouseEvent e) {	}

}
