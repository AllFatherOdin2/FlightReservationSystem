package CS509.client.Interfaces;

import java.util.HashMap;

public interface ITrip 
{
	public String getDepartureAirportCode();
	
	public String getArrivalAirportCode();

	public String getDepatureDate();
	
	public HashMap<String, IFlightPlan> getFlightPlans();
	
	public void Plan(IAirportManager airportManager, IFlightManager flightManager);
	
	public void setReserved(boolean isReserved);
	
	public boolean getReserved();
}
