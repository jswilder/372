
package StarTrekVideoGame.shared;

import java.util.UUID;

public class Base {

	private String id;
	private Empire empire;
	//location variables.
	private int SX;
	private int SY;
	private int PX;
	private int PY;

	public String getId() {
		return id;
	}

	public Empire getEmpire() {
		return empire;
	}

	public int getSectorX() {
		return SX;
	}

	public int getSectorY() {
		return SY;
	}

	public int getPositionX() {
		return PX;
	}

	public int getPositionY() {
		return PY;
	}

	/**
	 * Base constructor that takes in a string and GameState that parses out the coordinates of a base.
	 * @param string
	 * @param currentGameInformation
	 */
	public Base (String string, GameState currentGameInformation) {
		String [] baseInfo = string.trim().split("\t");
		id = baseInfo[0];
		
		//Find the appropriate empire in the list we already have.
		for(Empire e : currentGameInformation.getEmpires()) {
			if(e.getEmpireId().equals(baseInfo[1])) {
				empire = e;
			}
		}
		//Set locations
		SX = Integer.parseInt(baseInfo[2]);
		SY = Integer.parseInt(baseInfo[3]);
		PX = Integer.parseInt(baseInfo[4]);
		PY = Integer.parseInt(baseInfo[5]);
	}

	public Base() {
		id = UUID.randomUUID().toString();
		empire = new Empire();
		SX = 1;
		SY = 1;
		PX = 1;
		PY = 1;
	}

	public void setBases(String string) {

		id = string.substring(0,1);
		empire = new Empire(string);
		SX = Integer.parseInt(string.substring(2,3));
		SY = Integer.parseInt(string.substring(3,4));
		PX = Integer.parseInt(string.substring(4,5));
		PY = Integer.parseInt(string.substring(5));
	}
}
