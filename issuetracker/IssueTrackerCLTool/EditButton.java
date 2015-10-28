//package edu.clemson.cs.cpsc3720.project.client;

import javax.swing.JButton;

public class EditButton extends JButton implements Command {

	/**
	 *
	 */
	private static final long serialVersionUID = 7468935571432181253L;

	public EditButton(IssueActionListener actionListener) {
		super("Edit");
		this.addActionListener(actionListener);
	}

	@Override
	public void execute() {
		EditFrame frame = new EditFrame();
		FrameMediator.setCurrentFrame(frame);
		FrameMediator.setLastOpenedFrame(FrameMediator.getIssueFrame());
		frame.setVisible(true);
	}
}
