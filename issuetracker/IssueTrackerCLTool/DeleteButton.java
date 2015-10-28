//package edu.clemson.cs.cpsc3720.project.client;

import javax.swing.JButton;
import com.google.gson.Gson;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;

public class DeleteButton extends JButton implements Command{

	/**
	 *
	 */
	private static final long serialVersionUID = 5076957579306758154L;

	public DeleteButton(IssueActionListener actionListener) {
		super("Delete");
		this.addActionListener(actionListener);
	}
	@Override
	public void execute() {
		//Get indexes for creating the string
		String project = DataManager.getProject(FrameMediator.getMainFrame().getProjectTable().getSelectedRow());
		int selectedRow = FrameMediator.getIssueFrame().getIssueTable().getSelectedRow();
		int deleteId = DataManager.getIssues().get(selectedRow).getId();

		//create the string for sending a request
		String request = "<DELETE><" + project + "><" + deleteId + ">";
		//send request and get response
		String response = DataManager.sendRequest(request);

		//error handling
		if(response.contains("NOT")) {
			System.out.println("Didn't delete the issue");
			return;
		}

		request = "<REQUEST><" + project + ">";

		//get an updated list for datamanager.
		response = DataManager.sendRequest(request);

		//get arraylist from response list.
		ArrayList<Issue> responseList = new Gson().fromJson(response, new TypeToken<ArrayList<Issue>>(){}.getType());
		DataManager.setIssues(responseList);
	}

}
