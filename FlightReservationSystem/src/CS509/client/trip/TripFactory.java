package CS509.client.trip;

import java.util.HashMap;
import java.util.Scanner;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.ITrip;
import CS509.client.Interfaces.ITripFactory;
import CS509.client.Interfaces.ITripManager;
import CS509.client.dao.Server;

public class TripFactory implements ITripFactory
{	
	private IAirportManager airportManager;
	
	private IFlightManager flightManager;
	
	public TripFactory(IAirportManager airportManager, IFlightManager flightManager)
	{
		this.airportManager = airportManager;
		this.flightManager = flightManager;
	}

	@Override
	public ITripManager getNewTrip(int trip, Scanner sc) {
		// TODO This should load them up using reflection. Makes it far more dynamic. This however suffices for the project
		switch(trip)
		{
			case 1:
				return new OneWayTrip(airportManager, flightManager, sc);
			case 2:
				return new RoundTrip(airportManager, flightManager, sc);
			default:
				System.out.println("Unable to find type of trip");
				return null;
		}
	}
	
	public IFlightManager getFlightManager(){
		return flightManager;
	}

}
