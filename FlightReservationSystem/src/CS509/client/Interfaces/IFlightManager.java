package CS509.client.Interfaces;

import java.util.HashMap;
import java.util.List;

import CS509.client.flight.FlightNotFoundException;

public interface IFlightManager 
{
	IFlight getSpecificFlight(String number) throws FlightNotFoundException;
	public HashMap<String, IFlight> getFlights(IAirport departureAirport, IAirport arrivalAirport,String date);
	boolean addAll(String code, String day, boolean isDepartingDay);
	HashMap<String, IFlightPlan> getConnectingFlights(String arrivalCode, String day) throws FlightNotFoundException;
}
