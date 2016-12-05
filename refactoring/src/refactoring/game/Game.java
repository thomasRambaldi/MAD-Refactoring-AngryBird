package refactoring.game;

import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import refactoring.level.Level;


public class Game extends Panel implements MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;

	private Level[] levels;
	
	private int mouseX, mouseY, score;
	private boolean gameOver;
	Image background;

	public Game() {
		super();
		init();
		this.levels = new Level[2] ;
	}

	public Game(Level[] levels) {
		super();
		init();
		this.levels = levels;
	}
	
	public void init(){
		System.out.println("Constructeur de Game");
		this.score = 0;
		gameOver = false;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	// boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
	public void run() {
		while(true) {
			// un pas de simulation toutes les 10ms
			try { 
				Thread.currentThread();
				Thread.sleep(10); 
			} catch(InterruptedException e) { }
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
			//                    message = "Gagné : cliquez pour recommencer.";
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
	public void mouseDragged(java.awt.event.MouseEvent e) {
		//		mouseMoved(e);
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		//		mouseX = e.getX();
		//		mouseY = e.getY();
		//		repaint();		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		//		if(gameOver) {
		//			init();
		//		} else if(selecting) {
		//			velocityX = (birdX - mouseX) / 20.0;
		//			velocityY = (birdY - mouseY) / 20.0;
		//			message = "L'oiseau prend sont envol";
		//			selecting = false;
		//		}
		//		repaint();		
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {	}


}
