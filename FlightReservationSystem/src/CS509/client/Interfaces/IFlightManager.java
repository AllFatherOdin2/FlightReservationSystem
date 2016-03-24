package CS509.client.Interfaces;

import java.util.Dictionary;

public interface IFlightManager 
{
	public Dictionary<String, IFlight> getFlights(IAirport departureAirport, IAirport arrivalAirport,String date);
}
