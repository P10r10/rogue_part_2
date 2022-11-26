package items;

import interfaces.Pickable;
import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Sword extends GameElement implements Pickable {
	
	private boolean isPicked = false;

	public Sword(Point2D position, String room) {
		super(position, room);
		setLayer(2);
	}

	@Override
	public String getName() {
		if (isPicked) {
			return "Floor";
		}
		return "Sword";
	}

	@Override
	public void isPicked(boolean picked) {
		isPicked = picked;
	}
}
