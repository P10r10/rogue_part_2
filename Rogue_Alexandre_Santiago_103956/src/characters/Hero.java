package characters;

import interfaces.Living;
import interfaces.Pickable;
import logic.GameElement;
import logic.GameEngine;
import logic.HpBar;
import pt.iscte.poo.utils.Point2D;
import structures.Room;

public class Hero extends GameElement implements Living {
	/* Hero implements the singleton pattern */
	private static Hero INSTANCE = null;
	private int hp = 100; // initial health points
	private final HpBar hpBar = new HpBar(hp);

	private Hero(Point2D position) {
		super(position);
	}

	public static Hero getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Hero(new Point2D(1, 1)); // initial position
		return INSTANCE;
	}

	public void drop(int slot) {
		Room currentRoom = GameEngine.getInstance().getGurrentRoom();
		GameElement item = currentRoom.elementAt(new Point2D(7 + slot, 10));
		if (item != null && !(currentRoom.elementAt(getPosition()) instanceof Pickable)) {
			item.setPosition(getPosition());
		}
	}

	public void pick(GameElement item) {
		Room currentRoom = GameEngine.getInstance().getGurrentRoom();
		for (int i = 0; i < 3; i++) {
			GameElement itemInSlot = currentRoom.elementAt(new Point2D(7 + i, 10));
			if (itemInSlot == null) {
				item.setPosition(new Point2D(7 + i, 10)); // 7 is the x axis of 1st slot
				break;
			}
		}
	}

	public HpBar getHpBar() {
		return hpBar;
	}

	@Override
	public boolean isDead(int damage) {// change to TAKESDAMAGE
		hp = hp - damage;
		hpBar.setHp(hp);
		if (hp <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public int getHp() {
		return hp;
	}

	@Override
	public String getName() {
		if (hp <= 0) { // Hero is dead!
			return "Grave";
		}
		return "Hero";
	}

	@Override
	public int getLayer() {
		return 9;
	}
}
