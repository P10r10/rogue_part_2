package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;
import structures.Floor;
import structures.Room;
import structures.Wall;

public class FileReader {
	public static Room createRoom(String name) {
		List<ImageTile> elements = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File("rooms\\" + name + ".txt"));
			for (int y = 0; y < 10; y++) { //1st part of file with walls
				String line = scanner.nextLine();
				for (int x = 0; x < 10; x++) {
					if (line.charAt(x) == '#') {
						elements.add(new Wall(new Point2D(x, y), name));
					} else {
						elements.add(new Floor(new Point2D(x, y), name));
					}
				}
			}
			scanner.nextLine(); //reads empty line
			while (scanner.hasNextLine()) { //2nd part of file with game elements
				String[] args = scanner.nextLine().split(",");
				elements.add(GameElement.create(args, name));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return new Room(name, elements) ;
	}
}
