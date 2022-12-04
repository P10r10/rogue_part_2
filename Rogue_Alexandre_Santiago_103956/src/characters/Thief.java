package characters;

import java.util.Map;
import java.util.Random;

import interfaces.AwardsPoints;
import interfaces.Movable;
import interfaces.Pickable;
import logic.AliveGameElement;
import logic.GameElement;
import logic.GameEngine;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;
import structures.Door;
import structures.Room;
import structures.Wall;

public class Thief extends AliveGameElement implements Movable, AwardsPoints {
	
	ImageTile stolenItem = null;

	public Thief(Point2D position, String room) {
		super(position, room, 5); // initial hp = 5
		setLayer(6);
	}
	
	@Override
	public int points() {
		return 13;
	}

	@Override
	public String getName() {
		if (getHp() <= 0) {
			setLayer(4);
			return "DeadThief";
		}
		return "Thief";
	}
	
	private void steal(Hero hero) {
		Map<Integer, ImageTile> items = hero.getHpAndItemBar().getItems();
		do {
			stolenItem = hero.getHpAndItemBar().removeItem(new Random().nextInt(3));
		} while (stolenItem == null && items.size() > 0);
		if (stolenItem != null) {
			((GameElement) stolenItem).setLayer(0);
		}
	}
	
	public void drop() {
		if (stolenItem != null) {
			Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
			((Pickable) stolenItem).isPicked(false);
			((GameElement) stolenItem).setPosition(getPosition());
			thisRoom.addGameElement(((GameElement) stolenItem));
			GameEngine.getInstance().getGui().update();
		}
	}

	@Override
	public void move() {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		Point2D destination = thisRoom.wayToHero(this);
		if (stolenItem != null) {
			destination = thisRoom.awayFromHero(this);//TODO melhorar
		}

		if (thisRoom.elementAt(destination) instanceof Hero) { // attacks hero
			steal((Hero) thisRoom.elementAt(destination));
		} else if (thisRoom.elementAt(destination) instanceof Wall || thisRoom.elementAt(destination) instanceof Door
				|| thisRoom.elementAt(destination) instanceof AliveGameElement) {
			return; // can't cross walls, doors or other creatures
		} else { // can move freely
			setPosition(destination);
		}
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
