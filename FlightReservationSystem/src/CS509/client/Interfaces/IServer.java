package CS509.client.Interfaces;

public interface IServer 
{
	String getAirports();

	String getFlights(String airportCode, String day);

	String getAirplanes();

	boolean unlock();
	
	boolean lock();
}
