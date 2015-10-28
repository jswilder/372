//package edu.clemson.cs.cpsc3720.project.client;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigurationFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -4461916090538811893L;
	private JPanel mainPanel;
	private JPanel fieldPanel;
	private JPanel buttonPanel;
	private JTextField address;
	private JTextField port;
	private JLabel addressLabel;
	private JLabel portLabel;
	private ConfigurationSaveButton saveButton;
	private CancelButton cancelButton;
	private ProjectActionListener actionListener;

	public ConfigurationFrame() {

		//Initialize components
		mainPanel 								= new JPanel(new BorderLayout());
		fieldPanel 								= new JPanel(new GridBagLayout());
		buttonPanel 							= new JPanel(); //flow layout
		address 								= new JTextField(30);
		port									= new JTextField(30);
		addressLabel 							= new JLabel("IP Address:");
		portLabel								= new JLabel("Port:");
		actionListener 							= new ProjectActionListener();
		saveButton 								= new ConfigurationSaveButton(actionListener);
		cancelButton 							= new CancelButton(actionListener);

		//Add components to panels
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		fieldPanel.add(addressLabel, constraints);
		constraints.gridx = 1;
		constraints.weightx = 1;
		fieldPanel.add(address, constraints);
		constraints.weightx = 0;
		constraints.gridx = 0;
		constraints.gridy = 1;
		fieldPanel.add(portLabel, constraints);
		constraints.gridx = 1;
		constraints.weightx = 1;
		fieldPanel.add(port, constraints);

		//components are packed into panels and panels packed into frame.
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		mainPanel.add(fieldPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.setSize(500, 500);
		this.setVisible(true);

		//Deserialize configuration to set text fields for user.
		if(deserializeConfiguration()) {
			address.setText(DataManager.getServerAddress().getAddress());
			port.setText(String.valueOf(DataManager.getServerAddress().getPort()));
		}
	}

	//This will attempt to load the configuration information on file.
	private boolean deserializeConfiguration() {
		boolean retVal = false;
		String path = "/configuration.txt";
		ServerAddress address = null;

		try {
			FileInputStream inputFileStream = new FileInputStream(path);
			ObjectInputStream inputObjectStream = new ObjectInputStream(inputFileStream);
			address = (ServerAddress) inputObjectStream.readObject();
			inputObjectStream.close();
			inputFileStream.close();
		} catch (IOException e) {
			retVal = false;
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't find server address class");
		}

		if(address == null) {
			retVal = false;
		} else if (address.getAddress() != null && (address.getPort() == 80 || address.getPort() == 8080)) {
			retVal = true;
			DataManager.setServerAddress(address);
		} else {
			retVal = false;
		}

		return retVal;
	}

	public String getAddress() {
		return address.getText();
	}

	public int getPort() {
		return Integer.parseInt(port.getText());
	}
}
