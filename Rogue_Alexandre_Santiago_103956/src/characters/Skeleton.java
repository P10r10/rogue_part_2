package characters;

import logic.AliveGameElement;
import logic.GameEngine;
import pt.iscte.poo.utils.Point2D;
import structures.Door;
import structures.Room;
import structures.Wall;

public class Skeleton extends AliveGameElement {

	public Skeleton(Point2D position, String room) {
		super(position, room, 5); // initial hp = 5
	}

	@Override
	public String getName() {
		if (getHp() <= 0) {
			setLayer(0);
			return "DeadSkeleton";
		}
		return "Skeleton";
	}

	@Override
	public void move() {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		Point2D destination = thisRoom.wayToHero(this);

		if (GameEngine.getInstance().getTurns() % 2 == 1) {
			return; // towards Hero only 50% of turns
		} 
		if (thisRoom.elementAt(destination) instanceof Wall || thisRoom.elementAt(destination) instanceof Door) {
			return; // can't cross walls or doors
		}
		if (thisRoom.elementAt(destination) instanceof Hero) { // attacks hero
			((Hero) thisRoom.elementAt(destination)).takesDamage(1);
		} else { // can move freely
			setPosition(destination);
		}
	}
}
