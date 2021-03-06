package CS509.client.Display;

import java.util.*;
import CS509.client.Interfaces.*;

public class ScannerDisplay implements IDisplay
{
	private final String border = "\n-------------------------------------------------------------------------\n";
	private Scanner sc;
	
	public ScannerDisplay(){
		sc = new Scanner(System.in);
	}
	
	@Override
	public String GetUserInput(String output) {
		this.DisplayMessage(output);
		String input = this.sc.nextLine().toUpperCase();
		
		return input;
	}
	
	public void DisplayMessage(String message){
		System.out.println(message);
	}
	
	public void printFlights(List<IFlightPlan> currentFlights){
		System.out.println(this.border);
		
		for(IFlightPlan flightPlan : currentFlights){
			System.out.println(flightPlan.toString());
			System.out.println(this.border);
		}
	}

	@Override
	public void refreshDisplay() {
		char c = '\n';
		int length = 25;
		char[] chars = new char[length];
		Arrays.fill(chars, c);
		System.out.print(String.valueOf(chars));
	}
}
