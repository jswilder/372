package StarTrekVideoGame.client.UserInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import StarTrekVideoGame.client.Model.User;
import StarTrekVideoGame.client.Services.ClientProxy;

public class ConnectFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton SUBMIT;
    private JTextField ipField;
    private JLabel ipLabel;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JTextField passwordField;
    private JLabel passwordLabel;
    private JTextField portField;
    private JLabel portLabel;
    private JLabel title;
    private JTextField usernameField;
    private JLabel usernameLabel;

    public ConnectFrame() {
        initComponents();
        setLocationRelativeTo(null);
		setTitle("Search and Destroy");
        setVisible(true);
        setResizable(false);
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        SUBMIT = new JButton();
        title = new JLabel();
        ipLabel = new JLabel();
        portLabel = new JLabel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        ipField = new JTextField("localhost");
        portField = new JTextField("8080");
        usernameField = new JTextField();
        passwordField = new JTextField();

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(204, 255, 204));

        jPanel2.setBackground(new Color(0, 0, 0));

        SUBMIT.setBackground(new Color(0, 255, 0));
        SUBMIT.setText("SUBMIT");
        SUBMIT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SUBMITActionPerformed(evt);
            }
        });

        title.setFont(new Font("Showcard Gothic", 0, 14));
        title.setForeground(new Color(255, 255, 255));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("WELCOME to SEARCH AND DESTROY!");

        ipLabel.setFont(new Font("Showcard Gothic", 0, 12));
        ipLabel.setForeground(new Color(255, 255, 255));
        ipLabel.setText("IP ADDRESS");

        portLabel.setFont(new Font("Showcard Gothic", 0, 12));
        portLabel.setForeground(new Color(255, 255, 255));
        portLabel.setText("PORT NUM");

        usernameLabel.setFont(new Font("Showcard Gothic", 0, 12));
        usernameLabel.setForeground(new Color(255, 255, 255));
        usernameLabel.setText("USERNAME");

        passwordLabel.setFont(new Font("Showcard Gothic", 0, 12));
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordLabel.setText("PASSWORD");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(SUBMIT, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel)
                            .addComponent(passwordLabel)
                            .addComponent(portLabel)
                            .addComponent(ipLabel))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(ipField)
                            .addComponent(portField)
                            .addComponent(usernameField)
                            .addComponent(passwordField))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(ipLabel)
                    .addComponent(ipField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(portField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(SUBMIT)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void SUBMITActionPerformed(ActionEvent evt) {
    	//Error handling for empty boxes
        if( !((ipField.getText().equals("")) || (ipField.getText().equals("**REQUIRED FIELD**"))
                || (portField.getText().equals("")) || (portField.getText().equals("**REQUIRED FIELD**"))
                || (usernameField.getText().equals("")) || (usernameField.getText().equals("**REQUIRED FIELD**"))
                || (passwordField.getText().equals("")) || (passwordField.getText().equals("**REQUIRED FIELD**")))
                && passwordField.getText().equals("p") /* && usernameField.getText().equals("username")*/ ){
            GameSelectionFrame serverList = new GameSelectionFrame();
            //Set the IP and Port in the client proxy fields.
            ClientProxy.getInstance().setIpAddress(ipField.getText());
            ClientProxy.getInstance().setPort(portField.getText());
            User.initUser(usernameField.getText(), passwordField.getText());
            serverList.setVisible(true);
            dispose();
        }
        else{
            if(ipField.getText().equals("")){
                ipField.setText("**REQUIRED FIELD**");
            }
            if(portField.getText().equals("")){
                portField.setText("**REQUIRED FIELD**");
            }
            if(usernameField.getText().equals("")){
                usernameField.setText("**REQUIRED FIELD**");
            }
            if(passwordField.getText().equals("")){
                passwordField.setText("**REQUIRED FIELD**");
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //Set nimbus look and feel
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ConnectFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConnectFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ConnectFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectFrame();
            }
        });
    }

}
