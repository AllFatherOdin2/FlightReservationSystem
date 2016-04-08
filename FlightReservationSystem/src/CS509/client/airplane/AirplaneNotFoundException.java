package CS509.client.airplane;

public class AirplaneNotFoundException extends Exception {

	/**
	 * Basic exception to throw if an Airport isnt found
	 */
	public AirplaneNotFoundException() {
		super("Airplane was not found");
	}

	/**
	 * Exception to throw when a particular error message would be more beneficial
	 * 
	 * @param arg0 
	 */
	public AirplaneNotFoundException(String arg0) {
		super(arg0);
	}
}
