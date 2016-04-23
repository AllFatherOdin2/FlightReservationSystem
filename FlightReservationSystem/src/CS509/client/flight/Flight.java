/**
 * 
 */
package CS509.client.flight;

import java.text.SimpleDateFormat;
import java.util.Date;

import CS509.client.Interfaces.IFlight;

/**
 * This class holds values pertaining to a single flight from one airport to another. 
 * Class member attributes are the same as defined by the CS509 server API and store 
 * values after conversion from XML received from the server to Java primitives. 
 * Attributes are accessed via getter and setter methods.
 * 
 * @author blake
 * @version 1
 * @since 2016-02-24
 *
 */
public class Flight implements IFlight {

	/**
	 * Member attributes describing a flight
	 */
	private String mAirplane;
	private String mFlightTime;
	private String mNumber;
	private String mCodeDepart;
	private String mTimeDepart;
	private String mCodeArrival;
	private String mTimeArrival;
	private String mPriceFirstclass;
	private int mSeatsFirstclass;
	private String mPriceCoach;
	private int mSeatsCoach;
	
	/*
	public Flight () {
	    mAirplane = "";
	    mFlightTime = "";
	    mNumber = "";
	    mCodeDepart = "";
		mTimeDepart = "";
		mCodeArrival = "";
		mTimeArrival = "";
		mPriceFirstclass = "";
		mSeatsFirstclass = 0;
		mPriceCoach = "";
		mSeatsCoach = 0;
	}
	*/
	
	public Flight (
			String airplane,
			String flightTime,
			String number,
			String codeDepart,
			String timeDepart,
			String codeArrival,
			String timeArrival,
			String priceFirstclass,
			int seatsFirstclass,
			String priceCoach,
			int seatsCoach) {
		
		mAirplane = airplane;
		mFlightTime = flightTime;
		mNumber = number;
		mCodeDepart = codeDepart;
		mTimeDepart = timeDepart;
		mCodeArrival = codeArrival;
		mTimeArrival = timeArrival;
		mPriceFirstclass = priceFirstclass;
		mSeatsFirstclass = seatsFirstclass;
		mPriceCoach = priceCoach;
		mSeatsCoach = seatsCoach;
	}

	public boolean isValid() {
		try {
			if ((mAirplane == null) || (mAirplane.length() == 0)) {
				return false;
			}
			if (Integer.parseInt(mFlightTime) <= 0) {
				return false;
			}
			if (Integer.parseInt(mNumber) <= 0) {
				return false;
			}
			if (mCodeDepart.length() != 3) {
				return false;
			}
			if (mCodeArrival.length() != 3) {
				return false;
			}
			// verify departure time and arrival time are expected formats
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm z");
			@SuppressWarnings("unused")
			Date tmpDate = sdf.parse(mTimeDepart);
			tmpDate = sdf.parse(mTimeArrival);
			
			/**
			 * Need to match:
			 * $ at start of string
			 * some number of digits for dollars
			 * . as dollar cents separator
			 * two digits as cents
			 * end of string. no following characters
			 */
			
			if (!mPriceFirstclass.matches("^\\$\\d*\\.\\d\\d$")) {
				return false;
			}
			if (!mPriceCoach.matches("^\\$\\d*\\.\\d\\d$")) {
				return false;
			}
			
			if (mSeatsFirstclass < 0) {
				return false;
			}
			if (mSeatsCoach < 0) {
				return false;
			}
		} catch (Exception ex) {
			// On any exception, including parse exceptions, the object is not valid
			return false;
		}
		
		// Nothing invalid detected, the object instance looks good
		return true;
	}
	
	/**
	 * @return the mAirplane
	 */
	public String getmAirplane() {
		return mAirplane;
	}

	/**
	 * @return the mFlightTime
	 */
	public String getmFlightTime() {
		return mFlightTime;
	}

	/**
	 * @return the mNumber
	 */
	public String getmNumber() {
		return mNumber;
	}

	/**
	 * @return the mCodeDepart
	 */
	public String getmCodeDepart() {
		return mCodeDepart;
	}


	/**
	 * @return the mTimeDepart
	 */
	public String getmTimeDepart() {
		return mTimeDepart;
	}

	/**
	 * @return the mCodeArrival
	 */
	public String getmCodeArrival() {
		return mCodeArrival;
	}

	/**
	 * @return the mTimeArrival
	 */
	public String getmTimeArrival() {
		return mTimeArrival;
	}

	/**
	 * @return the mPriceFirstclass
	 */
	public String getmPriceFirstclass() {
		return mPriceFirstclass;
	}

	/**
	 * @return the mSeatsFirstclass
	 */
	public int getmSeatsFirstclass() {
		return mSeatsFirstclass;
	}

	/**
	 * @return the mPriceCoach
	 */
	public String getmPriceCoach() {
		return mPriceCoach;
	}

	/**
	 * @return the mSeatsEconomy
	 */
	public int getmSeatsCoach() {
		return mSeatsCoach;
	}	
	
	@Override
	public String toString(){
		String flightInfo = "";
		
		flightInfo = flightInfo + "Flight Number: " + this.mNumber + "\n";
		flightInfo = flightInfo + "Flight Time: " + this.mFlightTime + "\n";
		flightInfo = flightInfo + "From: " + this.mCodeDepart + " To: " + this.mCodeArrival +"\n";
		flightInfo = flightInfo + "\n";
		flightInfo = flightInfo + "Departure Time: " + this.getmTimeDepart() + "\n";
		flightInfo = flightInfo + "Arrival Time: " + this.getmTimeArrival() + "\n";
		flightInfo = flightInfo + "\n";
		flightInfo = flightInfo + "Coach Seats: " + this.mSeatsCoach + "\n";
		flightInfo = flightInfo + "Coach Price: " + this.mPriceCoach + "\n";
		flightInfo = flightInfo + "\n";
		flightInfo = flightInfo + "First Class Seats " + this.mSeatsFirstclass + "\n";
		flightInfo = flightInfo + "First Class Price: " + this.mPriceFirstclass + "\n";
		flightInfo = flightInfo + "\n";
		
		return flightInfo;
	}
}
 
