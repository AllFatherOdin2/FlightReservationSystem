package CS509.client.trip;

import java.util.HashMap;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.airport.AirportNotFoundException;

public class RoundTrip extends Trip
{

	public RoundTrip(IAirportManager airportManager, IFlightManager flightManager) {
		super(airportManager, flightManager);
	}

	@Override
	public void PlanTrip() {
		// TODO Auto-generated method stub
		try 
		{
			IAirport departureAirport = this.airportManager.getAirport(this.departureAirportCode);
			IAirport arrivalAirport = this.airportManager.getAirport(this.arrivalAirportCode);
			
			HashMap<String, IFlight> flights = this.flightManager.getFlights(departureAirport, arrivalAirport, this.depatureDate);
			this.tripLegs.put(this.depatureDate, flights);
			
			//TODO put the return home code
			
		} catch (AirportNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
