package structures;

import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Floor extends GameElement {

	public Floor(Point2D position, String room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "Floor";
	}

	@Override
	public int getLayer() {
		return 3;
	}
}
