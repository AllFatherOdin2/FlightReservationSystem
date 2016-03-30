/**
 * 
 */
package CS509.client.airplane;

/**
 * @author David
 *
 */
public class Airplane {
	private String mManufacturer;
	private String mModel;
	private int	mFirstClassSeats;
	private int mCoachSeats;
	
	/**
	 * Basic constructor for Airplane
	 */
	public Airplane(){
		mManufacturer = "";
		mModel = "";
		mFirstClassSeats = 0;
		mCoachSeats = 0;
	}
	
	/**
	 * Constructor that takes attributes for Airplane
	 */
	public Airplane(String manu, String model, int fcSeats, int cSeats){
		mManufacturer = manu;
		mModel = model;
		mFirstClassSeats = fcSeats;
		mCoachSeats = cSeats;
	}
	
}
