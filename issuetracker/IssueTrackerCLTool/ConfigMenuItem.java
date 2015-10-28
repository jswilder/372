//package edu.clemson.cs.cpsc3720.project.client;

import javax.swing.JMenuItem;

public class ConfigMenuItem extends JMenuItem implements Command {

	/**
	 *
	 */
	private static final long serialVersionUID = -5913170716993249421L;

	public ConfigMenuItem(String name, ProjectActionListener actionListener) {
		super(name);
		this.addActionListener(actionListener);
	}

	@Override
	public void execute() {
		ConfigurationFrame frame = new ConfigurationFrame();
		FrameMediator.setConfigurationFrame(frame);
		FrameMediator.setCurrentFrame(frame);
		FrameMediator.setLastOpenedFrame(FrameMediator.getMainFrame());
		frame.setVisible(true);
	}
}
