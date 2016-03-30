package CS509.client.Interfaces;

public interface ITripFactory 
{
	public enum TripType
	{
		OneWay,
		RoundTrip,
	}
	
	public ITrip getNewTrip(TripType trip);
}
