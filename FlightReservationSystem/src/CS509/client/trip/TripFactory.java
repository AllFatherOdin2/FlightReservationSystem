package CS509.client.trip;

import java.util.HashMap;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.ITrip;
import CS509.client.Interfaces.ITripFactory;

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
	public ITrip getNewTrip(TripType trip) {
		// TODO This should load them up using reflection. Makes it far more dynamic. This however suffices for the project
		switch(trip)
		{
			case OneWay:
				return new OneWayTrip();
			case RoundTrip:
				return new RoundTrip();
			default:
				return null;
		}
	}
}
