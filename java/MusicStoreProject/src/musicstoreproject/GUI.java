package musicstoreproject;

/**
 *
 * @author Leo Dubovyi, Valerii Doroshenko
 * Vanier College
 * System Analysis
 * Course Project
 * @Project  MusicStore @Class GUI
 */
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;

public class GUI implements ActionListener {

    private JFrame wFrame;
    private JMenuBar menuMB;
    private JMenuItem searchMI, exitMI, loginMI, managerOrdersMI, userOrderMI;
    private JMenu ordersM, songsM, loginM;

    public GUI() {
        this.init();
    }

    private void init() {
        try {
            DB.db = new DB();

        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        WindowsListener closeOp = new WindowsListener() {

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ResultSet res = DB.db.userSelect('Y');
                    while (res.next()) {
                        DB.db.userSetSession(res.getString(2), false);
                    }
                    DB.db.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        this.wFrame = new JFrame();

        this.menuMB = new JMenuBar();
        this.songsM = new JMenu("Songs and Albums");
        this.ordersM = new JMenu("Orders");
        this.loginM = new JMenu("My Store");
        this.loginMI = new JMenuItem("Login");
        this.managerOrdersMI = new JMenuItem("Manager Order");
        this.userOrderMI = new JMenuItem("User Order");
        this.searchMI = new JMenuItem("Search song");
        this.exitMI = new JMenuItem("Exit");

        this.menuMB.add(this.songsM);
        this.menuMB.add(this.ordersM);
        this.menuMB.add(this.loginM);

        this.songsM.add(this.searchMI);
        this.songsM.add(this.exitMI);

        this.loginM.add(this.loginMI);

        this.ordersM.add(this.managerOrdersMI);
        this.ordersM.add(this.userOrderMI);

        this.wFrame.setJMenuBar(this.menuMB);
        this.exitMI.addActionListener(this);
        this.loginMI.addActionListener(this);
        this.searchMI.addActionListener(this);
        this.managerOrdersMI.addActionListener(this);
        this.userOrderMI.addActionListener(this);
        MainPanel.mainPanel(wFrame);
        this.wFrame.setTitle("MUSIC STORE");
        this.wFrame.setSize(800, 600);
        this.wFrame.setResizable(false);
        this.wFrame.setLocationRelativeTo(null);
        this.wFrame.setVisible(true);
        this.wFrame.addWindowListener(closeOp);
        this.wFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if ((JMenuItem) e.getSource() == this.exitMI) {
            try {
                ResultSet res = DB.db.userSelect('Y');
                while (res.next()) {
                    DB.db.userSetSession(res.getString(2), false);
                }
                DB.db.close();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        } else if ((JMenuItem) e.getSource() == this.loginMI) {
            this.hideAllPanels();
            LoginPanel.loginPanel(wFrame);
        } else if ((JMenuItem) e.getSource() == this.searchMI) {
            this.hideAllPanels();
            MainPanel.mainPanel(wFrame);
        } else if ((JMenuItem) e.getSource() == this.managerOrdersMI) {
            try {
                int userId = 0;
                String role = null;
                ResultSet res = DB.db.userSelect("y".charAt(0));
                while (res.next()) {
                    userId = res.getInt(1);
                    role = res.getString("role");
                }
                DB.db.close();
                if (userId == 0) {
                    this.hideAllPanels();
                    LoginPanel.loginPanel(wFrame);
                }  else if (role.equalsIgnoreCase("m")) {
                    this.hideAllPanels();
                    ManagerPanel.managerPanel(wFrame);
                }
            } catch (SQLException ex) {
            }
        } else if ((JMenuItem) e.getSource() == this.userOrderMI) {
            try {
                int userId = 0;
                String role = null;
                ResultSet res = DB.db.userSelect("y".charAt(0));
                while (res.next()) {
                    userId = res.getInt(1);
                    role = res.getString("role");
                }
                DB.db.close();
                if (userId == 0) {
                    this.hideAllPanels();
                    LoginPanel.loginPanel(wFrame);
                }  else  {
                    this.hideAllPanels();
                    UserPanel.userPanel(wFrame);
                }
            } catch (SQLException ex) {
            }
        }
    }

    private void hideAllPanels() {
        if (MainPanel.paneMain != null) {
            MainPanel.paneMain.setVisible(false);
        }
        if (LoginPanel.paneLogin != null) {
            LoginPanel.paneLogin.setVisible(false);
        }
        if (ManagerPanel.paneManager != null) {
            ManagerPanel.paneManager.setVisible(false);
            ManagerPanel.paneManagerDetail.setVisible(false);
            ManagerPanel.tabs.setVisible(false);
        }
        if (UserPanel.paneUser != null) {
            UserPanel.paneUser.setVisible(false);
            UserPanel.paneUserInfo.setVisible(false);
            UserPanel.tabs.setVisible(false);
        }
        if (SignUpPanel.paneSignUp != null) {
            SignUpPanel.paneSignUp.setVisible(false);
        }
    }
}
