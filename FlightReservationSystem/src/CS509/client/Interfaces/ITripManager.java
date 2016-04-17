package CS509.client.Interfaces;

import java.util.List;

public interface ITripManager {
	
	void CollectInfo(IDisplay display);

	void PlanTrip();
	
	List<ITrip> getTrips();
}
