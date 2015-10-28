import java.io.IOException;
import java.net.BindException;


public class MainDriver {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		Server server = new Server(8080);
		
		   try {
			   server.start();
		   } catch (BindException ioe) {
			   System.err.println("Couldn't start server: port is already in use (is another instance running?)");
			   System.exit(-1);
		   } catch (IOException ioe) {
			   System.err.println("Couldn't start server:\n" + ioe);
			   System.exit(-1);
		   }
		
		System.out.println("Server started, Hit enter to stop.\n");
		System.in.read();
		
		server.stop();
		System.out.println("Server Stopped.\n");
	}
}
