//package edu.clemson.cs.cpsc3720.project.client;

import javax.swing.JTable;

public class IssueTable extends JTable {
	/**
	 *
	 */
	private static final long serialVersionUID = -9054283291323704175L;

	public IssueTable(IssueTableModel tableModel, IssueActionListener aListener) {
        super(tableModel);
        this.addMouseListener(aListener);
    }
}
