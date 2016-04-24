package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;
import CS509.client.util.SeatingClass;

public class ScannerCreateReservationState extends ScannerBaseState {

	private final String reservation = "\nWhat type of reservation do you want to make?\n1. Coach Only\n2. First Class Only\n3. Coach Priority\n4. First Class Priority\n";
	private ITrip trip;
	private IFlightPlan flightPlan;
	
	public ScannerCreateReservationState(IDisplay display, IServiceLocator services, ITripManager tripManager, ITrip trip, IFlightPlan flightPlan) {
		super(display, services, tripManager);
		
		this.trip = trip;
		this.flightPlan = flightPlan;
	}

	@Override
	public IDisplayState Process() {
		try
		{
			IReservationFactory reservationFactory = this.services.getReservationFactory();
			
			if(this.flightPlan == null){
				this.display.DisplayMessage(this.errorMessage);
				return new ScannerDisplayFlightsState(this.display,this.services,this.tripManager, this.trip);
			}

			this.display.DisplayMessage("Creating reservation for Flight Plan " + this.flightPlan.getName());
			
			String menuSelection = this.display.GetUserInput(this.reservation);
			
			if(this.CheckExit(menuSelection)){
				return new ScannerExitState(this.display, this.services, null);
			}
			
			int selection = Integer.parseInt(menuSelection);
			
			SeatingClass seat = SeatingClass.valueOf(selection);
			
			IReservation reservation = reservationFactory.getReservation(this.flightPlan, seat);
			return new ScannerConfirmReservationState(this.display, this.services, this.tripManager, this.trip, reservation);
			
		}
		catch(Exception e)
		{
			this.display.DisplayMessage(this.errorMessage);
			return new ScannerCreateReservationState(this.display, this.services, this.tripManager, this.trip, this.flightPlan);
		}
	}

	@Override
	public DisplayState getCurrentState() {
		// TODO Auto-generated method stub
		return DisplayState.CreateReservation;
	}
}
