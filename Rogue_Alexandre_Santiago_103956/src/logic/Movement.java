package logic;

import characters.Bat;
import characters.Hero;
import characters.Skeleton;
import interfaces.Movable;
import interfaces.Pickable;
import items.Key;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import structures.Door;
import structures.Floor;
import structures.Room;

public class Movement {
//	private static Room room = GameEngine.getInstance().getGurrentRoom();
//	private static Hero hero = room.getHero();
//
//	public static void keyPress(int keyPressed) {
//	//	int turns = GameEngine.getInstance().getTurns();
//		Point2D heroPosition = hero.getPosition();
//		Point2D destination = heroPosition.plus(Direction.directionFor(keyPressed).asVector());
//		//System.out.println(room.elementAt(destination));//DEBUG REMOVE
//		//System.out.println(heroPosition);
//		GameElement element = room.elementAt(destination);
//		//System.out.println(element);
//		
//		if (element instanceof Movable) {
//			Colision.withEnemy(element);
//		} else if (element instanceof Pickable) {
//			hero.pick(element);
//			hero.setPosition(destination);
//		} else if (element instanceof Door) {
//			Door door = (Door) element;
//			if (door.isOpen()) {
//				hero.setPosition(new Point2D(door.getX_dest(), door.getY_dest()));
//				GameEngine.getInstance().setCurrentRoom(door.getDestination());
//			} else {
//				for (int i = 0; i < 3; i++) {
//					GameElement itemInSlot = room.elementAt(new Point2D(7 + i, 10));
//					if (itemInSlot instanceof Key) {
//						Key key = (Key) itemInSlot;
//						if (key.getId().equals(door.getKey_id())) {
//							door.open();
//							room.removeGameElement(itemInSlot);
//							break;
//						}
//					}
//				}
//			}
//		} else if (element instanceof Floor) {
//			hero.setPosition(destination);
//		}
//	}
//
//	public static void move(GameElement element) {
//		Point2D heroPosition = hero.getPosition();
//		Vector2D directionToHero = element.getPosition().directionTo(heroPosition).asVector();
//		Point2D destination = element.getPosition().plus(directionToHero);
//
//		if (GameEngine.getInstance().getTurns() % 2 == 0) {
//			if (element instanceof Skeleton) {
//				return; // Skeleton doesn't move 50% of turns
//			}
//			if (element instanceof Bat) {// Bat moves randomly 50% of turns
//				destination = element.getPosition().plus(Direction.random().asVector());
//			}
//		}
//		if (hero.getPosition().equals(destination)) {
//			Colision.withHero(element);
//		} else if (room.elementAt(destination) instanceof Movable) {
//			//do nothing because monsters don't collide with each other
//		} else if (room.elementAt(destination) instanceof Pickable || room.elementAt(destination) instanceof Floor) {
//			element.setPosition(destination);
//		}
//	}
}
