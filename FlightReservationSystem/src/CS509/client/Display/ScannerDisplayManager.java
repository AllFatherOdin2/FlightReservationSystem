package CS509.client.Display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import CS509.client.Interfaces.*;

public class ScannerDisplayManager implements IDisplayManager
{
	private final String exitProgram = "q";
	private final String startMenu = "What type of trip do you want to plan? (Enter corresponding number) \n1. OneWay\n2. RoundTrip";
	private final String welcomeMessage = "Welcome to (Insert name here)\nTo navigate each menu, please select the corresponding number to the selection\nIf you wish to quit, type q at any menu screen";
	private final String border = "\n-------------------------------------------------------------------------\n";
	
	private IDisplay display;
	
	private ITripManagerFactory factory;
	
	private ITripManager manager;
	
	public ScannerDisplayManager(ITripManagerFactory factory)
	{
		this.display = new ScannerDisplay();
		this.factory = factory;
	}
	
	public void Display(){
		this.WelcomeScreen();
		
		try{
			this.display.DisplayMessage(this.border);
			this.CollectInfo();
			this.display.DisplayMessage(this.border);
		}catch(RequestToExitException exit){
			this.display.DisplayMessage(exit.getMessage());
		}
	}
	
	private void WelcomeScreen()
	{
		this.display.DisplayMessage(this.welcomeMessage);
	}
	
	private void CollectInfo() throws RequestToExitException{				
		try
		{
			String tripType = this.display.GetUserInput(this.startMenu);
			
			this.CheckExit(tripType);
				
			int trip = Integer.parseInt(tripType);
			this.manager = factory.getNewTrip(trip);
			
			this.display.DisplayMessage("You have selected a " + manager.toString() + "!");
			
			if(this.manager != null){
				this.display.DisplayMessage(this.border);
				this.manager.CollectInfo(this.display);
				this.manager.PlanTrip();
			}			
		}
		catch(Exception e)
		{
			if(e instanceof RequestToExitException){
				throw e;
			}else{
				this.display.DisplayMessage("Unable to display selected option. Please re-select a new trip");
			}
		}
	}
	
	private void DispalyFlights(){
		List<ITrip> trips = this.manager.getTrips();
		
		for(ITrip trip : trips){
			HashMap <String, IFlight> flights = trip.getFlights();			
			ArrayList<IFlight> currentFlights = new ArrayList<IFlight>(flights.values());
			
			
		}
	}
	
	private void CheckExit(String input) throws RequestToExitException{
		if(input.toUpperCase().equals(this.exitProgram.toUpperCase())){
			String confirmation = this.display.GetUserInput("You have requested to exit the program\nAre you sure? (enter q again to confirm)");
			if(confirmation.toUpperCase().equals(this.exitProgram.toUpperCase())){
				throw new RequestToExitException();
			}			
		}
	}
}
