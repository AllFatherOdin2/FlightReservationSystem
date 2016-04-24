package CS509.client.reservation;

import CS509.client.Interfaces.*;

public class CoachOnlyReservation implements IReservation {

	private IFlightPlan flightPlan;
	
	public CoachOnlyReservation(IFlightPlan flightPlan){
		this.flightPlan = flightPlan;
	}
	
	//TODO unreserve failures
	public void reserve(IDisplay display, IServer database)
	{	
		for(IFlight flight : this.flightPlan.getFlights()){			
			flight.reserveCoach(database);
			display.DisplayMessage("Coach Reserved for \n" + flight);
		}
	}

	@Override
	public boolean canReserve() {
		// TODO Auto-generated method stub
		return this.flightPlan.canReserveCoach();
	}
	
	@Override
	public String toString(){
		return "Coach Only Reservation for: " + this.flightPlan;
	}
}
