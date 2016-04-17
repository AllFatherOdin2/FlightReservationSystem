package CS509.client.Interfaces;

import java.util.Scanner;

public interface ITripManagerFactory 
{
	public enum TripType
	{
		OneWay,
		RoundTrip,
	}
	
	public ITripManager getNewTrip(int trip);
}
