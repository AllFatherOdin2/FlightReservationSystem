package CS509.client.trip;

import CS509.client.Interfaces.*;

public class RoundTripManager extends TripManager
{
	private final String getReturnDate = "What date would you like to return home? (yyyy_mm_dd)";

	public RoundTripManager(IAirportManager airportManager, IFlightManager flightManager) {
		super(airportManager, flightManager);
	}

	@Override
	public void CollectInfo(IDisplay display) {
		String departureAirportCode = display.GetUserInput(this.getDepartureCode);
		String arrivalAirportCode = display.GetUserInput(this.getArrivalCode);
		String departDate = display.GetUserInput(this.getDepartureDate);
		String returnDate = display.GetUserInput(this.getReturnDate);
		
		this.trips.add(new Trip(departureAirportCode, arrivalAirportCode, departDate));
		this.trips.add(new Trip(arrivalAirportCode, departureAirportCode, returnDate));
	}


	public String toString() {
		return "Round Trip";
	}
}
