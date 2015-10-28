package StarTrekVideoGame.shared;

import java.util.UUID;

public class ShipType {

	private String id;
	private String name;
	private String classType;
	private Empire empire;
	private int maxEnergy;
	private int maxSpeed;
	private int maxShields;
	private Weapon energyWeapon;
	private Weapon missileWeapon;
	private int maxMissile;

	public ShipType(String string, GameState currentGameInfo) {
		String [] shipTypeInfo = string.trim().split("\t");
		id = shipTypeInfo[0];
		name = shipTypeInfo[1];
		classType = shipTypeInfo[2];
		
		//Get the empire from the empires we already have.
		for(Empire e : currentGameInfo.getEmpires()) {
			if(e.getEmpireId().equals(shipTypeInfo[3])) {
				empire = e;
			}
		}
		
		// Set the ship attributes
		maxEnergy = Integer.parseInt(shipTypeInfo[4]);
		maxSpeed = Integer.parseInt(shipTypeInfo[5]);
		maxShields = Integer.parseInt(shipTypeInfo[6]);
		
		//Set energy weapon
		for(Weapon w : currentGameInfo.getWeapons()) {
			if (w.getId().equals(shipTypeInfo[7])) {
				energyWeapon = w;
			}
		}
		
		//Set missle weapon
		for(Weapon w : currentGameInfo.getWeapons()) {
			if (w.getId().equals(shipTypeInfo[8])) {
				missileWeapon = w;
			}
		}
		
		maxMissile = Integer.parseInt(shipTypeInfo[9]);
	}

	public ShipType() {
		id = UUID.randomUUID().toString();
		name = "Enterprise";
		classType = "Warship";
		empire = new Empire();
		maxEnergy = 3000;
		maxSpeed = 10;
		maxShields = 2000;
		energyWeapon = new Weapon(2);
		missileWeapon = new Weapon(1);
		maxMissile = 10;
	}

	//given a string parsed from a .dat game file, it will initialize the weapon.
	public void setShipType(String string) {

		id = string.substring(0,1);
		name = string.substring(1,2);
		classType = string.substring(2,3);
		empire = new Empire(string.substring(3,4));
		maxEnergy = Integer.parseInt(string.substring(4,5));
		maxSpeed = Integer.parseInt(string.substring(5,6));
		maxShields = Integer.parseInt(string.substring(6,7));
		energyWeapon = new Weapon(string.substring(7,8));
		missileWeapon = new Weapon(string.substring(8,9));
		maxMissile = Integer.parseInt(string.substring(9,10));
	}

	public String getid() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getClassType() {
		return classType;
	}

	public Empire getEmpire() {
		return empire;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public int getMaxShields() {
		return maxShields;
	}

	public Weapon getEnergyWeapon() {
		return energyWeapon;
	}

	public Weapon getMissileWeapon() {
		return missileWeapon;
	}

	public int getMaxMissile() {
		return maxMissile;
	}
}
