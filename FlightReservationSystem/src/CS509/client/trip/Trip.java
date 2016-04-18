package CS509.client.trip;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.ITrip;
import CS509.client.airport.AirportManager;
import CS509.client.airport.AirportNotFoundException;
import CS509.client.flight.FlightManager;

public class Trip implements ITrip
{
	protected String departureAirportCode;
	
	protected String arrivalAirportCode;

	protected String depatureDate;

	protected HashMap<String, IFlight> flights;
	
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
	public HashMap<String, IFlight> getFlights() {
		// TODO Auto-generated method stub
		return this.flights;
	}	
	
	@Override
	public void Plan(IAirportManager airportManager, IFlightManager flightManager){	
		try {
			IAirport departureAirport = airportManager.getAirport(this.departureAirportCode);
		
			IAirport arrivalAirport = airportManager.getAirport(this.arrivalAirportCode);
			
			flightManager.addAll(this.arrivalAirportCode, this.depatureDate, false);
			this.flights = flightManager.getFlights(departureAirport, arrivalAirport, this.depatureDate);
		} catch (AirportNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
