package items;

import interfaces.Pickable;
import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Sword extends GameElement implements Pickable {

	private int layer = 2;

	public Sword(Point2D position, String room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "Sword";
	}

	@Override
	public int getLayer() {
		return layer;
	}
}
