package StarTrekVideoGame.client.Model;

import java.util.ArrayList;

import javax.naming.ServiceUnavailableException;

import StarTrekVideoGame.client.Services.ClientProxy;
import StarTrekVideoGame.server.Response.GetGameStateListResponse;
import StarTrekVideoGame.server.Response.GetGameStateResponse;
import StarTrekVideoGame.server.Response.RestoreGameStateResponse;
import StarTrekVideoGame.shared.AlertLevelEnum;
import StarTrekVideoGame.shared.GameService;
import StarTrekVideoGame.shared.GameState;
import StarTrekVideoGame.shared.Ship;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GameServiceImpl implements GameService {
	
	private ClientProxy proxy;
	
		// Empty constructor
	public GameServiceImpl() {
		proxy = ClientProxy.getInstance();
	}

	@Override
	public ArrayList<GameState> getGameList() {

			// Create string to be sent as a message to the server
		String message = "<LIST>";
		String jsonResponse = null;

			// Receive response from the server			
		try {
			jsonResponse = ClientProxy.getInstance().sendMessage(message);
		} catch (ServiceUnavailableException e) {
			System.out.println("Service Unavailable");
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal argument");
		}

			// Parse response from the server		
		GetGameStateListResponse response = new Gson().fromJson(jsonResponse, GetGameStateListResponse.class);
		ArrayList<GameState> gameList = new Gson().fromJson(response.getJsonResponseListState(), new TypeToken<ArrayList<GameState>>(){}.getType());
		return gameList;
	}

	@Override
	public GameState restoreGame(String gameFile)
			throws ServiceUnavailableException {
		
			// Create string to be sent as a message to the server
		String message = "<RESTORE><" + gameFile + ">";
		String response = null;

			// Receive response from the server		
		response = proxy.sendMessage(message);
		RestoreGameStateResponse responseClass;

			// Parse response from the server		
		responseClass = new Gson().fromJson(response, RestoreGameStateResponse.class);
		GameState restoredState = new Gson().fromJson(responseClass.getJson(), GameState.class);
		
		return restoredState;
	}

		// Make sure username and password match
	@Override
	public boolean authenticate(String username, String password) {
		return false;
	}
	
	@Override
	public void attackTarget(String gameID, String shipID, int x, int y)
		throws ServiceUnavailableException {

				// Create string to be sent as a message to the server		
			String message = "<FIRE><" + gameID + "><" + shipID + "><" + x + y + ">";
			System.out.println(message);
			String response = null;

				// Receive response from the server			
			response = proxy.sendMessage(message);		
	}

	@Override
	public void navigate(String gameID, String shipID, int type, int x, int y) {

			// Create string to be sent as a message to the server	
		String message = "<NAVIGATE><" + gameID + "><" + shipID + "><" + type + "><" + x + y + ">";
		System.out.println(message);
		String response = null;

			// Receive response from the server		
		try {
			response = proxy.sendMessage(message);
		} catch (ServiceUnavailableException e) {
			System.out.println("Service Unavailable Exception");
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal exception");
		}
	}

	@Override
	public void setAlert(Ship source, int state, String gameId, AlertLevelEnum alert) {


			// Create string to be sent as a message to the server	
		String message = "<ALERT><" + gameId + "><" + source.getId() + "><" + alert.toString() + ">";

			// Nothing is received from the server for this action		
		try {
			proxy.sendMessage(message);
		} catch (ServiceUnavailableException e1) {
			System.out.println("Service Unavailable");
		} catch (IllegalArgumentException e1) {
			System.out.println("illegal argument");
		}
	}

		// This will be used to refresh the current game.
	@Override
	public GameState getGameState(String gameId)
			throws ServiceUnavailableException , IllegalArgumentException {

			// Create string to be sent as a message to the server
		String message = "<GET><" + gameId + ">";
		String response;
		GameState refreshedGameState = null;

			// Receive response from the server	
		response = proxy.sendMessage(message);
		
			// Parse response from the server
		GetGameStateResponse getGSResponse = new Gson().fromJson(response, GetGameStateResponse.class);
		refreshedGameState = new Gson().fromJson(getGSResponse.getJsonResponseState(), GameState.class);
		
		return refreshedGameState;
	}
}
