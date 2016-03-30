/**
 * 
 */
package CS509.client.airplane;

import java.io.ByteArrayInputStream;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import CS509.client.flight.Flight;

/**
 * @author David
 *
 */
public class AirplaneManager {
	HashMap<String,Airplane> airplanes; 
	
	/**
	 * Basic Constructor
	 */
	public AirplaneManager(){
		airplanes = new HashMap<String,Airplane>();
	}
	
	/**
	 * Constructor to set airplanes
	 * Primarily for testing
	 */
	public AirplaneManager(Map<String,Airplane> map){
		airplanes = (HashMap<String, Airplane>) map;
	}
	
	public boolean addAll (String xmlPlanes) {
		
		boolean collectionUpdated = false;
		
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each flight to our collection
		Document docPlanes = buildDomDoc (xmlPlanes);
		NodeList nodesPlanes = docPlanes.getElementsByTagName("Airplane");
		
		for (int i = 0; i < nodesPlanes.getLength(); i++) {
			Element elementPlane = (Element) nodesPlanes.item(i);
			Airplane flight = buildPlane (elementPlane);
			
			/*if (flight.isValid()) {
				this.add(flight);
				collectionUpdated = true;
			}*/
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
			return docBuilder.parse(new ByteArrayInputStream(xmlPlanes.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	private Airplane buildPlane(Element elementPlane) {
		/**
		 * Airplane will be instantiated after attributes are parsed
		 */
		Airplane airplane = null;
		return null;
	}

	
	
	
}
