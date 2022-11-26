package items;

import interfaces.Pickable;
import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Armor extends GameElement implements Pickable {

	private int layer = 2;

	public Armor(Point2D position, String room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "Armor";
	}

	@Override
	public int getLayer() {
		return layer;
	}
}
