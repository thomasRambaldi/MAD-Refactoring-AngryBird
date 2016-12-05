import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import refactoring.game.Game;
import refactoring.level.Difficulties;
import refactoring.level.Level;
import refactoring.objects.Bird;
import refactoring.objects.Pig;

public class Main  {

	private final static String fileName = "./res/levels.txt";
	private static List<Bird> listBirds = new ArrayList<>();
	private static List<Pig> listPigs = new ArrayList<>();;
	private static List<Object> listObjects = new ArrayList<>();
	private static Level[] levels;
	private static int maxLevel = 2;
	private static int currentLevel = 0;

	// met le jeu dans une fenêtre
	public static void main(String args[]) throws IOException {

		levels = new Level[maxLevel];

		levels[0] = new Level(Difficulties.EASY, listBirds, listPigs, listObjects, ImageIO.read(new File("./res/nature.jpg")), 1);
		levels[1] = new Level(Difficulties.EASY, listBirds, listPigs, listObjects, ImageIO.read(new File("./res/marais.jpg")), 1);

		Game game = new Game(levels);

		game.setBackground(Color.WHITE);
		Frame frame = new Frame("Oiseau pas content");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});

		//		readFile();

		frame.setPreferredSize(new Dimension(1500,800));
		frame.add(game);
//		checkCurrentLevel(frame);
		frame.pack();
		frame.setVisible(true);
	}

	public static void checkCurrentLevel(Frame frame){
		if( currentLevel < maxLevel && levels[currentLevel].isWin()  )
			currentLevel++;
		frame.add(levels[currentLevel]);
	}

	public static void readFile(  ) throws IOException{

		BufferedReader br = null;
		String line;
		String[] containsLine = null;

		try
		{
			br = new BufferedReader(new FileReader( fileName ));
			line = br.readLine();	// Passer la premier ligne
			while ((line = br.readLine()) != null){
				System.out.println(line);
				containsLine = line.split(" ");
			}
			br.close();
		}
		catch(FileNotFoundException exc)
		{
			System.out.println("Erreur d'ouverture du fichier " + fileName);
		}finally {
			br.close();
		}

	}

}
