package CS509.client.trip;

import java.util.HashMap;
import java.util.Scanner;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.ITrip;
import CS509.client.Interfaces.ITripManagerFactory;
import CS509.client.Interfaces.ITripManager;
import CS509.client.dao.Server;

public class TripManagerFactory implements ITripManagerFactory
{	
	private IAirportManager airportManager;
	
	private IFlightManager flightManager;
	
	public TripManagerFactory(IAirportManager airportManager, IFlightManager flightManager)
	{
		this.airportManager = airportManager;
		this.flightManager = flightManager;
	}

	@Override
	public ITripManager getNewTrip(int trip) {
		// TODO This should load them up using reflection. Makes it far more dynamic. This however suffices for the project
		switch(trip)
		{
			case 1:
				return new OneWayTripManager(airportManager, flightManager);
			case 2:
				return new RoundTripManager(airportManager, flightManager);
			default:
				System.out.println("Unable to find type of trip");
				return null;
		}
	}
	
	public IFlightManager getFlightManager(){
		return flightManager;
	}

}
