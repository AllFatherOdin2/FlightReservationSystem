package CS509.client.trip;

import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.airport.AirportManager;
import CS509.client.flight.FlightManager;

public class OneWayTrip extends Trip
{
	
	public OneWayTrip(IAirportManager airportManager, IFlightManager flightManager) {
		super(airportManager, flightManager);
	}

	@Override
	public void PlanTrip() {
		// TODO Auto-generated method stub
		
	}

}
