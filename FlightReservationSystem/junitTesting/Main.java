
import java.util.ArrayList;
import java.util.Scanner;

import CS509.client.Interfaces.IServiceLocator;
import CS509.client.Interfaces.ITrip;
import CS509.client.Interfaces.ITripFactory;
import CS509.client.Interfaces.ITripFactory.TripType;
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

		/*
		IServiceLocator serviceLocator = new ServiceLocator();
		//Get input from users regarding departure airport and date
		TripType tripType = TripType.OneWay;
		String departureAirportCode = getDepartAirport();
		String arrivalAirportCode = getArriveAirport();
		String depatureDate = getDepartDate();

		ITripFactory tripFactory = serviceLocator.getTripManager();
		
		ITrip trip = tripFactory.getNewTrip(tripType);
		trip.setDepartureAirportCode(departureAirportCode);
		trip.setDepatureDate(depatureDate);
		trip.setArrivalAirportCode(arrivalAirportCode);
		
		trip.PlanTrip();
		tripFactory.addAll();

		//Create flightManager using xmlstring from query factory using user inputs
		FlightManager flightManager = new FlightManager();
		xmlString = serverInterface.getFlights(agencyTicketString, arrivalAirportCode, departDate);
		flightManager.addAll(xmlString);
		
		String airplanesString = serverInterface.getAirplanes(agencyTicketString);
		
		
		
		for (Flight flight : flightManager) {
			if(arriveAirport.compareTo(flight.getmCodeArrival()) == 0){
				System.out.println();
				System.out.println(flight.getmNumber());
				System.out.println("Depart Time: " + flight.getmTimeDepart() + " from " + flight.getmCodeDepart());
				System.out.println("Arrive Time: " + flight.getmTimeArrival() + " to " + flight.getmCodeArrival());
			
			}
		}
		*/
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
