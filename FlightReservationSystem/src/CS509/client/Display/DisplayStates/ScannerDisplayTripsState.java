package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerDisplayTripsState extends ScannerBaseState {

	public ScannerDisplayTripsState(IDisplay display, ITripManagerFactory factory, ITripManager tripManager) {
		super(display, factory, tripManager);
	}
	
	@Override
	public IDisplayState Process() {
		for(ITrip trip : this.tripManager.getTrips()){
			if(!trip.getReserved()){
				return new ScannerDisplayFlightsState(this.display, this.factory, this.tripManager, trip);
			}
		}
		
		return new ScannerPlanAnotherTripState(this.display, this.factory, this.tripManager);
	}
	
	public DisplayState getCurrentState(){
		return DisplayState.DisplayTrip;
	}
}
