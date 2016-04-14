package CS509.client.trip;

import java.util.HashMap;
import java.util.Scanner;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.airport.AirportNotFoundException;

public class RoundTripManager extends TripManager
{

	public RoundTripManager(IAirportManager airportManager, IFlightManager flightManager, Scanner sc) {
		super(airportManager, flightManager, sc);
	}

	@Override
	public void CollectInfo() {

	}
	
	private String ReturnHomeDate(Scanner sc){
		System.out.println("What date would you like to return home? (yyyy_mm_dd)");
		String dateString = sc.nextLine().toUpperCase();
		
		//TODO implement BACK functionality and error handling
		return dateString;
	}
}
