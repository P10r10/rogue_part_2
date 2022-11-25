package characters;

import interfaces.Living;
import interfaces.Movable;
import logic.GameElement;
import logic.Movement;
import pt.iscte.poo.utils.Point2D;

public class Skeleton extends GameElement implements Movable, Living {

	private int hp = 5; // initial hp
	private int layer = 1;

	public Skeleton(Point2D position) {
		super(position);
	}

	@Override
	public boolean isDead(int damage) {
		hp = hp - damage;
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
		if (hp <= 0) {
			layer = 0;
			return "DeadSkeleton";
		}
		return "Skeleton";
	}

	@Override
	public int getLayer() {
		return layer;
	}

	@Override
	public void move() {
		//Movement.move(this);
	}
}
