import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.PatternSyntaxException;

import CS509.client.Interfaces.IFlight;

public class FlightSort{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	@SuppressWarnings("unchecked")
	public FlightSort(ArrayList flightList, String PriceOrTime) {
        if(PriceOrTime == "P"){
		    Comparator<IFlight> comparatorPrice = new Comparator<IFlight>(){
			    public int compare(IFlight f1, IFlight f2){
			    	if(f1.getmPriceCoach() != f2.getmPriceCoach()){
			    		return f1.getmPriceCoach().compareTo(f2.getmPriceCoach());
			    	}
			    }
		    };
		    Collections.sort(flightList, comparatorPrice);
		}
            else{
		    Comparator<IFlight> comparatorTime = new Comparator<IFlight>(){
			    public int compare(IFlight f1, IFlight f2){
			    	
			    	Date d1 = sdf.parse(f1.getmFlightTime().substring(11));
			    	Date d2 = sdf.parse(f2.getmFlightTime().substring(11));
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