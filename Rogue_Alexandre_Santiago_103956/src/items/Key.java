package items;

import interfaces.Pickable;
import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Key extends GameElement implements Pickable {

	private String id;
	private boolean isPicked = false;

	public Key(Point2D position, String room, String id) {
		super(position, room);
		this.id = id;
		setLayer(2);
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public void isPicked(boolean picked) {
		isPicked = picked;
	}

	@Override
	public String getName() {
		return "Key";
	}
}
