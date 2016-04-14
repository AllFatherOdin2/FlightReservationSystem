package CS509.client.trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import CS509.client.Interfaces.*;

public abstract class TripManager implements ITripManager 
{
	protected IAirportManager airportManager;
	protected IFlightManager flightManager;
	protected Scanner sc;
	protected List<ITrip> trips;
	
	public TripManager(IAirportManager airportManager, IFlightManager flightManager, Scanner sc) {
		this.airportManager = airportManager;
		this.flightManager = flightManager;
		this.sc = sc;
		this.trips = new ArrayList<ITrip>();
	}
	
	@Override
	//// This is currently set up to store the hashmap of flights by the departure date.
	//// this is set up for multi sections
	//// TODO is there a better way of doing this?
	public void PlanTrip() {
		// TODO Auto-generated method stub
		for(Iterator<ITrip> iterate = this.trips.iterator(); iterate.hasNext(); ) {
		    ITrip trip = iterate.next();
		    trip.Plan(this.airportManager, this.flightManager);
		}						
	}
	
	protected String getDepartAirport(){
		System.out.println("What airport are you departing from? (Three letter Code)");
		String departAirport = this.sc.nextLine().toUpperCase();
		
		return departAirport;
	}

	protected String getArriveAirport(){
		System.out.println("What airport so you wish to arrive at? (Three letter Code)");
		String arriveAirport = this.sc.nextLine().toUpperCase();
		
		return arriveAirport;
	}
	
	protected String getDepartDate(){

		System.out.println("What date would you like to leave? (yyyy_mm_dd)");
		String departureDate = this.sc.nextLine().toUpperCase();
		
		return departureDate;
	}
}
