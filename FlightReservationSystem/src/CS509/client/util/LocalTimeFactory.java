package CS509.client.util;

import java.util.HashMap;

import CS509.client.Interfaces.*;

public class LocalTimeFactory implements ILocalTimeFactory {
	
	HashMap <String, String> map = new HashMap<>();
	
	public LocalTimeFactory(){
		this.buildMap();
	}
    
	public ILocalTime getLocalTime(String airportCode){
		String offset = this.map.get(airportCode);
		
		return new LocalTime(offset);
	}
	
	private void buildMap(){
		map.put("AUS","GMT-5");
	    map.put("MCI","GMT-5");
	    map.put("MSP","GMT-5");
	    map.put("BNA","GMT-5");
	    map.put("MSY","GMT-5");
	    map.put("HOU","GMT-5");
	    map.put("STL","GMT-5");
	    map.put("IAH","GMT-5");
	    map.put("MEM","GMT-5");
	    map.put("MDW","GMT-5");
	    map.put("ORD","GMT-5");
	    map.put("DFW","GMT-5");
	    map.put("DEN","GMT-5");
	    map.put("SAT","GMT-5");
	    map.put("ATL","GMT-4");
	    map.put("IND","GMT-4");
	    map.put("JFK","GMT-4");
	    map.put("LGA","GMT-4");
	    map.put("CLT","GMT-4");
	    map.put("BOS","GMT-4");
	    map.put("BWI","GMT-4");
	    map.put("MIA","GMT-4");
	    map.put("CLE","GMT-4");
	    map.put("CMH","GMT-4");
	    map.put("CVG","GMT-4");
	    map.put("DTW","GMT-4");
	    map.put("FLL","GMT-4");
	    map.put("RSW","GMT-4");
	    map.put("BDL","GMT-4");
	    map.put("EWR","GMT-4");
	    map.put("ONT","GMT-4");
	    map.put("MCO","GMT-4");
	    map.put("PHL","GMT-4");
	    map.put("PIT","GMT-4");
	    map.put("RDU","GMT-4");
	    map.put("TPA","GMT-4");
	    map.put("IAD","GMT-4");
	    map.put("DCA","GMT-4");
	    map.put("LAS","GMT-7");
	    map.put("OAK","GMT-7");
	    map.put("LAX","GMT-7");
	    map.put("SAN","GMT-7");
	    map.put("SFO","GMT-7");
	    map.put("SJC","GMT-7");
	    map.put("SNA","GMT-7");
	    map.put("PDX","GMT-7");
	    map.put("SMF","GMT-7");
	    map.put("SEA","GMT-7");
	    map.put("SLC","GMT-6");
	    map.put("PHX","GMT-7");
	    map.put("HNL","GMT-10");
	    map.put("ANC","GMT-8");
		
	}

}
