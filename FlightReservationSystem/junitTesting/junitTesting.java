import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IAirportManager;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.IServer;
import CS509.client.Interfaces.IServiceLocator;
import CS509.client.airplane.Airplane;
import CS509.client.airplane.AirplaneManager;
import CS509.client.airplane.AirplaneNotFoundException;
import CS509.client.airport.Airport;
import CS509.client.airport.AirportNotFoundException;
import CS509.client.dao.Server;
import CS509.client.flight.Flight;
import CS509.client.flight.FlightManager;
import CS509.client.flight.FlightNotFoundException;
import CS509.client.servicelocator.ServiceLocator;
import CS509.client.util.LocalTime;


public class junitTesting {
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	static final String agencyTicketString = "Team07";
	IServiceLocator serviceLocator;
	static IServer server;
	
	@Before public void initialize(){
		serviceLocator = new ServiceLocator();
		server = new Server(agencyTicketString);
	}
	
	@AfterClass public static void cleanup(){
		server.unlock();
	}
	
	@Test
	public void testLockDatabase(){
		assertTrue(server.lock());
	}
	
	@Test
	public void testUnlockDatabase(){
		server.lock();
		assertTrue(server.unlock());
	}
	
	
	@Test
	public void testGetSpecificAirport() throws AirportNotFoundException{
		IAirportManager airportManger = serviceLocator.getAirportManager();
		
		IAirport airport = airportManger.getAirport("BOS");
		assertNotNull(airport);
		assertEquals("BOS", airport.getCode());
		assertEquals("Logan International", airport.getName());
		assertEquals(42, (int)airport.getLatitude());
		assertEquals(-71, (int)airport.getLongitude());
		assertTrue(airport.equals(airportManger.getAirport("BOS")));
	}
	
	@Test
	public void testAirportEqualsReturnsFalse() throws AirportNotFoundException{
		IAirportManager airportManger = serviceLocator.getAirportManager();
		
		IAirport airport = airportManger.getAirport("ATL");
		assertFalse(airport.equals(null));
		assertFalse(airport.equals("Not an Airport"));
		assertFalse(airport.equals(airportManger.getAirport("BOS")));
	}
	
	@Test
	public void testGetSpecificAirportFailed() throws AirportNotFoundException{
		thrown.expect(AirportNotFoundException.class);
		IAirportManager airportManger = serviceLocator.getAirportManager();
		
		airportManger.getAirport("XXX");
	}
	
	@Test
	public void testGetsFlights() throws AirportNotFoundException {
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String arriveAirport = "ATL";
		String departDate = "2016_05_10";
		
		//Create flightManager using xmlstring from query factory using user inputs
		IFlightManager flightManager = serviceLocator.getFlightManager();
		flightManager.addAll(departAirport, departDate);
		
		IAirportManager airportManger = serviceLocator.getAirportManager();

		IAirport departureAirport = airportManger.getAirport(departAirport);
		IAirport arrivalAirport = airportManger.getAirport(arriveAirport);
		
		HashMap<String, IFlight> flights = flightManager.getFlights(departureAirport, arrivalAirport, departDate);
		
		assertTrue(flights.size() > 0);
	}

	
	@Test
	public void testGetsSpecificFlight() throws FlightNotFoundException, AirportNotFoundException {
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String arriveAirport = "ATL";
		String departDate = "2016_05_10";
		
		//Create flightManager using xmlstring from query factory using user inputs
		IFlightManager flightManager = serviceLocator.getFlightManager();
		flightManager.addAll(departAirport, departDate);
		
		IAirportManager airportManger = serviceLocator.getAirportManager();

		IAirport departureAirport = airportManger.getAirport(departAirport);
		IAirport arrivalAirport = airportManger.getAirport(arriveAirport);
		
		//Used to get semi random flight
		HashMap<String, IFlight> flights = flightManager.getFlights(departureAirport, arrivalAirport, departDate);

		assertTrue(flights.size() > 0);
		ArrayList<IFlight> flightsList = new ArrayList<>(flights.values());
		assertTrue(flightsList.size() > 0);
		
		int position = (int)flightsList.size()/2;
		IFlight flight = flightsList.get(position);
		String numberString = flight.getmNumber();
		
		IFlight gottenFlight = flightManager.getSpecificFlight(numberString);

		assertEquals(gottenFlight.getmAirplane(), flight.getmAirplane());
		assertEquals(gottenFlight.getmFlightTime(), flight.getmFlightTime());
		assertEquals(gottenFlight.getmNumber(), flight.getmNumber());
		assertEquals(gottenFlight.getmCodeDepart(), flight.getmCodeDepart());
		assertEquals(gottenFlight.getmTimeDepart(), flight.getmTimeDepart());
		assertEquals(gottenFlight.getmCodeArrival(), flight.getmCodeArrival());
		assertEquals(gottenFlight.getmTimeArrival(), flight.getmTimeArrival());
		assertEquals(gottenFlight.getmPriceFirstclass(), flight.getmPriceFirstclass());
		assertEquals(gottenFlight.getmSeatsFirstclass(), flight.getmSeatsFirstclass());
		assertEquals(gottenFlight.getmPriceCoach(), flight.getmPriceCoach());
		assertEquals(gottenFlight.getmSeatsCoach(), flight.getmSeatsCoach());
	}
	
	@Test
	public void testGetsSpecificFlightFailed() throws FlightNotFoundException, AirportNotFoundException {
		thrown.expect(FlightNotFoundException.class);
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String departDate = "2016_05_10";
		
		//Create flightManager using xmlstring from query factory using user inputs
		IFlightManager flightManager = serviceLocator.getFlightManager();
		flightManager.addAll(departAirport, departDate);
		
		flightManager.getSpecificFlight("HelloWorld");
	}
	
	@Test
	public void testGetsAirplanes() throws FlightNotFoundException, AirportNotFoundException {
		AirplaneManager airplaneManager = serviceLocator.getAirplaneManager();

		assertTrue(airplaneManager.getAirplanes().size() > 0);
	}
	
	@Test
	public void testGetSpecificPlane() throws AirplaneNotFoundException{
		AirplaneManager airplaneManager = serviceLocator.getAirplaneManager();
		
		Airplane airplane = airplaneManager.getSpecificAirplane("Airbus", "A310");

		assertEquals(airplane.getmManufacturer(), "Airbus");
		assertEquals(airplane.getmModel(), "A310");
		assertEquals(airplane.getmFirstClassSeats(), 24);
		assertEquals(airplane.getmCoachSeats(), 200);
	}
	
	@Test
	public void testGetSpecificPlaneFails() throws AirplaneNotFoundException{
		thrown.expect(AirplaneNotFoundException.class);
		AirplaneManager airplaneManager = serviceLocator.getAirplaneManager();
		
		airplaneManager.getSpecificAirplane("HelloWorldBus", "XXXX");
	}
	
	
	@Test
	public void testReserveCoachSeat() throws FlightNotFoundException {
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String departDate = "2016_05_10";
		
		//This test would fail if someone reserved a flight between us getting and reserving
		server.lock();
		
		//Create flightManager using xmlstring from query factory using user inputs
		FlightManager flightManager = (FlightManager)serviceLocator.getFlightManager();
		
		flightManager.addAll(departAirport,departDate);

		Flight flight = (Flight) flightManager.getSpecificFlight("2809");
		int coachBefore = flight.getmSeatsCoach();
		int firstClassBefore = flight.getmSeatsFirstclass();
		
		assertTrue(server.buyTickets("2809", true)); 
		
		//get updated info
		server.unlock();
		
		flightManager.removeAllFlights();
		flightManager.addAll(departAirport,departDate);
		flight = (Flight) flightManager.getSpecificFlight("2809");

		assertEquals(coachBefore + 1, flight.getmSeatsCoach());
		assertEquals(firstClassBefore, flight.getmSeatsFirstclass());
	}
	
	@Test
	public void testReserveCoachSeatFail() throws FlightNotFoundException {		
		//This test would fail if someone reserved a flight between us getting and reserving
		server.lock();
		boolean test = server.buyTickets("0000", true);
	
		assertFalse(test); 
		
		//get updated info
		server.unlock();
	}
	
	@Test
	public void testGetLocalTime(){
		LocalTime time = new LocalTime();
		assertEquals("GMT-10", time.getTimeZone("HNL"));
	}
	
	/*
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
	*/
}
