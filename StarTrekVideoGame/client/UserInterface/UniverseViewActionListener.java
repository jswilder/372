package StarTrekVideoGame.client.UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	// Action listener for the Universe View
public class UniverseViewActionListener implements ActionListener {
	private int x;
	private int y;
	private static GameBoardFrame gameFrame;
	
	public UniverseViewActionListener(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		UniverseViewActionListener.gameFrame.setLastSelected(x, y);
		UniverseViewActionListener.gameFrame.setWarpSelected(true);
		UniverseViewActionListener.gameFrame.updateGridView(x, y);
	}
	
	public static void setGameFrame(GameBoardFrame frame) {
		gameFrame = frame;
	}

}
