package StarTrekVideoGame.client.UserInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.EventQueue;

	// **This file is not used for phase 3**

public class ShipSelectorFrame extends JFrame {

    private JButton cancelButton;
    private JLabel empireLabel;
    private JComboBox empireSelector;
    private JPanel jPanel1;
    private JButton joinButton;
    private JTextField selectionField;
    private JLabel shipLabel;
    private JComboBox shipSelector;


		// Creates the ship selector frame
    public ShipSelectorFrame() {
        initComponents();
        setVisible(true);
        setResizable(false);
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1         = new JPanel();
        empireLabel     = new JLabel();
        shipLabel       = new JLabel();
        empireSelector  = new JComboBox();
        shipSelector    = new JComboBox();
        joinButton      = new JButton();
        cancelButton    = new JButton();
        selectionField  = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(0, 0, 0));

        empireLabel.setForeground(new Color(255, 255, 255));
        empireLabel.setText("Empire");

        shipLabel.setForeground(new Color(255, 255, 255));
        shipLabel.setText("Ship");

        empireSelector.setModel(new DefaultComboBoxModel(new String[] { "Klingon", "Starfleet" }));
        shipSelector.setModel(new DefaultComboBoxModel(new String[] { "Explorer", "War" }));

        joinButton.setBackground(new Color(0, 255, 0));
        joinButton.setText("JOIN GAME");
        joinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                joinButtonActionPerformed(evt);
            }
        });

        cancelButton.setBackground(new Color(255, 0, 51));
        cancelButton.setText("CANCEL");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(joinButton)
                        .addGap(77, 77, 77)
                        .addComponent(cancelButton))
                    .addComponent(selectionField, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(empireLabel)
                        .addComponent(shipLabel))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(empireSelector, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(shipSelector, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addComponent(selectionField, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(joinButton)
                    .addComponent(cancelButton))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(empireLabel)
                        .addComponent(empireSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(shipLabel)
                        .addComponent(shipSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(120, Short.MAX_VALUE)))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void joinButtonActionPerformed(ActionEvent evt) {
        selectionField.setText("A " + empireSelector.getSelectedItem().toString() +
                " " + shipSelector.getSelectedItem().toString() + " ship has been selected");
        GameBoardFrame gameFrame = new GameBoardFrame();
        gameFrame.setVisible(true);
        dispose();
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    public static void main(String args[]) {
        //set nimbus look and feel
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShipSelectorFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ShipSelectorFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ShipSelectorFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ShipSelectorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        	/* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShipSelectorFrame();
            }
        });
    }
}
