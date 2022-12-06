package items;

import interfaces.AwardsPoints;
import interfaces.Consumable;
import interfaces.Pickable;
import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends GameElement implements Pickable, AwardsPoints, Consumable {

	public HealingPotion(Point2D position, String room) {
		super(position, room);
		setLayer(5);
	}

	@Override
	public void isPicked(boolean picked) {
		if (picked) {
			setLayer(2);
		} else {
			setLayer(5);
		}
	}

	@Override
	public int points() {
		return -7; // using potion awards penalty
	}

	@Override
	public void isConsumed() {
		setLayer(0);
	}

	@Override
	public String getName() {
		return "HealingPotion";
	}
}
