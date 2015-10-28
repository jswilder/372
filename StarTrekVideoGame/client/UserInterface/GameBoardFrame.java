package StarTrekVideoGame.client.UserInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.ServiceUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import StarTrekVideoGame.client.Model.GameServiceImpl;
import StarTrekVideoGame.client.Model.User;
import StarTrekVideoGame.shared.AlertLevelEnum;
import StarTrekVideoGame.shared.GameState;
import StarTrekVideoGame.shared.Player;
import StarTrekVideoGame.shared.Ship;

public class GameBoardFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel sectorPane;
	private JPanel buttonPanel;
    private JPanel backgroundPanel;
    private JPanel jPanel2;
    private JPanel playersPane;
    private JPanel universeArea;
    private JPanel universePane;
    private JPanel shipstatusPanel;
    private JPanel sectorArea;
    
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JScrollPane attacklogPane;
    
	private JTextArea attackLog;
    private JTextArea playersArea;
    private JTextArea shipstatusLog;
    
    private JLabel bottomDecoration;
    private JLabel attacklogLabel;
    private JLabel playerLabel;
    private JLabel sectorLabel;
    private JLabel titleLabel;
    private JLabel shipStatusLabel;
    private JLabel universeLabel;
    
    private JButton navigationButton;
    private JButton refreshButton;
    private JButton setAlertButton;
    private JButton torpedoButton;
    
    private JButton[][] sectorButtonGrid;
    private JButton[][] universeButtonGrid;
    
    private JTextField stardateField;
    
    private ArrayList<String> localAttackLog = new ArrayList<String>(20);

    private int displayedSectorX;
    private int displayedSectorY;
    private int displayedGridX;
    private int displayedGridY;
    private boolean warpSelected;


    /**
     * Creates new form GameBoardFrame
     */

    private GameState currentGame;
 
	Color enemyColor = new Color(178,34,34);
	Color friendlyColor = new Color(73,173,73);
	Color myColor = new Color(22,71,185);
	Color generalColor = new Color(30,27,27);

    public GameBoardFrame() {
    	UniverseViewActionListener.setGameFrame(this);
    	SectorViewActionListener.setGameFrame(this);
        initComponents();
        updateSectorView();
        updateGridView(1,1);
        setLocationRelativeTo(null);
		setTitle("Search and Destroy");
        setVisible(true);
        setResizable(false);
    }

	public GameBoardFrame(GameState state) {
    	this.currentGame = state;
    	UniverseViewActionListener.setGameFrame(this);
    	SectorViewActionListener.setGameFrame(this);
    	initComponents();
    	updatePlayerList();
    	updateSectorView();
    	updateGridView(1,1);
    	this.stardateField.setText("STARDATE " + currentGame.getStardate());
    	setLocationRelativeTo(null);
    	setTitle("Search and Destroy");
    	setVisible(true);
    	setResizable(false);
    }

    private void initComponents() {

        backgroundPanel = new JPanel();
        titleLabel = new JLabel();
        buttonPanel = new JPanel();
        refreshButton = new JButton();
        navigationButton = new JButton();
        setAlertButton = new JButton();
        torpedoButton = new JButton();
        shipstatusPanel = new JPanel();
        shipStatusLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        shipstatusLog = new JTextArea();
        jPanel2 = new JPanel();
        attacklogLabel = new JLabel();
        attacklogPane = new JScrollPane();
        attackLog = new JTextArea();
        universePane = new JPanel();
        universeLabel = new JLabel();
        jScrollPane4 = new JScrollPane();
        universeArea = new JPanel();
        universeButtonGrid = new JButton[8][8];
        sectorPane = new JPanel();
        sectorLabel = new JLabel();
        jScrollPane3 = new JScrollPane();
        sectorArea = new JPanel();
        sectorButtonGrid = new JButton[8][8];
        playersPane = new JPanel();
        playerLabel = new JLabel();
        jScrollPane2 = new JScrollPane();
        playersArea = new JTextArea();
        stardateField = new JTextField();
        bottomDecoration = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        backgroundPanel.setBackground(new Color(0, 0, 0));
        backgroundPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

				// Set Title Font
		try{
		    URL fontUrl = new URL("http://www.webpagepublicity.com/" +
		        "/free-fonts/a/Alexis%20Laser%20Italic.ttf");
		    try{
				Font myTitleFont = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
		    	myTitleFont = myTitleFont.deriveFont(Font.BOLD,28);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(myTitleFont);

				titleLabel.setFont(myTitleFont);
				bottomDecoration.setFont(myTitleFont); // NOI18N
			}catch( FontFormatException e ){
				System.out.println("#1 :: Default font used");
			}catch( IOException e1 ){
				System.out.println("#2 :: Default font used");
			}

		}catch(MalformedURLException e2){
			titleLabel.setFont(new Font("Franklin Gothic Medium", 1, 28)); // NOI18N
			bottomDecoration.setFont(new Font("Franklin Gothic Medium", 1, 28)); // NOI18N
		}

        titleLabel.setForeground(new Color(51, 204, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setText("--------------------------------------- SEARCH " + 
			"AND DESTROY ---------------------------------------");

        buttonPanel.setBackground(new Color(0, 0, 0));
        buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));

        refreshButton.setBackground(new Color(153, 153, 153));
        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                refreshButtonActionPerformed();
            }
        });

        navigationButton.setBackground(new Color(0, 204, 204));
        navigationButton.setText("NAVIGATION");
        navigationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                navigationButtonActionPerformed(evt);
                //This is here to refresh the game after you perform an action -Steven
                refreshButtonActionPerformed();
            }
        });

        setAlertButton.setBackground(new Color(0, 255, 0));
        setAlertButton.setText("SET ALERT");
        setAlertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setalertButtonActionPerformed(evt);
                //This is here to refresh the game after you perform an action -Steven
                refreshButtonActionPerformed();
            }
        });

        torpedoButton.setBackground(new Color(255, 51, 0));
        torpedoButton.setText("FIRE TORPEDO");
        torpedoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                torpedoButtonActionPerformed(evt);
                //This is here to refresh the game after you perform an action -Steven
                refreshButtonActionPerformed();
            }
        });

        GroupLayout buttonPanelLayout = new GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)

                    .addComponent(torpedoButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(navigationButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(refreshButton, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                    .addComponent(setAlertButton, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addContainerGap())
        );

        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(torpedoButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                    .addComponent(setAlertButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(navigationButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        shipstatusPanel.setBackground(new Color(0, 0, 0));
        shipstatusPanel.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));

        shipStatusLabel.setBackground(new Color(102, 102, 102));
        shipStatusLabel.setForeground(new Color(255, 255, 255));
        shipStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shipStatusLabel.setText("SHIP ATTRIBUTES");

        shipstatusLog.setEditable(false);
        shipstatusLog.setBackground(new Color(0, 0, 0));
        shipstatusLog.setColumns(20);
        shipstatusLog.setForeground(new Color(0, 255, 0));
        shipstatusLog.setRows(5);
        shipstatusLog.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));
        jScrollPane1.setViewportView(shipstatusLog);

        GroupLayout shipstatusPanelLayout = new GroupLayout(shipstatusPanel);
        shipstatusPanel.setLayout(shipstatusPanelLayout);
        shipstatusPanelLayout.setHorizontalGroup(
            shipstatusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(shipstatusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(shipstatusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(shipStatusLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        shipstatusPanelLayout.setVerticalGroup(
            shipstatusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(shipstatusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shipStatusLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel2.setBackground(new Color(0, 0, 0));
        jPanel2.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));

        attacklogLabel.setBackground(new Color(102, 102, 102));
        attacklogLabel.setForeground(new Color(255, 255, 255));
        attacklogLabel.setHorizontalAlignment(SwingConstants.CENTER);
        attacklogLabel.setText("ATTACK LOG");

        attackLog.setEditable(false);
        attackLog.setBackground(new Color(0, 0, 0));
        attackLog.setColumns(20);
        attackLog.setForeground(new Color(0, 255, 0));
        attackLog.setRows(20);
        attackLog.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));
        attacklogPane.setViewportView(attackLog);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(attacklogPane, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                    .addComponent(attacklogLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(attacklogLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attacklogPane, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        universePane.setBackground(new Color(0, 0, 0));
        universePane.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));

        universeLabel.setBackground(new Color(0, 0, 0));
        universeLabel.setForeground(new Color(255, 255, 255));
        universeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        universeLabel.setText("UNIVERSE VIEW");

        universeArea.setLayout(new GridLayout(8,8));
        universeArea.setBackground(new Color(0, 0, 0));
        universeArea.setFont(new Font("Monospaced", 1, 10)); // NOI18N
        universeArea.setForeground(new Color(0, 255, 0));
        universeArea.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));
        
        for(int x = 0; x < 8; x++) {
        	for(int y = 0; y < 8; y++) {
        		universeButtonGrid[y][x] = new JButton("0 0 0");
        		universeArea.add(universeButtonGrid[y][x]);
        		this.universeButtonGrid[y][x].addActionListener(new UniverseViewActionListener(y + 1, x + 1));
        	}
        }
        jScrollPane4.setViewportView(universeArea);

        GroupLayout universePaneLayout = new GroupLayout(universePane);
        universePane.setLayout(universePaneLayout);
        universePaneLayout.setHorizontalGroup(
            universePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, universePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(universePaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addComponent(universeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        //Setting the vertical height of the universePane.
        universePaneLayout.setVerticalGroup(
            universePaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(universePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(universeLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        sectorPane.setBackground(new Color(0, 0, 0));
        sectorPane.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));

        sectorLabel.setBackground(new Color(0, 0, 0));
        sectorLabel.setForeground(new Color(255, 255, 255));
        sectorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sectorLabel.setText("SECTOR VIEW");

        sectorArea.setLayout(new GridLayout(8, 8));
        sectorArea.setBackground(new Color(0, 0, 0));
        sectorArea.setFont(new Font("Monospaced", 1, 18)); // NOI18N
        sectorArea.setForeground(new Color(0, 255, 0));
        sectorArea.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));
        sectorArea.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        //Set button grid!
        for(int x = 0; x < 8; x++) {
        	for(int y = 0; y < 8; y++) {
        		this.sectorButtonGrid[y][x] = new JButton();
        		this.sectorButtonGrid[y][x].addActionListener(new SectorViewActionListener(y + 1, x + 1));;
        		sectorArea.add(sectorButtonGrid[y][x]);
        	}
        }
        jScrollPane3.setViewportView(sectorArea);

        GroupLayout sectorPaneLayout = new GroupLayout(sectorPane);
        sectorPane.setLayout(sectorPaneLayout);
        sectorPaneLayout.setHorizontalGroup(
            sectorPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, sectorPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sectorPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(sectorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sectorPaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        sectorPaneLayout.setVerticalGroup(
            sectorPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(sectorPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sectorLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );

        playersPane.setBackground(new Color(0, 0, 0));
        playersPane.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));

        playerLabel.setBackground(new Color(102, 102, 102));
        playerLabel.setForeground(new Color(255, 255, 255));
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel.setText("PLAYERS");

        playersArea.setEditable(false);
        playersArea.setBackground(new Color(0, 0, 0));
        playersArea.setColumns(20);
        playersArea.setForeground(new Color(255, 255, 255));
        playersArea.setLineWrap(true);
        playersArea.setRows(5);
        playersArea.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));
        jScrollPane2.setViewportView(playersArea);

        GroupLayout playersPaneLayout = new GroupLayout(playersPane);
        playersPane.setLayout(playersPaneLayout);
        playersPaneLayout.setHorizontalGroup(
            playersPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(playersPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(playersPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(playersPaneLayout.createSequentialGroup()
                        .addComponent(playerLabel, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        playersPaneLayout.setVerticalGroup(
            playersPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(playersPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playerLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        stardateField.setEditable(false);
        stardateField.setBackground(new Color(0, 0, 0));
        stardateField.setForeground(new Color(0, 255, 0));
        stardateField.setHorizontalAlignment(JTextField.CENTER);

			// Set all fonts, excluding title and bottom decoration

		try{
		    URL fontUrl = new URL("http://www.webpagepublicity.com/" +
		        "free-fonts/a/Alexis.ttf");
		    try{
				Font generalFont = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
		    	generalFont = generalFont.deriveFont(Font.BOLD,21);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(generalFont);

        		playerLabel.setFont(generalFont); // NOI18N
        		sectorLabel.setFont(generalFont); // NOI18N
       			attacklogLabel.setFont(generalFont); // NOI18N
        		shipStatusLabel.setFont(generalFont); // NOI18N
        		universeLabel.setFont(generalFont); // NOI18N

		    	generalFont = generalFont.deriveFont(Font.BOLD,15);
				GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge2.registerFont(generalFont);

        		stardateField.setFont(generalFont); // NOI18N

			}catch( FontFormatException e ){
				System.out.println("#1 :: Default font used");
			}catch( IOException e1 ){
				System.out.println("#2 :: Default font used");
			}

		}catch(MalformedURLException e2){
        	playerLabel.setFont(new Font("Showcard Gothic", 1, 15)); // NOI18N
        	sectorLabel.setFont(new Font("Showcard Gothic", 1, 15)); // NOI18N
        	attacklogLabel.setFont(new Font("Showcard Gothic", 1, 15)); // NOI18N
        	shipStatusLabel.setFont(new Font("Showcard Gothic", 1, 15)); // NOI18N
        	universeLabel.setFont(new Font("Showcard Gothic", 1, 15)); // NOI18N
        	stardateField.setFont(new Font("Showcard Gothic", 1, 15)); // NOI18N
		}

        bottomDecoration.setForeground(new Color(51, 204, 0));
        bottomDecoration.setHorizontalAlignment(SwingConstants.CENTER);
        bottomDecoration.setText("---------------------------------------------" 
		+ "------------------------------------------------------------------");

        GroupLayout backgroundPanelLayout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                        .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(shipstatusPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) 
                            .addComponent(universePane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                                .addComponent(sectorPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(stardateField)
                                    .addComponent(playersPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(bottomDecoration, GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE))
                .addContainerGap())
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addComponent(playersPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(11, 11, 11)
                                .addComponent(stardateField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                            .addComponent(sectorPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backgroundPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addComponent(universePane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(shipstatusPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomDecoration, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }    
    
    public void setDisplayedSectorCoord(int x, int y) {
    	this.displayedSectorX = x;
    	this.displayedSectorY = y;
    }
    
    public void setDisplayedGridCoord(int x, int y) {
    	this.displayedGridX = x;
    	this.displayedGridY = y;
    }
    
//=========================ACTION PERFORMED METHODS===========================
    
    private void refreshButtonActionPerformed() {
    	GameServiceImpl refreshService = new GameServiceImpl();
    	try {
			currentGame = refreshService.getGameState(currentGame.getGameId());
		} catch (ServiceUnavailableException e) {
			JOptionPane.showMessageDialog(this, "Service is unavailable");
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "IP Address or Port failed");
		}

			// Call all functions that repopulate text areas
    	updateSectorView();
    	updateGridView(displayedSectorX, displayedSectorY);
    	updateGameAttributes(displayedGridX, displayedGridY);
    	User.getInstance().setUserShip(currentGame);
    	stardateField.setText("STARDATE " + currentGame.getStardate());
    	
    	if(User.getInstance().getMe().getShip().getShields() < 0) {
    		JOptionPane.showMessageDialog(this, "DEFEATED");
    	}
    }

    private void torpedoButtonActionPerformed(ActionEvent evt) {
	GameServiceImpl impl = new GameServiceImpl();
		
				try {
					impl.attackTarget(currentGame.getGameId(), User.getInstance().getMe().getShip().getId(), displayedGridX, displayedGridY);

					Integer X = displayedGridX, Y = displayedGridY;
					String myAttack = " Stardate :: " + currentGame.getStardate() + ":: You attacked the ship at " + X.toString() + " , " + Y.toString() ;

						// If attack log not full, just add
					if( localAttackLog.size() < 20 ){
						localAttackLog.add( myAttack );
					}
						// If the attack log is full, remove the oldest one
					if( localAttackLog.size() == 20 ){
						localAttackLog.remove(20);
						localAttackLog.add( myAttack );
					}

					updateAttacklog();
					
				} catch ( ServiceUnavailableException e ){ 
					System.out.println("Attack unavailable ");
				}				
    }

    private void navigationButtonActionPerformed(ActionEvent evt) {
	
        GameServiceImpl impl = new GameServiceImpl();
        System.out.println("Navigation clicked");

	if(warpSelected) {
		impl.navigate(currentGame.getGameId(), User.getInstance().getMe().getShip().getId(), 1, displayedSectorX, displayedSectorY);
	}

	else {
		impl.navigate(currentGame.getGameId(), User.getInstance().getMe().getShip().getId(), 0, displayedGridX, displayedGridY);
	}

    }

    private void setalertButtonActionPerformed(ActionEvent evt) {
        AlertLevelEnum currentLevel = User.getInstance().getMe().getShip().getAlertLevel();
        GameServiceImpl impl = new GameServiceImpl();

        if(currentLevel == AlertLevelEnum.GREEN){
        	System.out.println("CLIENT: " + currentLevel.toString());
            impl.setAlert( User.getInstance().getMe().getShip(), 2, currentGame.getGameId(), AlertLevelEnum.YELLOW);
        } else if(currentLevel == AlertLevelEnum.YELLOW){
        	System.out.println("CLIENT: " + currentLevel.toString());
            impl.setAlert( User.getInstance().getMe().getShip(), 3, currentGame.getGameId(), AlertLevelEnum.RED);            
        } else {
        	System.out.println("CLIENT: " + currentLevel.toString());
            impl.setAlert( User.getInstance().getMe().getShip(), 1, currentGame.getGameId(), AlertLevelEnum.GREEN);
        }
        
       updateAlertButtonColor();
        
        return;
    }
    
    //====================UPDATE METHODS=======================================
    
    public void updateGameAttributes(int x, int y) {
    	
    	//reset text.
    	shipstatusLog.setText("");
    	if(currentGame.hasShipAt(displayedSectorX, displayedSectorY, x, y)) {
    		Ship selectedShip = currentGame.getShipAt(displayedSectorX, displayedSectorY, x, y);
    		
    		//set the text for the selected ship.
    		shipstatusLog.append("Type:\t" + selectedShip.getType().getName() + "\n");
    		shipstatusLog.append("Energy:\t" + selectedShip.getEnergy() + "/" + selectedShip.getType().getMaxEnergy() + "\n");
    		shipstatusLog.append("Shields:\t" + selectedShip.getShields() + "/" + selectedShip.getType().getMaxShields() + "\n");
    		shipstatusLog.append("Alert:\t" + selectedShip.getAlertLevel().toString() + "\n");    		
    		shipstatusLog.append("Missiles:\t" + selectedShip.getMissles() + "/" + selectedShip.getType().getMaxMissile());
    	}
	}

		// Function to repopulate the player list
    public void updatePlayerList() {
    	this.playersArea.setText("");
    	for(Player p : currentGame.getPlayers()) {
    		playersArea.append(p.getId() + "\n");
    	}
    }

		// Sets the ships alert status
    private void updateAlertButtonColor() {
    	AlertLevelEnum currentLevel = User.getInstance().getMe().getShip().getAlertLevel();
    	
    	if(currentLevel == AlertLevelEnum.GREEN) {
    		setAlertButton.setBackground(Color.GREEN);
    	} else if(currentLevel == AlertLevelEnum.YELLOW) {
    		setAlertButton.setBackground(Color.YELLOW);
    	} else {
    		setAlertButton.setBackground(Color.RED);
    	}
    }
    
    	// Updates the sector view with the appropriate buttons.
    public void updateGridView(int a, int b) {
    	for(int x = 0; x < 8; x++) {
    		for(int y = 0; y < 8; y++) {
    			
    			if(currentGame.hasPlayerAt(a, b, y + 1, x + 1)) {
    					// Update with player.
    				if(currentGame.getPlayerAt(a, b, y + 1, x + 1).getId().equals(User.getInstance().getMe().getId())) {

							// Check shield levels to determine if the ship is alive
						if( currentGame.getPlayerAt(a, b, y + 1, x + 1).getShip().getShields() > 0 ){
							sectorButtonGrid[y][x].setText("ME");
							sectorButtonGrid[y][x].setBackground(myColor);
						}
							// If the ship is dead, return the button to our default color
						else{
							sectorButtonGrid[y][x].setText("");
							sectorButtonGrid[y][x].setBackground(generalColor);						
						}
    				} else if(!currentGame.getPlayerAt(a, b, y + 1, x + 1).getEmpire().getEmpireId().equals(User.getInstance().getMe().getEmpire().getEmpireId())) {

							// Perform shield level check
						if( currentGame.getPlayerAt(a, b, y + 1, x + 1).getShip().getShields() > 0 ){
							sectorButtonGrid[y][x].setText("ES");
							sectorButtonGrid[y][x].setBackground(enemyColor);
						}
						else{
							sectorButtonGrid[y][x].setText("");
							sectorButtonGrid[y][x].setBackground(generalColor);						
						}
    				} else {
    					sectorButtonGrid[y][x].setText("FS");
    					sectorButtonGrid[y][x].setBackground(friendlyColor);
    				}
    			} else if(currentGame.hasShipAt(a, b, y + 1, x + 1)) {
					if(currentGame.getShipAt(a, b, y + 1, x + 1).getType().getEmpire().getEmpireId().equals(User.getInstance().getMe().getEmpire().getEmpireId())) {

							// Perform shield level check
						if( currentGame.getShipAt(a, b, y + 1, x + 1).getShields() > 0 ){
							sectorButtonGrid[y][x].setText("FS");
							sectorButtonGrid[y][x].setBackground(friendlyColor);
						}
						else{
							sectorButtonGrid[y][x].setText("");
							sectorButtonGrid[y][x].setBackground(generalColor);						
						}
					} else {
						if( currentGame.getShipAt(a, b, y + 1, x + 1).getShields() > 0 ){
						sectorButtonGrid[y][x].setText("ES");
						sectorButtonGrid[y][x].setBackground(enemyColor);
						}
						else{
							sectorButtonGrid[y][x].setText("");
							sectorButtonGrid[y][x].setBackground(generalColor);						
						}
					}
				} else if(currentGame.hasBaseAt(a, b, y + 1, x + 1)) {
    					//Update with base.
    				if(currentGame.getBaseAt(a, b, y + 1, x + 1).getEmpire().getEmpireId().equals(User.getInstance().getMe().getEmpire().getEmpireId())) {
    					sectorButtonGrid[y][x].setText("FB");
    					sectorButtonGrid[y][x].setBackground(friendlyColor);
    				} else {
    					sectorButtonGrid[y][x].setText("EB");
    					sectorButtonGrid[y][x].setBackground(enemyColor);
    				}
    			} else {
    					// Reset the text if nothing is found at that point
    				sectorButtonGrid[y][x].setText("");
    				sectorButtonGrid[y][x].setBackground(generalColor);
					sectorButtonGrid[y][x].setForeground(Color.WHITE);
    			}
    		}
    	}
    }

		// Function to repopulate the Sector View with new information
    public void updateSectorView() {
    	for(int px = 0; px < 8; px++) {
			for(int py = 0; py < 8; py++) {
				//Set the numbers for the universe grid.
				universeButtonGrid[py][px].setText(currentGame.getNumOfPlanets(py + 1, px + 1) + " " + currentGame.getNumOfEnemies(py + 1, px + 1) + " " + currentGame.getNumOfFriendlies(py + 1, px + 1));
				universeButtonGrid[py][px].setBackground(generalColor);
				universeButtonGrid[py][px].setForeground(Color.WHITE);
				
				for(int sx = 0; sx < 8; sx++) {
					for(int sy = 0; sy < 8; sy++) {
						// Determine if player is at the location
						if(currentGame.hasPlayerAt(py + 1, px + 1, sy + 1, sx + 1)) {
							// Determine if player is User
							
							if(currentGame.getPlayerAt(py + 1, px + 1, sy + 1, sx + 1).getId().equals(User.getInstance().getMe().getId())) {
								universeButtonGrid[py][px].setBackground(myColor);	
							}
						}
					}
				}
			}
    	}
    	
    	updateAlertButtonColor();
    }

		// Function to update the attackLog field 
    public void updateAttacklog(){

        currentGame.setAttackLog(localAttackLog);
        attackLog.setText("");

				// Repopulate attacklog field in reverse order
					// Newest attack printed first
        for( int i = localAttackLog.size(); i > 0; i-- ){
            attackLog.append( localAttackLog.get(i-1).toString() + "\n");
        }
    }

		// Keeps track of which sector has been selected
    public void setLastSelected(int x, int y) {
        this.displayedSectorX = x;
        this.displayedSectorY = y;
    }
    
		// Keeps track of which grid point within the sector has been selected
    public void setDisplayedGrid(int x, int y) {
    	this.displayedGridX = x;
    	this.displayedGridY = y;
    }

		// Determines how toy wish to navigate
    public void setWarpSelected(boolean t) {
    	warpSelected = t;
    }

    public static void main(String args[]) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameBoardFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GameBoardFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GameBoardFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GameBoardFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameBoardFrame();
            }
        });
    }
}
