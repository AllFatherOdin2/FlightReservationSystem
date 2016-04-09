package CS509.client.trip;

import java.util.HashMap;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.airport.AirportManager;
import CS509.client.airport.AirportNotFoundException;
import CS509.client.flight.FlightManager;

public class OneWayTrip extends Trip
{
	
	public OneWayTrip(IAirportManager airportManager, IFlightManager flightManager) {
		super(airportManager, flightManager);
	}

	@Override
	//// This is currently set up to store the hashmap of flights by the departure date.
	//// this is set up for multi sections
	//// TODO is there a better way of doing this?
	public void PlanTrip() {
		// TODO Auto-generated method stub
		
		try 
		{
			IAirport departureAirport = this.airportManager.getAirport(this.departureAirportCode);
			IAirport arrivalAirport = this.airportManager.getAirport(this.arrivalAirportCode);
			
			HashMap<String, IFlight> flights = this.flightManager.getFlights(departureAirport, arrivalAirport, this.depatureDate);
			this.tripLegs.put(this.depatureDate, flights);
			
		} catch (AirportNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
