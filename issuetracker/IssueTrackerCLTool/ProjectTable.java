//package edu.clemson.cs.cpsc3720.project.client;

import javax.swing.JTable;

public class ProjectTable extends JTable {
	/**
	 *
	 */
	private static final long serialVersionUID = 8203684276372760989L;

	public ProjectTable(ProjectTableModel tableModel, ProjectActionListener mouseListener) {
		super(tableModel);
		this.addMouseListener(mouseListener);
	}
}
