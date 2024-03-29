package structures;

import java.util.List;

import characters.Hero;
import interfaces.Movable;
import interfaces.Pickable;
import logic.AliveGameElement;
import logic.GameElement;
import logic.GameEngine;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Room {

	private String name;
	private List<ImageTile> elements;

	public Room(String name, List<ImageTile> elements) {
		this.name = name;
		this.elements = elements;
	}

	public void load() {
		GameEngine.getInstance().getGui().clearImages();
		GameEngine.getInstance().getGui().addImages(elements);
		GameEngine.getInstance().getGui().addImages(getHero().getHpAndItemBar().getComponents());
	}

	public Hero getHero() {
		for (ImageTile gameElement : elements) {
			if (gameElement instanceof Hero)
				return (Hero) gameElement;
		}
		return null;
	}

	public Point2D wayToHero(AliveGameElement elem) {
		Vector2D directionToHero = elem.getPosition().directionTo(getHero().getPosition()).asVector();
		Point2D destination = elem.getPosition().plus(directionToHero);
		return destination;
	}

	public void removeGameElement(ImageTile gameElement) {
		elements.remove(gameElement);
	}

	public void addGameElement(GameElement gameElement) {
		elements.add(gameElement);
	}

	public void moveEnemies() {
		for (ImageTile elem : elements) {
			if (elem instanceof AliveGameElement && !(elem instanceof Hero))
				((Movable) elem).move();
		}
	}

	public ImageTile elementAt(Point2D position) { // if 2 elements are in the same Point2D, assigns priority
		for (ImageTile elementAt : elements) {
			if (elementAt instanceof AliveGameElement && position.equals(elementAt.getPosition())) {
				return elementAt;
			}
		}
		for (ImageTile elementAt : elements) {
			if (elementAt instanceof Pickable && position.equals(elementAt.getPosition())) {
				return elementAt;
			}
		}
		for (ImageTile elementAt : elements) {
			if (elementAt instanceof Door && position.equals(elementAt.getPosition())) {
				return elementAt;
			}
		}
		for (ImageTile elementAt : elements) {
			if (elementAt instanceof Wall && position.equals(elementAt.getPosition())) {
				return elementAt;
			}
		}
		for (ImageTile elementAt : elements) {
			if (elementAt instanceof Floor && position.equals(elementAt.getPosition())) {
				return elementAt;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return name;
	}
}
