package characters;

import Interfaces.Movable;
import logic.AliveGameElement;
import logic.GameEngine;
import pt.iscte.poo.utils.Point2D;
import structures.Door;
import structures.Room;
import structures.Wall;

public class Scorpio extends AliveGameElement implements Movable {
	
	public Scorpio(Point2D position, String room) {
		super(position, room, 2); // initial hp = 2
		setLayer(6);
	}

	@Override
	public String getName() {
		if (getHp() <= 0) {
			setLayer(4);
			return "DeadScorpio";
		}
		return "Scorpio";
	}

	@Override
	public void move() {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		Point2D destination = thisRoom.wayToHero(this);
		
		if (thisRoom.elementAt(destination) instanceof Hero) { // attacks hero
			((Hero) thisRoom.elementAt(destination)).setPoisoned();
			System.out.println("You are poisoned...");
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
