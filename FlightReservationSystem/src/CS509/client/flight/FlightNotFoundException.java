/**
 * 
 */
package CS509.client.flight;

/**
 * @author David
 *
 */
public class FlightNotFoundException extends Exception {
	
	/**
	 * Basic exception to throw if an Flight isn't found
	 */
	public FlightNotFoundException() {
		super("Flight was not found by query");
	}

	/**
	 * Exception to throw when a particular error message would be more beneficial
	 * 
	 * @param message Message to return to user
	 */
	public FlightNotFoundException(String message) {
		super(message);
	}

}
