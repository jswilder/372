package StarTrekVideoGame.shared;

import java.util.ArrayList;
import java.util.UUID;

import StarTrekVideoGame.client.Model.User;

/**
 * GameState represents the game displayed to the user in the GameBoardFrame GUI.
 * It handles the processing for all of the information related to this the game in general.
 *
 */
public class GameState {

	// ================================ member variables ====================================
	private String title;
    private String starDate;
    private String gameId;
    private ArrayList<Player> players;
    private ArrayList<Ship> ships;
    private ArrayList<Base> bases;
    private ArrayList<Planet> planets;
    private ArrayList<Star> stars;
    private ArrayList<Empire> empires;
    private ArrayList<ShipType> shipTypes;
    private ArrayList<Weapon> weapons;
    private ArrayList<String> attackLog;

	// ===================================== constructors ====================================
    //Dummy game.
    public GameState() {
    	starDate = "2400";
    	gameId = UUID.randomUUID().toString();
    	title = "StarTrek";
    	players = new ArrayList<Player>();
    	Player player = new Player();
    	players.add(player);
    	ships = new ArrayList<Ship>();
    	ships.add(player.getShip());
    	bases = new ArrayList<Base>();
    	bases.add(new Base());
    	planets = new ArrayList<Planet>();
    	planets.add(new Planet(4, 4, 3, 3));
    	stars = new ArrayList<Star>();
    	stars.add(new Star());
    	empires = new ArrayList<Empire>();
    	empires.add(player.getEmpire());
    	weapons = new ArrayList<Weapon>();
    	weapons.add(player.getShip().getType().getEnergyWeapon());
    	weapons.add(player.getShip().getType().getMissileWeapon());
    	attackLog = new ArrayList<String>();
    }

    /**
     * This constructor takes the game string and parses it to make a gamestate from the .dat file.
     * @param gameString
     */
    public GameState(String gameString) {
    	//Init array lists.
    	gameId = UUID.randomUUID().toString();
    	players = new ArrayList<Player>();
    	ships = new ArrayList<Ship>();
    	bases = new ArrayList<Base>();
    	planets = new ArrayList<Planet>();
    	stars = new ArrayList<Star>();
    	empires = new ArrayList<Empire>();
    	shipTypes = new ArrayList<ShipType>();
    	weapons = new ArrayList<Weapon>();
    	attackLog = new ArrayList<String>();
    	attackLog.add("");
    	
    	//Get all the lines in the string.
    	String [] lines = gameString.split(System.getProperty("line.separator"));
    	boolean isEmpire=false, isWeapon=false, isShipType=false, isBase=false, isShip=false, isPlayer =false;
    	
    	//parse every line in the .dat files to get the values out of it.
    	for(String line : lines) {
    		//If we have a title.
    		if(line.contains("Title")) {
    			String [] gameInfoLine = lines[1].trim().split("\t");
    			title = gameInfoLine[0];
    			starDate = gameInfoLine[1];
    		} else if(line.contains("Empire Id")) {
    			isEmpire = true;
    		} else if(line.contains("Weapon Id")) {
    			isEmpire = false;
    			isWeapon = true;
    		} else if(line.contains("ShipType Id")) {
    			isWeapon = false;
    			isShipType = true;
    		} else if(line.contains("Base Id")) {
    			isShipType = false;
    			isBase = true;
    		} else if(line.contains("Ship ID")) {
    			isBase = false;
    			isShip = true;
    		} else if(line.contains("Player")) {
    			isShip = false;
    			isPlayer = true;
    		} else {
    			
    			//We have a newline so reset the booleans
    			if(line.trim().split("\t").length == 1) {
    				//do nothing new line
    			} else if(isEmpire) {
    				empires.add(new Empire(line));
    			} else if(isWeapon) {
    				weapons.add(new Weapon(line));
    			} else if(isShipType) {
    				shipTypes.add(new ShipType(line, this));
    			} else if(isBase) {
    				bases.add(new Base(line, this));
    			} else if(isShip) {
    				ships.add(new Ship(line, this));
    			} else if(isPlayer) {
    				players.add(new Player(line, this));
    			} else {
    				//Do nothing
    			}
    		}
    	}
	}

	// =================== getters ==============================
    public String getTitle() {
    	return title;
    }
    public String getStardate() {
        return starDate;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public ArrayList<Base> getBases() {
        return bases;
    }

    public ArrayList<Empire> getEmpires() {
    	return empires;
    }

    public ArrayList<Weapon> getWeapons() {
    	return weapons;
    }
    
    public  ArrayList<ShipType> getShipTypes() {
    	return shipTypes;
    }

    public String getGameId() {
    	return gameId;
    }

    public ArrayList<String> getAttackLog() {
		return attackLog;
	}

    // ============================ setters ===========================
    public void setTitle (String t) {
    	title = t;
    }
    
    public void setStarDate(String date) {
        starDate = date;
     }
    
    public void setAttackLog(ArrayList<String> attackLog) {
		this.attackLog = attackLog;
	}
    //returns the number of planets in a give sector.
	public String getNumOfPlanets(int px, int py) {
		int numOfPlanetsInSector = 0;
		for(Planet p : planets) {
			if((p.getPX() == px) && (p.getPY() == py)) {
				numOfPlanetsInSector++;
			}
		}
		return String.valueOf(numOfPlanetsInSector);
	}
    
    /**
     * Increments the star date and sets it to the current game.
     */
    public void incrementStarDate() {
    	int curStarDate = Integer.parseInt(starDate);
    	curStarDate++;
    	starDate = String.valueOf(curStarDate);
    }

    /**
     * getNumOfEnemies will calculate the number of enemy bases and enemy ships in the given sector (px, py). 
     * @param px
     * @param py
     * @return
     */
	public String getNumOfEnemies(int px, int py) {
		int numOfEnemiesInSector = 0;
		for(Base b : bases) {
			//increment the enemies if the enemy is in sector and not the same empire.
			if(((b.getPositionX() == px) && (b.getPositionY() == py)) 
					&& !User.getInstance().getMe().getEmpire().getEmpireId().equals(b.getEmpire().getEmpireId())) {
				numOfEnemiesInSector++;
			}
		}
		for(Ship s : ships) {
			if((s.getPX() == px) && (s.getPY() == py) && !User.getInstance().getMe().getEmpire().getEmpireId().equals(s.getType().getEmpire().getEmpireId())){
				numOfEnemiesInSector++;
			}
		}
		return String.valueOf(numOfEnemiesInSector);
	}

	/**
	 * getNumOfFriendlies will return the number of friendly bases in the given sector (px, py).
	 * @param px
	 * @param py
	 * @return
	 */
	public String getNumOfFriendlies(int px, int py) {
		int numOfFriendliesInSector = 0;
		//increments the friendlies by the num of bases and ships that have the same empire.
		for(Base b : bases) {
			if(((b.getPositionX() == px) && (b.getPositionY() == py)) 
					&& User.getInstance().getMe().getEmpire().getEmpireId().equals(b.getEmpire().getEmpireId())) {
				numOfFriendliesInSector++;
			}
		}
		/*
		for(Ship s : ships) {
			if(((s.getPX() == px) && (s.getPY() == py)) 
					&& User.getInstance().getMe().getEmpire().equals(s.getType().getEmpire())) {
				numOfFriendliesInSector++;
			}
		}*/
		return String.valueOf(numOfFriendliesInSector);
	}
	
	/**
	 * hasBaseAt will loop through all the bases and determine if a base is located at the given
	 * coordinates. Sector(px, py) Grid (sx, sy).
	 * @param px
	 * @param py
	 * @param sx
	 * @param sy
	 * @return
	 */
	public boolean hasBaseAt(int px, int py, int sx, int sy) {
		boolean retVal = false; 
		for(Base b : bases) {
			if(b.getPositionX() == px && b.getPositionY() == py && b.getSectorY() == sy && b.getSectorX() == sx) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}
	
	/**
	 * getBaseAt will return the Base object located at the given coordinates, (universe view)sector px, py and (sector view)grid point sx, sy
	 * @param px
	 * @param py
	 * @param sx
	 * @param sy
	 * @return
	 * @throws NullPointerException
	 */
	public Base getBaseAt(int px, int py, int sx, int sy) throws NullPointerException{
		Base retVal = null;
		for(Base b : bases) {
			if(b.getPositionX() == px && b.getPositionY() == py && b.getSectorX() == sx && b.getSectorY() == sy) {
				retVal = b;
				break;
			}
		}
		
		return retVal;
	}
	
	/**
	 * hasPlayerAt will return a true if there is a player located at the given coordinates and false if otherwise.
	 * @param px
	 * @param py
	 * @param sx
	 * @param sy
	 * @return
	 */
	public boolean hasPlayerAt(int px, int py, int sx, int sy) {
		boolean retVal = false; 
		for(Player s : players) {
			if((s.getShip().getPX() == px) && (s.getShip().getPY() == py) && (s.getShip().getSY() == sy) && (s.getShip().getSX() == sx)) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}
	
	/**
	 * getPlayerAt returns the player located at the given coordinates. It loops through all players in the gamestate.
	 * @param px
	 * @param py
	 * @param sx
	 * @param sy
	 * @return
	 */
	public Player getPlayerAt(int px, int py, int sx, int sy) {
		Player retVal = null;
		for(Player p : players) {
			if(p.getShip().getPX() == px && p.getShip().getPY() == py && p.getShip().getSY() == sy && p.getShip().getSX() == sx) {
				retVal = p;
				break;
			}
		}
		return retVal;
	}
	
	/**
	 * hasShipAt returns a true if a ship is located at the current game coordinates. It loops through all possible ships in the gamestate.
	 * @param px
	 * @param py
	 * @param sx
	 * @param sy
	 * @return
	 */
	public boolean hasShipAt(int px, int py, int sx, int sy) {
		boolean retVal = false; 
		for(Ship s : ships) {
			if((s.getPX() == px) && (s.getPY() == py) && (s.getSY() == sy) && (s.getSX() == sx)) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}
	
	/**
	 * getShipAt returns the ship object located at the given coordinates.
	 * @param px
	 * @param py
	 * @param sx
	 * @param sy
	 * @return
	 */
	public Ship getShipAt(int px, int py, int sx, int sy) {
		Ship retVal = null;
		for(Ship p : ships) {
			if((p.getPX() == px) && (p.getPY() == py) && (p.getSY() == sy) && (p.getSX() == sx)) {
				retVal = p;
				break;
			}
		}
		return retVal;
	}
	
	/**
	 * removeShipAt removes the ship located at the given coordinates.
	 * HAS KNOWN PROBLEMS, IT DOESN'T ACTUALLY REMOVE THE OBJECT.
	 * @param px
	 * @param py
	 * @param sx
	 * @param sy
	 */
	public void removeShipAt(int px, int py, int sx, int sy) {
		for(Ship s : ships) {
			if((s.getPX() == px) && (s.getPY() == py) && (s.getSX() == sx) && (s.getSY() == sy)) {
				//remove the ship at the given point.
				ships.remove(s);
			}
		}
	}
	
	/**
	 * fireAI will select every CPU ship and get them to fire at the closest enemy in their current sector.
	 * The damage done is equivalent to a regular attack.
	 */
	public void fireAI() {
		boolean wasPlayer = false;
		Ship target = null;
		
		for(Ship s : ships) {
			wasPlayer = false;
			for (Player p : players) {
				if(p.getShip().getId().equals(s.getId())) {
					wasPlayer = true;
				}
			}
			if(!wasPlayer) {
				int x = 0;
				int y = 0;
				double min = 100000;
				for(Ship s2 : ships) {
					if(!s.getId().equals(s2.getId()) && !s.getType().getEmpire().getEmpireId().equals(s2.getType().getEmpire().getEmpireId()) 
							&& (s2.getPX() == s.getPX()) && (s2.getPY() == s.getPY())) {
						int x1 = s.getSX();
						int x2 = s2.getSX();
						int y1 = s.getSY();
						int y2 = s2.getSY();
						
						double distance = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
						if(distance < min) {
							min = distance;
							target = s2;
						}
					}
				}
				
				target.wasAttacked(s.getType().getEnergyWeapon().getMaxYield());
			}
		}
	}
	
	/**
	 * Prints all the information about the game state to the terminal
	 */
	public void dump() {
		
		System.out.println("Title: " + title);
		System.out.println("StarDate: " + starDate);
		System.out.println("Game ID: " + gameId);
		System.out.println("\n");
		
		System.out.println("PLAYERS");
		
		for(Player p : players) {
			System.out.println("Player ID: " + p.getId());
			System.out.println("Empire: " + p.getEmpire().getName());
			System.out.println("Ship: " + p.getShip().getType().getName());
		}
		
		System.out.println("SHIPS: ");
		
		for(Ship s : ships) {
			System.out.println("Ship ID: " + s.getId());
			System.out.println("Ship type: " + s.getType().getName());
			System.out.println("Cur Energy: " + s.getEnergy());
			System.out.println("Cur shields: " + s.getShields());
			System.out.println("Cur missles: " + s.getMissles());
			System.out.println("Alert Level: " + s.getAlertLevel().toString());
			System.out.println("SX: " + s.getSX());
			System.out.println("SY: " + s.getSY());
			System.out.println("PX: " + s.getPX());
			System.out.println("PY: " + s.getPY());
		}
		
		System.out.println("BASES:");
		
		for(Base b : bases) {
			System.out.println("Empire: " + b.getEmpire().getName());
			System.out.println("SX: " + b.getSectorX());
			System.out.println("SY: " + b.getSectorY());
			System.out.println("PX: " + b.getPositionX());
			System.out.println("PY: " + b.getPositionY());
		}
		
		System.out.println("EMPIRES: ");
		
		for(Empire e : empires) {
			System.out.println("ID: " + e.getEmpireId());
			System.out.println("Name: " + e.getName());
			System.out.println("Mission Type: " + e.getType());
		}
		
		System.out.println("SHIPTYPES");
		
		
		for(ShipType st : shipTypes) {
			System.out.println("ID: " + st.getid());
			System.out.println("Name: " + st.getName());
			System.out.println("Class: "  + st.getClassType());
			System.out.println("Empire: " + st.getEmpire().getName());
			System.out.println("Max Energy: " + st.getMaxEnergy());
			System.out.println("Max Speed: " + st.getMaxSpeed());
			System.out.println("Max Shields: " + st.getMaxShields());
			System.out.println("Energy Wep Type: " + st.getEnergyWeapon().getId());
			System.out.println("Missile Wep Type: " + st.getMissileWeapon().getId());
			System.out.println("Max Missile: " + st.getMaxMissile());
		}
		
		System.out.println("WEAPONS: ");
		
		for(Weapon w : weapons) {
			System.out.println("ID: " + w.getId());
			System.out.println("Name " + w.getName());
			System.out.println("Weapon Type " + w.getType());
			System.out.println("Max yield " + w.getMaxYield());
		}
		
		System.out.println("FINISHED DUMPING");
	}
}
//}
