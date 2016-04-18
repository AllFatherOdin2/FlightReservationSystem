package CS509.client.Display.DisplayStates;

import CS509.client.Display.RequestToExitException;
import CS509.client.Interfaces.*;

public class ScannerCollectInfoState extends ScannerBaseState 
{
	private final String startMenu = "What type of trip do you want to plan? (Enter corresponding number) \n1. OneWay\n2. RoundTrip";

	public ScannerCollectInfoState(IDisplay display, ITripManagerFactory factory, ITripManager tripManager)
	{
		super(display, factory, tripManager);
	}
	
	@Override
	public IDisplayState Process() {
		// TODO Auto-generated method stub
		try
		{
			String tripType = this.display.GetUserInput(this.startMenu);
			
			if(this.CheckExit(tripType)){
				return new ScannerExitState(this.display);
			}
				
			int trip = Integer.parseInt(tripType);
			this.tripManager = this.factory.getNewTrip(trip);
			
			this.display.DisplayMessage("You have selected a " + this.tripManager.toString() + "!");
			
			if(this.tripManager != null){
				//this.display.DisplayMessage(this.border);
				this.tripManager.CollectInfo(this.display);
				this.tripManager.PlanTrip();
			}			
			
			return new ScannerDisplayTripsState(this.display, this.factory, this.tripManager);
		}
		catch(Exception e)
		{			
			this.display.DisplayMessage(this.errorMessage);	
			return new ScannerCollectInfoState(this.display, this.factory, this.tripManager);
		}
	}	
	
	public DisplayState getCurrentState(){
		return DisplayState.CollectInfo;
	}
}
