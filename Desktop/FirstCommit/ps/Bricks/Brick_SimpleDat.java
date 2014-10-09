package ps.Bricks;

public class Brick_SimpleDat {
	private String brickValue;
	private String brickName;
	private String brickIntention;

	public Brick_SimpleDat(String name, String intention, String value) {
		brickName = name;
		brickValue = value;
		brickIntention = intention;
	}

	public String getVal() {
		return brickValue;
	}

	public String getName() {
		return brickName;
	}

	public String getIntention() {
		return brickIntention;
	}
}
