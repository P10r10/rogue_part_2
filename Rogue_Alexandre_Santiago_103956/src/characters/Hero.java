package characters;

import java.util.Map;

import items.Key;
import items.Pickable;
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

public class Hero extends AliveGameElement {

	private final HpAndItemBar hpAndItemBar = new HpAndItemBar(10);
	private int keyPressed;
	private boolean isPoisoned = false;

	public Hero(Point2D position, String room) {
		super(position, room, 100); // initial hp = 10
		setLayer(5);
	}

	public void drop(int slot) {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		ImageTile item = hpAndItemBar.removeItem(slot);
		if (item != null) {
			((Pickable) item).isPicked(false);
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
		// TODO com armadura 50% dos ataques não dao dano
		setHp(getHp() - damage);
		hpAndItemBar.setHp(getHp());
		if (getHp() <= 0) {
			GameEngine.getInstance().getGui().setMessage("YOU ARE DEAD!!! GAME OVER");
			GameEngine.getInstance().getGui().dispose();
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
			elem.takesDamage(1);// TODO with sword
			if (elem.getHp() <= 0) { // enemy dies
				thisRoom.removeGameElement(elem);
			}
		} else if (thisRoom.elementAt(destination) instanceof Door) { // interaction with doors
			Door door = (Door) thisRoom.elementAt(destination);
			if (door.isOpen()) { // door is already open
				thisRoom.removeGameElement(this); // removes hero from current room
				Point2D dest = new Point2D(door.getX_dest(), door.getY_dest());
				setPosition(dest); // new position from door
				setThisRoom(door.getDestination()); // Hero new room name
				Room newRoom = GameEngine.getInstance().getRoom(thisRoom());
				((Door) newRoom.elementAt(dest)).open(); // open door in new room
				GameEngine.getInstance().getRoom(thisRoom()).addGameElement(this); // add Hero to new Room
				GameEngine.getInstance().getRoom(door.getDestination()).load();
			} else { // door is closed
				Map<Integer, ImageTile> items = hpAndItemBar.getItems();
				for (ImageTile item : items.values()) {
					if (item instanceof Key) {
						if (((Key) item).getId().equals(door.getKey_id())) {
							for (int num : items.keySet()) { // takes key from inventory
								if (items.get(num).equals(item)) {
									((Key) item).setLayer(0);
									hpAndItemBar.removeItem(num);
									break;
								}
							}
							door.open();
						}
						break;
					}
				}
			}
		} else if (thisRoom.elementAt(destination) instanceof Pickable) { // picks
			pick(thisRoom.elementAt(destination));
		} else { // can move freely
			setPosition(destination);
		}
	}
}
