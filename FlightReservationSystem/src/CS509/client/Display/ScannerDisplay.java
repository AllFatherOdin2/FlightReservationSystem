package CS509.client.Display;

import java.util.List;
import java.util.Scanner;

import CS509.client.Interfaces.IDisplay;
import CS509.client.Interfaces.IFlight;

public class ScannerDisplay implements IDisplay
{
	Scanner sc;
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
	
	private void printFlights(List<IFlight> currentFlights){
		System.out.println("-------------------------------------------");
		
		for(IFlight flight : currentFlights){
			System.out.println(flight.toString());
			System.out.println("-------------------------------------------");
		}
	}
}
