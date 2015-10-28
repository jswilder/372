//package edu.clemson.cs.cpsc3720.project.client;

import javax.swing.JButton;
import com.google.gson.Gson;

public class EditDoneButton extends JButton implements Command{

	/**
	 *
	 */
	private static final long serialVersionUID = 1050302140609474609L;
	public EditDoneButton(IssueActionListener actionListener) {
		super("Done");
		this.addActionListener(actionListener);
	}
	@Override
	public void execute() {
		//Initialize the string
		int selectedRow = FrameMediator.getMainFrame().getProjectTable().getSelectedRow();
		String project 	= DataManager.getProject(selectedRow);
		selectedRow 	= FrameMediator.getIssueFrame().getIssueTable().getSelectedRow();
		int editId 		= DataManager.getIssues().get(selectedRow).getId();

		Issue issue = DataManager.getIssueById(editId);
		issue.setTitle(FrameMediator.getEditFrame().getTitleTextField().getText());
		issue.setDescription(FrameMediator.getEditFrame().getDescriptionTextField().getText());
		issue.setStatus(FrameMediator.getEditFrame().getStatus().getText());

		String jsonObject = new Gson().toJson(issue);
		String request = "<EDIT><" + project + "><" + editId + "><" + jsonObject + ">";

		//get response from server and parse
		String response = DataManager.sendRequest(request);
		if (response == null) {
			System.out.println("response was null");
			return;
		} else if (response.contains("NOT")) {
			System.out.println("edit failed");
			return;
		} else {
			FrameMediator.getEditFrame().dispose();
			FrameMediator.setLastOpenedFrame(FrameMediator.getEditFrame());
			FrameMediator.setCurrentFrame(FrameMediator.getIssueFrame());
		}


	}
}
