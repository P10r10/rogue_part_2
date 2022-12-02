package logic;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

	@Override
	public String toString() {
		return String.format("%9s: %d PTS", name, score);
	}
	
	public static String getHighScores() {
		List<Score> scores = FileReader.readScores();
		scores.sort((p1,p2) -> p2.getScore() - p1.getScore());
		String highScores = "";
		for (int i = 0; i < 5 ; i++) {
			highScores += scores.get(i) + "\n";
		}
		return highScores;
	}
	
	public static void addScoreToFile(String name, int points) {
		try (FileWriter fw = new FileWriter("scores\\scores.txt", true)) {
			PrintWriter pw = new PrintWriter(fw);
			pw.println(name + "," + points);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
