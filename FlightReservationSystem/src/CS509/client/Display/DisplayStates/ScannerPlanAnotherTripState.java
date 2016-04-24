package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerPlanAnotherTripState extends ScannerBaseState {

	public ScannerPlanAnotherTripState(IDisplay display, IServiceLocator services, ITripManager tripManager) {
		super(display, services, tripManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IDisplayState Process() {
		// TODO Auto-generated method stub
		try{			
			String menuSelection = this.display.GetUserInput("Plan another trip?\n1. Yes\n2. No");
			
			if(this.CheckExit(menuSelection)){
				return new ScannerExitState(this.display, this.services, null);
			}
			
			int selection = Integer.parseInt(menuSelection);
			
			switch(selection){
				case 1: //Yes case
					return new ScannerCollectInfoState(this.display,this.services,this.tripManager);
				case 2: //No case
					this.display.DisplayMessage("Exiting");
					return new ScannerExitState(this.display, this.services, null);
				default:
					this.display.DisplayMessage(this.errorMessage);
					return new ScannerPlanAnotherTripState(this.display, this.services, this.tripManager);
			}
		}catch(Exception e){
			this.display.DisplayMessage(this.errorMessage);
			return new ScannerPlanAnotherTripState(this.display,this.services, this.tripManager);
		}
	}

	@Override
	public DisplayState getCurrentState() {
		// TODO Auto-generated method stub
		return DisplayState.PlanAnotherTrip;
	}
}
