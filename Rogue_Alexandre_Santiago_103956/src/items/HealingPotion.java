package items;

import Interfaces.Pickable;
import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends GameElement implements Pickable {
	
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
	public String getName() {
		return "HealingPotion";
	}
}
