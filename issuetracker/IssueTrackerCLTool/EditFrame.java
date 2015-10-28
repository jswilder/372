//package edu.clemson.cs.cpsc3720.project.client;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 673629843183309789L;
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
	private EditDoneButton doneButton;
	private CancelButton cancelButton;

	public EditFrame() {
		//Update mediator
		FrameMediator.setEditFrame(this);

		//Initialize panels
		mainPanel				= new JPanel(new BorderLayout());
		textFieldPanel			= new JPanel(new GridBagLayout());
		buttonPanel				= new JPanel();

		//Get selected cell information.
		int row 			= (FrameMediator.getIssueFrame().getIssueTable().getSelectedRow());
		String title 		= (String) FrameMediator.getIssueFrame().getIssueTableModel().getValueAt(row, 2);
		String description 	= (String) FrameMediator.getIssueFrame().getIssueTableModel().getValueAt(row, 3);
		String status 		= (String) FrameMediator.getIssueFrame().getIssueTableModel().getValueAt(row, 4);

		//Initialize components
		titleLabel				= new JLabel("Title");
		descriptionLabel		= new JLabel("Description");
		statusLabel				= new JLabel("Status");
		actionListener			= new IssueActionListener();
		titleTextField 			= new JTextField(title);
		descriptionTextField 	= new JTextField(description);
		statusTextField 		= new JTextField(status);
		doneButton 				= new EditDoneButton(actionListener);
		cancelButton			= new CancelButton(actionListener);

		//Add components to panels
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

		//Add buttons to panels
		buttonPanel.add(doneButton);
		buttonPanel.add(cancelButton);

		this.setSize(500, 200);
		this.setTitle("Edit Issue");
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
