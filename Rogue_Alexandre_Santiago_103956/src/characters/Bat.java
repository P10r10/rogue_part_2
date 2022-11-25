package characters;
import logic.AliveGameElement;
import logic.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bat extends AliveGameElement {

public Bat(Point2D position) {
		super(position, 3);
	}

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
		Point2D randomDestination = getPosition().plus(Direction.random().asVector());
		
		if (GameEngine.getInstance().getTurns() % 2 == 0) {
			setPosition(GameEngine.getInstance().getGurrentRoom().wayToHero(this)); // colision
		} else {
			setPosition(randomDestination); // colision
		}
	}
}
