
import java.util.ArrayList;
import java.util.Scanner;

import CS509.client.Interfaces.IServiceLocator;
import CS509.client.Interfaces.ITrip;
import CS509.client.Interfaces.ITripFactory;
import CS509.client.Interfaces.ITripFactory.TripType;
import CS509.client.Interfaces.ITripManager;
import CS509.client.airport.Airport;
import CS509.client.airport.AirportManager;
import CS509.client.dao.Server;
import CS509.client.flight.Flight;
import CS509.client.flight.FlightManager;
import CS509.client.servicelocator.ServiceLocator;
import CS509.client.trip.OneWayTrip;
import CS509.client.trip.TripFactory;


public class Main {
	static Scanner sc = new Scanner(System.in);
	static final String agencyTicketString = "Team07";
	
	/**
	 * Main file, asks user for flight location and date.
	 * Sanity checks inputs for correctness
	 * Queries database as needed and continutes taking inputs from user as needed
	 * 
	 * @param args -> empty
	 */
	public static void main(String[] args){

		
		}
	
	private static String getTripType(){
		System.out.println("What type of trip do you want to plan? (Enter corresponding number) \n1. OneWay\n2. RoundTrip");
		
		String departureDate = sc.nextLine();		
		return departureDate;
	}
}