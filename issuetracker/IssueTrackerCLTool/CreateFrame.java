//package edu.clemson.cs.cpsc3720.project.client;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CreateFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = -7148264734714783095L;
	private JPanel mainPanel;
	private JPanel textFieldPanel;
	private JPanel buttonPanel;
	private JLabel titleLabel;
	private JLabel descriptionLabel;
	private JLabel statusLabel;
	private JTextField titleTextField;
	private JTextField descriptionTextField;
	private JTextField statusTextField;
	private IssueActionListener actionListener;
	private CreateDoneButton doneButton;
	private CancelButton cancelButton;

	public CreateFrame() {
		//Update FrameMediator
		FrameMediator.setCreateFrame(this);

		//Initialize panels
		mainPanel				= new JPanel(new BorderLayout());
		textFieldPanel			= new JPanel(new GridBagLayout());
		buttonPanel				= new JPanel();

		//Initialize Components
		titleLabel				= new JLabel("Title");
		descriptionLabel		= new JLabel("Description");
		statusLabel				= new JLabel("Status");
		titleTextField 			= new JTextField(30);
		descriptionTextField 	= new JTextField(30);
		statusTextField 		= new JTextField(30);
		actionListener			= new IssueActionListener();
		doneButton				= new CreateDoneButton(actionListener);
		cancelButton			= new CancelButton(actionListener);

		/*
		 * Going to add components to the panel. The panel is a GridBagLayout
		 * I will add components by changing the grid values for every component.
		 * As gridy increments the components are added more toward the southern
		 * side of the panel. As gridx increments the components will be added
		 * to the east side.
		 */
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill 	= GridBagConstraints.HORIZONTAL;
		constraints.anchor 	= GridBagConstraints.WEST;
		constraints.gridx 	= 0;
		constraints.gridy 	= 0;
		constraints.insets  = new Insets(5,5,5,5);
		textFieldPanel.add(titleLabel, constraints);
		constraints.gridy = 1;
		textFieldPanel.add(descriptionLabel, constraints);
		constraints.gridy = 2;
		textFieldPanel.add(statusLabel, constraints);
		constraints.gridx = 1;
		constraints.weightx = 1;
		textFieldPanel.add(statusTextField, constraints);
		constraints.gridy = 1;
		constraints.weightx = 1;
		textFieldPanel.add(descriptionTextField, constraints);
		constraints.gridy = 0;
		constraints.weightx = 1;
		textFieldPanel.add(titleTextField, constraints);

		//Going to add buttons to the button panel
		buttonPanel.add(doneButton);
		buttonPanel.add(cancelButton);

		this.setSize(500,200);
		this.setTitle("Create Issue");
		this.setFocusable(true);
		mainPanel.add(textFieldPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
		this.add(mainPanel);
	}

	public JTextField getTitleTextField() {
		return titleTextField;
	}

	public void setTitleTextField(JTextField titleTextField) {
		this.titleTextField = titleTextField;
	}

	public JTextField getDescriptionTextField() {
		return descriptionTextField;
	}

	public void setDescriptionTextField(JTextField descriptionTextField) {
		this.descriptionTextField = descriptionTextField;
	}

	public JTextField getStatus() {
		return statusTextField;
	}

	public void setStatus(JTextField status) {
		this.statusTextField = status;
	}
}
