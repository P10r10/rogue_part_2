package items;

import interfaces.Pickable;
import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends GameElement implements Pickable {
	
	private boolean isPicked = false;

	public HealingPotion(Point2D position, String room) {
		super(position, room);
		setLayer(2);
	}
	
	@Override
	public void isPicked(boolean picked) {
		isPicked = picked;
	}

	@Override
	public String getName() {
		return "HealingPotion";
	}
}
