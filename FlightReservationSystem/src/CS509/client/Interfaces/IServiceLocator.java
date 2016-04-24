package CS509.client.Interfaces;

public interface IServiceLocator 
{
	public IAirportManager getAirportManager();
	
	public IFlightManager getFlightManager();
	
	public ITripManagerFactory getTripManager();

	public IAirplaneManager getAirplaneManager();
	
	public IDisplayManager getDisplayManager();
	
	public IReservationFactory getReservationFactory();
	
	public IServer getDatabase();
}
