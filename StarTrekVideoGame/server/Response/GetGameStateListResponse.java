package StarTrekVideoGame.server.Response;

/**
 * 
 * This class is used in the game server processGetGameList function to
 * send a message back to the client that gives it information about
 * the game state list. The jsonResponse string will have a json object
 * serialized in it.
 *
 */

public class GetGameStateListResponse extends GameStateResponse {
	private boolean isSuccessful;
	private String jsonResponseState;
	
	public GetGameStateListResponse(String jsonState) {
		isSuccessful = true;
		jsonResponseState = jsonState;
	}
	
	public GetGameStateListResponse() {
		isSuccessful = false;
	}
	
	public String getJsonResponseListState() {
		return jsonResponseState;
	}
}
