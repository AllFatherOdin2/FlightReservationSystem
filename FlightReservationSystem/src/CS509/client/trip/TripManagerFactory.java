package CS509.client.trip;

import CS509.client.Interfaces.*;

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
