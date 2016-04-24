package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerConfirmReservationState extends ScannerBaseState {
	
	private final String confirmReservation = "Are you sure you want to reserve Flight ";
	private final String menu = "1. Yes\n2. No";
	private ITrip trip;
	private IFlightPlan flightPlan;
	
	public ScannerConfirmReservationState(IDisplay display, ITripManagerFactory factory, ITripManager tripManager, ITrip trip, IFlightPlan flight) {
		super(display, factory, tripManager);
		this.trip = trip;
		this.flightPlan = flight;
	}

	@Override
	public IDisplayState Process() {
		
		try{
			if(this.flightPlan == null){
				this.display.DisplayMessage(this.errorMessage);
				return new ScannerDisplayFlightsState(this.display,this.factory,this.tripManager, this.trip);
			}
			
			this.display.DisplayMessage(this.confirmReservation + this.flightPlan.getName());
			
			String menuSelection = this.display.GetUserInput(this.menu);
			
			if(this.CheckExit(menuSelection)){
				return new ScannerExitState(this.display);
			}
			
			int selection = Integer.parseInt(menuSelection);
			
			switch(selection){
				case 1: //Yes case
					this.display.DisplayMessage("Reserving flight");
					return new ScannerReserveFlightState(this.display,this.factory,this.tripManager,this.trip, this.flightPlan);
				case 2: //No case
					this.display.DisplayMessage("Returning to display screen");
					return new ScannerDisplayFlightsState(this.display, this.factory, this.tripManager, this.trip);
				default:
					this.display.DisplayMessage(this.errorMessage);
					return new ScannerDisplayFlightsState(this.display, this.factory, this.tripManager, this.trip);
			}
			
		}catch(Exception e){
			this.display.DisplayMessage(this.errorMessage);
			return new ScannerDisplayFlightsState(this.display, this.factory, this.tripManager, this.trip);
		}
		
	}

	@Override
	public DisplayState getCurrentState() {
		// TODO Auto-generated method stub
		return DisplayState.ConfirmReservation;
	}
}
