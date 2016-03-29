package CS509.client.Interfaces;

public interface ITripManager 
{
	public enum TripType
	{
		OneWay,
		RoundTrip,
	}
	
	public void planTrip(ITrip currentTrip);
	
	public ITrip getNewTrip(TripType trip);
}
