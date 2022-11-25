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
	private List<GameElement> elements;
	//private Hero hero = Hero.getInstance();// added review

	public Room(String name, List<GameElement> elements) {
		this.name = name;
		this.elements = elements;
	}
	
	public void load() {
		GameEngine.getInstance().getGui().clearImages();
		for (ImageTile image : elements) {
			GameEngine.getInstance().getGui().addImage(image);
		}
	//	GameEngine.getInstance().getGui().addImage(hero);
	//	GameEngine.getInstance().getGui().addImages(hero.getHpBar().getComponents());
	}
	
	/*remove?*/
	public List<GameElement> getElements() {
		return elements;
	}
	/*remove?*/

	private Point2D heroPosition() {
		for (GameElement gameElement : elements) {
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
		for (GameElement gameElement : elements) {
			if (gameElement instanceof AliveGameElement)
				((AliveGameElement) gameElement).move();
		}
	}

	public GameElement elementAt(Point2D position) {
		List<GameElement> elementsAt = new ArrayList<>();
		// gathers elements in the same position
		for (GameElement element : elements) {
			if (position.equals(element.getPosition())) {
				elementsAt.add(element);
			}
		}
		// if 2 elements are in the same Point2D, assigns priority
		for (GameElement elementAt : elementsAt) {
			if (elementAt instanceof Movable) {
				return elementAt;
			}
		}
		for (GameElement elementAt : elementsAt) {
			if (elementAt instanceof Pickable) {
				return elementAt;
			}
		}
		for (GameElement elementAt : elementsAt) {
			if (elementAt instanceof Door) {
				return elementAt;
			}
		}
		for (GameElement elementAt : elementsAt) {
			if (elementAt instanceof Wall) {
				return elementAt;
			}
		}
		for (GameElement elementAt : elementsAt) {
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
