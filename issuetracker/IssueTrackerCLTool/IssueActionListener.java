//package edu.clemson.cs.cpsc3720.project.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class IssueActionListener implements ActionListener, MouseListener {

	//Enable buttons when a cell is clicked.
	@Override
	public void mouseClicked(MouseEvent e) {
		FrameMediator.getIssueFrame().getEditButton().setEnabled(true);
		FrameMediator.getIssueFrame().getDeleteButton().setEnabled(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Command comm = (Command) e.getSource();
		comm.execute();
	}
}
