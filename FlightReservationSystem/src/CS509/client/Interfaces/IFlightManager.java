package CS509.client.Interfaces;

import java.util.List;

public interface IFlightManager 
{
	public List<IFlight> getFlights(IAirport departureAirport, IAirport arrivalAirport,String date);
}
