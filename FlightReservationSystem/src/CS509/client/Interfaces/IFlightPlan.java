package CS509.client.Interfaces;

import java.util.List;

public interface IFlightPlan 
{
	String getName();
	
	String getDepartureTime();
	
	String getArrivalTime();
	
	double getTotalCoachCost();
	
	double getTotalFirstClass();
	
	String getTotalTime();
	
	List<IFlight> getFlights();
	
	boolean canReserveCoach();
	
	boolean canReserveFirstClass();
}
