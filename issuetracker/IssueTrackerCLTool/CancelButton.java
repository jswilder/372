//package edu.clemson.cs.cpsc3720.project.client;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CancelButton extends JButton implements Command {
	/**
	 *
	 */
	private static final long serialVersionUID = 6424166512876182055L;

	public CancelButton(ActionListener actionListener) {
		super("Cancel");
		this.addActionListener(actionListener);
	}

	@Override
	public void execute() {
		FrameMediator.getCurrentFrame().dispose();
		//Need to reset variable of current frame that is focused
		FrameMediator.setCurrentFrame(FrameMediator.getLastOpenedFrame());
	}
}
