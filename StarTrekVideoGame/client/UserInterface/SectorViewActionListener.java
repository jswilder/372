package StarTrekVideoGame.client.UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	// Action listener for the Sector View
public class SectorViewActionListener implements ActionListener {
	private int sx;
	private int sy;
	private static GameBoardFrame gameFrame;
	
	public SectorViewActionListener(int sx, int sy) {
		this.sx = sx;
		this.sy = sy;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SectorViewActionListener.gameFrame.setDisplayedGrid(sx, sy);
		SectorViewActionListener.gameFrame.setWarpSelected(false);
		SectorViewActionListener.gameFrame.updateGameAttributes(sx, sy);
		
	}
	
	public static void setGameFrame(GameBoardFrame frame) {
		gameFrame = frame;
	}
}
