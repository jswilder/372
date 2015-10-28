//package edu.clemson.cs.cpsc3720.project.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;

public class ConfigurationSaveButton extends JButton implements Command {

	/**
	 *
	 */
	private static final long serialVersionUID = 5439310540760629761L;

	public ConfigurationSaveButton(ProjectActionListener actionListener) {
		super("Save");
		this.addActionListener(actionListener);
	}

	@Override
	public void execute() {
		//Get information for error checking.
		String path 	= "/configuration.txt";
		String address 	= FrameMediator.getConfigurationFrame().getAddress();
		int port 		= FrameMediator.getConfigurationFrame().getPort();

		//Error handling for invalid inputs.
		System.out.println("Address: " + address + "\nPort: " + port);
		if((port != 80 && port != 8080 && port != 8081) || address == null) {
			JDialog errorBoxMessage = new JDialog(FrameMediator.getConfigurationFrame(), "INVALID INPUT", true);
			errorBoxMessage.setSize(400, 400);
			errorBoxMessage.setVisible(true);
			return;
		}

		DataManager.setServerAddress(new ServerAddress(address, port));

		//Serialize the configuration object.
		try {
			FileOutputStream fileInput = new FileOutputStream(path);
			ObjectOutputStream objectinput = new ObjectOutputStream(fileInput);
			objectinput.writeObject(DataManager.getServerAddress());
			objectinput.close();
			fileInput.close();
		} catch (IOException e) {
			System.out.println("IO EXCEPTION");
			e.printStackTrace();
		}
		FrameMediator.getConfigurationFrame().dispose();
	}
}
