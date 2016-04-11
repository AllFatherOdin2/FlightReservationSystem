package CS509.client.Interfaces;

public interface IServer 
{
	String getAirports();

	String getFlightsDeparting(String airportCode, String day);

	String getAirplanes();

	boolean unlock();
	
	boolean lock();

	boolean buyTickets(String team, boolean isCoach);

	String getFlightsArriving(String airportCode, String day);
}
