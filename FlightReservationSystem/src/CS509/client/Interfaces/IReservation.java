package CS509.client.Interfaces;

public interface IReservation {
	
	boolean canReserve();
	
	void reserve(IDisplay dispaly, IServer database);
}
