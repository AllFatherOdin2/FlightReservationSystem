package CS509.client.util;

import java.util.ArrayList;
import java.util.HashMap;

public class Converter {
	
	public static <K,V> ArrayList<V> convertMapToArray(HashMap<K, V> map){
		ArrayList<V> returnList = new ArrayList<V>();
		
		for(V v : map.values()){
			returnList.add(v);
		}
		
		return returnList;
		
	}
	
	/**
	 * Given a day string, gets the string for the following day
	 * 
	 * @param currentDay String representing current day
	 * @return String representing day after the current day
	 */
	public static String getNextDay(String currentDay){
		String[] dayArray = currentDay.split(" ");
		int day = Integer.parseInt(dayArray[2]) + 1;
		String toReturn = "";
		for(int i = 0; i < dayArray.length; i++){
			if (i == 2){
				toReturn = toReturn + day;
			} else {
				toReturn = toReturn + dayArray[i];
			}
		}
		
		return toReturn;
	}

}
