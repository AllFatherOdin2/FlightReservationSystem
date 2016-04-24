/**
 * 
 */
package CS509.client.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David
 *
 */
public enum SeatingClass {
	COACH(1),
	FIRSTCLASS(2),
	CoachPriority(3),
	FirstClassPriority(4);
	
	private int seatingNum;
	private static Map<Integer,SeatingClass> map = new HashMap<Integer,SeatingClass>();
	
	static{
		for(SeatingClass c : SeatingClass.values()){
			map.put(c.seatingNum, c);
		}
	}
	
	private SeatingClass (final int seatNum){
		seatingNum = seatNum;
	}
	
	public static SeatingClass valueOf(int seatNum){
		return map.get(seatNum);
	}
}
