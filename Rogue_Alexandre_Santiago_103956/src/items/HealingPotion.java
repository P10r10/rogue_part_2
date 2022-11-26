package items;

import interfaces.Pickable;
import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends GameElement implements Pickable {

	private int layer = 2;

	public HealingPotion(Point2D position, String room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "HealingPotion";
	}

	@Override
	public int getLayer() {
		return layer;
	}
}
