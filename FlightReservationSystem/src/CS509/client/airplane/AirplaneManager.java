/**
 * 
 */
package CS509.client.airplane;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import CS509.client.Interfaces.*;

/**
 * @author David
 *
 */
public class AirplaneManager implements IAirplaneManager {
	HashMap<String,Airplane> airplanes;
	
	/**
	 * Basic Constructor
	 */
	public AirplaneManager(IServer database){
		airplanes = new HashMap<String,Airplane>();
		addAll(database.getAirplanes());
	}
	
	
	public boolean addAll (String xmlPlanes) {
		
		boolean collectionUpdated = false;
		
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each flight to our collection
		Document docPlanes = buildDomDoc (xmlPlanes);
		NodeList nodesPlanes = docPlanes.getElementsByTagName("Airplane");
		
		for (int i = 0; i < nodesPlanes.getLength(); i++) {
			Element elementPlane = (Element) nodesPlanes.item(i);
			Airplane plane = buildPlane (elementPlane);
			
			if (plane.isValid()) {
				airplanes.put(plane.getModel(), plane);
				collectionUpdated = true;
			}
		}
		
		return collectionUpdated;
	}
	
	/**
	 * Builds a DOM tree from an XML string
	 * Parses the XML file and returns a DOM tree that can be processed
	 * @param xmlPlanes XML string containing set of planes
	 * @return DOM tree from parsed XML or null if exception is caught
	 */
	private Document buildDomDoc(String xmlPlanes) {
		try{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xmlPlanes));
			
			return docBuilder.parse(inputSource);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	private Airplane buildPlane(Node nodePlane) {
		/**
		 * Airplane will be instantiated after attributes are parsed
		 */
		Airplane airplane = null;
		String manufacturer;
		String model;
		int seatsFirstClass;
		int seatsCoach;
		
		Element elementPlane = (Element) nodePlane;
		manufacturer = elementPlane.getAttributeNode("Manufacturer").getValue();
		model = elementPlane.getAttributeNode("Model").getValue();
		
		Element elementFirstClass;
		Element elementCoach;
		
		elementFirstClass = (Element) elementPlane.getElementsByTagName("FirstClassSeats").item(0);
		elementCoach = (Element) elementPlane.getElementsByTagName("CoachSeats").item(0);
		seatsFirstClass = Integer.parseInt(getCharacterDataFromElement(elementFirstClass));
		seatsCoach = Integer.parseInt(getCharacterDataFromElement(elementCoach));
		
		airplane = new Airplane(manufacturer, model, seatsFirstClass, seatsCoach);
		
		return airplane;
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
	
	public HashMap<String,Airplane> getAirplanes(){
		return airplanes;
	}
	

	public IAirplane getAirplane(String model) {
		//TODO: not sure how just one string is strong enough reference -> not sure if keeping throws AirplaneNotFoundException
		Airplane airplane = airplanes.get(model);
		
		//if(airplane == null){
		//	throw new AirplaneNotFoundException();
		//}
		
		return airplane;		
	}
}
