//package edu.clemson.cs.cpsc3720.project.client;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -8039733846922915416L;
	private ProjectTable table;
	private ProjectTableModel tableModel;
	private ProjectActionListener tableActionListener;
	private ConfigMenuItem configMenuItem;
	private JScrollPane scrollPane;
	private JPanel mainPanel;
	private JMenu config;
	private JMenuBar menuBar;

	//default constructor method
	public MainFrame() {
		this.setLayout(new BorderLayout());
		FrameMediator.setMainFrame(this);
		FrameMediator.setCurrentFrame(this);

		Color backgroundColor = new Color(0,0,0);
		this.setTitle("Issue Tracker");
		this.setSize(600,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//window is placed in center of screen
		this.setLocationRelativeTo(null);

		//Initialize the table
		tableActionListener = new ProjectActionListener();
		menuBar 			= new JMenuBar();
		config 				= new JMenu("Configuration");
		configMenuItem		= new ConfigMenuItem("Config", tableActionListener);
		config.add(configMenuItem);
		menuBar.add(config);
		tableModel 			= new ProjectTableModel();
		table 				= new ProjectTable(tableModel, tableActionListener);
		table.setGridColor(backgroundColor);
		scrollPane			= new JScrollPane(table);
		table.setFillsViewportHeight(true);
		mainPanel 			= new JPanel(new BorderLayout());
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		//Add the panel with the table to the main panel.
		this.add(menuBar, BorderLayout.PAGE_START);
		this.add(mainPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}

	public ProjectTable getProjectTable() {
		return table;
	}

	public ProjectTableModel getProjectTableModel() {
		return tableModel;
	}
}
