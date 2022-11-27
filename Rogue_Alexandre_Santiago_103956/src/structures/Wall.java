package structures;

import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Wall extends GameElement {

	public Wall(Point2D position, String room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "Wall";
	}

	@Override
	public int getLayer() {
		return 2;
	}
}
