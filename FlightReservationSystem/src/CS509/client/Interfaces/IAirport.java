package CS509.client.Interfaces;

public interface IAirport 
{
	public String getCode();

	public String getName();

	double getLongitude();

	double getLatitude();
	
	String getLocalTime(String gmtTime);
}
