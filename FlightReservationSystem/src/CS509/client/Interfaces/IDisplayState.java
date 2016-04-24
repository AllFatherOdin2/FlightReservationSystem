package CS509.client.Interfaces;


public interface IDisplayState {

	public enum DisplayState{
		Welcome,
		CollectInfo,
		DisplayTrip,
		DisplayFlights,
		ConfirmReservation,
		CreateReservation,
		ReserveFlight,
		PlanAnotherTrip,
		Exit
	}

	public IDisplayState Process();
	
	public DisplayState getCurrentState();
}
