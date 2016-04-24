package CS509.client.Display.DisplayStates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import CS509.client.Interfaces.*;

public class ScannerDisplayFlightsState extends ScannerBaseState {

	private ITrip trip;
	
	private final String flightMenu = "Please select an option:\n1. Reserve a Flight Plan\n2. Sort by Coach price \n3. Sort by First Class Price\n4. Sort by duration\n5. Sort by arrival time\n6. Sort by departure time";
	
	private HashMap<String, IFlightPlan> flightMap;
	
	public ScannerDisplayFlightsState(IDisplay display, IServiceLocator services, ITripManager tripManager, ITrip trip) {
		super(display, services, tripManager);
		this.trip = trip;
		this.flightMap = trip.getFlightPlans();
	}

	@Override
	public IDisplayState Process() {
		// TODO Auto-generated method stub
		ArrayList<IFlightPlan> flights = new ArrayList<IFlightPlan>(this.flightMap.values());
		
		try{
			while(true)
			{
				if(flights.isEmpty()){
					this.display.DisplayMessage("There are no flights for this day; continuing onto other legs of your trip");
					this.trip.setReserved(true);
					return new ScannerDisplayTripsState(this.display,this.services,this.tripManager);
				}
				
				this.display.printFlights(flights);
				String menuSelection = this.display.GetUserInput(this.flightMenu);
				
				if(this.CheckExit(menuSelection)){
					return new ScannerExitState(this.display, this.services, null);
				}
				
				int selection = Integer.parseInt(menuSelection);
				
				//TODO if this was a real project I wouldn't do this
				String sortSelection = "";
				switch(selection){
				
					case 1:
						String flightNumber = this.display.GetUserInput("Enter number of the flight plan you want to reserve: ");
						IFlightPlan flight = this.flightMap.get(flightNumber);
						return new ScannerCreateReservationState(this.display, this.services, this.tripManager, this.trip, flight);
					case 2:
						this.SortByCoachPrice(flights);
						sortSelection ="\nSorted by coach price\n";
						break;
					case 3:
						this.SortByFirstClassPrice(flights);
						sortSelection ="\nSorted by first class price\n";
						break;
					case 4:
						this.SortByTime(flights);
						sortSelection ="\nSorted by duration\n";
						break;
					case 5:
						this.SortByArrivalTime(flights);
						sortSelection ="\nSorted by arrival time\n";
						break;
					case 6:
						this.SortByDepartureTime(flights);
						sortSelection ="\nSorted by departure time\n";
						break;
					default:
						break;
				}
				
				this.display.refreshDisplay();
				this.display.DisplayMessage(sortSelection);
			}	
		}catch(Exception e){
			this.display.DisplayMessage(this.errorMessage);
			return new ScannerDisplayFlightsState(this.display, this.services, this.tripManager, this.trip);
		}
	}

	@Override
	public DisplayState getCurrentState() {
		// TODO Auto-generated method stub
		return DisplayState.DisplayFlights;
	}
	
	private void SortByCoachPrice(List<IFlightPlan> flights){
		Comparator<IFlightPlan> comparatorPrice = new Comparator<IFlightPlan>(){
		    public int compare(IFlightPlan f1, IFlightPlan f2){
		    	double difference = f1.getTotalCoachCost() - f2.getTotalCoachCost();
	    		if(difference < 0){
	    			return -1;
	    		}else {
	    			return 1;
	    		}
		    }
	    };
	    Collections.sort(flights, comparatorPrice);
	}
	
	private void SortByFirstClassPrice(List<IFlightPlan> flights){
		Comparator<IFlightPlan> comparatorPrice = new Comparator<IFlightPlan>(){
		    public int compare(IFlightPlan f1, IFlightPlan f2){
		    		double difference = f1.getTotalFirstClass() - f2.getTotalFirstClass();
		    		if(difference < 0){
		    			return -1;
		    		}else {
		    			return 1;
		    		}
		    }
	    };
	    Collections.sort(flights, comparatorPrice);
	}
	
	private void SortByTime(List<IFlightPlan> flights){	
		Comparator<IFlightPlan> comparatorTime = new Comparator<IFlightPlan>(){
		    public int compare(IFlightPlan f1, IFlightPlan f2){
		    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		    	Date d1 = null;
				try {
					d1 = sdf.parse(f1.getTotalTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Date d2 = null;
				try {
					d2 = sdf.parse(f2.getTotalTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	if(d1 == null && d2 == null) return 0;
		    	if(d1 == null) return -1;
		    	if(d2 == null) return 1;
		    	return d1.compareTo(d2);
		    }
	    };
	    Collections.sort(flights, comparatorTime);
	}
	
	private void SortByArrivalTime(List<IFlightPlan> flights){
		Comparator<IFlightPlan> comparatorTime = new Comparator<IFlightPlan>(){
		    public int compare(IFlightPlan f1, IFlightPlan f2){
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		    	Date d1 = null;
				try {
					d1 = sdf.parse(f1.getArrivalTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Date d2 = null;
				try {
					d2 = sdf.parse(f2.getArrivalTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	if(d1 == null && d2 == null) return 0;
		    	if(d1 == null) return -1;
		    	if(d2 == null) return 1;
		    	return d1.compareTo(d2);
		    }
	    };
	    Collections.sort(flights, comparatorTime);
	}
	
	private void SortByDepartureTime(List<IFlightPlan> flights){
		
		Comparator<IFlightPlan> comparatorTime = new Comparator<IFlightPlan>(){
		    public int compare(IFlightPlan f1, IFlightPlan f2){
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		    	Date d1 = null;
				try {
					d1 = sdf.parse(f1.getDepartureTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Date d2 = null;
				try {
					d2 = sdf.parse(f2.getDepartureTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	if(d1 == null && d2 == null) return 0;
		    	if(d1 == null) return -1;
		    	if(d2 == null) return 1;
		    	return d1.compareTo(d2);
		    }
	    };
	    Collections.sort(flights, comparatorTime);
	}
}
