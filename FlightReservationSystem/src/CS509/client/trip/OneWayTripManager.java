package CS509.client.trip;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IDisplay;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.ITrip;
import CS509.client.airport.AirportManager;
import CS509.client.airport.AirportNotFoundException;
import CS509.client.flight.FlightManager;

public class OneWayTripManager extends TripManager
{
	
	public OneWayTripManager(IAirportManager airportManager, IFlightManager flightManager) {
		super(airportManager, flightManager);
	}
	
	@Override
	public void CollectInfo(IDisplay display) {
		String departureAirportCode = display.GetUserInput(this.getDepartureCode);
		String arrivalAirportCode = display.GetUserInput(this.getArrivalCode);
		String departDate = display.GetUserInput(this.getDepartureDate);
		
		this.trips.add(new Trip(departureAirportCode, arrivalAirportCode, departDate));
	}
	
	public String toString(){
		return "One way trip";
	}
}
