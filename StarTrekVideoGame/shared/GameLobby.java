
package StarTrekVideoGame.shared;

import java.util.ArrayList;

public class GameLobby {
    private GameState currentGame;
    private ArrayList<GameState> allGames;

    public GameState getCurrentGameState() {
        return currentGame;
    }
}
