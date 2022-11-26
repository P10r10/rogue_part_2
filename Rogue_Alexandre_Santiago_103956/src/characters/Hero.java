package characters;

import interfaces.Pickable;
import logic.AliveGameElement;
import logic.GameElement;
import logic.GameEngine;
import logic.HpAndItemBar;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import structures.Room;
import structures.Wall;

public class Hero extends AliveGameElement {
	
	private final HpAndItemBar hpBar = new HpAndItemBar(10);
	private int keyPressed;
	private boolean isPoisoned = false;

	public Hero(Point2D position, String room) {
		super(position, room, 100); // initial hp = 10
		setLayer(9);
	}

	public void drop(int slot) {
	//	Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		
//		Room currentRoom = GameEngine.getInstance().getGurrentRoom();
//		GameElement item = currentRoom.elementAt(new Point2D(7 + slot, 10));
//		if (item != null && !(currentRoom.elementAt(getPosition()) instanceof Pickable)) {
//			item.setPosition(getPosition());
//		}
	}

	public void pick(ImageTile item) {
		
		((Pickable) item).isPicked(true);
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		thisRoom.removeGameElement(item);
		//TODO HERE item add BAR
		
//		Room currentRoom = GameEngine.getInstance().getGurrentRoom();
//		for (int i = 0; i < 3; i++) {
//			GameElement itemInSlot = currentRoom.elementAt(new Point2D(7 + i, 10));
//			if (itemInSlot == null) {
//				item.setPosition(new Point2D(7 + i, 10)); // 7 is the x axis of 1st slot
//				break;
//			}
//		}
	}

	public HpAndItemBar getHpBar() {
		return hpBar;
	}

	public boolean isPoisoned() {
		return isPoisoned;
	}

	public void setPoisoned() {
		isPoisoned = true;
	}

	public void setKeyPressed(int keyPressed) {
		this.keyPressed = keyPressed;
		move();
	}

	@Override
	public String getName() {
		if (getHp() <= 0) { // Hero is dead!
			return "Grave";
		}
		return "Hero";
	}
	
	@Override
	public void takesDamage(int damage) {
		//TODO com armadura 50% dos ataques não dao dano
		setHp(getHp() - damage);
		hpBar.setHp(getHp());
		if (getHp() <= 0) {
			GameEngine.getInstance().getGui().setMessage("YOU ARE DEAD!!! GAME OVER");
			GameEngine.getInstance().getGui().dispose();
		}
	}
	
	@Override
	public void move() {
		Room thisRoom = GameEngine.getInstance().getRoom(thisRoom());
		Point2D destination = getPosition().plus(Direction.directionFor(keyPressed).asVector());
		System.out.println(thisRoom.elementAt(destination));//REMOVE
		if (!(thisRoom.elementAt(destination) instanceof Wall)) { // can't cross walls
			if (thisRoom.elementAt(destination) instanceof AliveGameElement) { // attacks
				AliveGameElement elem = ((AliveGameElement) thisRoom.elementAt(destination));
				elem.takesDamage(1);//TODO with sword
				if (elem.getHp() <= 0) { // enemy dies
					thisRoom.removeGameElement(elem);
				}
			} else {
				if (thisRoom.elementAt(destination) instanceof Pickable) {
					//TODO PICK
					System.out.println("Pick!");
					pick(thisRoom.elementAt(destination));
				}
				setPosition(destination);
			}
		}
		thisRoom.moveEnemies();
	}
}
