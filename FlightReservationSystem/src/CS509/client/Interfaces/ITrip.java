package CS509.client.Interfaces;

public interface ITrip 
{
	public String getDepartureAirportCode();
	
	public String getArrivalAirportCode();
	
	public void setDepartureAirportCode(String departureAirportCode);
	
	public void setArrivalAirportCode(String arrivalAirportCode);
	
	public void PlanTrip();

	public String getDepatureDate();

	public void setDepatureDate(String depatureDate);
}
