
package CS509.client.util;

//import java.util.Calendar;
//import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMM dd hh:mm zzzz" );
        SimpleDateFormat sdfLocal = new SimpleDateFormat("yyyy MMM dd hh:mm zzzz");
        TimeZone tzLocal = TimeZone.getTimeZone(this.timeOffset);
        Date dateLocal = null;
        String sDateLocal = null;
        
        try{
        	dateLocal = formatter.parse(date);
        	sdfLocal.setTimeZone(tzLocal);
            sDateLocal = sdfLocal.format(dateLocal);
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        return sDateLocal;
    }
}