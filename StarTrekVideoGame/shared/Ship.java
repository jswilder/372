
package StarTrekVideoGame.shared;

import java.util.UUID;

import StarTrekVideoGame.server.Services.GameStateDataBase;


public class Ship {
    private String id;
    private ShipType type;
    private int energy;
    private int shields;
    private AlertLevelEnum alertLevel; // 1 = GREEN 2 = YELLOW 3 = RED
    private int SX;
    private int SY;
    private int PX;
    private int PY;
	private int missles;
    
    //constructor for adding ship from .dat file.
    public Ship (String string, GameState currentGameInformation) {
    	String [] shipInfo = string.trim().split("\t");
    	id = shipInfo[0];
    	
    	//Set the ship type.
    	for(ShipType s : currentGameInformation.getShipTypes()) {
    		if(s.getid().equals(shipInfo[1])) {
    			type = s;
    		}
    	}
    	
    	//Set the refreshable values
    	SX = Integer.parseInt(shipInfo[2]);
    	SY = Integer.parseInt(shipInfo[3]);
    	PX = Integer.parseInt(shipInfo[4]);
    	PY = Integer.parseInt(shipInfo[5]);
    	energy = Integer.parseInt(shipInfo[6]);
    	missles = Integer.parseInt(shipInfo[7]);
    	alertLevel = AlertLevelEnum.valueOf(shipInfo[8]);
    	shields = Integer.parseInt(shipInfo[9]);
    }
    
    public Ship () {
    	id = UUID.randomUUID().toString();
    	type = new ShipType();
    	energy = type.getMaxEnergy();
    	shields = type.getMaxShields();
    	alertLevel = AlertLevelEnum.GREEN;
    	SX = 4;
    	SY = 4;
    	PX = 4;
    	PY = 4;
    	missles = type.getMaxMissile();
    }

    /**
     * Boolean function that checks to see String id sent in is equal to the 
     * id in the class.
     * @param id
     * @return
     */
    public boolean hasShipId(String id) {
        boolean retVal = false;
        if(this.id.equals(id))  {
            retVal = true;
        }
        return retVal;
    }

		// Compute damaged received and decrement    
    public boolean wasAttacked(int maxYield) {
    	boolean retVal = false;
    	if(alertLevel == AlertLevelEnum.GREEN) {
    		//Ship is destroyed
    		shields = -1;
    		retVal = true;
    	} else if(alertLevel == AlertLevelEnum.YELLOW) {
    		double newYield = maxYield * 0.5;
    		shields -= newYield;
    		if(shields < 0) {
    			retVal = true;
    		}
    	} else {
    		shields -= maxYield;
    		
    		if(shields < 0) {
    			retVal = true;
    		}
    	}
    	
    	return retVal;
    }

		// Perform warp to given sector    
    public void warpTo(int x, int y, String gameId) {
    	this.PX = x;
    	this.PY = y;
    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < 8; j++) {
    			if((!GameStateDataBase.instance().getGameState(gameId).hasShipAt(x, y, i + 1, j + 1)) 
    					|| (!GameStateDataBase.instance().getGameState(gameId).hasBaseAt(x, y, i + 1, j + 1))) {
    				this.SX = i + 1;
    				this.SY = j + 1;
    			}
    		}
    	}
    }

		// Perform impulse navigation to given grid within sector    
    public void impulseTo(int x, int y, String gameId) {
    	if((!GameStateDataBase.instance().getGameState(gameId).hasShipAt(this.PX, this.PY, x, y)) 
    			|| !GameStateDataBase.instance().getGameState(gameId).hasBaseAt(this.PX, this.PY, x, y)) {
    		this.SX = x;
        	this.SY = y;
    	}
    }

    //some logic of getting the alert level of the ship.
    public AlertLevelEnum getAlertLevel() {
    	return alertLevel;
    }

    public int getShields() {
        return shields;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMissles() {
        return missles;
    }
    
    public int getSX() {
		return SX;
	}

	public int getSY() {
		return SY;
	}

	public int getPX() {
		return PX;
	}

	public int getPY() {
		return PY;
	}

    public ShipType getType() {
		return type;
	}
    
    public String getId() {
    	return id;
    }

		// Set alert level for the player's ship
	public void setAlertLevel(AlertLevelEnum level) {
        this.alertLevel = level;
    }

		// Decremenet missile count by one
     public void decrement() {
		missles-=1;
	}

		// Check to make sure enough energy is available
     public int checkWarpEnergy(int oldX, int oldY, int newX, int newY) {
    	 
    	 if (oldX == newX) {
    		 if (oldY > newY)
    			 return (oldY-newY) * 100;
    		 else
    			 return (newY-oldY) * 100;
    	 }
    	 else if (oldY == newY) {
    		 if (oldX > newX)
    			 return (oldX-newX) * 100;
    		 else
    			 return (newX-oldX) * 100;
    	 }
         else
    	   return ((int) Math.sqrt((double)((oldX*newX) + (oldY*newY)))) * 100;
     }

		// Check to make sure enough energy is available     
     public int checkImpulseEnergy(int oldX, int oldY, int newX, int newY) {
    	 if (oldX == newX) {
    		 if (oldY > newY)
    			 return (oldY-newY) * 10;
    		 else
    			 return (newY-oldY) * 10;
    	 }
    	 else if (oldY == newY) {
    		 if (oldX > newX)
    			 return (oldX-newX) * 10;
    		 else
    			 return (newX-oldX) * 10;
    	 }
    	 
    	 else 
    	   return ((int) Math.sqrt((double)((oldX*newX) + (oldY*newY)))) * 10;
     }

		// Decrement the energy available for warp travel
     public void warpDecrement(int oldX, int oldY, int newX, int newY) {
    	 
       	 if (oldX == newX) {
    		 if (oldY > newY)
    			 energy -= (oldY-newY) * 100;
    		 else
    			 energy -= (newY-oldY) * 100;
    	 }
    	 else if (oldY == newY) {
    		 if (oldX > newX)
    			 energy -= (oldX-newX) * 100;
    		 else
    			 energy -= (newX-oldX) * 100;
    	 }
    	 
    	 else {
    	   int decrement = ((int) Math.sqrt((double)((oldX*newX) + (oldY*newY)))) * 100;
    	 
    	   energy -= decrement;
    	 }
     }

		// Decrement the energy available for impulse travel     
     public void impulseDecrement(int oldX, int oldY, int newX, int newY) {
    	 
    	 if (oldX == newX) {
    		 if (oldY > newY)
    			 energy -= (oldY-newY) * 10;
    		 else
    			 energy -= (newY-oldY) * 10;
    	 }
    	 else if (oldY == newY) {
    		 if (oldX > newX)
    			 energy -= (oldX-newX) * 10;
    		 else
    			 energy -= (newX-oldX) * 10;
    	 }
    	 
    	 else {
    	   int decrement = ((int) Math.sqrt((double)((oldX*newX) + (oldY*newY)))) * 10;    	 
    	   energy -= decrement;
    	 }
     }
}
