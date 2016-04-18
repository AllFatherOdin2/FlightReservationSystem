/**
 * 
 */
package CS509.client.flight;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import CS509.client.Interfaces.IAirport;
import CS509.client.Interfaces.IFlight;
import CS509.client.Interfaces.IFlightManager;
import CS509.client.Interfaces.IServer;
import CS509.client.util.Converter;

/**
 * This class holds values pertaining to an aggregate flights. The aggregate is implemented
 * as an ArrayList. Flights can be populated from XML string returned from CS509 server.
 * 
 * @author benelson
 * @version 1
 * @since 2016-02-24
 *
 */
public class FlightManager implements IFlightManager{

	private static final long serialVersionUID = 1L;
	private HashMap<String,IFlight> flightMap;
	private IServer database;
	private final int LAYOVER_MIN = 1;
	private final int LAYOVER_MAX = 5;
	
	public FlightManager(IServer database) {
		this.flightMap = new HashMap<String,IFlight>();
		this.database = database;
	}
	
	@Override
	public boolean addAll(String code, String day, boolean isDepartingDay){
		String xmlFlights = "";
		if(isDepartingDay)
			xmlFlights = database.getFlightsDeparting(code, day);
		else {
			xmlFlights = database.getFlightsArriving(code, day);
		}
		
		return addAll(xmlFlights, flightMap);
	}
	
	private boolean addAll (String xmlFlights, Map<String, IFlight> fMap) {
		
		boolean collectionUpdated = false;
		
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each flight to our collection
		Document docFlights = buildDomDoc (xmlFlights);
		NodeList nodesFlights = docFlights.getElementsByTagName("Flight");
		
		for (int i = 0; i < nodesFlights.getLength(); i++) {
			Element elementFlight = (Element) nodesFlights.item(i);
			Flight flight = buildFlight (elementFlight);
			
			if (flight.isValid()) {
				//this.add(flight);
				fMap.put(flight.getmNumber(), flight);
				collectionUpdated = true;
			}
		}
		return collectionUpdated;
	}
	
	/**
	 * Builds a DOM tree form an XML string
	 * 
	 * Parses the XML file and returns a DOM tree that can be processed
	 * 
	 * @param xmlString XML String containing set of objects
	 * @return DOM tree from parsed XML or null if exception is caught
	 */
	private Document buildDomDoc (String xmlString) {
		/**
		 * load the xml string into a DOM document and return the Document
		 */
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			/*InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xmlString));
			
			return docBuilder.parse(inputSource);*/
			return docBuilder.parse(new ByteArrayInputStream(xmlString.getBytes()));
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		catch (SAXException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates a FLight object form a DOM node
	 * 
	 * Processes a DOM Node that describes a Flight and creates a Flight object from the information
	 * @param nodeFlight is a DOM Node describing an Airport
	 * @return Flight object created from the DOM Node representation of the Flight
	 * 
	 * @preconditions nodeFlight is of format specified by CS509 server API
	 */
	private Flight buildFlight (Node nodeFlight) {
		/**
		 * flight will be instantiated after attributes are parsed from XML node
		 */
		Flight flight = null;

		String airplane;
		String flightTime;
		String number;
		String codeDepart;
		String timeDepart;
		String codeArrival;
		String timeArrival;
		String priceFirstclass;
		int seatsFirstclass;
		String priceCoach;
		int seatsCoach;
	
		
		// The flight element has attributes of Airplane, FlightTime and [Flight]Number
		Element elementFlight = (Element) nodeFlight;
		airplane = elementFlight.getAttributeNode("Airplane").getValue();
		flightTime = elementFlight.getAttributeNode("FlightTime").getValue();
		number = elementFlight.getAttributeNode("Number").getValue();
		
		// The Departure and Arrival are child elements each with Code and Time children
		Element elementDeparture;
		Element elementArrival;
		Element elementCode;
		Element elementTime;
		
		elementDeparture = (Element)elementFlight.getElementsByTagName("Departure").item(0);
		elementCode = (Element)elementDeparture.getElementsByTagName("Code").item(0);
		elementTime = (Element)elementDeparture.getElementsByTagName("Time").item(0);
		codeDepart = getCharacterDataFromElement(elementCode);
		timeDepart = getCharacterDataFromElement(elementTime);
		
		elementArrival = (Element)elementFlight.getElementsByTagName("Arrival").item(0);
		elementCode = (Element)elementArrival.getElementsByTagName("Code").item(0);
		elementTime = (Element)elementArrival.getElementsByTagName("Time").item(0);
		codeArrival = getCharacterDataFromElement(elementCode);
		timeArrival = getCharacterDataFromElement(elementTime);

		//Seating is child element with children of FirstClass and Coach
		Element elementSeating;
		Element elementFirstclass;
		Element elementCoach;
		
		elementSeating = (Element)elementFlight.getElementsByTagName("Seating").item(0);
		elementFirstclass = (Element)elementSeating.getElementsByTagName("FirstClass").item(0);
		elementCoach = (Element)elementSeating.getElementsByTagName("Coach").item(0);
		
		priceFirstclass = elementFirstclass.getAttributeNode("Price").getValue();
		seatsFirstclass = Integer.parseInt(getCharacterDataFromElement(elementFirstclass));
		
		priceCoach = elementCoach.getAttributeNode("Price").getValue();
		seatsCoach = Integer.parseInt(getCharacterDataFromElement(elementCoach));
		
		flight = new Flight (airplane, flightTime, number, codeDepart, timeDepart, 
				codeArrival, timeArrival, priceFirstclass, seatsFirstclass, priceCoach, seatsCoach);

		return flight;
	}
	
	/**
	 * Retrieve character data from an element if it exists
	 * 
	 * @param e is the DOM Element to retrieve character data from
	 * @return the character data as String [possibly empty String]
	 */
	private static String getCharacterDataFromElement (Element e) {
		Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	        CharacterData cd = (CharacterData) child;
	        return cd.getData();
	      }
	      return "";
	}
	
	/**
	 * Searches through list and returns a specific flight based on the flight number which should be unique
	 * 
	 * @param number Unique identifier for flights
	 * @return IFlight that the user is searching for
	 * @throws FlightNotFoundException Thrown if the flight is not returned by the query.
	 */
	@Override
	public IFlight getSpecificFlight(String number) throws FlightNotFoundException{
		IFlight flight = flightMap.get(number);
		
		if(flight == null){
			throw new FlightNotFoundException();
		}
		
		
		return flight;
	}


	/**
	 * Finds all flights that take off from a given airport and land at another given airport on a given date
	 * 
	 * @param departureAirport Airport flight will be leaving from
	 * @param arrivalAirport Airport flight will be landing at
	 * @param date Date that flight will be leaving at. Must be in "yyyy mmm dd " format, space delimited, and month values are chars
	 * 
	 * @return Hashmap containing all flights matching the requested parameters
	 */
	@Override
	public HashMap<String, IFlight> getFlights(IAirport departureAirport, IAirport arrivalAirport, String date) {
		HashMap<String, IFlight> returnMap = new HashMap<String, IFlight>();
		
		//ArrayList<IFlight> flightList = (ArrayList<IFlight>) flightMap.values();
		/*
		 * TODO: Need to add functionality of sorting by date and time 
		 */
		for(IFlight f : flightMap.values()){
			if (f.getmCodeDepart().compareTo(departureAirport.getCode()) == 0 &&
					f.getmCodeArrival().compareTo(arrivalAirport.getCode()) == 0){
				returnMap.put(f.getmNumber(), f);
			}
		}
		
		return returnMap;
	}
	
	
	/**
	 * Takes a string containing a date from an XML document, and returns it. 
	 * All XML dates should be in "yyyy mmm dd hh:mm:ss a" format
	 * 
	 * @param xmlDate Date to parse
	 * @return Parsed date
	 * @throws InvalidFlightException Thrown if a date is malformed, and cannot be used in our code.
	 */
	private String parseFullDate(String xmlDate) throws InvalidFlightException{
		String parsedDate = "";
		if(xmlDate.length() > 11){
			parsedDate = xmlDate.substring(0, 10);
		} else if (xmlDate.length() < 11){
			throw new InvalidFlightException("Invalid Date Format");
		} else {
			parsedDate = xmlDate;
		}
		
		/*
		 * This code is only to be used if we decide to modify the date further. If we do not, then it should never be uncommented
		 * 
		String year = parsedDate.substring(0,3);
		String month = parsedDate.substring(5,7);
		String day = parsedDate.substring(9,10);
		*/
		return parsedDate;
	}
	
	/**
	 * Simply resets the main hashmap attribute of this class
	 */
	public void removeAllFlights(){
		flightMap = new HashMap<String,IFlight>();
	}

	@Override
	public List<List<IFlight>> getConnectingFlights(String arrivalCode, String day) throws FlightNotFoundException{
		String xmlFlights = database.getFlightsArriving(arrivalCode, day);
		
		System.out.println(xmlFlights);
		
		HashMap<String,IFlight> arrivalFlights = new HashMap<String,IFlight>();
		List<List<IFlight>> returnList = new ArrayList<List<IFlight>>();
		System.out.println(addAll(xmlFlights, arrivalFlights));
		
		if(arrivalFlights.size() == 0){
			throw new FlightNotFoundException("No arrival flights were found/put into the map");
		}
		
		//Case: Direct Flight
		ArrayList<IFlight> departureList = Converter.convertMapToArray(flightMap);
		for(IFlight f : departureList){
			if(f.getmCodeArrival().compareTo(arrivalCode) == 0){
				ArrayList<IFlight> directFlight = new ArrayList<IFlight>();
				directFlight.add(f);
				returnList.add(directFlight);
			}
		}
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a" );
		//Case: 1 connection
		ArrayList<IFlight> arrivalList = Converter.convertMapToArray(arrivalFlights);
		for(IFlight departFlight : departureList){
			for(IFlight arriveFlight : arrivalList){
				if(departFlight.getmCodeArrival().compareTo(arriveFlight.getmCodeDepart()) == 0){
					try {
						if(layoverValid(departFlight.getmTimeArrival(), arriveFlight.getmTimeDepart())){
							ArrayList<IFlight> oneConnectionFlight = new ArrayList<IFlight>();
							oneConnectionFlight.add(departFlight);
							oneConnectionFlight.add(arriveFlight);
							returnList.add(oneConnectionFlight);
						}
					} catch (ParseException e) {
						//Should only ever happen if there is an error in the database
						e.printStackTrace();
					}
					
				}
			}
		}
		
		//Case: 2 connections
		for(IFlight departFlight : departureList){
			String layoverXMLFlight = database.getFlightsDeparting(departFlight.getmCodeArrival(), day);
			HashMap<String,IFlight> firstStopFlightMap = new HashMap<String,IFlight>();
			addAll(layoverXMLFlight,firstStopFlightMap);
			ArrayList<IFlight> firstStopFlightList = Converter.convertMapToArray(firstStopFlightMap);
			
			for(IFlight firstStopFlight: firstStopFlightList){	
				for(IFlight arriveFlight : arrivalList){
					if(firstStopFlight.getmCodeArrival().compareTo(arriveFlight.getmCodeDepart()) == 0){
						try {
							if(layoverValid(firstStopFlight.getmTimeArrival(), arriveFlight.getmTimeDepart())){
								ArrayList<IFlight> twoConnectionFlight = new ArrayList<IFlight>();
								twoConnectionFlight.add(departFlight);
								twoConnectionFlight.add(firstStopFlight);
								twoConnectionFlight.add(arriveFlight);
								returnList.add(twoConnectionFlight);
							}
						} catch (ParseException e) {
							//Should only ever happen if there is an error in the database
							e.printStackTrace();
						}
						
					}
				}
			}
		}
		
		return returnList;
	}

	private boolean layoverValid(String getmTimeArrival, String getmTimeDepart) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM hh:mm a" );
		Date layoverStart = formatter.parse(getmTimeArrival);
		Date layoverEnd = formatter.parse(getmTimeDepart);
		
		if(layoverStart.after(layoverEnd)){
			return false;
		}
		
		long layoverDuration = layoverEnd.getTime() - layoverStart.getTime();
		if(layoverDuration >= (1000*60*60*LAYOVER_MIN) && layoverDuration >= (1000*60*60*LAYOVER_MAX)){
			return true;
		}
		return false;
	}
	

	/**
	 * Helper function to determine if we need to check flights from the following day or not
	 * 
	 * @param flight IFlight that is landing at a particular time (timeArrival)
	 * @return True if timeArival is within LAYOVER_MAX hours of midnight, else false
	 * @throws ParseException If the date format is incorrect, this function will fail
	 */
	private boolean checkNextDay(IFlight flight) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM hh:mm a" );
		Date layoverStart = formatter.parse(flight.getmTimeArrival());
		
		Calendar timeArrival = Calendar.getInstance();
		timeArrival.setTime(layoverStart);
		if(timeArrival.get(Calendar.HOUR_OF_DAY)+LAYOVER_MAX >= 24){
			return true;
		}
		return false;
	}
	
	
}