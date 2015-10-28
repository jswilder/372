//package edu.clemson.cs.cpsc3720.project.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class IssueFrame extends JFrame{
	/**
	 *
	 */
	private static final long serialVersionUID = -3223832412825060877L;
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private JPanel tablePanel;
	private JScrollPane scrollPane;
	private IssueTable table;
    private IssueTableModel tableModel;
    private CreateButton createButton;
    private EditButton editButton;
    private DeleteButton deleteButton;
	private RefreshButton refreshButton;
    private IssueActionListener actionListener;
	private String project;

    public IssueFrame() {
    	FrameMediator.setIssueFrame(this);
    	this.setSize(600, 400);
		this.setLayout(new BorderLayout());

    	//Initialize Panels.
    	mainPanel 						= new JPanel(new BorderLayout());
    	tablePanel 						= new JPanel(new BorderLayout());
    	buttonPanel 					= new JPanel(); //Flow layout

    	//Initialize Buttons
    	actionListener 					= new IssueActionListener();
    	createButton 					= new CreateButton(actionListener);
    	editButton 						= new EditButton(actionListener);
    	deleteButton 					= new DeleteButton(actionListener);
		refreshButton					= new RefreshButton(actionListener);

    	/*Set buttons visiblity to false. They will be enabled
    	 *When a cell is selected.
    	 */
    	editButton.setEnabled(false);
    	deleteButton.setEnabled(false);

    	//Add buttons to button panel. Order of buttons is by order added.
    	buttonPanel.add(createButton);
    	buttonPanel.add(editButton);
    	buttonPanel.add(deleteButton);
		buttonPanel.add(refreshButton);

    	//Initialize and set table.
		DataManager.addIssue(new Issue("Project 1", 5, "a", "b", "c"));
        tableModel 	= new IssueTableModel();
        table       = new IssueTable(tableModel, actionListener);
		tablePanel.add(table, BorderLayout.CENTER);
		//scrollPane 	= new JScrollPane(table);
        //scrollPane.add(table);

        //Add panels together
        //mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.PAGE_END);
    }

    public IssueTable getIssueTable () {
    	return table;
    }

    public IssueTableModel getIssueTableModel() {
    	return tableModel;
    }

    public EditButton getEditButton() {
    	return editButton;
    }

    public DeleteButton getDeleteButton() {
    	return deleteButton;
    }
}
