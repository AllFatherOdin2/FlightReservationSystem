package CS509.client.Interfaces;

import java.util.*;

import CS509.client.airplane.Airplane;

public interface IAirplaneManager {
	IAirplane getAirplane(String model);
	
	HashMap<String,Airplane> getAirplanes();
}
