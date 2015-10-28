package StarTrekVideoGame.client.Services;

import javax.naming.ServiceUnavailableException;

import StarTrekVideoGame.shared.HttpRequest;

public class ClientProxy {
    private static ClientProxy instance = null;
    private String serverIpAddress;
    private String serverPort;

    static {
        if(instance == null) {
            instance = new ClientProxy();
        }
    }

		// Constructor
    private ClientProxy() {
        serverIpAddress = null;
        serverPort = null;
    }

		// The functions that sends the message to the server
    public String sendMessage(String body) throws ServiceUnavailableException, IllegalArgumentException {
        String url = "http://" + serverIpAddress + ":" + serverPort + "/";
        HttpRequest req = HttpRequest.post(url).send(body);
        String response = req.body();
        return response;
    }

//==================Getters and Setters=========================================

    public static ClientProxy getInstance() {
        if(instance == null) {
            instance = new ClientProxy();
        }

        return instance;
    }

    public void setIpAddress(String ipAddress) {
        serverIpAddress = ipAddress;
    }
    
    public void setPort(String port) {
    	serverPort = port;
    }

	public String getIPAddress() {
		return serverIpAddress;
	}
	
	public String getPort() {
		return serverPort;
	}
}
