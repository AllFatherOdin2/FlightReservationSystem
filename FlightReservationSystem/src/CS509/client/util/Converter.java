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

}
