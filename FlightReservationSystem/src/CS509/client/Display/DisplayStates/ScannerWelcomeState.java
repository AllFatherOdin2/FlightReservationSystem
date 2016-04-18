package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerWelcomeState extends ScannerBaseState {

	private final String welcomeMessage = "Welcome to (Insert name here)\nTo navigate each menu, please select the corresponding number to the selection\nIf you wish to quit, type q at any menu screen";

	
	public ScannerWelcomeState(IDisplay display, ITripManagerFactory factory, ITripManager tripManager){
		super(display, factory, tripManager);
	}
	
	public IDisplayState Process()
	{
		this.display.DisplayMessage(this.welcomeMessage);
		return new ScannerCollectInfoState(this.display, this.factory, this.tripManager);
	}

	@Override
	public DisplayState getCurrentState() {
		// TODO Auto-generated method stub
		return DisplayState.Welcome;
	}
}
