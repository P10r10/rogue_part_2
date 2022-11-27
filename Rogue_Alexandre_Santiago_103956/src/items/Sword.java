package items;

import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Sword extends GameElement implements Pickable {
	
	public Sword(Point2D position, String room) {
		super(position, room);
		setLayer(3);
	}

	@Override
	public void isPicked(boolean picked) {
		if (picked) {
			setLayer(1);
		} else {
			setLayer(3);
		}
	}
	
	@Override
	public String getName() {
		return "Sword";
	}
}
