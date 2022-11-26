package characters;

import logic.AliveGameElement;
import logic.GameEngine;
import pt.iscte.poo.utils.Point2D;
import structures.Door;
import structures.Room;
import structures.Wall;

public class Thug extends AliveGameElement {

	public Thug(Point2D position, String room) {
		super(position, room, 10); // initial hp = 10
		setLayer(1);
	}

	@Override
	public String getName() {
		if (getHp() <= 0) {
			setLayer(0);
			return "DeadThug";
		}
		return "Thug";
	}

	@Override
	public void move() {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		Point2D destination = thisRoom.wayToHero(this);

		if (thisRoom.elementAt(destination) instanceof Hero) { // attacks hero
			((Hero) thisRoom.elementAt(destination)).takesDamage(1);// specialize
		} else if (thisRoom.elementAt(destination) instanceof Wall || thisRoom.elementAt(destination) instanceof Door
				|| thisRoom.elementAt(destination) instanceof AliveGameElement) {
			return; // can't cross walls, doors or other creatures
		} else { // can move freely
			setPosition(destination);
		}
	}
}
