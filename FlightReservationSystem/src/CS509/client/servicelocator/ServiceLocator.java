package CS509.client.servicelocator;

import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.IServer;
import CS509.client.Interfaces.IServiceLocator;
import CS509.client.Interfaces.ITripFactory;
import CS509.client.airport.AirportManager;
import CS509.client.dao.Server;
import CS509.client.flight.FlightManager;
import CS509.client.trip.TripFactory;

public class ServiceLocator implements IServiceLocator
{
	private final String agencyTicketString = "Team07";
	private IAirportManager airportManager;
	
	private IFlightManager flightManager;
	
	private ITripFactory tripFactory;
	
	private IServer database;
	
	public ServiceLocator()
	{
		this.database = new Server(agencyTicketString);
		this.airportManager = new AirportManager(database);
		this.flightManager =  new FlightManager(database);
		this.tripFactory = new TripFactory(this.airportManager, this.flightManager);
	}
	
	public IAirportManager getAirportManager()
	{
		return this.airportManager;
	}
	
	@Override
	public IFlightManager getFlightManager() {
		// TODO Auto-generated method stub
		return this.flightManager;
	}
	
	public ITripFactory getTripManager(){
		return this.tripFactory;
	}
}
