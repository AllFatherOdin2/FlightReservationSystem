package CS509.client.trip;

import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.ITrip;
import CS509.client.Interfaces.ITripManager;

public class TripManager implements ITripManager
{
	private IAirportManager airportManager;
	
	private IFlightManager flightManager;
	
	public TripManager(IAirportManager airportManager, IFlightManager flightManager)
	{
		this.airportManager = airportManager;
		this.flightManager = flightManager;
	}

	@Override
	public void planTrip(ITrip currentTrip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ITrip getNewTrip() {
		// TODO Auto-generated method stub
		return new Trip();
	}
}
