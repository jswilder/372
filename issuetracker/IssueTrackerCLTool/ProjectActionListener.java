	//package edu.clemson.cs.cpsc3720.project.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ProjectActionListener implements MouseListener, ActionListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		IssueFrame issueFrame = new IssueFrame();
		int selectedRow = FrameMediator.getMainFrame().getProjectTable().getSelectedRow();
		String projectName = DataManager.getProject(selectedRow);
		String request = "<REQUEST><" + projectName + ">";
		String response = DataManager.sendRequest(request);

		if(response == null) {
			System.out.println("list request was null");
		} else if(response.contains("NOT")) {
			System.out.println("list request failed");
		} if(response != "[]") {
			System.out.println("server side broke foo: " + response);
			Type type = new TypeToken<ArrayList<Issue>>(){}.getType();
			ArrayList<Issue> list = new Gson().fromJson(response, type);
			DataManager.setIssues(list);
		}

		issueFrame.setVisible(true);
		return;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//nothing
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//nothing
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//nothing
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Command comm = (Command) e.getSource();
		comm.execute();
	}

}
