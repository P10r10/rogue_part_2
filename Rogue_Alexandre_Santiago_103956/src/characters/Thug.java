package characters;

import java.util.Random;

import Interfaces.Movable;
import logic.AliveGameElement;
import logic.GameEngine;
import pt.iscte.poo.utils.Point2D;
import structures.Door;
import structures.Room;
import structures.Wall;

public class Thug extends AliveGameElement implements Movable {

	public Thug(Point2D position, String room) {
		super(position, room, 10); // initial hp = 10
		setLayer(6);
	}

	@Override
	public String getName() {
		if (getHp() <= 0) {
			setLayer(4);
			return "DeadThug";
		}
		return "Thug";
	}

	@Override
	public void move() {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		Point2D destination = thisRoom.wayToHero(this);

		if (thisRoom.elementAt(destination) instanceof Hero) { // attacks hero
			if (new Random().nextDouble() <= 0.3) { // only damages in 30% of attacks
				((Hero) thisRoom.elementAt(destination)).takesDamage(3);
			}
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
