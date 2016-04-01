/**
 * 
 */
package CS509.client.flight;

/**
 * @author David
 *
 */
public class InvalidFlightException extends Exception {

	/**
	 * Basic exception to throw if an Flight isn't found
	 */
	public InvalidFlightException() {
		super("Flight is invalid or malformed");
	}

	/**
	 * Exception to throw when a particular error message would be more beneficial
	 * 
	 * @param message Message to return to user
	 */
	public InvalidFlightException(String message) {
		super(message);
	}
}
