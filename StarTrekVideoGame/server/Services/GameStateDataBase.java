package StarTrekVideoGame.server.Services;

import java.util.ArrayList;

import StarTrekVideoGame.shared.GameState;

/**
 * GameStateDataBase contains the information for all the games and processes the requests
 * by the GameService class for getting and setting the information.
 *
 */
public class GameStateDataBase {

	ArrayList<GameState> gState = new ArrayList<GameState>();

	/**
	 * getGameState takes a game id and loops through all the
	 * games to return the right game state.
	 * @param gameID
	 * @return
	 */
	public GameState getGameState(String gameID) {
		GameState retVal = null;

		for(GameState gs : gState) {
			if(gs.getGameId().equals(gameID)) {
				retVal = gs;
			}
		}
		return retVal;
	}

	
	private static GameStateDataBase instance = new GameStateDataBase();

	public static GameStateDataBase instance() {
		return instance;
	}
	
	public ArrayList<GameState> getGameStates() {
		return gState;
	}
	
	public void addGameState(GameState state) {
		gState.add(state);
	}
}
