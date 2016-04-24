package CS509.client.trip;

import java.util.HashMap;
import CS509.client.Interfaces.*;
import CS509.client.airport.AirportNotFoundException;
import CS509.client.flight.FlightNotFoundException;

public class Trip implements ITrip
{
	protected String departureAirportCode;
	
	protected String arrivalAirportCode;

	protected String depatureDate;

	protected HashMap<String, IFlightPlan> flightPlans;
	
	private boolean isReserved;
	
	public Trip(String departureCode, String arrivalCode, String departureDate) {
		this.departureAirportCode = departureCode;
		this.arrivalAirportCode = arrivalCode;
		this.depatureDate = departureDate;
		this.isReserved = false;
	}
	
	@Override
	public String getDepartureAirportCode() {
		return arrivalAirportCode;
	}

	@Override
	public String getArrivalAirportCode() {
		return departureAirportCode;
	}

	@Override
	public String getDepatureDate() {
		return depatureDate;
	}	

	@Override
	public HashMap<String, IFlightPlan> getFlightPlans() {
		// TODO Auto-generated method stub
		return this.flightPlans;
	}
	
	@Override
	public void Plan(IAirportManager airportManager, IFlightManager flightManager){	

		this.flightPlans = flightManager.getFlightPlans(departureAirportCode, arrivalAirportCode, depatureDate, 2);	
	}

	@Override
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;		
	}

	@Override
	public boolean getReserved() {
		return this.isReserved;
	}
}
