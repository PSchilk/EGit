package ps;

import java.util.ArrayList;

import ps.Bricks.Brick_SimpleDat;

public class EDIntepreter {
	/*
	 * Exchange Data Layout
	 * 
	 * ~PSEDstart| §
	 * 
	 * 1) Start of EDC 2) EDC 3) Separator, not at end of command
	 * 
	 * Possible EDCs:
	 * 
	 * ~PSEDstart     Beginning of a DE !REQUIRED!
	 * 
	 * ~PSEDend     End of a DE !Required!
	 * 
	 * ~O [origin]      Origin of the DE
	 * 
	 * ~D [destination] Destination of the
	 * 
	 * ~SD [name] [intention] [data] a brick of data
	 * 
	 * ~AR [name] [intention] [# of dimensions] [dimension (repeat for
	 * multiple)] [Data (array in order, first dimension then sec etc)]
	 * 
	 * ~HBQ [random data]
	 * 
	 * ~HBR [repeated data]
	 * 
	 * ~ACT [Action]
	 * 
	 * ~ANNOUNCE 
	 */

	public static ExchangeData interpret(String rawDE)
			throws InvalidPSEDException {
		String origin = null;
		String destination = null;
		ArrayList<Brick_SimpleDat> brick_SimpleDats = new ArrayList();

		// Check if rawDE contains a separator
		if (rawDE.contains("|")) {
			// Split the rawDE at the separators
			String[] parts = rawDE.split("\\|");

			// Check if the DE has a valid start & end
			if (parts[0].equals("~PSEDstart")
					&& parts[parts.length - 1].equals("~PSEDend")) {
				// DE has a valid start & end

				// Turn into ExchangeData
				for (String s : parts) {

					// Check if EDIC start or EDIC end
					if (s.equals("~PSEDstart") || s.equals("~PSEDend")) {
						// Start or end, disregard
					}
					// Check if EDIC Origin
					else if (s.contains("~O ")) {
						// is EDIC Origin
						// remove "~O " so only the origin remains and set
						// origin
						origin = s.replace("~O ", "");

					}
					// Check if EDIC Destination
					else if (s.contains("~D ")) {
						// is EDIC Goal
						// remove "~G " so only the origin remains and set goal
						destination = s.replace("~D ", "");
					}
					// Check if Brick_SimpleDat
					else if (s.contains("~B ")) {
						s = s.replace("~B ", "");
						String[] brickComponents = s.split(" ", 3);
						brick_SimpleDats.add(new Brick_SimpleDat(brickComponents[0],
								brickComponents[1], brickComponents[2]));
					} else {
						throw new InvalidPSEDException("Invalid command: " + s,
								rawDE);
					}
				}
				// Create Exchange Data out of found data
				ExchangeData result = new ExchangeData(rawDE);
				// Add Bricks
				if (!brick_SimpleDats.isEmpty()) {
					Brick_SimpleDat[] br = new Brick_SimpleDat[brick_SimpleDats.toArray().length];
					int i = 0;
					for (Brick_SimpleDat b : brick_SimpleDats) {
						br[i] = b;
						i++;
					}
					result.addBrickArray(br);
				}
				// Add Destination
				if (destination != null) {
					result.setDestination(destination);
				}
				// Add Origin
				if (origin != null) {
					result.setOrigin(origin);
				}
				return result;

			} else {
				throw new InvalidPSEDException(
						"Found no beginning or/and no end in the raw Data Exchange",
						rawDE);
			}
		} else
			throw new InvalidPSEDException(
					"Found no '|' separators in the raw Data Exchange", rawDE);
	}

	public static String makeRawED(ExchangeData ED)
			throws InvalidStringforEDCException {
		String result = "";
		result = "~PSEDstart|";
		if (ED.hasOrign()) {
			if (isLegal(ED.getOrigin()))
				result = result + "~O " + ED.getOrigin() + "|";
			else
				throw new InvalidStringforEDCException("Invalid Origin",
						ED.getOrigin());
		}
		if (ED.hasDestination()) {
			if (isLegal(ED.getOrigin()))
				result = result + "~D " + ED.getDestination() + "|";
			else
				throw new InvalidStringforEDCException("Invalid Destination",
						ED.getDestination());
		}
		if (ED.hasBrick()) {
			for (Brick_SimpleDat b : ED.getBricks()) {
				// Check if Name and Intention contain spaces
				if (!b.getName().contains(" ")
						&& !b.getIntention().contains(" ")) {
					// Check if Name, Intention and Value are legal
					if (isLegal(b.getName()) && isLegal(b.getIntention())
							&& isLegal(b.getVal())) {
						// All Components are legal plus Name and Intention do
						// not contain spaces
						result = result + "~B " + b.getName() + " "
								+ b.getIntention() + " " + b.getVal() + "|";
					} else
						throw new InvalidStringforEDCException(
								"Name Intention or Val are illegal ", "Name: "
										+ b.getName() + " Intention: "
										+ b.getIntention() + " Val: "
										+ b.getVal());

				} else
					throw new InvalidStringforEDCException(
							"Name or Intention contains Spaces ", "Name: "
									+ b.getName() + " Intention: "
									+ b.getIntention());

			}

		}

		// Add end
		result = result + "~PSEDend";

		// Return
		return result;
	}

	private static boolean isLegal(String Subject) {
		if (Subject.contains("~") || Subject.contains("|")) {
			return false;
		} else
			return true;
	}
}
