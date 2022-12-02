package logic;

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
}
