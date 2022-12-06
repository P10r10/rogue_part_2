package items;

import interfaces.AwardsPoints;
import interfaces.Pickable;
import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Treasure extends GameElement implements Pickable, AwardsPoints {

	public Treasure(Point2D position, String room) {
		super(position, room);
		setLayer(5);
	}

	@Override
	public void isPicked(boolean picked) {
		if (picked) {
			setLayer(2);
		}
	}

	@Override
	public int points() {
		return 29;
	}

	@Override
	public String getName() {
		return "Treasure";
	}
}
