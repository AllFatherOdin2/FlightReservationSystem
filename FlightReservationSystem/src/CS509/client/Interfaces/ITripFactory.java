package CS509.client.Interfaces;

import java.util.Scanner;

public interface ITripFactory 
{
	public enum TripType
	{
		OneWay,
		RoundTrip,
	}
	
	public ITripManager getNewTrip(int trip, Scanner sc);
}
