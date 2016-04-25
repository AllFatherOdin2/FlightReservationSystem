import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import CS509.client.Interfaces.*;
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
import CS509.client.util.LocalTimeFactory;


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
		flightManager.addAll(departAirport, departDate, true);
		
		IAirportManager airportManger = serviceLocator.getAirportManager();

		IAirport departureAirport = airportManger.getAirport(departAirport);
		IAirport arrivalAirport = airportManger.getAirport(arriveAirport);
		
		HashMap<String, IFlight> flights = flightManager.getFlights(departureAirport, arrivalAirport, departDate);
		
		assertTrue(flights.size() > 0);
	}
	
	@Test
	public void testGetsFlightsByArrivalDay() throws AirportNotFoundException{
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String arriveAirport = "ATL";
		String departDate = "2016_05_10";
		
		//Create flightManager using xmlstring from query factory using user inputs
		IFlightManager flightManager = serviceLocator.getFlightManager();
		flightManager.addAll(arriveAirport, departDate, false);
		
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
		flightManager.addAll(departAirport, departDate, true);
		
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
		flightManager.addAll(departAirport, departDate, true);
		
		flightManager.getSpecificFlight("HelloWorld");
	}
	
	@Test
	public void testGetsAirplanes() throws FlightNotFoundException, AirportNotFoundException {
		IAirplaneManager airplaneManager = serviceLocator.getAirplaneManager();

		assertTrue(airplaneManager.getAirplanes().size() > 0);
	}
	
	@Test
	public void testGetSpecificPlane() throws AirplaneNotFoundException{
		IAirplaneManager airplaneManager = serviceLocator.getAirplaneManager();
		
		IAirplane airplane = airplaneManager.getAirplane("A310");

		assertEquals(airplane.getmManufacturer(), "Airbus");
		assertEquals(airplane.getModel(), "A310");
		assertEquals(airplane.getmFirstClassSeats(), 24);
		assertEquals(airplane.getmCoachSeats(), 200);
	}
	
	@Test
	public void testGetSpecificPlaneFails(){
		IAirplaneManager airplaneManager = serviceLocator.getAirplaneManager();
		
		IAirplane airplane = airplaneManager.getAirplane("XXXX");
		assertNull(airplane);
	}
	
	
	@Test
	public void testReserveCoachSeat() throws FlightNotFoundException {
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String departDate = "2016_05_10";
		
		//This test would fail if someone reserved a flight between us getting and reserving
		assertTrue(server.lock());
		
		//Create flightManager using xmlstring from query factory using user inputs
		FlightManager flightManager = (FlightManager)serviceLocator.getFlightManager();
		
		flightManager.addAll(departAirport,departDate, true);

		Flight flight = (Flight) flightManager.getSpecificFlight("2809");
		int coachBefore = flight.getmSeatsCoach();
		int firstClassBefore = flight.getmSeatsFirstclass();
			
		flight.reserveCoach(server); 		
		
		flightManager.removeAllFlights();
		flightManager.addAll(departAirport,departDate, true);
		flight = (Flight) flightManager.getSpecificFlight("2809");
		//get updated info
		assertTrue(server.unlock());

		assertEquals(coachBefore + 1, flight.getmSeatsCoach());
		assertEquals(firstClassBefore, flight.getmSeatsFirstclass());
	}
	
	@Test
	public void testReserveFirstClassSeat() throws FlightNotFoundException {
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String departDate = "2016_05_10";
		
		//This test would fail if someone reserved a flight between us getting and reserving
		assertTrue(server.lock());
		
		//Create flightManager using xmlstring from query factory using user inputs
		FlightManager flightManager = (FlightManager)serviceLocator.getFlightManager();
		
		flightManager.addAll(departAirport,departDate, true);

		Flight flight = (Flight) flightManager.getSpecificFlight("2809");
		int coachBefore = flight.getmSeatsCoach();
		int firstClassBefore = flight.getmSeatsFirstclass();
			
		flight.reserveFirstClass(server); 		
		
		flightManager.removeAllFlights();
		flightManager.addAll(departAirport,departDate, true);
		flight = (Flight) flightManager.getSpecificFlight("2809");
		//get updated info
		assertTrue(server.unlock());

		assertEquals(coachBefore, flight.getmSeatsCoach());
		assertEquals(firstClassBefore + 1, flight.getmSeatsFirstclass());
	}
	
	//@Test
	//public void testReserveCoachSeatFail() throws FlightNotFoundException {		
		//This test would fail if someone reserved a flight between us getting and reserving
	//	server.lock();
	//	boolean test = server.buyTickets("0000", true);
	
	//	assertFalse(test); 
		
		//get updated info
	//	server.unlock();
	//}
	
	@Test
	public void testGetLocalTime(){
		LocalTimeFactory timeFactory = new LocalTimeFactory();
		ILocalTime time = timeFactory.getLocalTime("HNL");
		assertEquals("GMT-10", time.getOffset());
	}
	
	@Test
	public void testGetOneWayWithConnectingFlights() throws AirportNotFoundException, FlightNotFoundException{
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String arriveAirport = "ATL";
		String departDate = "2016_05_10";
		
		//Create flightManager using xmlstring from query factory using user inputs
		IFlightManager flightManager = serviceLocator.getFlightManager();
		flightManager.addAll(departAirport, departDate, true);
		
		HashMap<String, IFlightPlan> connectingFlights = flightManager.getConnectingFlights(arriveAirport, departDate);
	
		System.out.println(connectingFlights.size());
	}
	
	@Test
	public void testGetFlightPlans(){
		//Get input from "users" regarding departure airport and date
		String departAirport = "BOS";
		String arriveAirport = "ATL";
		//String departDate = "2016_05_10";
		String departDate = "2016 MAY 10";
		
		//Create flightManager using xmlstring from query factory using user inputs
		IFlightManager flightManager = serviceLocator.getFlightManager();
		HashMap<String, IFlightPlan> flightPlans = flightManager.getFlightPlans(departAirport, arriveAirport, departDate, 2);
		
		assertTrue(flightPlans.size() > 0);
	}
}
