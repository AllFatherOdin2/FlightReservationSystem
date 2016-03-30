import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Dictionary;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import CS509.client.airport.Airport;
import CS509.client.airport.AirportNotFoundException;
import CS509.client.airport.AirportManager;
import CS509.client.dao.Server;
import CS509.client.flight.Flight;
import CS509.client.flight.FlightManager;
import CS509.client.flight.FlightNotFoundException;
import CS509.client.util.QueryFactory;


public class junitTesting {
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	static final String agencyTicketString = "Team07";
	Server serverInterface;
	
	@Before public void initialize(){
		serverInterface = new Server();
	}
	
	
	@Test
	public void testCodeGetsAirports() throws AirportNotFoundException{
		
		//Create airportManager using xmlString from query factory that gets all airports
		AirportManager airportManger = new AirportManager();
		String xmlString = serverInterface.getAirports(agencyTicketString);
		airportManger.addAll(xmlString);
		
		assertTrue(airportManger.size()>0);
	}
	
	@Test
	public void getSpecificAirport() throws AirportNotFoundException{
		AirportManager airportManger = new AirportManager();
		String xmlString = serverInterface.getAirports(agencyTicketString);
		airportManger.addAll(xmlString);
		
		Airport airport = airportManger.getSpecificAirport("BOS");
		assertEquals("BOS", airport.code());		
	}
	
	@Test
	public void getSpecificAirportFailed() throws AirportNotFoundException{
		thrown.expect(AirportNotFoundException.class);
		AirportManager airportManger = new AirportManager();
		String xmlString = serverInterface.getAirports(agencyTicketString);
		airportManger.addAll(xmlString);
		
		airportManger.getSpecificAirport("XXX");
	}
	
	@Test
	public void testCodeGetsFlights() throws ParseException {
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String departDate = "2016_05_10";
		
		//Create flightManager using xmlstring from query factory using user inputs
		FlightManager flightManager = new FlightManager();
		String xmlString = serverInterface.getFlights(agencyTicketString, departAirport, departDate);
		
		flightManager.addAll(xmlString);
		
		assertTrue(flightManager.size()>0);
	}

	@Test
	public void testCodeGetsSpecificFlight() throws ParseException, FlightNotFoundException {
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String departDate = "2016_05_10";
		String flightNumberString = "2807";
		
		//Create flightManager using xmlstring from query factory using user inputs
		FlightManager flightManager = new FlightManager();
		String xmlString = serverInterface.getFlights(agencyTicketString, departAirport, departDate);
		
		flightManager.addAll(xmlString);
		
		Flight flight = flightManager.getSpecificFlight(flightNumberString);

		assertEquals(flightNumberString, flight.getmNumber());
		assertEquals(departAirport, flight.getmCodeDepart());
		
		flight.setmAirplane("B2");
		assertEquals("B2", flight.getmAirplane());
		flight.setmFlightTime("Test Time");
		assertEquals("Test Time",flight.getmFlightTime());
		flight.setmNumber("9999");
		assertEquals("9999", flight.getmNumber());
		flight.setmCodeDepart("XXX");
		assertEquals("XXX", flight.getmCodeDepart());
		flight.setmTimeDepart("Test Time");
		assertEquals("Test Time", flight.getmTimeDepart());
		flight.setmCodeArrival("XXX");
		assertEquals("XXX", flight.getmCodeArrival());
		flight.setmTimeArrival("Test Time");
		assertEquals("Test Time", flight.getmTimeArrival());
		flight.setmPriceFirstclass("$9.95");
		assertEquals("$9.95", flight.getmPriceFirstclass());
		flight.setmPriceCoach("$4.95");
		assertEquals("$4.95", flight.getmPriceCoach());
		flight.setmSeatsFirstclass(10);
		assertEquals(10, flight.getmSeatsFirstclass());
		flight.setmSeatsCoach(10);
		assertEquals(10, flight.getmSeatsCoach());
	}
	

	@Test
	public void testCodeGetsSpecificFlightFailed() throws ParseException, FlightNotFoundException {
		thrown.expect(FlightNotFoundException.class);
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String departDate = "2016_05_10";
		
		//Create flightManager using xmlstring from query factory using user inputs
		FlightManager flightManager = new FlightManager();
		String xmlString = serverInterface.getFlights(agencyTicketString, departAirport, departDate);
		
		flightManager.addAll(xmlString);

		Flight flight = flightManager.getSpecificFlight("invalid flight");
	}
	
	@Test
	public void testReserveCoachSeat() throws ParseException, FlightNotFoundException {
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String departDate = "2016_05_10";

		//This test would fail if someone reserved a flight between us getting and reserving
		serverInterface.lock(agencyTicketString);
		
		//Create flightManager using xmlstring from query factory using user inputs
		FlightManager flightManager = new FlightManager();
		String xmlString = serverInterface.getFlights(agencyTicketString, departAirport, departDate);
		
		flightManager.addAll(xmlString);

		Flight flight = flightManager.getSpecificFlight("2807");
		int coachBefore = flight.getmSeatsCoach();
		int firstClassBefore = flight.getmSeatsFirstclass();
		
		serverInterface.buyTickets(agencyTicketString, "2807", true); 
		
		//get updated info
		flightManager = new FlightManager();
		xmlString = serverInterface.getFlights(agencyTicketString, departAirport, departDate);
		
		serverInterface.unlock(agencyTicketString);
		
		flightManager.addAll(xmlString);
		flight = flightManager.getSpecificFlight("2807");

		assertEquals(coachBefore + 1, flight.getmSeatsCoach());
		assertEquals(firstClassBefore, flight.getmSeatsFirstclass());
	}

	@Test
	public void testReserveFirstClassSeat() throws ParseException, FlightNotFoundException {
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String departDate = "2016_05_10";

		//This test would fail if someone reserved a flight between us getting and reserving
		serverInterface.lock(agencyTicketString);
		
		//Create flightManager using xmlstring from query factory using user inputs
		FlightManager flightManager = new FlightManager();
		String xmlString = serverInterface.getFlights(agencyTicketString, departAirport, departDate);
		
		flightManager.addAll(xmlString);

		Flight flight = flightManager.getSpecificFlight("2807");
		int coachBefore = flight.getmSeatsCoach();
		int firstClassBefore = flight.getmSeatsFirstclass();
		
		serverInterface.buyTickets(agencyTicketString, "2807", false); 
		
		//get updated info
		flightManager = new FlightManager();
		xmlString = serverInterface.getFlights(agencyTicketString, departAirport, departDate);
		
		serverInterface.unlock(agencyTicketString);
		
		flightManager.addAll(xmlString);
		flight = flightManager.getSpecificFlight("2807");

		assertEquals(coachBefore, flight.getmSeatsCoach());
		assertEquals(firstClassBefore + 1, flight.getmSeatsFirstclass());
	}
	
	@Test
	public void testServiceGetsAirplanes(){
		String xmlAirplanesString = serverInterface.getAirports(agencyTicketString);
		assertNotNull(xmlAirplanesString);
	}
}
