package structures;

import java.util.ArrayList;
import java.util.List;

import characters.Hero;
import interfaces.Movable;
import interfaces.Pickable;
import logic.GameElement;
import logic.GameEngine;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Room {

	private String name;
	private List<GameElement> elements;
	private Hero hero = Hero.getInstance();// added review

	public Room(String name, List<GameElement> elements) {
		this.name = name;
		this.elements = elements;
	}
	
	public void load() {
		GameEngine.getInstance().getGui().clearImages();
		for (ImageTile image : elements) {
			GameEngine.getInstance().getGui().addImage(image);
		}
		GameEngine.getInstance().getGui().addImage(hero);
		GameEngine.getInstance().getGui().addImages(hero.getHpBar().getComponents());
		System.out.println("BREAK!");
		elements.forEach(e -> System.out.println(e));
	}
	
	/*remove*/
	public List<GameElement> getElements() {
		return elements;
	}
	/*remove*/

	public Hero getHero() {
		return hero;
	}

	public void removeGameElement(ImageTile gameElement) {
		elements.remove(gameElement);
	}

	public void moveEnemies() {
		for (ImageTile gameElement : elements) {
			if (gameElement instanceof Movable)
				((Movable) gameElement).move();
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
