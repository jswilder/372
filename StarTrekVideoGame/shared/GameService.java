
package StarTrekVideoGame.shared;

import java.util.ArrayList;

import javax.naming.ServiceUnavailableException;

/**
 * 
 * This is used by the client and server to process responses and requests for manipulating gamestate data.
 *
 */
public interface GameService {
	/**
	 * returns the entire array list to be used either by the GameSelectionFrame or the server functions.
	 * @return
	 */
    ArrayList<GameState> getGameList();
    
    /**
     * restoreGame returns a game state that should represent a gamestate from a .dat file.
     * @param gameFile
     * @return
     * @throws ServiceUnavailableException
     */
    GameState restoreGame(String gameFile) throws ServiceUnavailableException;
    /**
     * not used.
     * @param username
     * @param password
     * @return
     */
    boolean authenticate(String username, String password);
    /**
     * attackTarget should be used to process a ship being attacked by another ship.
     * @param gameID
     * @param shipSourceID
     * @param targetX
     * @param targetY
     * @throws ServiceUnavailableException
     */
    void attackTarget(String gameID, String shipSourceID, int targetX, int targetY) throws ServiceUnavailableException;
    /**
     * navigate should move the player to the given coordinates with the given type which is either warp or impulse.
     * @param gameId
     * @param shipId
     * @param type
     * @param x
     * @param y
     */
    void navigate(String gameId, String shipId, int type, int x, int y);
    /**
     * given the source ship and the game state, this function will change the alert level of the ship.
     * @param source
     * @param state
     * @param gameId
     * @param alert
     */
	void setAlert(Ship source, int state, String gameId, AlertLevelEnum alert);
	/**
	 * gets a game state that represents the current game being used in the GameBoardFrame.
	 * @param gameId
	 * @return
	 * @throws ServiceUnavailableException
	 */
    GameState getGameState(String gameId) throws ServiceUnavailableException;
}
