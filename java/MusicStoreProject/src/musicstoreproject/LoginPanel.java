package musicstoreproject;

/**
 *
 * @author Leo Dubovyi, Valerii Doroshenko
 * Vanier College
 * System Analysis
 * Course Project
 * @Project  MusicStore @Class LoginPanel
 */

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class LoginPanel {

    static JPanel paneLogin;

    protected static void loginPanel(final JFrame wFrame) {

        paneLogin = (JPanel) wFrame.getContentPane().
                add(new IPanel("login.jpg"));
        paneLogin.setLayout(null);

        JButton signInB = new JButton("Sign In");
        JButton signUpB = new JButton("Sign Up");

        final JTextField resultTF = new JTextField(20);
        resultTF.setOpaque(false);
        resultTF.setForeground(Color.red);
        resultTF.setBorder(null);
        resultTF.setEditable(false);
        resultTF.setFocusable(false);
        final JTextField loginTF = new JTextField(12);
        JLabel loginL = new JLabel("Login");

        final JPasswordField passwTF = new JPasswordField(12);
        passwTF.setEchoChar('*');
        JLabel passwL = new JLabel("Password");

        ActionListener handlerSIB = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Sign Up")) {
                    paneLogin.setVisible(false);
                    SignUpPanel.signUpPanel(wFrame);
                } else if (e.getActionCommand().equals("Sign In")) {
                    try {
                        resultTF.setText(null);
                        Auth t = new Auth(loginTF.getText(), new String(passwTF.getPassword()));
                        ResultSet res = DB.db.userSelect();
                        boolean exist = false;
                        String p = null;
                        String role = null;
                        while (res.next()) {
                            if(loginTF.getText().equals(res.getString(2))){
                                p = res.getString(3);
                                role = res.getString("role");
                                exist=true;
                            }
                        }
                        if (exist) {
                            if (t.getPasswHash().equals(p) && t.getLogin().equals(loginTF.getText())) {
                                DB.db.userSetSession(loginTF.getText(), true);
                                paneLogin.setVisible(false);

                                if(role.equalsIgnoreCase("u") || role.equalsIgnoreCase("s"))
                                    UserPanel.userPanel(wFrame);
                                else if(role.equalsIgnoreCase("m"))
                                    ManagerPanel.managerPanel(wFrame);
                            } else {
                                resultTF.setText("Username or password not found");
                                exist = false;
                            }
                        } else {
                            resultTF.setText("Username or password not found");
                        }
                        loginTF.setText(null);
                        passwTF.setText(null);
                        DB.db.close();
                    } catch (Exception ex) {
                        Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        KeysListener handlerR = new KeysListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                resultTF.setText(null);
            }
        };
        loginTF.addKeyListener(handlerR);
        passwTF.addKeyListener(handlerR);
        signInB.addActionListener(handlerSIB);
        signUpB.addActionListener(handlerSIB);

        resultTF.setSize(200, 20);
        loginTF.setSize(120, 20);
        loginL.setSize(120, 20);
        passwL.setSize(120, 20);
        passwTF.setSize(120, 20);
        signInB.setSize(90, 25);
        signUpB.setSize(90, 25);

        resultTF.setLocation(250, 20);
        loginL.setLocation(20, 20);
        loginTF.setLocation(100, 20);
        passwL.setLocation(20, 45);
        passwTF.setLocation(100, 45);
        signInB.setLocation(130, 80);
        signUpB.setLocation(20, 80);

        paneLogin.add(resultTF);
        paneLogin.add(loginL);
        paneLogin.add(loginTF);
        paneLogin.add(passwL);
        paneLogin.add(passwTF);
        paneLogin.add(signInB);
        paneLogin.add(signUpB);

        paneLogin.setVisible(true);
    }

}
