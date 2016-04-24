package CS509.client.servicelocator;

import CS509.client.Display.ScannerDisplayManager;
import CS509.client.Interfaces.*;
import CS509.client.airplane.AirplaneManager;
import CS509.client.airport.AirportManager;
import CS509.client.dao.Server;
import CS509.client.flight.FlightManager;
import CS509.client.reservation.ReservationFactory;
import CS509.client.trip.TripManagerFactory;
import CS509.client.util.LocalTimeFactory;

public class ServiceLocator implements IServiceLocator
{
	private final String agencyTicketString = "Team07";
	private IAirportManager airportManager;
	
	private IFlightManager flightManager;
	
	private ITripManagerFactory tripFactory;
	
	private IAirplaneManager airplaneManager;
	
	private IServer database;
	
	private IDisplayManager displayManager;
	
	private ILocalTimeFactory timeFactory;
	
	private IReservationFactory reservationFactory;
	
	public ServiceLocator()
	{
		this.database = new Server(agencyTicketString);
		this.timeFactory = new LocalTimeFactory();
		this.reservationFactory = new ReservationFactory();
		this.airplaneManager = new AirplaneManager(database);
		this.airportManager = new AirportManager(database, this.timeFactory);
		this.flightManager =  new FlightManager(database, this.airportManager, this.airplaneManager);
		this.tripFactory = new TripManagerFactory(this.airportManager, this.flightManager);
		this.displayManager = new ScannerDisplayManager(this);
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
	
	@Override
	public ITripManagerFactory getTripManager(){
		return this.tripFactory;
	}
	
	@Override
	public IAirplaneManager getAirplaneManager(){
		return airplaneManager;
	}
	
	public IDisplayManager getDisplayManager(){
		return this.displayManager;
	}
	
	public IReservationFactory getReservationFactory(){
		return this.reservationFactory;
	}
	
	public IServer getDatabase(){
		return this.database;
	}
}
