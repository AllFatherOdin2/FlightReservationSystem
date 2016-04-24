package CS509.client.Interfaces;

import CS509.client.util.SeatingClass;

public interface IReservationFactory {
	IReservation getReservation(IFlightPlan flightPlan, SeatingClass seat);
}
