package CS509.client.trip;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.ITrip;
import CS509.client.airport.AirportManager;
import CS509.client.airport.AirportNotFoundException;
import CS509.client.flight.FlightManager;

public class OneWayTrip extends TripManager
{
	
	public OneWayTrip(IAirportManager airportManager, IFlightManager flightManager, Scanner sc) {
		super(airportManager, flightManager, sc);
	}
	
	@Override
	public void CollectInfo() {
		String departureAirportCode = this.getDepartAirport();
		String arrivalAirportCode = this.getArriveAirport();
		String departDate = this.getDepartDate();
		
		this.trips.add(new Trip(departureAirportCode, arrivalAirportCode, departDate));
	}
}
