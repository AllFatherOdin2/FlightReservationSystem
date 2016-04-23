package CS509.client.Interfaces;

public interface IFlightPlan 
{
	String getName();
	
	String getDepartureTime();
	
	String getArrivalTime();
	
	double getTotalCoachCost();
	
	double getTotalFirstClass();
	
	String getTotalTime();
	
	void UpdateLocalTimes(IAirportManager airportManager);
}
