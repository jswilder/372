package StarTrekVideoGame.server.Model;

public class GameStateServiceException extends Exception {

	public GameStateServiceException() {

	}

	public GameStateServiceException(String message) {
	    super(message);
	}

	public GameStateServiceException(Throwable cause) {
		super(cause);
	}

	public GameStateServiceException(String message, Throwable cause) {
	    super(message, cause);
	}

	public GameStateServiceException(String message, Throwable cause,
			  
	boolean enableSuppression, boolean writableStackTrace) {
	    super();
		//super(message, cause, enableSuppression, writableStackTrace);
	}
}
