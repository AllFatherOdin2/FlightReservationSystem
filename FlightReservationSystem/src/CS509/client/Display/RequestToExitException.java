package CS509.client.Display;

public class RequestToExitException extends Exception
{
	public RequestToExitException(){
		super();		
	}
	
	public RequestToExitException(String message) {
		super(message);
	}
}
