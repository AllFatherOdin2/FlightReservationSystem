package CS509.client.Interfaces;

public interface IAirport 
{
	public String GetLocalTime(String gmtTime);

	public String getCode();

	public String getName();

	double getLongitude();

	double getLatitude();
}
