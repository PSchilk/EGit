package ps;

import java.util.ArrayList;

import ps.Bricks.Brick_SimpleDat;

public class ExchangeData {
	private ArrayList<Brick_SimpleDat> bricksInExchange = new ArrayList();
	private String origin = null;
	private String destination = null;
	private String rawDe = null;

	public ExchangeData(String rd) {
		rawDe = rd;
	}

	public ExchangeData() {
	}

	public void setOrigin(String o) {
		origin = o;
	}

	public void setDestination(String g) {
		destination = g;
	}

	public void addBrick(Brick_SimpleDat b) {
		bricksInExchange.add(b);
	}

	public void addBrickArray(Brick_SimpleDat[] b) {
		for (Brick_SimpleDat br : b) {
			bricksInExchange.add(br);
		}
	}

	public boolean hasBrick() {
		return !bricksInExchange.isEmpty();
	}

	public Brick_SimpleDat[] getBricks() {
		int gimmedafacts = bricksInExchange.size();
		Brick_SimpleDat[] result = new Brick_SimpleDat[bricksInExchange.size()];
		int i = 0;
		for (Brick_SimpleDat b : bricksInExchange) {
			result[i] = b;
			i++;
		}
		return result;
	}

	public int getNumBricks() {
		return bricksInExchange.size();
	}

	public boolean hasDestination() {
		return !(destination == null);
	}

	public String getDestination() {
		return destination;
	}

	public boolean hasOrign() {
		return !(origin == null);
	}

	public String getOrigin() {
		return origin;
	}

	public boolean hasRawDe() {
		return !(rawDe == null);
	}

	public String getRawDe() {
		return rawDe;
	}

	public void debugInfoOut() {
		System.out.println("--------------------------------");
		System.out.println("Debug Info of ExchnageData:");
		System.out.println("--------------------------------");
		System.out.println();
		if (this.hasRawDe())
			System.out.println("Raw ED: " + this.rawDe);
		else
			System.out.println("No RawED availabel");
		System.out.println();
		System.out.println("Has Origin: " + this.hasOrign());
		if (this.hasOrign())
			System.out.println("Origin: " + this.getOrigin());
		System.out.println();
		System.out.println("Has Destination: " + this.hasDestination());
		if (this.hasDestination())
			System.out.println("Destination: " + this.getDestination());
		System.out.println();
		System.out.println("Has Bricks: " + this.hasBrick());
		if (this.hasBrick()) {
			System.out.println("Number of Bricks: " + this.getNumBricks());
			System.out.println("-----Bricks------");
			System.out.println("Name | Intention | Value");
			int i = 1;
			for (Brick_SimpleDat b : this.getBricks()) {
				System.out.println(i + ": " + b.getName() + " | "
						+ b.getIntention() + " | " + b.getVal());
				i++;
			}

		}
	}
}
