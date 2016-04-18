package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerPlanAnotherTripState extends ScannerBaseState {

	public ScannerPlanAnotherTripState(IDisplay display, ITripManagerFactory factory, ITripManager tripManager) {
		super(display, factory, tripManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IDisplayState Process() {
		// TODO Auto-generated method stub
		try{			
			String menuSelection = this.display.GetUserInput("Plan another trip?\n1. Yes\n2. No");
			
			if(this.CheckExit(menuSelection)){
				return new ScannerExitState(this.display);
			}
			
			int selection = Integer.parseInt(menuSelection);
			
			switch(selection){
				case 1: //Yes case
					return new ScannerCollectInfoState(this.display,this.factory,this.tripManager);
				case 2: //No case
					this.display.DisplayMessage("Returning to display screen");
					return new ScannerExitState(this.display);
				default:
					this.display.DisplayMessage(this.errorMessage);
					return new ScannerPlanAnotherTripState(this.display,this.factory,this.tripManager);
			}
		}catch(Exception e){
			this.display.DisplayMessage(this.errorMessage);
			return new ScannerPlanAnotherTripState(this.display,this.factory,this.tripManager);
		}
	}

	@Override
	public DisplayState getCurrentState() {
		// TODO Auto-generated method stub
		return DisplayState.PlanAnotherTrip;
	}
}
