
package StarTrekVideoGame.shared;

public enum AlertLevelEnum {
    RED, YELLOW, GREEN;
    
    /**
     * Returning the alert level of each ship in the game.
     * @param level
     * @return
     */
    public String toString(AlertLevelEnum level) {
    	String retVal;
    	switch(level) {
    	case RED:
    		retVal =  "RED";
    		break;
    	case YELLOW:
    		retVal = "YELLOW";
    		break;
    	case GREEN:
    		retVal = "GREEN";
    		break;
    	default:
    		retVal = "ERROR";
    		break;
    	}
    	
    	return retVal;
    }
}
