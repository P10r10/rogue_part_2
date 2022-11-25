package logic;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import structures.Room;

public abstract class AliveGameElement extends GameElement {
	private int hp;
	private boolean isAlive = true;
//	private Room room = GameEngine.getInstance().getGurrentRoom();
//	private Point2D heroPosition = room.getHero().getPosition();
//	private Vector2D directionToHero = getPosition().directionTo(heroPosition).asVector();
//	private Point2D destination = getPosition().plus(directionToHero);

	public AliveGameElement(Point2D position, int hp) {
		super(position);
		this.hp = hp;
	}
	
//	public Point2D getDestination() {
//		return destination;
//	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void takeDamage(int damage) {
		hp -= damage;
		if (hp <= 0) {
			isAlive = false;
		}
	}
	
	public abstract void move();
}
