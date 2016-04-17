
import java.util.ArrayList;
import java.util.Scanner;

import CS509.client.Interfaces.*;
import CS509.client.servicelocator.ServiceLocator;



public class Main {
	static final String agencyTicketString = "Team07";
	
	/**
	 * Main file, asks user for flight location and date.
	 * Sanity checks inputs for correctness
	 * Queries database as needed and continutes taking inputs from user as needed
	 * 
	 * @param args -> empty
	 */
	public static void main(String[] args){
		
		IServiceLocator services = new ServiceLocator();
		IDisplayManager displayManager = services.getDisplayManager();
		displayManager.Display();		
	}
}