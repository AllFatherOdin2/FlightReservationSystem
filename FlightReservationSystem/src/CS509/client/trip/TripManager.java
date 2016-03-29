package CS509.client.trip;

import java.util.HashMap;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
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
	public void planTrip(ITrip currentTrip) 
	{
		// TODO Auto-generated method stub
		// Where are we parsing the layover time? FlightManager?
		String departureAirportCode = currentTrip.getDepartureAirportCode();
		IAirport departureAirport = this.airportManager.GetAirport(departureAirportCode);
		IAirport arrivalAirport = this.airportManager.GetAirport(currentTrip.getArrivalAirportCode());
		
		HashMap<String, IFlight> flights = this.flightManager.getFlights(departureAirport, arrivalAirport, null);
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
				return new Trip();
		}
	}
}
