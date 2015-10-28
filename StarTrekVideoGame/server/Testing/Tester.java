package StarTrekVideoGame.server.Testing;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import StarTrekVideoGame.server.Model.GameStateServiceImpl;
import StarTrekVideoGame.shared.Empire;
import StarTrekVideoGame.shared.GameState;

//import Service.GameStateService;


public class Tester {
	
	private GameStateServiceImpl service;
	
	public Tester () {
		service = new GameStateServiceImpl();
	}
	
	public GameState testFile() {
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get("/Users/Steven/git/cpsc3720project/StarTrekVideoGame/server/Testing/TrekUniverse.dat"));
		} catch (IOException e) {
			System.out.println("IO Error");
		}
		String gameString = null;
		try {
			gameString = new String(encoded, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("get your encoding correct!");
		}
		GameState retVal;
		if(!gameString.equals(null)) {
			 retVal = new GameState(gameString);
		} else {
			return null;
		}
		return retVal;
	}
	
	public static void main(String [] args) {
		Tester tester = new Tester();
		GameState returnedGame = tester.testFile();
		if(!returnedGame.equals(null)) {
			for(Empire p : returnedGame.getEmpires()) {
				System.out.println("ID: " + p.getEmpireId());
				System.out.println("Name: " + p.getName());
				System.out.println("Mission Type: " + p.getType());
//				System.out.println("Alert: " + p.getAlertLevel().toString());
//				System.out.println("Energy: " + p.getEnergy());
//				System.out.println("Shields: " + p.getShields());
//				System.out.println("Missles: " + p.getMissles());
//				System.out.println("SX: " + p.getSX());
//				System.out.println("SY: " + p.getSY());
//				System.out.println("PX: " + p.getPX());
//				System.out.println("PY: " + p.getPY());
				System.out.println("\n\n");
			}
		}
	}
}
