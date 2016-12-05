import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import refactoring.game.Game;

public class Main  {

	private final static String fileName = "./res/levels.txt";
	
	// met le jeu dans une fen�tre
	public static void main(String args[]) throws IOException {

		int w = 1300;
		int h = 800;
		
		
		Frame frame = new Frame("Oiseau pas content");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});

		Game game = new Game(w, h);
		frame.setPreferredSize(new Dimension(w, h));
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
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
