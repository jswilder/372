//package edu.clemson.cs.cpsc3720.project.client;
//Author: Jeremy Wilder

import javax.swing.JButton;

public class RefreshButton extends JButton implements Command{
	/**
	 *
	 */
	private static final long serialVersionUID = -2885495170253431178L;

	public RefreshButton(IssueActionListener actionListener) {
		super("Refresh");
		this.addActionListener(actionListener);
	}

	@Override
	public void execute() {
		System.out.println("refreshin");
		FrameMediator.getIssueFrame().getIssueTableModel().fireTableDataChanged();
		FrameMediator.getIssueFrame().getIssueTable().repaint();
		/*FrameMediator.getIssueFrame().invalidate();
		FrameMediator.getIssueFrame().validate();
		FrameMediator.getIssueFrame().repaint();*/
	}
}
