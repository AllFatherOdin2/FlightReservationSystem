package CS509.client.reservation;

import CS509.client.Interfaces.*;

public class FirstClassPriorityReservation implements IReservation {
	private IFlightPlan flightPlan;
	
	public FirstClassPriorityReservation(IFlightPlan flightPlan){
		this.flightPlan = flightPlan;
	}
	
	//TODO unreserve failures
	public void reserve(IDisplay display, IServer database)
	{	
		for(IFlight flight : this.flightPlan.getFlights()){	
			
			if(flight.canReserveFirstClass())
			{
				display.DisplayMessage("Reserving first class for:\n" + flight.toString() + "\n");
				flight.reserveFirstClass(database);
			}else
			{
				display.DisplayMessage("Unable to reserve first class; will reserve coach instead for: \n" + flight.toString() + "\n");
				flight.reserveCoach(database);
			}
		}
	}
	
	public boolean canReserve(){
		boolean success = true;
		
		for(IFlight flight : this.flightPlan.getFlights()){
			success = success && (flight.canReserveCoach() || flight.canReserveFirstClass());
		}
		
		return success;		
	}
	
	@Override
	public String toString(){
		return "First Class Priority Reservation for\n" + this.flightPlan;
	}
}
