package CS509.client.Interfaces;

public interface IFlight 
{
	public String getmAirplane();
	public String getmFlightTime();
	public String getmNumber();
	public String getmCodeDepart();
	public String getmTimeDepart();
	public String getmCodeArrival();
	public String getmTimeArrival();
	public String getmPriceFirstclass();
	public int getmSeatsFirstclass();
	public String getmPriceCoach();
	public int getmSeatsCoach();
	
	public boolean canReserveCoach();
	public boolean canReserveFirstClass();
	
	public void reserveCoach(IServer database);
	public void reserveFirstClass(IServer database);
}
