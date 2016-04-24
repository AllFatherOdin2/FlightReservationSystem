package CS509.client.reservation;

import CS509.client.Interfaces.*;

public class FirstClassOnlyReservation implements IReservation {
	private IFlightPlan flightPlan;
	
	public FirstClassOnlyReservation(IFlightPlan flightPlan){
		this.flightPlan = flightPlan;
	}
	
	//TODO unreserve failures
	public void reserve(IDisplay display, IServer database)
	{	
		for(IFlight flight : this.flightPlan.getFlights()){			
			flight.reserveFirstClass(database);
			display.DisplayMessage("First class Reservation made for \n" + flight);
		}			
	}

	@Override
	public boolean canReserve() {
		return this.flightPlan.canReserveFirstClass();
	}
	
	@Override
	public String toString(){
		return "First Class Only Reservation for\n" + this.flightPlan;
	}
}
