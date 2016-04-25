
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
    public String TimeConvert (String timezone, String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a" );
        SimpleDateFormat sdfLocal = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        TimeZone tzLocal = TimeZone.getTimeZone(timezone);
        sdfLocal.setTimeZone(tzLocal);
        String sDateLocal = sdfLocal.format(date);
        Date dateLocal;
        String DateLocalF = "";
        try {
            dateLocal = formatter.parse(sDateLocal);
            DateLocalF = formatter.format(dateLocal);
            System.out.println("Date:" + DateLocalF);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return sDateLocal;
    }

	@Override
	public String getLocalTime(String date) {
		// TODO Auto-generated method stub
		return null;
	}
}