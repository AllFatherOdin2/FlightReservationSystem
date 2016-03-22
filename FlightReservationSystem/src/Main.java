import java.util.Scanner;

import CS509.client.airport.AirportManager;
import CS509.client.dao.ServerInterface;
import CS509.client.flight.Flight;
import CS509.client.flight.FlightManager;


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
		String arriveAirport = getArriveAirport();
		String departDate = getDepartDate();
		ServerInterface serverInterface = new ServerInterface();
		
		//Create airportManager using xmlString from query factory that gets all airports
		AirportManager airportManger = new AirportManager();
		String xmlString = serverInterface.getAirports(agencyTicketString);
		airportManger.addAll(xmlString);
		
		//Create flightManager using xmlstring from query factory using user inputs
		FlightManager flightManager = new FlightManager();
		xmlString = serverInterface.getFlights(agencyTicketString, departAirport, departDate);
		flightManager.addAll(xmlString);
		
		/*
		//Lock database for our use
		serverInterface.lock(agencyTicketString);
		//unlock database for other teams to use
		serverInterface.unlock(agencyTicketString);
		*/
		
		for (Flight flight : flightManager) {
			if(arriveAirport.compareTo(flight.getmCodeArrival()) == 0){
				System.out.println();
				System.out.println(flight.getmNumber());
				System.out.println("Depart Time: " + flight.getmTimeDepart() + " from " + flight.getmCodeDepart());
				System.out.println("Arrive Time: " + flight.getmTimeArrival() + " to " + flight.getmCodeArrival());
			
			}
		}
	}
	
	private static String getDepartAirport(){
		System.out.println("What airport are you departing from? (Three letter Code)");
		String departAirport = sc.nextLine().toUpperCase();
		
		return departAirport;
	}

	private static String getArriveAirport(){
		System.out.println("What airport so you wish to arrive at? (Three letter Code)");
		String arriveAirport = sc.nextLine().toUpperCase();
		
		return arriveAirport;
	}
	
	private static String getDepartDate(){

		System.out.println("What date would you like to leave? (yyyy_mm_dd)");
		String dateString = sc.nextLine().toUpperCase();
		
		//TODO implement BACK functionality and error handling
		return dateString;
	}

}
