//package edu.clemson.cs.cpsc3720.project.client;

import javax.swing.JButton;

public class CreateButton extends JButton implements Command{
	/**
	 *
	 */
	private static final long serialVersionUID = -2885495170253431178L;

	public CreateButton(IssueActionListener actionListener) {
		super("Create");
		this.addActionListener(actionListener);
	}

	@Override
	public void execute() {
		CreateFrame frame = new CreateFrame();
		FrameMediator.setCurrentFrame(frame);
		FrameMediator.setCreateFrame(frame);
		FrameMediator.setLastOpenedFrame(FrameMediator.getIssueFrame());
		frame.setVisible(true);
	}
}
