package CS509.client.Display;

import CS509.client.Display.DisplayStates.ScannerWelcomeState;
import CS509.client.Interfaces.*;
import CS509.client.Interfaces.IDisplayState.DisplayState;

public class ScannerDisplayManager implements IDisplayManager
{
	private IDisplay display;
	
	private ITripManagerFactory factory;
	
	private IDisplayState displayState;
	
	public ScannerDisplayManager(ITripManagerFactory factory)
	{
		this.display = new ScannerDisplay();
		this.factory = factory;
		this.displayState = new ScannerWelcomeState(this.display, this.factory, null);
	}
	
	public void Display(){
		while(this.displayState.getCurrentState() != DisplayState.Exit){
			this.displayState = this.displayState.Process();
		}
		
		this.displayState.Process();
	}
}
