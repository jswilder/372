package StarTrekVideoGame.shared;

import java.util.UUID;


public class Weapon {

	private String id;
	private String name; 
	private String type;
	private int maxYield;
	
	/**
	 * Weapon constructor that takes in a string and parses out the 
	 * information for all the weapon variables.
	 * @param string
	 */
	public Weapon (String string) {
		String [] weaponInfo = string.trim().split("\t");
		id = weaponInfo[0];
		name = weaponInfo[1];
		type = weaponInfo[2];
		maxYield = Integer.parseInt(weaponInfo[3]);
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public int getMaxYield() {
		return maxYield;
	}


	/**
	 * Weapon constructor that takes in a weapon type integer and decides 
	 * if your weapon type is a missile or energy.
	 * @param wepType
	 */
	public Weapon(int wepType) {
		if(wepType == 1) {
			name = "Launcher";
			id = UUID.randomUUID().toString();
			type = "Missles";
			maxYield = 500;
		} else {
			name = "Phaser";
			id = UUID.randomUUID().toString();
			type = "Energy";
			maxYield = 200;
		}
		
	}
/*
	public void setWeapons(String string) {
		 name = string.substring(0,1);
		 id = string.substring(1,2);
		 type = string.substring(2,3);
		 maxYield = Integer.parseInt(string.substring(3));
	 }*/
}
