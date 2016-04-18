package CS509.client.trip;

import java.util.ArrayList;
import java.util.List;
import CS509.client.Interfaces.*;

public abstract class TripManager implements ITripManager 
{
	protected IAirportManager airportManager;
	protected IFlightManager flightManager;
	protected List<ITrip> trips;
	
	protected final String getDepartureCode = "What airport are you departing from? (Three letter Code)";
	protected final String getArrivalCode = "What airport are you arriving to? (Three letter Code)";
	protected final String getDepartureDate = "What date would you like to leave? (yyyy_mm_dd)";
	
	public TripManager(IAirportManager airportManager, IFlightManager flightManager) {
		this.airportManager = airportManager;
		this.flightManager = flightManager;
		this.trips = new ArrayList<ITrip>();
	}
	
	@Override
	//// This is currently set up to store the hashmap of flights by the departure date.
	//// this is set up for multi sections
	//// TODO is there a better way of doing this?
	public void PlanTrip() {
		// TODO Auto-generated method stub
		for(ITrip trip : this.trips ) {
		    trip.Plan(this.airportManager, this.flightManager);
		}						
	}
	
	public List<ITrip> getTrips(){
		return this.trips;
	}
}
