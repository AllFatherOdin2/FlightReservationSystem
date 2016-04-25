package CS509.client.flight;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import CS509.client.Interfaces.*;

public class FlightBuilder 
{
	private IServer database;
	private IAirportManager airportManager;
	private IAirplaneManager airplaneManager;
	private String date;
	private String aCode;
	
	private HashMap<String, HashMap<String, IFlight>> departures;
	private HashMap<String, ArrayList<IFlight>> arrivals;
	
	public FlightBuilder(IServer database, IAirportManager airportManager, IAirplaneManager airplaneManager, String aCode, String date)
	{
		this.database = database;
		this.date = date;
		this.airportManager = airportManager;
		this.aCode = aCode;
		this.airplaneManager = airplaneManager;
		
		this.departures	= new HashMap<String, HashMap<String, IFlight>>();
		this.arrivals = new HashMap<String, ArrayList<IFlight>>();
	}
	
	public HashMap<String, IFlight> getDepartures(String code){
		if(!this.departures.containsKey(code)){
			this.addDepartureFlight(code);
		}
		
		return this.departures.get(code);
	}
	
	public List<IFlight> goesToDestination(String dCode){
		if(this.arrivals.size() == 0){
			this.addArrivals(this.date);
			String currentDate = this.getNextDay(this.date);
			this.addArrivals(currentDate);
		}
		
		List<IFlight> flights = this.arrivals.get(dCode);
		
		if(flights == null){
			flights = new ArrayList<IFlight>();
		}
		return flights;
	}
	
	private void addArrivals(String currentDate){
		String xmlFlights = database.getFlightsArriving(this.aCode, currentDate);
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each flight to our collection
		Document docFlights = buildDomDoc (xmlFlights);
		NodeList nodesFlights = docFlights.getElementsByTagName("Flight");
		
		for (int i = 0; i < nodesFlights.getLength(); i++) {
			Element elementFlight = (Element) nodesFlights.item(i);
			Flight flight = buildFlight (elementFlight);
			
			if (flight.isValid()) {
				
				if(!this.arrivals.containsKey(flight.getmCodeDepart())){
					this.arrivals.put(flight.getmCodeDepart(), new ArrayList<IFlight>());
				}
				
				ArrayList<IFlight> currentFlights = this.arrivals.get(flight.getmCodeDepart());
				currentFlights.add(flight);
			}
		}
	}
	
	private void addDepartureFlight(String code){
		String xmlFlights = database.getFlightsDeparting(code, this.date);
		HashMap<String, IFlight> flightMap = new HashMap<String, IFlight>();
		
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each flight to our collection
		Document docFlights = buildDomDoc (xmlFlights);
		NodeList nodesFlights = docFlights.getElementsByTagName("Flight");
		
		for (int i = 0; i < nodesFlights.getLength(); i++) {
			Element elementFlight = (Element) nodesFlights.item(i);
			Flight flight = buildFlight (elementFlight);
			
			if (flight.isValid()) {
				flightMap.put(flight.getmNumber(), flight);
			}
		}
		this.departures.put(code, flightMap);
	}
	
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
		codeArrival = this.getCharacterDataFromElement(elementCode);
		timeArrival = this.getCharacterDataFromElement(elementTime);

		//Seating is child element with children of FirstClass and Coach
		Element elementSeating;
		Element elementFirstclass;
		Element elementCoach;
		
		elementSeating = (Element)elementFlight.getElementsByTagName("Seating").item(0);
		elementFirstclass = (Element)elementSeating.getElementsByTagName("FirstClass").item(0);
		elementCoach = (Element)elementSeating.getElementsByTagName("Coach").item(0);
		
		priceFirstclass = elementFirstclass.getAttributeNode("Price").getValue();
		seatsFirstclass = Integer.parseInt(this.getCharacterDataFromElement(elementFirstclass));
		
		priceCoach = elementCoach.getAttributeNode("Price").getValue();
		seatsCoach = Integer.parseInt(this.getCharacterDataFromElement(elementCoach));
		IAirport dAirport = null;
		IAirport arAirport = null;
		IAirplane plane = null;
		try
		{	
			dAirport = this.airportManager.getAirport(codeDepart);
			arAirport = this.airportManager.getAirport(codeArrival);
			plane = this.airplaneManager.getAirplane(airplane);
		}catch(Exception e){
			
		}
		
		flight = new Flight (plane, flightTime, number, dAirport, timeDepart, 
				arAirport, timeArrival, priceFirstclass, seatsFirstclass, priceCoach, seatsCoach);

		return flight;
	}
	
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
	
	private String getCharacterDataFromElement (Element e) {
		Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	        CharacterData cd = (CharacterData) child;
	        return cd.getData();
	      }
	      return "";
	}
	

    /**
	 * Given a day string, gets the string for the following day
	 * 
	 * @param currentDay String representing current day
	 * @return String representing day after the current day
	 */
	private String getNextDay(String currentDay){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
		Date currentDate = new Date();
		
		try {
			currentDate = formatter.parse(currentDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		currentDate.setTime(currentDate.getTime()+86400000);
		String nextDate = formatter.format(currentDate);
				
		return nextDate;
	}
}
