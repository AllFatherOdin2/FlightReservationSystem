/**
 * 
 */
package CS509.client.flight;

import java.text.SimpleDateFormat;
import java.util.Date;

import CS509.client.Interfaces.*;

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
	private IAirplane mAirplane;
	private String mFlightTime;
	private String mNumber;
	private IAirport dAirport;
	private String mTimeDepart;
	private IAirport arAirport;
	private String mTimeArrival;
	private String mPriceFirstclass;
	private int mSeatsFirstclass;
	private String mPriceCoach;
	private int mSeatsCoach;
	
	private String flightInfo = null;
	
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
			IAirplane airplane,
			String flightTime,
			String number,
			IAirport dAirport,
			String timeDepart,
			IAirport arAirport,
			String timeArrival,
			String priceFirstclass,
			int seatsFirstclass,
			String priceCoach,
			int seatsCoach) {
		
		mAirplane = airplane;
		mFlightTime = flightTime;
		mNumber = number;
		this.dAirport = dAirport;
		mTimeDepart = timeDepart;
		this.arAirport = arAirport;
		mTimeArrival = timeArrival;
		mPriceFirstclass = priceFirstclass;
		mSeatsFirstclass = seatsFirstclass;
		mPriceCoach = priceCoach;
		mSeatsCoach = seatsCoach;
	}

	public boolean isValid() {
		try {
			if ((mAirplane == null) || (mAirplane.getModel().length() == 0)) {
				return false;
			}
			if (Integer.parseInt(mFlightTime) <= 0) {
				return false;
			}
			if (Integer.parseInt(mNumber) <= 0) {
				return false;
			}
			if (this.dAirport.getCode().length() != 3) {
				return false;
			}
			if (this.arAirport.getCode().length() != 3) {
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
	
	public IAirport getDepartureAirport(){
		return this.dAirport;
	}
	
	public IAirport getArrivalAirport(){
		return this.arAirport;
	}
	
	/**
	 * @return the mAirplane
	 */
	public String getmAirplane() {
		return this.mAirplane.getModel();
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
		return this.dAirport.getCode();
	}


	/**
	 * @return the mTimeDepart
	 */
	public String getmTimeDepart() {
		return this.dAirport.getLocalTime(this.mTimeDepart);
	}

	/**
	 * @return the mCodeArrival
	 */
	public String getmCodeArrival() {
		return this.arAirport.getCode();
	}

	/**
	 * @return the mTimeArrival
	 */
	public String getmTimeArrival() {
		return this.arAirport.getLocalTime(this.mTimeArrival);
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
	
	public boolean canReserveCoach(){
		return this.mSeatsCoach < this.mAirplane.getmCoachSeats();
	}
	
	public boolean canReserveFirstClass(){
		return this.mSeatsFirstclass < this.mAirplane.getmFirstClassSeats();
	}
	
	public void reserveCoach(IServer database){
		String xmlReservation = "<Flights>"
				+ "<Flight number=\"" + this.mNumber + "\" seating=\"Coach\"/>"
				+ "</Flights>";
		if(database.buyTickets(xmlReservation)){
			this.mSeatsCoach++;
		}
	}
	
	public void reserveFirstClass(IServer database){
		String xmlReservation = "<Flights>"
				+ "<Flight number=\"" + this.mNumber + "\" seating=\"FirstClass\"/>"
				+ "</Flights>";
		if(database.buyTickets(xmlReservation)){
			this.mSeatsFirstclass++;
		}
	}
	
	@Override
	public String toString(){
		
		if(this.flightInfo == null)
		{
			this.flightInfo = "";
			flightInfo = flightInfo + "Flight Number: " + this.mNumber + "\n";
			flightInfo = flightInfo + "Flight Time: " + this.mFlightTime + "\n";
			flightInfo = flightInfo + "From: " + this.dAirport.getCode() + " To: " + this.arAirport.getCode() +"\n";
			flightInfo = flightInfo + "\n";
			flightInfo = flightInfo + "Departure Time: " + this.dAirport.getLocalTime(this.mTimeDepart) + "\n";
			flightInfo = flightInfo + "Arrival Time: " + this.arAirport.getLocalTime(this.mTimeArrival) + "\n";
			flightInfo = flightInfo + "\n";
			flightInfo = flightInfo + "Coach Seats: " + this.mSeatsCoach + "\n";
			flightInfo = flightInfo + "Coach Price: " + this.mPriceCoach + "\n";
			flightInfo = flightInfo + "\n";
			flightInfo = flightInfo + "First Class Seats " + this.mSeatsFirstclass + "\n";
			flightInfo = flightInfo + "First Class Price: " + this.mPriceFirstclass + "\n";
			flightInfo = flightInfo + "\n";
		}
		
		return flightInfo;
	}
}
 
