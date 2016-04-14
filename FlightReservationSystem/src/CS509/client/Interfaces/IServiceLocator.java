package CS509.client.Interfaces;

import CS509.client.airplane.AirplaneManager;

public interface IServiceLocator 
{
	public IAirportManager getAirportManager();
	
	public IFlightManager getFlightManager();
	
	public ITripManagerFactory getTripManager();

	public AirplaneManager getAirplaneManager();
}
