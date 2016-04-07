package CS509.client.Interfaces;

import CS509.client.airport.Airport;
import CS509.client.airport.AirportNotFoundException;

public interface IAirportManager 
{
	public IAirport getAirport(String airportCode) throws AirportNotFoundException;

}
