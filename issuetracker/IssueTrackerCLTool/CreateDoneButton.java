//package edu.clemson.cs.cpsc3720.project.client;

import javax.swing.JButton;
import java.util.Random;
import com.google.gson.Gson;

public class CreateDoneButton extends JButton implements Command{

	/**
	 *
	 */
	private static final long serialVersionUID = -8457063030732068073L;

	public CreateDoneButton(IssueActionListener actionListener) {
		super("Done");
		this.addActionListener(actionListener);
	}

	@Override
	public void execute() {
		Random generator = new Random();

		//Initialize the Issue
		String project = DataManager.getProject(FrameMediator.getMainFrame().getProjectTable().getSelectedRow());
		int id = generator.nextInt(10000);
		String title 		= FrameMediator.getCreateFrame().getTitleTextField().getText();
		String description 	= FrameMediator.getCreateFrame().getDescriptionTextField().getText();
		String status 		= FrameMediator.getCreateFrame().getStatus().getText();
		Issue newIssue = new Issue(project, id, title, description, status);

		//Create gson string
		String jsonString = new Gson().toJson(newIssue);

		//Create request string
		String request = "<CREATE><" + DataManager.getProject(FrameMediator.getMainFrame().getProjectTable().getSelectedRow()) + "><" + jsonString + ">";

		//Send request and receive response.
		String response = DataManager.sendRequest(request);
		if(response == null) {
			System.out.println("create response was null");
		} else if(response.contains("NOT")) {
			System.out.println("create response failed");
		} else {
			//Close frame and update mediator.
			FrameMediator.getCurrentFrame().dispose();
			FrameMediator.setLastOpenedFrame(FrameMediator.getCreateFrame());
			FrameMediator.setCurrentFrame(FrameMediator.getIssueFrame());
		}
		return;
	}
}
