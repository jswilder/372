
package StarTrekVideoGame.shared;

import java.util.UUID;

//TODO: figure the logic for adding based off .dat file and for adding brand new players.
public class Player {
    private String id;
    protected Ship ship;
    protected Empire empire;

    public Player() {
    	id = "John Doe Username";
    	ship = new Ship();
    	empire = new Empire();
    }

    public Player(Ship ship, Empire empire) {
        this.id = UUID.randomUUID().toString();
        this.ship = ship;
        this.empire = empire;
    }

    //Constructor specific to server.
    public Player(String string, GameState currentGameInfo) {
    	String [] playerInfo = string.trim().split("\t");
    	
    	id = playerInfo[0];
    
    	//Set the empire
    	for(Empire e : currentGameInfo.getEmpires()) {
    		if(e.getEmpireId().equals(playerInfo[1])) {
    			empire = e;
    		}
    	}
    	
    	//Set the appropriate ship
    	for(Ship s : currentGameInfo.getShips()) {
    		if(s.getId().equals(playerInfo[2])) {
    			ship = s;
    		}
    	}
    }

    /* Given the string we assume the player probably exists in the database
     * but the player doesn't exist on the client side. That being said we need
     * to be able to access our list of players and set that as our instance
     * somehow.
     */
    
 /*   public void setPlayers(String string) {

		 id = string.substring(0,1);
		 empire = new Empire(string.substring(1,2));
		 ship = new Ship(string.substring(2));
	 }*/

    public String getId() {
        return id;
    }

    public Ship getShip() {
        return ship;
    }

    public Empire getEmpire() {
        return empire;
    }
}
