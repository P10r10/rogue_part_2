package logic;

import pt.iscte.poo.utils.Point2D;

public abstract class AliveGameElement extends GameElement {
	private int hp;
	private boolean isAlive = true;

	public AliveGameElement(Point2D position, String room, int hp) {
		super(position, room);
		this.hp = hp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void takesDamage(int damage) {
		hp -= damage;
		if (hp <= 0) {
			isAlive = false;
		}
	}
}
