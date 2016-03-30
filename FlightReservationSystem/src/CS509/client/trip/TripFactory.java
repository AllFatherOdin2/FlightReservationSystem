package CS509.client.trip;

import java.util.HashMap;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.ITrip;
import CS509.client.Interfaces.ITripFactory;
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
	public void addAll(){
		Server server = new Server("Team07");
		flightManager.addAll(server.getFlights("BOS", "2016_05_10"));
	}

	@Override
	public ITrip getNewTrip(TripType trip) {
		// TODO This should load them up using reflection. Makes it far more dynamic. This however suffices for the project
		switch(trip)
		{
			case OneWay:
				return new OneWayTrip(airportManager, flightManager);
			case RoundTrip:
				return new RoundTrip(airportManager, flightManager);
			default:
				return null;
		}
	}
	
	public IFlightManager getFlightManager(){
		return flightManager;
	}

}
