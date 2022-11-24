package logic;

import characters.Bat;
import characters.Skeleton;
import characters.Thug;
import items.Armor;
import items.HealingPotion;
import items.Key;
import items.Sword;
import items.Treasure;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;
import structures.Door;

public abstract class GameElement implements ImageTile {

	private Point2D position;
	private int layer = 0;
	
	public GameElement(Point2D position) {
		this.position = position;
	}
	
	@Override
	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}
	
	@Override
	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public static GameElement create(String[] args) {
		String name = args[0];
		Point2D position = new Point2D(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		switch (name) {
		case "Skeleton":
			return new Skeleton(position);
		case "Bat":
			return new Bat(position);
		case "Sword":
			return new Sword(position);
		case "HealingPotion":
			return new HealingPotion(position);
		case "Treasure":
			return new Treasure(position);
		case "Thug":
			return new Thug(position);
		case "Armor":
			return new Armor(position);
		case "Key":
			return new Key(position, args[3]);
		case "Door":
			if (args.length == 7) { //has Key_ID
				return new Door(position, args[3], Integer.parseInt(args[4]),
						Integer.parseInt(args[5]), args[6]);
			} else { //has no Key_ID and thus the door is open
				return new Door(position, args[3], Integer.parseInt(args[4]),
						Integer.parseInt(args[5]), null);
			}
		default:
			throw new IllegalArgumentException(name + " does not exist!");
		}
	}
}