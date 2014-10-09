package ps;

public class InvalidStringforEDCException extends Exception {

	public InvalidStringforEDCException(String n, String invalidString)
	{
		super(n);
		System.out.println("---ERROR IN String for EDC-----");
		System.out.println("Broken String: \""+invalidString+"\"");
		
	}
}
