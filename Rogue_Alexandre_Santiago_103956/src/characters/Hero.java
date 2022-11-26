package characters;

import logic.AliveGameElement;
import logic.GameElement;
import logic.GameEngine;
import logic.HpBar;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import structures.Room;
import structures.Wall;

public class Hero extends AliveGameElement {
	
	private final HpBar hpBar = new HpBar(10);
	
	private int keyPressed;

	public Hero(Point2D position, String room) {
		super(position, room, 10);
	}

	public void drop(int slot) {
//		Room currentRoom = GameEngine.getInstance().getGurrentRoom();
//		GameElement item = currentRoom.elementAt(new Point2D(7 + slot, 10));
//		if (item != null && !(currentRoom.elementAt(getPosition()) instanceof Pickable)) {
//			item.setPosition(getPosition());
//		}
	}

	public void pick(GameElement item) {
//		Room currentRoom = GameEngine.getInstance().getGurrentRoom();
//		for (int i = 0; i < 3; i++) {
//			GameElement itemInSlot = currentRoom.elementAt(new Point2D(7 + i, 10));
//			if (itemInSlot == null) {
//				item.setPosition(new Point2D(7 + i, 10)); // 7 is the x axis of 1st slot
//				break;
//			}
//		}
	}

	public HpBar getHpBar() {
		return hpBar;
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
		//TODO com armadura 50% dos ataques não dao dano
		setHp(getHp() - damage);
		hpBar.setHp(getHp());
		if (getHp() <= 0) {
			GameEngine.getInstance().getGui().setMessage("YOU ARE DEAD!!! GAME OVER");
			GameEngine.getInstance().getGui().dispose();
		}
	}
	
	@Override
	public void move() {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		Point2D destination = getPosition().plus(Direction.directionFor(keyPressed).asVector());
		if (!(thisRoom.elementAt(destination) instanceof Wall)) {
			if (thisRoom.elementAt(destination) instanceof AliveGameElement) { // attacks
				AliveGameElement elem = ((AliveGameElement) thisRoom.elementAt(destination));
				elem.takesDamage(1);//TODO with sword
				if (elem.getHp() <= 0) { // enemy dies
					thisRoom.removeGameElement(elem);
				}
			} else {
				setPosition(destination);
			}
		}
		thisRoom.moveEnemies();
	}
}
