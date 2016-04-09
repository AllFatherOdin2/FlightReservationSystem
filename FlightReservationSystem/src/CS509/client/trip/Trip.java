package CS509.client.trip;

import java.util.HashMap;
import java.util.List;

import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.ITrip;
import CS509.client.airport.AirportManager;
import CS509.client.flight.FlightManager;

public abstract class Trip implements ITrip
{
	protected String departureAirportCode;
	
	protected String arrivalAirportCode;

	protected String depatureDate;
	
	protected IAirportManager airportManager;
	protected IFlightManager flightManager;
	protected HashMap<String, HashMap<String,IFlight>> tripLegs;
	
	public Trip(IAirportManager airportManager, IFlightManager flightManager) {
		this.airportManager = airportManager;
		this.flightManager = flightManager;
		this.tripLegs = new HashMap<String, HashMap<String, IFlight>>();
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
	public abstract void PlanTrip();

	@Override
	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

	@Override
	public void setArrivalAirportCode(String arrivalAirportCode) {
		this.arrivalAirportCode = arrivalAirportCode;
	}

	@Override
	public String getDepatureDate() {
		return depatureDate;
	}

	@Override
	public void setDepatureDate(String depatureDate) {
		this.depatureDate = depatureDate;
	}	
	
	public HashMap<String, IFlight> GetFlightsFromTrip(String searchedDate){
		return this.tripLegs.get(searchedDate);
	}
}
