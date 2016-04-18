package CS509.client.Display.DisplayStates;

import CS509.client.Interfaces.*;

public class ScannerExitState extends ScannerBaseState {

	public ScannerExitState(IDisplay display){
		super(display, null, null);
	}
	
	public ScannerExitState(IDisplay display, ITripManagerFactory factory, ITripManager tripManager) {
		super(display, factory, tripManager);
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
