package ps;

public class Main {

	public static void main(String[] args) {
		try {
			EDIntepreter.interpret("~PSEDstart|~O Server|~D AllClients|~B b00 STRING _ |~B b01 STRING _|~B b02 STRING _|~B b10 STRING _|~B b11 STRING _|~B b12 STRING _|~B b20 STRING _|~B b21 STRING _|~B b22 STRING _|~PSEDend").debugInfoOut();
		} catch (InvalidPSEDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
