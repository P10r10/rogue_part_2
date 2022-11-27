package characters;

import logic.AliveGameElement;
import logic.GameEngine;
import pt.iscte.poo.utils.Point2D;
import structures.Door;
import structures.Room;
import structures.Wall;

public class Scorpio extends AliveGameElement {
	
	public Scorpio(Point2D position, String room) {
		super(position, room, 2); // initial hp = 2
		setLayer(4);
	}

	@Override
	public String getName() {
		if (getHp() <= 0) {
			setLayer(3);
			return "DeadSkeleton";//create dead scorpio
		}
		return "Scorpio";
	}

	@Override
	public void move() {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		Point2D destination = thisRoom.wayToHero(this);
		
		if (thisRoom.elementAt(destination) instanceof Hero) { // attacks hero
			((Hero) thisRoom.elementAt(destination)).setPoisoned();
		} else if (thisRoom.elementAt(destination) instanceof Wall || thisRoom.elementAt(destination) instanceof Door
				|| thisRoom.elementAt(destination) instanceof AliveGameElement) {
			return; // can't cross walls, doors or other creatures
		} else { // can move freely
			setPosition(destination);
		}	
	}
}
