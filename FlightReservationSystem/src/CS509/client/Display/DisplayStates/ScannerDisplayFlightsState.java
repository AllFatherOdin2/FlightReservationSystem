package CS509.client.Display.DisplayStates;

import java.util.ArrayList;
import java.util.HashMap;

import CS509.client.Interfaces.*;

public class ScannerDisplayFlightsState extends ScannerBaseState {

	private ITrip trip;
	
	private final String flightMenu = "Please select an option:\n1. Reserve a Flight\n2. Sort by price ascending\n3. Sort by price descending\n4. Sort by time ascending\n5. Sort by time descending";
	
	private HashMap<String, IFlight> flightMap;
	
	public ScannerDisplayFlightsState(IDisplay display, ITripManagerFactory factory, ITripManager tripManager, ITrip trip) {
		super(display, factory, tripManager);
		this.trip = trip;
		this.flightMap = trip.getFlights();
	}

	@Override
	public IDisplayState Process() {
		// TODO Auto-generated method stub
		ArrayList<IFlight> flights = new ArrayList<IFlight>(this.flightMap.values());
		
		try{
			while(true)
			{
				if(flights.isEmpty()){
					this.display.DisplayMessage("There are no flights for this day; continuing onto other legs of your trip");
					this.trip.setReserved(true);
					return new ScannerDisplayTripsState(this.display,this.factory,this.tripManager);
				}
				
				this.display.printFlights(flights);
				String menuSelection = this.display.GetUserInput(this.flightMenu);
				
				if(this.CheckExit(menuSelection)){
					return new ScannerExitState(this.display);
				}
				
				int selection = Integer.parseInt(menuSelection);
				
				//TODO if this was a real project I wouldn't do this
				switch(selection){
				
					case 1:
						String flightNumber = this.display.GetUserInput("Enter number of the flight you want to reserve: ");
						IFlight flight = this.flightMap.get(flightNumber);
						return new ScannerConfirmReservationState(this.display, this.factory, this.tripManager, this.trip, flight);
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
					default:
						break;
				}
			}	
		}catch(Exception e){
			this.display.DisplayMessage(this.errorMessage);
			return new ScannerDisplayFlightsState(this.display, this.factory, this.tripManager, this.trip);
		}
	}

	@Override
	public DisplayState getCurrentState() {
		// TODO Auto-generated method stub
		return DisplayState.DisplayFlights;
	}
}
