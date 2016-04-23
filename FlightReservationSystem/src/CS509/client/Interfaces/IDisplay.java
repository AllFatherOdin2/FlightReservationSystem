package CS509.client.Interfaces;

import java.util.List;

public interface IDisplay 
{
	public String GetUserInput(String output);
	
	public void DisplayMessage(String message);
	
	public void printFlights(List<IFlightPlan> currentFlights);
}
