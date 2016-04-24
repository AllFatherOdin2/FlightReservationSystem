package CS509.client.Display;

import CS509.client.Display.DisplayStates.ScannerWelcomeState;
import CS509.client.Interfaces.*;
import CS509.client.Interfaces.IDisplayState.DisplayState;

public class ScannerDisplayManager implements IDisplayManager
{
	private IDisplay display;
	
	private IServiceLocator services;
	
	private IDisplayState displayState;
	
	public ScannerDisplayManager(IServiceLocator services)
	{
		this.display = new ScannerDisplay();
		this.services = services;
		this.displayState = new ScannerWelcomeState(this.display, this.services, null);
	}
	
	public void Display(){
		while(this.displayState.getCurrentState() != DisplayState.Exit){
			this.displayState = this.displayState.Process();
		}
		
		this.displayState.Process();
	}
}
