/**
 * 
 */
package CS509.client.flight;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

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
		
		return addAll(xmlFlights);
	}
	
	private boolean addAll (String xmlFlights) {
		
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
				flightMap.put(flight.getmNumber(), flight);
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
		
		/*
		IFlight flight = null;
		int counter = 0;
		int found = 0;
		ArrayList<IFlight> flightCollection =  new ArrayList<>(flightMap.values());
		for (IFlight a : flightCollection){
			if (flight == null && a.getmNumber().compareToIgnoreCase(number) == 0){
				flight = a;
				found = counter;
			} else if (flight != null) {
				//should only occur if a flight is duplicated somehow.
				//because we are using a HashMap, this should no longer even be possible
				//TODO: Test to see if this can even happen, or if we even want to keep this functionality.
				if(flightCollection.get(counter).equals(flightCollection.get(found))){
					flightCollection.remove(counter);
					flightMap.remove(a.getmNumber());
				}
			} else {
				throw new FlightNotFoundException("Flight " + number +" not found by query");
			}
			counter++;
		}
		if(flight == null)
			throw new FlightNotFoundException("Flight " + number +" not found by query");
		
		return flight;
		*/
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

	public void removeAllFlights(){
		flightMap = new HashMap<String,IFlight>();
	}
}