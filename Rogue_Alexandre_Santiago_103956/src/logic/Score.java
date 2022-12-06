package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Score {
	private String name;
	private int score;

	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("%9s: %d PTS", name, score);
	}

	public static String getHighScores(String name, int points) {
		List<Score> scores = new ArrayList<>();
		String res = "";
		try (Scanner scanner = new Scanner(new File("scores\\scores.txt"))) {
			while (scanner.hasNextLine()) {
				String[] split = scanner.nextLine().split(",");
				scores.add(new Score(split[0], Integer.parseInt(split[1])));
			}
			scores.add(new Score(name, points));
			scores.sort((s1, s2) -> s2.getScore() - s1.getScore());
			scores.remove(scores.size() - 1); // discards lower score
			PrintWriter pw = new PrintWriter(new File("scores\\scores.txt"));
			for (Score score : scores) { // saves scores to file scores.txt
				pw.println(score.getName() + "," + score.getScore());
				res += score + "\n";
			}
			scanner.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
}
