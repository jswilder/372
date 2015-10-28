
package StarTrekVideoGame.server.Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.naming.ServiceUnavailableException;

import StarTrekVideoGame.server.Response.GameStateResponse;
import StarTrekVideoGame.server.Response.GetGameStateListResponse;
import StarTrekVideoGame.server.Response.GetGameStateResponse;
import StarTrekVideoGame.server.Response.RestoreGameStateResponse;
import StarTrekVideoGame.server.Services.GameStateDataBase;
import StarTrekVideoGame.shared.AlertLevelEnum;
import StarTrekVideoGame.shared.GameState;
import StarTrekVideoGame.shared.NanoHTTPD;
import StarTrekVideoGame.shared.Ship;

import com.google.gson.Gson;

/**
 * 
 * @author CPSC 3720 Team
 * This class will take requests from NanoHTTPD and parse the body of the requests to get the message
 * from the clients. It will call corresponding methods to perform the requested actions.
 */
public class GameStateServer extends NanoHTTPD {

	  GameStateServiceImpl service;

	  public GameStateServer(int port) {
	    super(port);
	    service = new GameStateServiceImpl();
	  }

	  /**
	   * This function will take a session, parse the body and, depending on the message
	   * will call the appropriate process function. It will return a response with what is
	   * returned from the process methods. This response gets sent back to the clients.
	   */
	  @Override
	  public synchronized Response serve(IHTTPSession session)  {

	    String path = session.getUri();
	    String body = getBody(session);
	    String jsonResponse;
	    GameStateResponse result = null;
	    GetGameStateResponse refreshedGameState = null;
	    System.out.println("Incoming request: path = " + path + "; body: " + body);

	    try {
	    	if (body.contains("<RESTORE>")) {
	    		result = processRestoreGame(body);
	    	} else if (body.contains("<GET>")) {
	    		result = processGetGameState(body);
	    	} else if (body.contains("<LIST>")){
	    		result = processGetList();
	    	} else if (body.contains("<FIRE>")) {
	    		processFire(body);
	    	} else if (body.contains("<NAVIGATE>")) {
	    		processNavigate(body);
	    	} else if (body.contains("<ALERT>")) {
	    		processSetAlert(body);
	    	} else {
	    		result = new GameStateResponse("Unimplemented request " + path);
	    	}
	    } catch (GameStateServiceException ex) {
	    	result = new GameStateResponse(ex.getMessage());
	    } catch (ServiceUnavailableException ex) {
	    	result = new GameStateResponse(ex.getMessage());
	    }

	    jsonResponse = new Gson().toJson(result);
	    //System.out.println("Sending response: " + jsonResponse);

	    return new Response(jsonResponse);
	  }
	  
	  /**
	   * processSetAlert: This function will take body and split it by the delimiters and
	   * send the information to the service.
	   * @param body
	   */
	  private void processSetAlert(String body) {
		  String [] info = body.split("[<>]+");
		  
		  String gameId = info[2];
		  String shipId = info[3];
		  AlertLevelEnum newLevel = AlertLevelEnum.valueOf(info[4]);
		  
		  Ship source = null;
		  
		  for(Ship s : GameStateDataBase.instance().getGameState(gameId).getShips()) {
			  if(s.getId().equals(shipId)) {
				  source = s;
				  System.out.println("Last alert level: " + s.getAlertLevel().toString());
			  }
		  }
		  
		  service.setAlert(source, -1, gameId, newLevel);
	  }

	  /**
	   * This function uses the GameService interface and calls the restore game and expects
	   * a game state. The game state gets converted to a json and placed inside a Response class.
	   * @param body
	   * @return
	   * @throws GameStateServiceException
	   */
	  private RestoreGameStateResponse processRestoreGame(String body)
	  	throws GameStateServiceException {
		  GameState refreshedGameState = null;
		  try {
			 refreshedGameState = service.restoreGame(body);
		  } catch (ServiceUnavailableException e) {
			  //System.out.println("SERVICE UNAVAILABLE");
			return new RestoreGameStateResponse();
		  }
		  return new RestoreGameStateResponse(new Gson().toJson(refreshedGameState));
	  }
	  
	  /**
	   * This function uses the GameService interface and sends the body passed to the
	   * get game state function. It expects a game state back. It sets this game state
	   * to a json string that gets put into a response and sends it back to the serve function.
	   * @param body
	   * @return
	   * @throws GameStateServiceException
	   * @throws ServiceUnavailableException
	   */
	  private GetGameStateResponse processGetGameState(String body) 
	    throws GameStateServiceException, ServiceUnavailableException {
		  
		  //get the new gamestate by passing the ID part of the string.
		  GameState state = service.getGameState(body);
		  
		  GetGameStateResponse response = new GetGameStateResponse(new Gson().toJson(state));
		  return response;
	  }

	  /**
	   * processGetList sends the processing to the GameStateServiceImpl and expects an
	   * array list back. This array list gets processed into a json string, put into a response
	   * and sent back to the caller, serve.
	   * @return
	   */
	  private GetGameStateListResponse processGetList() {
		  
		  ArrayList<GameState> gStates;
		  
		  gStates = service.getGameList();
		  
		  GetGameStateListResponse response = new GetGameStateListResponse(new Gson().toJson(gStates));
		  
		  return response;
	  }

	  /**
	   * processFire processes the body string into the information that is important to send
	   * to the GameStateServiceImpl. There isn't a response returned because we don't expect
	   * the server to response to the client with anything important.
	   * @param body
	   * @throws GameStateServiceException
	   * @throws ServiceUnavailableException
	   */
	  private void processFire(String body)
	    throws GameStateServiceException, ServiceUnavailableException {
		  String [] info = body.split("[<>]+");
		  char [] coordinates = info[4].toCharArray();
		  int x = Integer.parseInt(String.valueOf(coordinates[0]));
		  int y = Integer.parseInt(String.valueOf(coordinates[1]));
		  
		  service.attackTarget(info[2], info[3], x, y);
	  }
	  
	  /**
	   * processNavigate processes the string passed into important information for the GameStateServiceImpl
	   * for the navigate function. The function doesn't return anything because the server isn't expecting
	   * to return anything to client.
	   * @param body
	   */
	  private void processNavigate(String body) {
		  String [] info = body.split("[<>]+");
		  
		  char [] coordinates = info[5].toCharArray();
		  String gameId = info[2];
		  String shipId = info[3];
		  int wasWarp = Integer.parseInt(info[4]);
		  int x = Integer.parseInt(String.valueOf(coordinates[0]));
		  int y = Integer.parseInt(String.valueOf(coordinates[1]));
		  
		  service.navigate(gameId, shipId, wasWarp, x, y);
	  }
	  
	  protected String getBody(IHTTPSession session) {
	    int len = Integer.parseInt(session.getHeaders().get("content-length"));
	    InputStream inputStream = session.getInputStream();

	    try {
	      byte[] buf = new byte[len];
	      int bytesRead = 0;
	      int read = 0;
	      while (bytesRead < len
	          && (read = inputStream.read(buf, bytesRead, len - bytesRead)) > 0) {
	        bytesRead += read;
	      }
	      return new String(buf);
	    } catch (IOException ex) {
	      ex.printStackTrace();
	    }
	    return "";
	  }
}

