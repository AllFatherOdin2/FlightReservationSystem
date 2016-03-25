package CS509.client.Interfaces;

import java.util.HashMap;

public interface IFlightManager 
{
	public HashMap<String, IFlight> getFlights(IAirport departureAirport, IAirport arrivalAirport,String date);
}
