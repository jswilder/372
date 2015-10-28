package StarTrekVideoGame.server.Model;

import java.util.ArrayList;

import javax.naming.ServiceUnavailableException;

import StarTrekVideoGame.server.Services.GameStateDataBase;
import StarTrekVideoGame.shared.GameService;
import StarTrekVideoGame.shared.GameState;
import StarTrekVideoGame.shared.Ship;
import StarTrekVideoGame.shared.AlertLevelEnum;

public class GameStateServiceImpl implements GameService {

	public GameStateServiceImpl() {}

	/*
	 * (non-Javadoc)
	 * @see StarTrekVideoGame.shared.GameService#getGameState(java.lang.String)
	 */
	@Override
	public GameState getGameState(String body) {
		String [] items = body.split("[<>]+");
		
		String gameId = items[2];
		
		GameStateDataBase.instance().getGameState(gameId).fireAI();
		GameStateDataBase.instance().getGameState(gameId).incrementStarDate();
		
		return GameStateDataBase.instance().getGameState(gameId);
	}

	/*
	 * (non-Javadoc)
	 * @see StarTrekVideoGame.shared.GameService#getGameList()
	 */
	@Override
	public ArrayList<GameState> getGameList() { 
		
		return GameStateDataBase.instance().getGameStates();
	}

	/*
	 * (non-Javadoc)
	 * @see StarTrekVideoGame.shared.GameService#restoreGame(java.lang.String)
	 */
	@Override
	public GameState restoreGame(String gameFile)
			throws ServiceUnavailableException {
		
		String [] requestMessage = gameFile.split("[<>]+");
		
		GameState restoredGameState = new GameState(requestMessage[2]);
		GameStateDataBase.instance().addGameState(restoredGameState);
		
		return restoredGameState;
	}

	/*
	 * (non-Javadoc)
	 * @see StarTrekVideoGame.shared.GameService#authenticate(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean authenticate(String username, String password) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see StarTrekVideoGame.shared.GameService#attackTarget(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public void attackTarget(String gameId, String shipSourceId, int x, int y) 
		throws ServiceUnavailableException {
		
		GameState state = GameStateDataBase.instance().getGameState(gameId);
		for(Ship s : state.getShips()) {
			if(s.getId().equals(shipSourceId)) {
				
				int px = s.getPX();
				int py = s.getPY();
				if(state.hasShipAt(px, py, x, y)) {
					Ship victim = state.getShipAt(px, py, x, y);
					if(s.getMissles() > 0) {
						if(victim.wasAttacked(s.getType().getMissileWeapon().getMaxYield())) {
							//player was destroyed.
							s.decrement();
							GameStateDataBase.instance().getGameState(gameId).removeShipAt(px, py, x, y);
						}
						
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see StarTrekVideoGame.shared.GameService#navigate(java.lang.String, java.lang.String, int, int, int)
	 */
	@Override
	public void navigate(String gameId, String shipId, int wasWarp, int x, int y) {
		GameState currentGame = GameStateDataBase.instance().getGameState(gameId);
		  
		  int energyWarp, energyImpulse, currentEnergy;
		
		  //Find the ship and tell it to navigate.
		  for(Ship s : currentGame.getShips()) {
			  energyWarp = s.checkWarpEnergy(s.getPX(), s.getPY(), x, y);
			  energyImpulse = s.checkImpulseEnergy(s.getSX(), s.getSY(), x, y);
			  currentEnergy = s.getEnergy();
			  if(s.getId().equals(shipId)) {
				  if(wasWarp == 1 && energyWarp < currentEnergy) {
					  s.warpDecrement(s.getPX(), s.getPY(), x, y);
					  s.warpTo(x , y, gameId);
				  } else if (energyImpulse < currentEnergy){
					  s.impulseDecrement(s.getSX(), s.getSY(), x, y);
					  s.impulseTo(x,y, gameId);
				  }
			  }
		  }
	}

	/*
	 * (non-Javadoc)
	 * @see StarTrekVideoGame.shared.GameService#setAlert(StarTrekVideoGame.shared.Ship, int, java.lang.String, StarTrekVideoGame.shared.AlertLevelEnum)
	 */
	@Override
	public void setAlert(Ship source, int state, String gameId, AlertLevelEnum alert) {
		source.setAlertLevel(alert);
	}
}
