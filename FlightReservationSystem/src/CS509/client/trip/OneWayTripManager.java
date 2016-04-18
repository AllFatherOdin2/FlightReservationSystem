package CS509.client.trip;

import CS509.client.Interfaces.*;

public class OneWayTripManager extends TripManager
{
	
	public OneWayTripManager(IAirportManager airportManager, IFlightManager flightManager) {
		super(airportManager, flightManager);
	}
	
	@Override
	public void CollectInfo(IDisplay display) {
		String departureAirportCode = display.GetUserInput(this.getDepartureCode);
		String arrivalAirportCode = display.GetUserInput(this.getArrivalCode);
		String departDate = display.GetUserInput(this.getDepartureDate);
		
		this.trips.add(new Trip(departureAirportCode, arrivalAirportCode, departDate));
	}
	
	public String toString(){
		return "One way trip";
	}
}
