package ps;

public class InvalidPSEDException extends Exception {

	public InvalidPSEDException(String string, String rawDE) {
		super(string);
		System.out.println("---ERROR IN RDE-----");
		System.out.println("Broken Raw Data Exchange: \"" + rawDE + "\"");
	}

}
