//package edu.clemson.cs.cpsc3720.project.client;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

//import edu.clemson.cs.cpsc3720.project.Issue;

public class IssueTableModel extends AbstractTableModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 313217821465283138L;

	@Override
	public int getRowCount() {
		String selectedProject;
		int selectedRow = FrameMediator.getMainFrame().getProjectTable().getSelectedRow();
		int selectedColumn = 1;
		selectedProject = (String) FrameMediator.getMainFrame().getProjectTableModel().getValueAt(selectedRow, selectedColumn);

		//add issues to new array list.
		ArrayList<Issue> filteredList = new ArrayList<Issue>();
		ArrayList<Issue> list = DataManager.getIssues();

		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getProject().equals(selectedProject)) {
				filteredList.add(list.get(i));
			}
		}
		return filteredList.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object retVal = new Object();
		String selectedProject;
		int selectedRow = FrameMediator.getMainFrame().getProjectTable().getSelectedRow();
		int selectedColumn = 1;
		selectedProject = (String) FrameMediator.getMainFrame().getProjectTableModel().getValueAt(selectedRow, selectedColumn);

		//add issues to new array list.
		ArrayList<Issue> filteredList = new ArrayList<Issue>();
		ArrayList<Issue> list = DataManager.getIssues();

		//filter the issues for the current project
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getProject().equals(selectedProject)) {
				filteredList.add(list.get(i));
			}
		}

		System.out.println("size: " + list.size());

		if(filteredList.size() == 0) {//If the filteredList is empty, then we know there are no items
			System.out.println("No items to add");
			return null;
		} else if (filteredList.size() <= rowIndex) { //If the table model reaches the end.
			System.out.println("Bad index");
			return null;
		} else {
			Issue issue = filteredList.get(rowIndex);
			switch(columnIndex) {
			case 0:
				retVal = issue.getProject();
				break;
			case 1:
				retVal = issue.getId();
				break;
			case 2:
				retVal = issue.getTitle();
				break;
			case 3:
				retVal = issue.getDescription();
				break;
			case 4:
				retVal = issue.getStatus();
				break;
			}
		}
		return retVal;
	}
}
