import static org.junit.Assert.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import CS509.client.airport.Airport;
import CS509.client.airport.AirportNotFoundException;
import CS509.client.airport.Airports;
import CS509.client.dao.ServerInterface;
import CS509.client.flight.Flights;
import CS509.client.util.QueryFactory;


public class junitTesting {
	static final String agencyTicketString = "Team07";
	
	@Test
	public void testCodeGetsAirports() throws AirportNotFoundException{
		ServerInterface serverInterface = new ServerInterface();
		
		//Lock database for our use
		serverInterface.lock(agencyTicketString);
		
		//Create airportManager using xmlString from query factory that gets all airports
		Airports airportManger = new Airports();
		String xmlString = serverInterface.getAirports(agencyTicketString);
		airportManger.addAll(xmlString);
		
		serverInterface.unlock(agencyTicketString);
		
		assertTrue(airportManger.size()>0);
	}
	
	@Test
	public void testCodeGetsFlights() throws ParseException {
		//Get input from users regarding departure airport and date
		ServerInterface serverInterface = new ServerInterface();
		String departAirport = "BOS";
		String departDate = "2016_05_10";
		
		
		//Lock database for our use
		serverInterface.lock(agencyTicketString);
		
		//Create flightManager using xmlstring from query factory using user inputs
		Flights flightManager = new Flights();
		String xmlString = serverInterface.getFlights(agencyTicketString, departAirport, departDate);
		
		flightManager.addAll(xmlString);
	
		
		//unlock database for other teams to use
		serverInterface.unlock(agencyTicketString);
		
		assertTrue(flightManager.size()>0);
	}

}
