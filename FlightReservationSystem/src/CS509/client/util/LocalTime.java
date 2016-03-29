package CS509.client.util;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.api.client.http.HttpTransport;
//Here is using time zone to convert. What need? --time zone!
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
//Parse json
import java.util.HashMap;
import java.lang.String;
import java.util.List;
import net.sf.json.JSON;

import javax.lang.model.element.Element;

import CS509.client.Interfaces.ILocalTime;
import CS509.client.airport.Airport;
import CS509.client.airport.AirportNotFoundException;
import CS509.client.flight.Flight;

public class LocalTime implements ILocalTime
{
	private double mlat;
	private double mlng;
	private String mgmt;
	private String mcode;
	private String mtimezone;
	private String TimeFlight, TimeDepart, TimeArrival;
	private final String mUrlBase = "https://maps.googleapis.com/maps/api/timezone/json?location="+mlat+","+mlng+"&timestamp=1331161200&key=AIzaSyCzTeXN4-RjWcDUxRWDZpTltUdROU51oUc";
	
	public String TimeConvert (String timezone) {
		
		mtimezone = timezone;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a" );
		
		String dateInString = "22-01-2015 10:15:55 AM";
		Date date = formatter.parse(dateInString);
		TimeZone tz = TimeZone.getDefault();
		
		SimpleDateFormat sdfLocal = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		TimeZone tzLocal = TimeZone.getTimeZone(mtimezone);
		sdfLocal.setTimeZone(tzLocal);
		
		String sDateLocal = sdfLocal.format(date);
		Date dateLocal = formatter.parse(sDateLocal);
		System.out.println("Date:" + formatter.format(dateLocal));
		}
	public String getTimeZone (double lat, double lng) {
		
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		
		
		return mtimezone;
	}
		
		
	
	//Here is to get the date and time. From flight we can get date, time.
	Calendar 
	
	public LocalTime()
	{
		Airport airport = new Airport();
		
		Flight flight = new Flight();
		String name;
		String code;
		double latitude;
		double longitude;
		Airport airportSpecific = new Airport.getSpecificAirport(code);
		Element elementAirport = (Element) nodeAirport;
		Element elementLat, elementLng;
		elementLat = = (Element)elementAirport.getElementsByTagName("Latitude").item(0);
		latitude = Double.parseDouble(getCharacterDataFromElement(elementLat));
		elementLng = (Element)elementAirport.getElementsByTagName("Longitude").item(0);
		longitude = Double.parseDouble(getCharacterDataFromElement(elementLng));
		
		airport.name(name);
		airport.code(code);
		airport.latitude(latitude);
		airport.longitude(longitude);
		mlat = 90.0;
		mlng = 180.0;
		mgmt = "";
	}
	
	public LocalTime(double lat, double lng, String gmt){
	    this.mlat = lat;
	    this.mlng = lng;
	    this.mgmt = gmt;
	}
	void Lat(double lat){
		if(lat < -180.0 || lat > 180.0){
			throw new AirportNotFoundException("Airport not exist");
		}
		this.mlat = lat;
	}
	void lng(double lng){
		if(lng < -90.0 || lng > 90.0){
			throw new AirportNotFoundException("Airport not exist");
		}
		this.mlng = lng;
	}	
	void getGMT(String gmt){
		String TimeFlight, TimeDepart, TimeArrival;
		Flight flight = new Flight();
	    this.TimeFlight = flight.getmFlightTime();
	    this.TimeDepart = flight.getmTimeDepart();
	    this.TimeArrival = flight.getmTimeArrival();
	    
	}
	GET https://maps.googleapis.com/maps/api/timezone/json?location="+lat+","+lng+"&timestamp=1331161200&key=AIzaSyCzTeXN4-RjWcDUxRWDZpTltUdROU51oUc
		X-JavaScript-User-Agent: Google APIs Explorer
	void convert(String gmt){
	    double lngD = mlng - 
	}	
		
		
		
		
		
		
		
		URL timeZoneUrl;
		HttpURLConnection TZconnection;
		BufferedReader reader;
		String ine;
		StringBuffer timeZone = new StringBuffer();
		try {
			timeZoneUrl = new URL()
		}
		
		
		
		this.getmTimeDepart();
	}

	public String getLocalTime(float lat, float lng, String gmt)
	{
		return null;
	}
}
