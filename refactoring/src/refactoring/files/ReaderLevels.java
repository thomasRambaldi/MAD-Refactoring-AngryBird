package refactoring.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import refactoring.level.Level;
import refactoring.objects.Bird;
import refactoring.objects.ObjectFactory;
import refactoring.objects.ObjectOfLevel;
import refactoring.objects.Pig;
import refactoring.point.Point;

public class ReaderLevels {
	private ObjectFactory objectFactory;
	private int windowWidth, windowHeight;
	private int FLOOR;
	
	public ReaderLevels(ObjectFactory objectFactory, int windowWidth, int windowHeight, int FLOOR) {
		this.objectFactory = objectFactory;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.FLOOR = FLOOR;
	}
	
	public ArrayList<Level> readLevelsXML(){
		ArrayList<Level> levels = new ArrayList<Level>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setIgnoringComments(true);

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("res/levels.xml");
			Element racine = doc.getDocumentElement();
			NodeList list = racine.getChildNodes();
			for(int i=0; i < list.getLength(); i++){
				Node level = list.item(i);
				if(level.getNodeType()==Node.ELEMENT_NODE){
					levels.add(getLevel(level));
				}	
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return levels;
	}

	private Level getLevel(Node nodeLevel) {
		Level level = new Level();
		NodeList infosLevel = nodeLevel.getChildNodes();
		for(int i=0; i < infosLevel.getLength(); i++){
			Node infoLevel = infosLevel.item(i);
			if(infoLevel.getNodeType()==Node.ELEMENT_NODE){
				switch (infoLevel.getNodeName()) {
				case "background": try { level.setBackground(ImageIO.read(new File("./res/"+infoLevel.getTextContent())));}
				catch (IOException e) {e.printStackTrace();} break;
				case "birds"   : level.setListBirds(getBirds(infoLevel)); break;
				case "pigs"    : level.setListPigs(getPigs(infoLevel)); break;
				case "objects" : level.setListObjects(getObjects(infoLevel)); break;
				default: break;
				}
			}	
		}
		if(level.getListObjects().isEmpty()){
			ArrayList<ObjectOfLevel> objects = new ArrayList<ObjectOfLevel>();
			ObjectOfLevel lancePierre = (ObjectOfLevel) objectFactory.createObject("Slingshot");
			lancePierre.setPosition(new Point(windowWidth/6, windowHeight/1.3));
			objects.add(lancePierre);
			level.setListObjects(objects);
		}
		return level;
	}

	private List<ObjectOfLevel> getObjects(Node nodeObjects) {
		ArrayList<ObjectOfLevel> objects = new ArrayList<ObjectOfLevel>();
		ObjectOfLevel lancePierre = (ObjectOfLevel) objectFactory.createObject("Slingshot");
		lancePierre.setPosition(new Point(windowWidth/6, windowHeight/1.3));
		objects.add(lancePierre);
		NodeList infoObjects = nodeObjects.getChildNodes();
		for(int i=0; i < infoObjects.getLength(); i++){
			Node infoObject = infoObjects.item(i);
			if(infoObject.getNodeType()==Node.ELEMENT_NODE){
				objects.add(getObject(infoObject));
			}	
		}
		return objects;
	}

	private ObjectOfLevel getObject(Node nodeObject) {
		ObjectOfLevel object =null;
		NodeList infosObject = nodeObject.getChildNodes();
		for(int i=0; i < infosObject.getLength(); i++){
			Node itemObject = infosObject.item(i);
			if(itemObject.getNodeType()==Node.ELEMENT_NODE){
				if(itemObject.getNodeName().equals("type"))
					object=getObjectOfLevelByType(Integer.parseInt(itemObject.getTextContent()));
				if(itemObject.getNodeName().equals("position")){
					object.setPosition(getPosition(itemObject));
				} 
			}	
		}
		return object;
	}

	private List<Pig> getPigs(Node nodePigs) {
		ArrayList<Pig> pigs = new ArrayList<Pig>();
		NodeList infoPigs = nodePigs.getChildNodes();
		for(int i=0; i < infoPigs.getLength(); i++){
			Node infoPig = infoPigs.item(i);
			if(infoPig.getNodeType()==Node.ELEMENT_NODE){
				pigs.add(getPig(infoPig));
			}	
		}
		return pigs;
	}

	private Pig getPig(Node nodePig) {
		Pig pig =null;
		NodeList infosPig = nodePig.getChildNodes();
		for(int i=0; i < infosPig.getLength(); i++){
			Node itemPig = infosPig.item(i);
			if(itemPig.getNodeType()==Node.ELEMENT_NODE){
				if(itemPig.getNodeName().equals("type"))
					pig=getPigByType(Integer.parseInt(itemPig.getTextContent()));
				if(itemPig.getNodeName().equals("position")){
					pig.setPosition(getPosition(itemPig));
				} 
			}	
		}
		return pig;
	}

	private Point getPosition(Node nodePosition) {
		Point pointPos = new Point(1, 1);
		NodeList position = nodePosition.getChildNodes();
		for(int i=0; i < position.getLength(); i++){
			Node pos = position.item(i);
			if(pos.getNodeType()==Node.ELEMENT_NODE){
				if(pos.getNodeName().equals("x")){
					if(pos.getTextContent().equals("random"))
						pointPos.setX(generatePigPosition(300, windowWidth-100));
					else //TODO utile que pour les objectsoflevel pour l'instant mettre plus tard -> Double.parseDouble(pos.getTextContent())
						pointPos.setX(windowWidth/Double.parseDouble(pos.getTextContent()));
				}
				else if(pos.getNodeName().equals("y")){
					if(pos.getTextContent().equals("random"))
						pointPos.setY(windowHeight - 140);
					else
						pointPos.setY(windowHeight/Double.parseDouble(pos.getTextContent()));

				}	
			}
		}
		return pointPos;
	}

	private List<Bird> getBirds(Node nodeBirds) {
		ArrayList<Bird> birds = new ArrayList<Bird>();
		NodeList infoBirds = nodeBirds.getChildNodes();
		for(int i=0; i < infoBirds.getLength(); i++){
			Node infoBird = infoBirds.item(i);
			if(infoBird.getNodeType()==Node.ELEMENT_NODE){
				birds.add(getBird(infoBird));
			}	
		}
		setBirdsPosition(birds);
		return birds;
	}

	private Bird getBird(Node nodeBird) {
		NodeList infosBird = nodeBird.getChildNodes();
		for(int i=0; i < infosBird.getLength(); i++){
			Node typeBird = infosBird.item(i);
			if(typeBird.getNodeType()==Node.ELEMENT_NODE && typeBird.getNodeName().equals("type")){
				return getBirdByType(Integer.parseInt(typeBird.getTextContent()));
			}	
		}
		return getBirdByType(0);
	}

	private void setPigPosition(ArrayList<Pig> pigs) {
		for(Pig pig : pigs)
			pig.setPosition(new Point( generatePigPosition(300, windowWidth-100), windowHeight - 140));
	}

	private void setBirdsPosition(ArrayList<Bird> birds) {
		int  afterLancePierre = windowWidth/6; 
		int pas = 180/birds.size();
		for(int i = 1; i < birds.size() ; i++)
			birds.get(i).setPosition(new Point(afterLancePierre - pas*i, FLOOR));

	}

	private Bird getBirdByType(int type) {
		if(type == 0)
			return (Bird) objectFactory.createObject("Little Red Bird");
		if(type == 1)
			return (Bird) objectFactory.createObject("Yellow Bird");
		if(type == 2)
			return (Bird) objectFactory.createObject("Bomb Bird");
		if(type == 3)
			return (Bird) objectFactory.createObject("Terence Bird");
		return (Bird) objectFactory.createObject("Little Red Bird");
	}

	private Pig getPigByType(int type) {
		if(type == 0)
			return (Pig) objectFactory.createObject("Normal Pig");
		if(type == 1)
			return (Pig) objectFactory.createObject("Armor Pig");
		if(type == 2)
			return (Pig) objectFactory.createObject("King Pig");
		return (Pig) objectFactory.createObject("Normal Pig");
	}

	private ObjectOfLevel getObjectOfLevelByType(int type) {
		if(type == 0)
			return (ObjectOfLevel) objectFactory.createObject("Slingshot");
		if(type == 1)
			return (ObjectOfLevel) objectFactory.createObject("Pipe");
		return (ObjectOfLevel) objectFactory.createObject("Pipe");
	}
	
	public int generatePigPosition( int valueMin, int valueMax ){
		Random ran = new Random();
		return (valueMin + ran.nextInt(valueMax - valueMin));
	}
	
	public ArrayList<Level> readLevels() {
		ArrayList<Level> levels = new ArrayList<Level>();
		String background = null;
		try(BufferedReader br = new BufferedReader(new FileReader("res/levels"))) {
			for(String line = br.readLine() ; line != null ; line=br.readLine()){
				if(line.isEmpty()) continue;
				String [] data = line.split(" ");

				if(data.length==1){
					background=line;
					continue;
				}

				Level level = new Level();
				level.setBackground(ImageIO.read(new File("./res/"+background)));
				ArrayList<Bird> listBirds = new ArrayList<Bird>();
				ArrayList<Pig> listPigs = new ArrayList<Pig>();
				ArrayList<ObjectOfLevel> listObjects = new ArrayList<ObjectOfLevel>();

				ObjectOfLevel lancePierre = (ObjectOfLevel) objectFactory.createObject("Slingshot");
				lancePierre.setPosition(new Point(windowWidth/6, windowHeight/1.3));
				listObjects.add(lancePierre);

				for(int i = 0; i < data.length ; i++){
					int typeObject = Integer.parseInt(data[i]);
					switch(typeObject){
					case 0 : listBirds.add(getBirdByType(Integer.parseInt(data[i+1]))); i = i + 1 ; break;
					case 1 : listPigs.add(getPigByType(Integer.parseInt(data[i+1]))); i = i + 1 ; break;
					case 2 : ObjectOfLevel o = getObjectOfLevelByType(Integer.parseInt(data[i+1]));
					o.setPosition(new Point(windowWidth/Double.parseDouble(data[i+2]), windowHeight/Double.parseDouble(data[i+3]))); 
					listObjects.add(o); i = i + 3; break;
					}	
				}
				setBirdsPosition(listBirds);
				setPigPosition(listPigs);
				level.setListBirds(listBirds);
				level.setListPigs(listPigs);
				level.setListObjects(listObjects);
				levels.add(level);
			}
		} catch (FileNotFoundException e) {	e.printStackTrace();
		} catch (IOException e) { e.printStackTrace(); }
		return levels;
	}
}
