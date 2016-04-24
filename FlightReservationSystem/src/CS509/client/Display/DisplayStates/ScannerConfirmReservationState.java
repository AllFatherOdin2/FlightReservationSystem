package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerConfirmReservationState extends ScannerBaseState {
	
	private final String confirmReservation = "Are you sure you want to create a ";
	private final String menu = "1. Yes\n2. No";
	private ITrip trip;
	private IReservation reservation;
	
	public ScannerConfirmReservationState(IDisplay display, IServiceLocator services, ITripManager tripManager, ITrip trip, IReservation reservation) {
		super(display, services, tripManager);
		this.trip = trip;
		this.reservation = reservation;
	}

	@Override
	public IDisplayState Process() {
		
		try{	
			
			if(this.reservation == null){
				this.display.DisplayMessage(this.errorMessage);
				return new ScannerDisplayFlightsState(this.display,this.services,this.tripManager, this.trip);
			}
			
			if(!this.reservation.canReserve()){
				this.display.DisplayMessage("\nUnable to make " + this.reservation + "Seats may not be available");
				return new ScannerDisplayFlightsState(this.display,this.services,this.tripManager, this.trip);
			}
			
			this.display.DisplayMessage(this.confirmReservation + this.reservation.toString());
			
			String menuSelection = this.display.GetUserInput(this.menu);
			
			if(this.CheckExit(menuSelection)){
				return new ScannerExitState(this.display, this.services, null);
			}
			
			int selection = Integer.parseInt(menuSelection);
			
			switch(selection){
				case 1: //Yes case
					this.display.DisplayMessage("Reserving flight");
					return new ScannerReserveFlightState(this.display,this.services,this.tripManager,this.trip, this.reservation);
				case 2: //No case
					this.display.DisplayMessage("Returning to display screen");
					return new ScannerDisplayFlightsState(this.display, this.services, this.tripManager, this.trip);
				default:
					this.display.DisplayMessage(this.errorMessage);
					return new ScannerDisplayFlightsState(this.display, this.services, this.tripManager, this.trip);
			}
			
		}catch(Exception e){
			this.display.DisplayMessage(this.errorMessage);
			return new ScannerDisplayFlightsState(this.display, this.services, this.tripManager, this.trip);
		}
		
	}

	@Override
	public DisplayState getCurrentState() {
		// TODO Auto-generated method stub
		return DisplayState.ConfirmReservation;
	}
}
