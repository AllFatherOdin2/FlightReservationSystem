package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerExitState extends ScannerBaseState {
	
	public ScannerExitState(IDisplay display, IServiceLocator services, ITripManager tripManager) {
		super(display, services, tripManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IDisplayState Process() {
		this.display.DisplayMessage("Exiting reservation system. Good-bye!");
		return null;
	}

	@Override
	public DisplayState getCurrentState() {
		// TODO Auto-generated method stub
		return DisplayState.Exit;
	}

}
