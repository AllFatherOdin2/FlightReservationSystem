import java.util.Scanner;

import CS509.client.airport.Airports;
import CS509.client.flight.Flights;
import CS509.client.util.QueryFactory;


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
		//Get input from users regarding departure airport and date
		String departAirport = getDepartAirport();
		String departDate = getDepartDate();
		
		
		//Lock database for our use
		QueryFactory.lock(agencyTicketString);
		
		//Create airportManager using xmlString from query factory that gets all airports
		Airports airportManger = new Airports();
		String xmlString = QueryFactory.getAirports(agencyTicketString);
		airportManger.addAll(xmlString);
		
		//Create flightManager using xmlstring from query factory using user inputs
		Flights flightManager = new Flights();
		xmlString = QueryFactory.getFlightsDeparting(agencyTicketString, departAirport, departDate);
		flightManager.addAll(xmlString);
		
		//unlock database for other teams to use
		QueryFactory.unlock(agencyTicketString);
		
	}
	
	private static String getDepartAirport(){
		System.out.println("What airport are you departing from? (Three letter Code)");
		String departAirport = sc.nextLine().toUpperCase();
		
		return departAirport;
	}
	
	private static String getDepartDate(){

		System.out.println("What date would you like to leave? (mm/dd/yyyy) or \"Back\"");
		String dateString = sc.nextLine().toUpperCase();
		
		//TODO implement BACK functionality and error handling
		return dateString;
	}

}
