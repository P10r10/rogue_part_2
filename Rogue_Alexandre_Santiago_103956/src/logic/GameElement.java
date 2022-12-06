package logic;

import characters.Bat;
import characters.Scorpio;
import characters.Skeleton;
import characters.Thief;
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
	private String thisRoom;
	private int layer = 0;

	public GameElement(Point2D position, String room) {
		this.position = position;
		thisRoom = room;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	public String thisRoom() {
		return thisRoom;
	}

	public void setThisRoom(String thisRoom) {
		this.thisRoom = thisRoom;
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

	public static GameElement create(String[] args, String room) {
		String name = args[0];
		Point2D position = new Point2D(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		switch (name) {
		case "Bat":
			return new Bat(position, room);
		case "Skeleton":
			return new Skeleton(position, room);
		case "Thug":
			return new Thug(position, room);
		case "Thief":
			return new Thief(position, room);
		case "Scorpio":
			return new Scorpio(position, room);
		case "Sword":
			return new Sword(position, room);
		case "HealingPotion":
			return new HealingPotion(position, room);
		case "Treasure":
			return new Treasure(position, room);
		case "Armor":
			return new Armor(position, room);
		case "Key":
			return new Key(position, room, args[3]);
		case "Door":
			if (args.length == 7) { // has Key_ID
				return new Door(position, room, args[3], Integer.parseInt(args[4]), Integer.parseInt(args[5]), args[6]);
			} else { // having no Key_ID corresponds to open door
				return new Door(position, room, args[3], Integer.parseInt(args[4]), Integer.parseInt(args[5]), null);
			}
		default:
			throw new IllegalArgumentException(name + " does not exist!");
		}
	}
}
