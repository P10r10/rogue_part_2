package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;
import structures.Black;

public class HpAndItemBar {

	private List<ImageTile> barComponents = new ArrayList<>();
	private Map<Integer, ImageTile> items = new HashMap<>();
	private int hp;

	public HpAndItemBar(int hp) {
		this.hp = hp;
		barComponents.add(new HpStub(new Point2D(0, 10), 2));
		barComponents.add(new HpStub(new Point2D(1, 10), 4));
		barComponents.add(new HpStub(new Point2D(2, 10), 6));
		barComponents.add(new HpStub(new Point2D(3, 10), 8));
		barComponents.add(new HpStub(new Point2D(4, 10), 10));
		barComponents.add(new Black(new Point2D(5, 10))); // visual background/never changes
		barComponents.add(new Black(new Point2D(6, 10))); // visual background/never changes
		barComponents.add(new Black(new Point2D(7, 10))); // visual background/never changes
		barComponents.add(new Black(new Point2D(8, 10))); // visual background/never changes
		barComponents.add(new Black(new Point2D(9, 10))); // visual background/never changes
	}

	public List<ImageTile> getComponents() {
		return barComponents;
	}
	
	public void addItem(ImageTile item) { //TODO gestão das slots
		((GameElement) item).setPosition(new Point2D(7, 10));
		((GameElement) item).setLayer(1);
		items.put(0, item);
		barComponents.add(items.get(0));//review
	}

	public ImageTile removeItem(int slot) {
		barComponents.remove(items.get(0));//review
		return items.remove(slot);
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}

	private class HpStub implements ImageTile {

		private Point2D position;
		private int limit;

		public HpStub(Point2D position, int limit) {
			this.position = position;
			this.limit = limit;
		}

		@Override
		public String getName() {
			if (hp >= limit) {
				return "Green";
			}
			if (hp == limit - 1) {
				return "GreenRed";
			}
			return "Red";
		}

		@Override
		public Point2D getPosition() {
			return position;
		}

		@Override
		public int getLayer() {
			return 0;
		}
	}
}
