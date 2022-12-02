package logic;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import characters.Hero;
import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Point2D;
import structures.Room;

public class GameEngine implements Observer {

	public static final int GRID_HEIGHT = 11;
	public static final int GRID_WIDTH = 10;

	private static GameEngine INSTANCE = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

	private Hero hero = new Hero(new Point2D(1, 1), "room0");
	private Map<String, Room> rooms = new HashMap<>();

	private int turns;

	public static GameEngine getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameEngine();
		return INSTANCE;
	}

	public ImageMatrixGUI getGui() {
		return gui;
	}

	public int getTurns() {
		return turns;
	}

	private GameEngine() {
		gui.registerObserver(this);
		gui.setSize(GRID_WIDTH, GRID_HEIGHT);
		gui.go();
	}

	public Room getRoom(String room) {
		return rooms.get(room);
	}

	public void start() { // init only once

		rooms.put("testRoom", FileReader.createRoom("testRoom"));//change in Hero too!
		rooms.put("testRoom2", FileReader.createRoom("testRoom2"));//change in Hero too!
		rooms.put("room0", FileReader.createRoom("room0"));
		rooms.put("room1", FileReader.createRoom("room1"));
		rooms.put("room2", FileReader.createRoom("room2"));
		rooms.put("room3", FileReader.createRoom("room3"));
		
		rooms.get("room0").addGameElement(hero);
		rooms.get("room0").load();
		
		gui.setStatusMessage("Good luck!");
		gui.update();
	}

	@Override
	public void update(Observed source) {
		int keyPressed = ((ImageMatrixGUI) source).keyPressed();
		if (keyPressed >= KeyEvent.VK_LEFT && keyPressed <= KeyEvent.VK_DOWN) {
			hero.setKeyPressed(keyPressed);
		}
		switch (keyPressed) {
		case KeyEvent.VK_1:
			hero.drop(0); // drops item in slot 1
			break;
		case KeyEvent.VK_2:
			hero.drop(1); // drops item in slot 2
			break;
		case KeyEvent.VK_3:
			hero.drop(2); // drops item in slot 3
			break;
		}
		if (hero.isPoisoned()) {
			hero.takesDamage(1);
		}
		getRoom(hero.thisRoom()).moveEnemies();
		turns++;
		gui.setStatusMessage("Turn: " + turns + " Points: " + hero.getPoints());
		gui.update();
		if (hero.hasWon()) {
			String name = gui.askUser("Congratulations! Insert your name: ");
			Score.addScoreToFile(name, hero.getPoints());
			gui.setMessage("     TOP 5 SCORES\n\n" + Score.getHighScores() + "\n");
			gui.dispose();
		}
	}
}
