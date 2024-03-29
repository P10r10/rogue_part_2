package characters;

import java.util.Map;

import interfaces.AwardsPoints;
import interfaces.Healable;
import interfaces.Movable;
import interfaces.Pickable;
import items.Armor;
import items.HealingPotion;
import items.Key;
import items.Sword;
import items.Treasure;
import logic.AliveGameElement;
import logic.GameElement;
import logic.GameEngine;
import logic.HpAndItemBar;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import structures.Door;
import structures.Room;
import structures.Wall;

public class Hero extends AliveGameElement implements Movable, Healable {

	private final HpAndItemBar hpAndItemBar = new HpAndItemBar(10);
	private int keyPressed;
	private boolean isPoisoned = false;
	private boolean hasSword = false;
	private boolean hasArmor = false;
	private boolean canBlock = true;
	private boolean hasWon = false;
	private int points = 0;

	public Hero(Point2D position, String room) {
		super(position, room, 10); // initial hp = 10
		setLayer(7);
	}

	public void heal() {
		if (getHp() < 10) {
			System.out.println("You heal!");
			setHp(getHp() + 5);
		}
		if (getHp() > 10) {
			setHp(10);
		}
		hpAndItemBar.setHp(getHp());
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int pointsToAdd) {
		points += pointsToAdd;
		if (points < 0) {
			points = 0;
		}
	}

	public void drop(int slot) {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		ImageTile item = hpAndItemBar.removeItem(slot);
		if (item != null) {
			((Pickable) item).isPicked(false);
			if (item instanceof Sword) {
				hasSword = false;
			}
			if (item instanceof Armor) {
				hasArmor = false;
			}
			if (item instanceof HealingPotion && getHp() < 10) {
				heal(); // consumes potion only with hp missing
				isPoisoned = false;
				addPoints(((AwardsPoints) item).points());
				((HealingPotion) item).isConsumed();
				return;
			}
			((GameElement) item).setPosition(getPosition());
			thisRoom.addGameElement(((GameElement) item));
		}
	}

	public void pick(ImageTile item) {
		if (hpAndItemBar.availableSlots() < 3) {
			((Pickable) item).isPicked(true);
			Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
			thisRoom.removeGameElement(item);
			hpAndItemBar.addItem(item);
		}
		if (item instanceof Treasure) {
			addPoints(((AwardsPoints) item).points());
			hasWon = true;
		}
	}

	public boolean hasWon() {
		return hasWon;
	}

	public HpAndItemBar getHpAndItemBar() {
		return hpAndItemBar;
	}

	public boolean isPoisoned() {
		return isPoisoned;
	}

	public void setPoisoned() {
		isPoisoned = true;
	}

	public void setKeyPressed(int keyPressed) {
		this.keyPressed = keyPressed;
		move();
	}

	@Override
	public String getName() {
		if (getHp() <= 0) { // Hero is dead!
			return "Grave";
		}
		return "Hero";
	}

	@Override
	public void takesDamage(int damage) {
		if (hasArmor && canBlock) {
			System.out.println("You blocked damage with your armor!");
			canBlock = false;
		} else {
			setHp(getHp() - damage);
			System.out.println("You take " + damage + " damage");
			hpAndItemBar.setHp(getHp());
			if (getHp() <= 0) { // Hero dies
				isDead();
			}
			canBlock = true;
		}
	}

	@Override
	public void move() {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		Point2D destination = getPosition().plus(Direction.directionFor(keyPressed).asVector());

		if (thisRoom.elementAt(destination) instanceof Wall || thisRoom.elementAt(destination) == null) {
			return; // can't cross walls or go to empty spaces beyond open doors
		}

		if (thisRoom.elementAt(destination) instanceof AliveGameElement) { // attacks
			AliveGameElement elem = ((AliveGameElement) thisRoom.elementAt(destination));
			int damage = 1;
			if (hasSword) {
				damage++;
			}
			System.out.println("You deal " + damage + " damage to the " + elem);
			elem.takesDamage(damage);
			if (elem.getHp() <= 0) { // enemy dies
				addPoints(((AwardsPoints) elem).points());
				if (elem instanceof Thief) {
					((Thief) elem).drop();
				}
				thisRoom.removeGameElement(elem);
			}
		} else if (thisRoom.elementAt(destination) instanceof Door) { // interaction with doors
			Door door = (Door) thisRoom.elementAt(destination);
			if (door.isOpen()) { // door is already open
				thisRoom.removeGameElement(this); // removes hero from current room
				Point2D dest = new Point2D(door.getX_dest(), door.getY_dest());
				setPosition(dest); // Hero's new position from door
				setThisRoom(door.getDestination()); // Hero new room name
				thisRoom = GameEngine.getInstance().getRoom(thisRoom());
				((Door) thisRoom.elementAt(dest)).open(); // open door in new room
				thisRoom.addGameElement(this); // add Hero to new Room
				thisRoom.load();
			} else { // door is closed
				Map<Integer, ImageTile> items = hpAndItemBar.getItems();
				for (ImageTile item : items.values()) {
					if (item instanceof Key && ((Key) item).getId().equals(door.getKey_id())) { // key matches
						for (int num : items.keySet()) { // takes key from inventory
							if (items.get(num).equals(item)) {
								((Key) item).isConsumed();
								hpAndItemBar.removeItem(num);
								break;
							}
						}
						door.open();
						break;
					}
				}
			}
		} else if (thisRoom.elementAt(destination) instanceof Pickable) { // picks
			if (thisRoom.elementAt(destination) instanceof Sword) {
				hasSword = true;
			}
			if (thisRoom.elementAt(destination) instanceof Armor) {
				hasArmor = true;
			}
			pick(thisRoom.elementAt(destination));
		} else { // can move freely
			setPosition(destination);
		}
	}
}
