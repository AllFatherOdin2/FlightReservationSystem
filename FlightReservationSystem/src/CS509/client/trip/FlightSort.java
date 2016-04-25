package CS509.client.trip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import CS509.client.Interfaces.IFlight;
import CS509.client.util.Converter;

public class FlightSort{
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	public FlightSort(HashMap<String, IFlight> flightMap, String PriceOrTime) {
		ArrayList<IFlight> flightList = Converter.convertMapToArray(flightMap);
		
        if(PriceOrTime == "P"){
		    Comparator<IFlight> comparatorPrice = new Comparator<IFlight>(){
			    public int compare(IFlight f1, IFlight f2){
			    		return f1.getmPriceCoach().compareTo(f2.getmPriceCoach());
			    }
		    };
		    Collections.sort(flightList, comparatorPrice);
		}
        
        else{
		    Comparator<IFlight> comparatorTime = new Comparator<IFlight>(){
			    public int compare(IFlight f1, IFlight f2){
			    	
			    	Date d1 = null;
					try {
						d1 = sdf.parse(f1.getmFlightTime().substring(11));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	Date d2 = null;
					try {
						d2 = sdf.parse(f2.getmFlightTime().substring(11));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	if(d1 == null && d2 == null) return 0;
			    	if(d1 == null) return -1;
			    	if(d2 == null) return 1;
			    	return d1.compareTo(d2);
			    }
		    };
		    Collections.sort(flightList, comparatorTime);
        }
		
		return;
        }
	
}