package StarTrekVideoGame.server.Response;

/**
 * This class is used in the game server processGetGameState function to
 * send a message back to the client that gives it information about
 * the game state list. The jsonResponse string will have a json object
 * serialized in it.
 *
 */
public class GetGameStateResponse extends GameStateResponse {
	private boolean isSuccessful;
	private String jsonResponseState;
	
	public GetGameStateResponse(String jsonState) {
		isSuccessful = true;
		jsonResponseState = jsonState;
	}
	
	public GetGameStateResponse() {
		isSuccessful = false;
	}
	
	public String getJsonResponseState() {
		return jsonResponseState;
	}
}
