package items;

import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Key extends GameElement implements Pickable {

	private String id;

	public Key(Point2D position, String room, String id) {
		super(position, room);
		this.id = id;
		setLayer(3);
	}
	
	public String getId() {
		return id;
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
		return "Key";
	}
}
