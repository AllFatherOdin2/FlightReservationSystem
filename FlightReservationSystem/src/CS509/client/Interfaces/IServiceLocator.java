package CS509.client.Interfaces;

public interface IServiceLocator 
{
	public IAirportManager getAirportManager();
	
	public IFlightManager getFlightManager();
	
	public ITripFactory getTripManager();
}
