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
	
	private final String flightMenu = "Please select an option:\n1. Reserve a Flight\n2. Sort by price \n3. Sort by duration\n4. Sort by arrival time\n5. Sort by departure time";
	
	private HashMap<String, IFlightPlan> flightMap;
	
	public ScannerDisplayFlightsState(IDisplay display, ITripManagerFactory factory, ITripManager tripManager, ITrip trip) {
		super(display, factory, tripManager);
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
						IFlightPlan flight = this.flightMap.get(flightNumber);
						return new ScannerConfirmReservationState(this.display, this.factory, this.tripManager, this.trip, flight);
					case 2:
						this.SortByCoachPrice(flights);
						break;
					case 3:
						this.SortByFirstClassPrice(flights);
						break;
					case 4:
						this.SortByTime(flights);
						break;
					case 5:
						this.SortByArrivalTime(flights);
						break;
					case 6:
						this.SortByDepartureTime(flights);
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Comparator<IFlightPlan> comparatorTime = new Comparator<IFlightPlan>(){
		    public int compare(IFlightPlan f1, IFlightPlan f2){
		    	
		    	Date d1 = null;
				try {
					d1 = sdf.parse(f1.getTotalTime().substring(11));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Date d2 = null;
				try {
					d2 = sdf.parse(f2.getTotalTime().substring(11));
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Comparator<IFlightPlan> comparatorTime = new Comparator<IFlightPlan>(){
		    public int compare(IFlightPlan f1, IFlightPlan f2){
		    	
		    	Date d1 = null;
				try {
					d1 = sdf.parse(f1.getArrivalTime().substring(11));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Date d2 = null;
				try {
					d2 = sdf.parse(f2.getArrivalTime().substring(11));
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Comparator<IFlightPlan> comparatorTime = new Comparator<IFlightPlan>(){
		    public int compare(IFlightPlan f1, IFlightPlan f2){
		    	
		    	Date d1 = null;
				try {
					d1 = sdf.parse(f1.getDepartureTime().substring(11));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Date d2 = null;
				try {
					d2 = sdf.parse(f2.getDepartureTime().substring(11));
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
