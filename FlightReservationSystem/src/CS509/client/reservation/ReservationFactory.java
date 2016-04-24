package CS509.client.reservation;

import CS509.client.Interfaces.*;
import CS509.client.util.*;

public class ReservationFactory implements IReservationFactory {
	
	public IReservation getReservation(IFlightPlan flightPlan, SeatingClass seat){
		
		IReservation reservation = null;
		
		switch(seat)
		{
			case COACH:
				reservation = new CoachOnlyReservation(flightPlan);
				break;
			case FIRSTCLASS:
				reservation = new FirstClassOnlyReservation(flightPlan);
				break;
			case FirstClassPriority:
				reservation = new FirstClassPriorityReservation(flightPlan);
				break;
			case CoachPriority:
				reservation = new CoachPriorityReservation(flightPlan);
				break;
			default:
				break;
		}
		
		return reservation;
	}
}
