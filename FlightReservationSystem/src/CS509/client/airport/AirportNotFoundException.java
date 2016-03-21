/**
 * 
 */
package CS509.client.airport;

/**
 * @author David
 *
 */
public class AirportNotFoundException extends Exception {

	/**
	 * Basic exception to throw if an Airport isnt found
	 */
	public AirportNotFoundException() {
		super("Airport was not found by query");
	}

	/**
	 * Exception to throw when a particular error message would be more beneficial
	 * 
	 * @param arg0 
	 */
	public AirportNotFoundException(String arg0) {
		super(arg0);
	}

}
