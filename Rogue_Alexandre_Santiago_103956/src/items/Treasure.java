package items;

import logic.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Treasure extends GameElement/* implements Pickable*/{
	
	public Treasure(Point2D position, String room) {
		super(position, room);
		setLayer(2);
	}
	
	@Override
	public String getName() {
		return "Treasure";
	}
	
//	public void pick() {//review
//		GameEngine.getInstance().getGui().setMessage("Congratulations!!! YOU WON!");
//		GameEngine.getInstance().getGui().dispose();
//	}
}
