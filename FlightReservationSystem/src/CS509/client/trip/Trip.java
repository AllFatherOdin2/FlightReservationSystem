package CS509.client.trip;

import CS509.client.Interfaces.ITrip;

public abstract class Trip implements ITrip
{
	private String departureAirportCode;
	
	private String arrivalAirportCode;

	@Override
	public String getDepartureAirportCode() {
		// TODO Auto-generated method stub
		return this.departureAirportCode;
	}

	@Override
	public String getArrivalAirportCode() {
		// TODO Auto-generated method stub
		return this.arrivalAirportCode;
	}
	
	public void setArrivalAirportCode(){
		
	}

	@Override
	public void PlanTrip() {
		// TODO Auto-generated method stub
		
	}	
}
