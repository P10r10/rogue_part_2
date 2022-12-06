package items;

import interfaces.Consumable;
import interfaces.Pickable;
import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Key extends GameElement implements Pickable, Consumable {

	private String id;

	public Key(Point2D position, String room, String id) {
		super(position, room);
		this.id = id;
		setLayer(5);
	}

	public String getId() {
		return id;
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
	public void isConsumed() {
		setLayer(0);
	}

	@Override
	public String getName() {
		return "Key";
	}
}
