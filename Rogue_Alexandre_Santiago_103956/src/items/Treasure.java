package items;

import logic.GameElement;
import logic.GameEngine;
import pt.iscte.poo.utils.Point2D;

public class Treasure extends GameElement implements Pickable{
	
	public Treasure(Point2D position, String room) {
		super(position, room);
		setLayer(3);
	}
	
	@Override
	public void isPicked(boolean picked) {
		if (picked) {
			GameEngine.getInstance().getGui().setMessage("Congratulations!!! YOU WON!");
			GameEngine.getInstance().getGui().dispose();
		}
	}
	
	@Override
	public String getName() {
		return "Treasure";
	}
}
