package CS509.client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//import java.util.Calendar;
//import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
//Parse json
import org.json.*;
import org.json.JSONException;

import CS509.client.Interfaces.ILocalTime;
//import CS509.client.airport.Airport;
//import CS509.client.airport.AirportNotFoundException;

public class LocalTime implements ILocalTime
{
	
//Use timezone and date to get the local time	
	public String TimeConvert (String timezone, String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a" );
		SimpleDateFormat sdfLocal = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		TimeZone tzLocal = TimeZone.getTimeZone(timezone);
		sdfLocal.setTimeZone(tzLocal);
		
		String sDateLocal = sdfLocal.format(date);
		Date dateLocal;
		try {
			dateLocal = formatter.parse(sDateLocal);
			System.out.println("Date:" + formatter.format(dateLocal));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}
//Use Google API to get the timezone.
	public String getTimeZone (double lat, double lng) {

		HttpURLConnection connection;
		String LocalTimeZone = "";
			try {
				URL url = new URL("https://maps.googleapis.com/maps/api/timezone/json?location="+lat+","+lng+"&timestamp=1331161200&key=AIzaSyCzTeXN4-RjWcDUxRWDZpTltUdROU51oUc");
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.setRequestProperty("Content-type", "application/x-java-serialized-object");
				InputStream is = connection.getInputStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(is));
				StringBuilder response = new StringBuilder();
				String line;
				while((line = rd.readLine())!= null){
					response.append(line);
					response.append("\r");
				}
				rd.close();
				JSONObject jsonObject1 = new JSONObject(response);
				LocalTimeZone = jsonObject1.getString("timeZoneName");
	            System.out.println("Local Time Zone:" + LocalTimeZone);
            } catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
            } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
            }
			return LocalTimeZone;
    }
	public String getLocalTime(double lat, double lng, String gmt)
	{   
        String timezone = getTimeZone(lat,lng);
        String timeLocal = TimeConvert(timezone,gmt);
        System.out.println(timeLocal);
        return null;
	}
	@Override
	public String getLocalTime(float lat, float lng, String gmt) {
		// TODO Auto-generated method stub
		return null;
	}

}
