package CS509.client.Interfaces;

import java.util.HashMap;
import java.util.Scanner;

public interface ITrip 
{
	public String getDepartureAirportCode();
	
	public String getArrivalAirportCode();

	public String getDepatureDate();
	
	public HashMap<String, IFlight> getFlights();
	
	public void Plan(IAirportManager airportManager, IFlightManager flightManager);
}
