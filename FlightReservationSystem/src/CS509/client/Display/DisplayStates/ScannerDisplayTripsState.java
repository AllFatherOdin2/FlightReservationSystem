package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerDisplayTripsState extends ScannerBaseState {

	public ScannerDisplayTripsState(IDisplay display, IServiceLocator services, ITripManager tripManager) {
		super(display, services, tripManager);
	}
	
	@Override
	public IDisplayState Process() {
		
		for(ITrip trip : this.tripManager.getTrips()){
			if(!trip.hasFlights()){
				this.display.DisplayMessage("A section of your trip has no flights; Unable to process this trip\n");
				return new ScannerPlanAnotherTripState(this.display, this.services, this.tripManager);
			}
		}
		
		for(ITrip trip : this.tripManager.getTrips()){
				if(!trip.getReserved()){
					return new ScannerDisplayFlightsState(this.display, this.services, this.tripManager, trip);
				}
		}
		
		return new ScannerPlanAnotherTripState(this.display, this.services, this.tripManager);
	}
	
	public DisplayState getCurrentState(){
		return DisplayState.DisplayTrip;
	}
}
