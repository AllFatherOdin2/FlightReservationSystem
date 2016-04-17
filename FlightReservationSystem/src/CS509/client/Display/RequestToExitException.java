package CS509.client.Display;

public class RequestToExitException extends Exception
{
	public RequestToExitException(){
		super("User requested to exit. Thank you");		
	}
	
	public RequestToExitException(String message) {
		super(message);
	}
}
