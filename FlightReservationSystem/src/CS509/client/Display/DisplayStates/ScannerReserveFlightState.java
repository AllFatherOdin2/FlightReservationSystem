package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerReserveFlightState extends ScannerBaseState {

	private ITrip trip;
	private IReservation reservation;
	
	public ScannerReserveFlightState(IDisplay display, IServiceLocator services, ITripManager tripManager, ITrip trip, IReservation reservation) {
		super(display, services, tripManager);
		// TODO Auto-generated constructor stub
		this.trip = trip;
		this.reservation = reservation;
	}

	@Override
	public IDisplayState Process() {
		try{
			IServer server = this.services.getDatabase();
			this.reservation.reserve(this.display, server);
			this.trip.setReserved(true);
			return new ScannerDisplayTripsState(this.display, this.services, this.tripManager);
			
		}catch(Exception e){
			this.display.DisplayMessage(this.errorMessage);
			return new ScannerDisplayFlightsState(this.display, this.services, this.tripManager, this.trip);
		}
	}

	@Override
	public DisplayState getCurrentState() {
		// TODO Auto-generated method stub
		return DisplayState.ReserveFlight;
	}

}
