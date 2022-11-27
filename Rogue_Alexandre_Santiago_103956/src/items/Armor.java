package items;

import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Armor extends GameElement implements Pickable {
	
	public Armor(Point2D position, String room) {
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
		return "Armor";
	}
}
