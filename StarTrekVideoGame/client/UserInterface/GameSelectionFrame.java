package StarTrekVideoGame.client.UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.ServiceUnavailableException;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import StarTrekVideoGame.client.Model.GameServiceImpl;
import StarTrekVideoGame.client.Model.User;
import StarTrekVideoGame.shared.GameState;

public class GameSelectionFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton disconnectButton;
    private Box.Filler filler1;
    //private DefaultComboBoxModel gameListModel;
    private JComboBox<String> gameList;
    private JPanel jPanel1;
    private JButton restoreButton;
    private JButton refreshButton;
    private JButton selectorButton;
    private JButton rejoinButton;
    private JLabel titleLabel;
    private GameServiceImpl gameService;
    private GameState dummyGame;
    private ArrayList<GameState> allGames;

    public GameSelectionFrame() {
    	dummyGame = new GameState();
        initComponents();
        setLocationRelativeTo(null);
		setTitle("Search and Destroy");
        setVisible(true);
        setResizable(false);
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        titleLabel = new JLabel();
        selectorButton = new JButton();
        disconnectButton = new JButton();
        refreshButton = new JButton();
        rejoinButton = new JButton();
        gameList = new JComboBox<String>();
        restoreButton = new JButton();
        filler1 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
        gameService = new GameServiceImpl();
        //games = gameService.getGameList();
        

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(0, 0, 0));

        titleLabel.setFont(new Font("Showcard Gothic", 0, 14)); // NOI18N
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setText("GAME LIST");

        selectorButton.setBackground(new Color(0, 255, 0));
        selectorButton.setText("CONTINUE");
        selectorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                selectorButtonActionPerformed(evt);
            }
        });

        disconnectButton.setBackground(new Color(255, 0, 51));
        disconnectButton.setText("DISCONNECT");
        disconnectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                disconnectButtonActionPerformed(evt);
            }
        });
        
        refreshButton.setBackground(Color.BLUE);
        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		refreshButtonActionPerformed();
        	}
        });
        
        rejoinButton.setBackground(Color.BLUE);
        rejoinButton.setText("REJOIN");
        rejoinButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		rejoinButtonActionPerformed();
        	}
        });

        restoreButton.setBackground(new Color(255, 204, 0));
        restoreButton.setText("RESTORE");
        restoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                restoreButtonActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(gameList, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rejoinButton, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(selectorButton, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39))
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(restoreButton)
                                .addGap(35, 35, 35)
                                .addComponent(disconnectButton)))))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addGap(11, 11, 11)
                .addComponent(gameList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(rejoinButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(selectorButton)
                            .addComponent(disconnectButton)
                            .addComponent(restoreButton)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(filler1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

		// continue button was pushed
    private void selectorButtonActionPerformed(ActionEvent evt) {
    	User.getInstance().setUserShip(dummyGame);
        GameBoardFrame gameFrame = new GameBoardFrame(dummyGame);
        gameFrame.setVisible(true);
        dispose();
    }

    private void disconnectButtonActionPerformed(ActionEvent evt) {

        dispose();
    }

		// resfresh button was pushed
    private void refreshButtonActionPerformed() {
    	GameServiceImpl service = new GameServiceImpl();
    	
    	allGames = service.getGameList();
    	
    		//Clear the items
    	gameList.removeAll();
    		//Add the title of the list to the gamelist
    	if(allGames != null) {
    		for(GameState g : allGames) {
        		gameList.addItem(g.getTitle());
        	}
    	}
    }

		// rejoin button was pushed    
    private void rejoinButtonActionPerformed() {
    	String selectedGame = null;
    	
    	if(gameList.getSelectedItem() != null) {
    		selectedGame = (String)gameList.getSelectedItem();
    	} else {
    		return;
    	}
    	
    	for (GameState g : allGames) {
    		if(g.getTitle().equals(selectedGame)) {
    			User.getInstance().setUserShip(g);
    			GameBoardFrame board = new GameBoardFrame(g);
    		}
    	}
    }

		// restore button was pushed
    private void restoreButtonActionPerformed(ActionEvent evt) {
        final JFileChooser dataFileChooser = new JFileChooser();
        dataFileChooser.showOpenDialog(this);
        File restoreGameFile;
        restoreGameFile = dataFileChooser.getSelectedFile();
        
        FileInputStream fis;
        String string = null;
		try {
			fis = new FileInputStream(restoreGameFile);
			byte[] data = new byte[(int) restoreGameFile.length()];
	        fis.read(data);
	        fis.close();
	        string = new String(data, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GameState state = null;
		
		try {
			state = gameService.restoreGame(string);
		} catch (ServiceUnavailableException e) {
			JOptionPane.showMessageDialog(this, "Service is unavailable");
		}
		
		User.getInstance().setUserShip(state);

			// Open the game board, remove previous frame		
		GameBoardFrame frame = new GameBoardFrame(state);
		dispose();
    }


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional)SELECTOR        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         /* For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameSelectionFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GameSelectionFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GameSelectionFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GameSelectionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameSelectionFrame();
            }
        });
    }
}
