package structures;

import java.util.ArrayList;
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
		GameEngine.getInstance().getGui().addImages(getHero().getHpBar().getComponents());
	}
	/*remove?*/
	private Hero getHero() {
		for (ImageTile gameElement : elements) {
			if (gameElement instanceof Hero)
				return (Hero) gameElement;
		}
		return null;
	}
	/*remove?*/
	
	private Point2D heroPosition() {
		for (ImageTile gameElement : elements) {
			if (gameElement instanceof Hero)
				return gameElement.getPosition();
		}
		return null;
	}
	
	public Point2D wayToHero(AliveGameElement elem) {
		Vector2D directionToHero = elem.getPosition().directionTo(heroPosition()).asVector();
		Point2D destination = elem.getPosition().plus(directionToHero);
		return destination;
	}
//	public Hero getHero() {
//		return hero;
//	}

	public void removeGameElement(GameElement gameElement) {
		elements.remove(gameElement);
	}
	
	public void addGameElement(GameElement gameElement) {
		elements.add(gameElement);
	}

	public void moveEnemies() {
		for (ImageTile elem : elements) {
			if (elem instanceof AliveGameElement && !(elem instanceof Hero))
				((AliveGameElement) elem).move();
		}
	}

	public ImageTile elementAt(Point2D position) {
		List<ImageTile> elementsAt = new ArrayList<>();
		// gathers elements in the same position
		for (ImageTile element : elements) {
			if (position.equals(element.getPosition())) {
				elementsAt.add(element);
			}
		}
		// if 2 elements are in the same Point2D, assigns priority
		for (ImageTile elementAt : elementsAt) {
			if (elementAt instanceof AliveGameElement) {
				return elementAt;
			}
		}
		for (ImageTile elementAt : elementsAt) {
			if (elementAt instanceof Pickable) {
				return elementAt;
			}
		}
		for (ImageTile elementAt : elementsAt) {
			if (elementAt instanceof Door) {
				return elementAt;
			}
		}
		for (ImageTile elementAt : elementsAt) {
			if (elementAt instanceof Wall) {
				return elementAt;
			}
		}
		for (ImageTile elementAt : elementsAt) {
			if (elementAt instanceof Floor) {
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
