
package StarTrekVideoGame.server.AdminInterface;

import java.io.IOException;
import java.net.BindException;

import StarTrekVideoGame.server.Model.GameStateServer;

public class MainDriver {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Throwable {
	
	    GameStateServer server = new GameStateServer(8080);
		
	    try {
	      server.start();
	    } catch (BindException ioe) {
	      System.err
	          .println("Couldn't start server: port is already in use (is another instance running?)");
	      System.exit(-1);
	    } catch (IOException ioe) {
	      System.err.println("Couldn't start server:\n" + ioe);
	      System.exit(-1);
	    }
	    
	    System.out.println("Server started, Hit Enter to stop.\n");

	    System.in.read();

	    //server.stop();
	    System.out.println("Server stopped.\n");
	  }
		
		
		/*GameStateDataBase data = new GameStateDataBase();
		Receiver rec = new Receiver();
		String ID;
		
		//for (;;) {
			//Object o = rec.listen();
			Object o = "asdf";
			// parse to get gameState ID 
			
			if (o.getClass() == String.class) {
				System.out.println("string\n");
				data.getGameState(ID).getAttackLog().add(o);
			}
		
			else if (o.getClass() == GameState.class) {
				System.out.println("gameState\n");
				data.add(o);
			}
		// end of for loop}
		*/
}
