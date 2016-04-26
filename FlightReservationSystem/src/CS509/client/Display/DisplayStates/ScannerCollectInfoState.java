package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerCollectInfoState extends ScannerBaseState 
{
	private final String startMenu = "\nWhat type of trip do you want to plan? (Enter corresponding number) \n1. OneWay\n2. RoundTrip";

	public ScannerCollectInfoState(IDisplay display, IServiceLocator services, ITripManager tripManager)
	{
		super(display, services, tripManager);
	}
	
	@Override
	public IDisplayState Process() {
		// TODO Auto-generated method stub
		try
		{
			String tripType = this.display.GetUserInput(this.startMenu);
			
			if(this.CheckExit(tripType)){
				return new ScannerExitState(this.display, this.services, null);
			}
				
			int trip = Integer.parseInt(tripType);
			this.tripManager = this.factory.getNewTrip(trip);
			
			this.display.DisplayMessage("\nYou have selected a " + this.tripManager.toString() + "!\n");
			
			if(this.tripManager != null){
				this.tripManager.CollectInfo(this.display);
				this.display.DisplayMessage("\nPlanning your trip. Please Wait\n");
				this.tripManager.PlanTrip();
			}			
			
			return new ScannerDisplayTripsState(this.display, this.services, this.tripManager);
		}
		catch(Exception e)
		{			
			e.printStackTrace();
			this.display.DisplayMessage(this.errorMessage);	
			return new ScannerCollectInfoState(this.display, this.services, this.tripManager);
		}
	}	
	
	public DisplayState getCurrentState(){
		return DisplayState.CollectInfo;
	}
}
