//package edu.clemson.cs.cpsc3720.project.client;

import javax.swing.table.AbstractTableModel;

public class ProjectTableModel extends AbstractTableModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 8956813876215238344L;
	@Override
	public int getRowCount() {
		return 3;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return DataManager.getProject(rowIndex);
	}
	@Override
	public String getColumnName(int column) {
		return "Project";
	}
}
