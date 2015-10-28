//package edu.clemson.cs.cpsc3720.project.client;

import javax.swing.JFrame;

public class FrameMediator {
	private static MainFrame mainFrame;
	private static IssueFrame issueFrame;
	private static EditFrame editFrame;
	private static CreateFrame createFrame;
	private static ConfigurationFrame configurationFrame;
	private static JFrame currentlyOpenFrame;
	private static JFrame lastOpenedFrame;

	public static MainFrame getMainFrame() {
		return mainFrame;
	}
	public static void setMainFrame(MainFrame mainFrame) {
		FrameMediator.mainFrame = mainFrame;
	}
	public static IssueFrame getIssueFrame() {
		return issueFrame;
	}
	public static void setIssueFrame(IssueFrame issueFrame) {
		FrameMediator.issueFrame = issueFrame;
	}

	public static void setEditFrame(EditFrame editFrame) {
		FrameMediator.editFrame = editFrame;
	}

	public static ConfigurationFrame getConfigurationFrame() {
		return configurationFrame;
	}

	public static EditFrame getEditFrame() {
		return editFrame;
	}

	public static void setCreateFrame(CreateFrame createFrame) {
		FrameMediator.createFrame = createFrame;
	}

	public static CreateFrame getCreateFrame() {
		return createFrame;
	}

	public static JFrame getCurrentFrame () {
		return currentlyOpenFrame;
	}
	public static void setCurrentFrame(JFrame currentFrame) {
		currentlyOpenFrame = currentFrame;
	}
	public static void setConfigurationFrame(ConfigurationFrame frame) {
		FrameMediator.configurationFrame = frame;
	}

	public static void setLastOpenedFrame(JFrame frame) {
		FrameMediator.lastOpenedFrame = frame;
	}

	public static JFrame getLastOpenedFrame() {
		return lastOpenedFrame;
	}
}
