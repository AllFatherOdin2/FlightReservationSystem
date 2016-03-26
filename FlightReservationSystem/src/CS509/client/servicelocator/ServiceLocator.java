package CS509.client.servicelocator;

import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.IServiceLocator;
import CS509.client.Interfaces.ITripManager;
import CS509.client.airport.AirportManager;
import CS509.client.flight.FlightManager;
import CS509.client.trip.TripManager;

public class ServiceLocator implements IServiceLocator
{
	private IAirportManager airportManager;
	
	private IFlightManager flightManager;
	
	private ITripManager tripManager;
	
	public ServiceLocator()
	{
		this.airportManager = new AirportManager();
		//this.flightManager =  new FlightManager();
		this.tripManager = new TripManager(this.airportManager, this.flightManager);
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
	
	public ITripManager getTripManager(){
		return this.tripManager;
	}
}
