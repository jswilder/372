package StarTrekVideoGame.server.Response;


/**
 * This class is used in the game server processRestoreGame function to
 * send a message back to the client that gives it information about
 * the game state. The jsonResponse string will have a json object
 * serialized in it that contains the restored game.
 *
 */
public class RestoreGameStateResponse extends GameStateResponse {
	private String response;
	private boolean isSuccessful;

	public RestoreGameStateResponse(String jsonResponse) {
		isSuccessful = true;
		this.response = jsonResponse;
	}
	
	public RestoreGameStateResponse() {
		isSuccessful = false;
	}
	
	public String getJson() {
		return response;
	}
}
