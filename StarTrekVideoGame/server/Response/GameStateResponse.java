package StarTrekVideoGame.server.Response;

/**
 * This class is used as a super class for GetGameStateListResponse, GetGameStateResponse, and RestoreGameStateResponse
 * It is generally used for debugging purposed on the client side to interpret messages from the server.
 * 
 */

public class GameStateResponse {

	  protected boolean isSuccess;

	  protected String errorMsg;
	  
	  public GameStateResponse() {
	    isSuccess = true;
	  }
	  
	  public GameStateResponse(String errorMsg) {
	    isSuccess = false;
	    this.errorMsg = errorMsg;
	  }
	  
	  public boolean isSuccess() {
	    return isSuccess;
	  }
	  
	  public void setSuccess(boolean isSuccess) {
	    this.isSuccess = isSuccess;
	  }
	  
	  public String getErrorMsg() {
	    return errorMsg;
	  }
	  
	  public void setErrorMsg(String errorMsg) {
	    this.errorMsg = errorMsg;
	  }
}
