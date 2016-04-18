package CS509.client.Interfaces;

public interface ITripManagerFactory 
{
	public enum TripType
	{
		OneWay,
		RoundTrip,
	}
	
	public ITripManager getNewTrip(int trip);
}
