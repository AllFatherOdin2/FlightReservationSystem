package CS509.client.reservation;

import CS509.client.Interfaces.*;

public class CoachPriorityReservation implements IReservation {
	private IFlightPlan flightPlan;
	
	public CoachPriorityReservation(IFlightPlan flightPlan){
		this.flightPlan = flightPlan;
	}
	
	//TODO unreserve failures
	public void reserve(IDisplay display, IServer database)
	{	
		for(IFlight flight : this.flightPlan.getFlights()){	
			
			if(flight.canReserveCoach())
			{
				display.DisplayMessage("Reserving coach for:\n" + flight.toString() + "\n");
				flight.reserveCoach(database);
			}else
			{
				display.DisplayMessage("Unable to reserve coach; will reserve first class instead for: \n" + flight.toString() + "\n");
				flight.reserveFirstClass(database);
			}
		}		
	}

	@Override
	public boolean canReserve() {
		boolean success = true;
		
		for(IFlight flight : this.flightPlan.getFlights()){
			success = success && (flight.canReserveCoach() || flight.canReserveFirstClass());
		}
		
		return success;
	}
	
	@Override
	public String toString(){
		return "Coach Priority Reservation for\n" + this.flightPlan;
	}
}
