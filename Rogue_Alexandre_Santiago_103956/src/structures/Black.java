package structures;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Black implements ImageTile {
	
	private Point2D position;

	public Black(Point2D position) {
		this.position = position;
	}

	public Point2D getPosition() {
		return position;
	}

	@Override
	public String getName() {
		return "Black";
	}

	@Override
	public int getLayer() {
		return 0;
	}
}
