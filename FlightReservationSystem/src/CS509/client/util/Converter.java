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
	 * Takes a date in "YYYY_MM_DD" format and puts it in "YYYY MMM DD hh:mm zzzz" format
	 * @param date
	 * @return
	 */
	public static String convertDateFormatToLongForm(String date){
		String toReturn = "";
		String[] dateArray = date.split("_");
		StringBuilder newDate = new StringBuilder();
		newDate.append(dateArray[0] + " "); //Add the year
		switch(dateArray[1]){
			case "01":
				newDate.append("JAN ");
				break;
			case "02":
				newDate.append("FEB ");
				break;
			case "03":
				newDate.append("MAR ");
				break;
			case "04":
				newDate.append("APR ");
				break;
			case "05":
				newDate.append("MAY ");
				break;
			case "06":
				newDate.append("JUN ");
				break;
			case "07":
				newDate.append("JUL ");
				break;
			case "08":
				newDate.append("AUG ");
				break;
			case "09":
				newDate.append("SEP ");
				break;
			case "10":
				newDate.append("OCT ");
				break;
			case "11":
				newDate.append("NOV ");
				break;
			case "12":
				newDate.append("DEC ");
				break;
		}
		newDate.append(dateArray[2] + " 00:00 AM");
		return newDate.toString();
	}
	
	/**
	 * Takes a date in "YYYY MMM DD hh:mm zzzz" and puts it in "YYYY_MM_DD" format
	 * @param date
	 * @return
	 */
	public static String ConvertDateFormatToShortForm(String date){
		String toReturn = "";
		String[] dateArray = date.split(" ");
		StringBuilder newDate = new StringBuilder();
		newDate.append(dateArray[0] + "_"); //Add the year
		switch(dateArray[1]){
			case "JAN":
				newDate.append("01_");
				break;
			case "FEB":
				newDate.append("02_");
				break;
			case "MAR":
				newDate.append("03_");
				break;
			case "APR":
				newDate.append("04_");
				break;
			case "MAY":
				newDate.append("05_");
				break;
			case "JUN":
				newDate.append("06_");
				break;
			case "JUL":
				newDate.append("07_");
				break;
			case "AUG":
				newDate.append("08_");
				break;
			case "SEP":
				newDate.append("09_");
				break;
			case "OCT":
				newDate.append("10_");
				break;
			case "NOV":
				newDate.append("11_");
				break;
			case "DEC":
				newDate.append("12_");
				break;
		}
		newDate.append(dateArray[2]);
		return newDate.toString();
	}

}
