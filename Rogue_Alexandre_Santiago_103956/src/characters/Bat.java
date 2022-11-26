package characters;

import logic.AliveGameElement;
import logic.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import structures.Door;
import structures.Room;
import structures.Wall;

public class Bat extends AliveGameElement {

	private boolean canDamage = true;

	public Bat(Point2D position, String room) {
		super(position, room, 3); // initial hp = 3
		setLayer(1);
	}

	public void heal() {
		if (getHp() < 3) {
			setHp(getHp() + 1);
		}
	}

	@Override
	public String getName() {
		if (getHp() <= 0) {
			setLayer(0);
			return "DeadBat";
		}
		return "Bat";
	}

	@Override
	public void move() {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		Point2D destination = thisRoom.wayToHero(this);

		if (GameEngine.getInstance().getTurns() % 2 == 1) { // random 50% of turns
			destination = getPosition().plus(Direction.random().asVector());
		}
		if (thisRoom.elementAt(destination) instanceof Hero) { // attacks hero
			heal(); // heals with successful attack
			if (canDamage) { // can damage only 50% of times
				((Hero) thisRoom.elementAt(destination)).takesDamage(1);
				canDamage = false;
			} else {
				canDamage = true;
			}
		} else if (thisRoom.elementAt(destination) instanceof Wall || thisRoom.elementAt(destination) instanceof Door
				|| thisRoom.elementAt(destination) instanceof AliveGameElement) {
			return; // can't cross walls, doors or other creatures
		} else { // can move freely
			setPosition(destination);
		}
	}
}
