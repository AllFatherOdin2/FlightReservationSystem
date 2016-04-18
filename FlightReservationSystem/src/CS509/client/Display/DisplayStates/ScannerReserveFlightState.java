package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerReserveFlightState extends ScannerBaseState {

	private ITrip trip;
	private IFlight flight;
	
	public ScannerReserveFlightState(IDisplay display, ITripManagerFactory factory, ITripManager tripManager, ITrip trip, IFlight flight) {
		super(display, factory, tripManager);
		// TODO Auto-generated constructor stub
		this.trip = trip;
		this.flight = flight;
	}

	@Override
	public IDisplayState Process() {
		try{
			
			//this.flight.reserve();
			this.trip.setReserved(true);
			return new ScannerDisplayTripsState(this.display, this.factory, this.tripManager);
			
		}catch(Exception e){
			this.display.DisplayMessage(this.errorMessage);
			return new ScannerDisplayFlightsState(this.display, this.factory, this.tripManager, this.trip);
		}
	}

	@Override
	public DisplayState getCurrentState() {
		// TODO Auto-generated method stub
		return DisplayState.ReserveFlight;
	}

}
