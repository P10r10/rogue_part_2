package characters;
import logic.AliveGameElement;
import logic.Movement;
import pt.iscte.poo.utils.Point2D;

public class Bat extends AliveGameElement {

public Bat(Point2D position) {
		super(position, 3);
	}

//	public Bat(Point2D position, 3) {
//		super(position);
//	}
	
//	@Override
//	public boolean isDead(int damage) {
//		hp = hp - damage;
//		if (hp <= 0) {
//			return true;
//		}
//		return false;
//	}
	
//	@Override
//	public int getHp() {
//		return hp;
//	}
//	
	public void heal() {
		if (getHp() < 3) {
			setHp(getHp() + 1);;
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
		Movement.move(this);
	}
}
