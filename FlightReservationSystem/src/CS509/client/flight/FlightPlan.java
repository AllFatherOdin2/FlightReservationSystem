package CS509.client.flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import CS509.client.Interfaces.*;

public class FlightPlan implements IFlightPlan
{
	private List<IFlight> connectingFlights;
	private String fpString = null;
	
	private int flightPlanNumber;
	private float mPriceFirstclass = 0;
	private float mPriceCoach = 0;
	private String totalFlightTime;
	private String arrivalTime;
	private String departureTime;
	
	public FlightPlan(List<IFlight> connectingFlights, int flightPlanNumber){
		this.connectingFlights = connectingFlights;
		this.flightPlanNumber = flightPlanNumber;
		this.loadInfo();
	}
	
	public String getName(){
		return this.flightPlanNumber + "";
	}
	
	@Override
	public String toString()
	{
		if(this.fpString == null)
		{
			this.fpString = "Flight Plan Id: " + this.flightPlanNumber + "\n";
			this.fpString = this.fpString + "Total Flight Time: " + this.totalFlightTime + "\n";
			this.fpString = this.fpString + "Total Coach Cost: " + this.mPriceCoach + "\n";
			this.fpString = this.fpString + "Total First Class Cost: " + this.mPriceFirstclass + "\n";
			
			this.fpString = this.fpString + "**********************************\n";
			
			for(IFlight flight : this.connectingFlights){
				this.fpString = fpString + flight.toString() + "\n";
				this.fpString = fpString + "**********************************\n";
			}
		}
		
		return fpString;		
	}		
	
	private void loadInfo()
	{
		if(this.connectingFlights.size() > 0)
		{
			
			try{
				
				for(IFlight flight : this.connectingFlights){
					this.mPriceFirstclass = this.mPriceFirstclass + Float.parseFloat(flight.getmPriceFirstclass().replace("$", ""));
					this.mPriceCoach = this.mPriceCoach + Float.parseFloat(flight.getmPriceCoach().replace("$", ""));
					this.arrivalTime = flight.getmTimeArrival();
				}
				
				
				this.departureTime = this.connectingFlights.get(0).getmTimeDepart();
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMM dd hh:mm zzzz");
				
				SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss");
				Date layoverStart = formatter.parse(this.departureTime);
				Date layoverEnd = formatter.parse(this.arrivalTime);
				
				long layoverDuration = layoverEnd.getTime() - layoverStart.getTime();
				
				Date totalTime = new Date(layoverDuration);
				this.totalFlightTime = timeFormatter.format(totalTime);
			}catch(Exception e){
				e.printStackTrace();
			}		
		}
	}

	@Override
	public String getDepartureTime() {
		// TODO Auto-generated method stub
		return this.departureTime;
	}

	@Override
	public String getArrivalTime() {
		// TODO Auto-generated method stub
		return this.arrivalTime;
	}

	@Override
	public double getTotalCoachCost() {
		// TODO Auto-generated method stub
		return this.getTotalCoachCost();
	}

	@Override
	public double getTotalFirstClass() {
		// TODO Auto-generated method stub
		return this.getTotalFirstClass();
	}

	@Override
	public String getTotalTime() {
		// TODO Auto-generated method stub
		return this.getTotalTime();
	}
}
