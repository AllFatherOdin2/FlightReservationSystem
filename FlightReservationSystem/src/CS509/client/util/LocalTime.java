
package CS509.client.util;

//import java.util.Calendar;
//import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;

import CS509.client.Interfaces.ILocalTime;
//import CS509.client.airport.Airport;
//import CS509.client.airport.AirportNotFoundException;

public class LocalTime implements ILocalTime
{
	private String timeOffset;

    public LocalTime(String timeOffset)
    {
    	this.timeOffset = timeOffset;
    }
    
    public String getOffset(){
    	return this.timeOffset;
    }
    
    //Use timezone and date to get the local time
    public String getLocalTime (String date) {
    	Date TimeTemp = new Date();
    	String lcTime = new String();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMM dd kk:mm zzz" );
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        try{
        	TimeTemp = formatter.parse(date);
        	formatter.setTimeZone(TimeZone.getTimeZone(this.timeOffset));
            
        }catch(Exception e){
        	e.printStackTrace();
        }
        return lcTime = formatter.format(TimeTemp);
        //return formatter.format(lcTime);
    }
    
}