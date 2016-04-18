package CS509.client.Display.DisplayStates;

import CS509.client.Display.RequestToExitException;
import CS509.client.Interfaces.*;

public abstract class ScannerBaseState implements IDisplayState {
	
	protected IDisplay display;
	protected ITripManagerFactory factory;
	protected ITripManager tripManager;
	
	protected final String errorMessage = "Unable to process selection; please try again\n";
	private final String exitProgram = "q";
	
	public ScannerBaseState(IDisplay display, ITripManagerFactory factory, ITripManager tripManager){
		this.display = display;
		this.factory = factory;
		this.tripManager = tripManager;
	}
	
	protected boolean CheckExit(String input){
		if(input.toUpperCase().equals(this.exitProgram.toUpperCase())){
			String confirmation = this.display.GetUserInput("You have requested to exit the program\nAre you sure? (enter q again to confirm)");
			if(confirmation.toUpperCase().equals(this.exitProgram.toUpperCase())){
				return true;
			}else{
				return false;
			}
		}
		
		return false;
	}
}
