package logic;

import java.util.Random;

import characters.Bat;
import characters.Hero;
import characters.Skeleton;
import characters.Thug;
import interfaces.Living;
import pt.iscte.poo.gui.ImageTile;

public class Colision {

	public static void withEnemy(ImageTile gameElement) {
		//System.out.println("Hero colidiu com " + gameElement.getName());
		if (gameElement instanceof Living) {
			Living living = (Living) gameElement;
			int heroDamage = 1; // + sword multiplier
			if (living.isDead(heroDamage)) { // if takes deadly damage remove from room
			//	GameEngine.getInstance().getGurrentRoom().removeGameElement(gameElement);
			}
			//System.out.println(gameElement.getName() + " has " + living.getHp() + " hp left");
		}
	}

	public static void withHero(GameElement gameElement) {
	//	Hero hero = GameEngine.getInstance().getGurrentRoom().getHero();
		int damage = 0;
		if (gameElement instanceof Bat) { // Bat damage
			damage = new Random().nextInt(2); // either 0 or 1
			if (damage == 1) {
				((Bat) gameElement).heal(); // successful attack heals bat
			}
		}
		if (gameElement instanceof Thug) { // Thug damage
			if (new Random().nextDouble() <= 0.3) {
				damage = 3;
			}
		}
		if (gameElement instanceof Skeleton) { // Skeleton damage
			damage = 1;
		}
//		if (hero.isDead(damage)) { // if hero takes deadly damage game is over
//			GameEngine.getInstance().getGui().setMessage("YOU ARE DEAD!!! GAME OVER");
//			GameEngine.getInstance().getGui().dispose();
//			System.exit(0);
//		}
		//System.out.println("Hero has " + hero.getHp() + " hp left");
	}
}
