package CS509.client.trip;

import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.ITrip;
import CS509.client.airport.AirportManager;
import CS509.client.flight.FlightManager;

public abstract class Trip implements ITrip
{
	private String departureAirportCode;
	
	private String arrivalAirportCode;

	private String depatureDate;
	
	private IAirportManager airportManager;
	private IFlightManager flightManager;
	
	public Trip(IAirportManager airportManager, IFlightManager flightManager) {
		this.airportManager = airportManager;
		this.flightManager = flightManager;
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
}
