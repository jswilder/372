
package StarTrekVideoGame.client.Model;

import StarTrekVideoGame.shared.GameState;
import StarTrekVideoGame.shared.Player;

public class User {
    private static User instance = null;
    private String username;
    private String password;
    private Player me;

    	// Empty constructor
    public User() {}

    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    	// This should be initialized.
    public static User getInstance() {
    	return instance;
    }

    public static void initUser(String username, String password) {
        if(instance != null) {
            return;
        } else {
            instance = new User(username, password);
        }
    }

		// Assign the player the selected ship
    public void setUserShip(GameState currentGameInfo) {
    	for(Player p : currentGameInfo.getPlayers()) {
    		if(p.getId().equals(username)) {
    			this.me = p;
    		}
    	}
    	// Set the default if we couldn't match the player
    	if(this.me == null) {
    		this.me = new Player();
    	}
    }

		// Returns the User's player
    public Player getMe() {
    	return me;
    }
}
