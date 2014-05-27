package musicstoreproject;

/**
 *
 * @author Leo Dubovyi, Valerii Doroshenko
 * Vanier College
 * System Analysis
 * Course Project
 * @Project  MusicStore @Class SignUpPanel
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class SignUpPanel {

    static JPanel paneSignUp;

    protected static void signUpPanel(final JFrame wFrame) {

        paneSignUp = (JPanel) wFrame.getContentPane().
                add(new IPanel("signup.jpg"));
        paneSignUp.setLayout(null);

        final JButton signUpB = new JButton();
        final JTextField msg = new JTextField();

        JLabel[] labels = {
            new JLabel("First name:", JLabel.RIGHT),
            new JLabel("Second name:", JLabel.RIGHT),
            new JLabel("Choose username:", JLabel.RIGHT),
            new JLabel("Password:", JLabel.RIGHT),
            new JLabel("Re-enter password:", JLabel.RIGHT),
            new JLabel("E-mail:", JLabel.RIGHT),
            new JLabel("Country:", JLabel.RIGHT),
            new JLabel("State:", JLabel.RIGHT),
            new JLabel("Address:", JLabel.RIGHT),
            new JLabel("ZIP:", JLabel.RIGHT),
            new JLabel("Phone:", JLabel.RIGHT)};

        JTextArea msgTA = new JTextArea("Please fill all fields up " +
                "carefully. \nYour registration will be compleated.");

        final JTextField[] fields = new JTextField[11];
        for (int i = 0; i < fields.length; i++)
            if (i == 3 || i == 4)
                fields[i] = new JPasswordField();
            else
                fields[i] = new JTextField();
        fields[0].setBackground(new Color(240, 250, 200));
        fields[1].setBackground(new Color(240, 250, 200));
        fields[2].setBackground(new Color(240, 250, 200));
        fields[5].setBackground(new Color(240, 250, 200));
        fields[3].setDocument(new JTextFieldLimit(16));      //passw
        fields[4].setDocument(new JTextFieldLimit(16));      //repassw
        fields[9].setDocument(new JTextFieldLimit(7));       //ZIP
        fields[10].setDocument(new JTextFieldLimit(12));     //Phone

        fields[3].setToolTipText("Password must be at least 8 characters.");
        fields[4].setToolTipText("Re-enter the password");

        if (fields[3] instanceof JPasswordField)
            ((JPasswordField) fields[3]).setEchoChar('*');

        if (fields[4] instanceof JPasswordField)
            ((JPasswordField) fields[4]).setEchoChar('*');

        msg.setOpaque(false);
        msg.setEditable(false);
        msg.setFocusable(false);
        msg.setBorder(null);
        msg.setForeground(Color.red);
        msg.setFont(new Font("Arial", Font.ITALIC, 12));

        signUpB.setOpaque(false);
        signUpB.setContentAreaFilled(false);
        signUpB.setBorderPainted(false);

        msgTA.setEditable(false);
        msgTA.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 16));
        msgTA.setForeground(Color.BLUE);
        msgTA.setWrapStyleWord(true);
        msgTA.setLineWrap(true);
        msgTA.setFocusable(false);

        ActionListener handlerSUB = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if ((JButton) e.getSource() == signUpB) {
                    User user = new User();
                    try {
                        String p = new String(((JPasswordField) fields[3]).getPassword());
                        String rp = new String(((JPasswordField) fields[4]).getPassword());
                        if (fields[0].getText().isEmpty() ||
                                fields[1].getText().isEmpty() ||
                                fields[2].getText().isEmpty() || /*name and username*/
                                fields[5].getText().isEmpty()) /*e-mail*/

                            throw new Exception("Mandatory fields has to be filled");
                        if (!p.equals(rp) || p.length() < 8)
                            throw new Exception("Passwords do not match or too short");
                        ResultSet rs = DB.db.userSelect(fields[2].getText());
                        while (rs.next()) {

                            if (!rs.getString("lName").isEmpty())
                                throw new Exception("User already exist");
                        }


                        if (!fields[5].getText().matches(
                                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"))
                            throw new Exception("Wrong e-mail");
                        if (fields[2].getText().equalsIgnoreCase(p))
                            throw new Exception("Username and password the same");

                        Auth t = new Auth(fields[2].getText(), p);
                        String[] nm = new String[6];
                        for (int i = 0; i < 6; i++)
                            if (i == 3 || i == 4)
                                nm[i] = t.getPasswHash();
                            else
                                nm[i] = fields[i].getText();
                        user.setName(nm);
                        String[] adr = new String[5];
                        for (int i = 0; i < 5; i++)
                            if (fields[i + 6].getText().isEmpty())
                                adr[i] = null;
                            else
                                adr[i] = fields[i + 5].getText();
                        user.setAddress(adr);
                        DB.db.userInsert(user);
                        paneSignUp.setVisible(false);
                        UserPanel.userPanel(wFrame);
                        DB.db.close();
                    } catch (Exception ex) {
                        fields[3].setText(null);
                        fields[4].setText(null);
                        DB.db.close();
                        msg.setText(ex.getMessage());
                    }
                }

            }
        };
        signUpB.addActionListener(handlerSUB);

        KeysListener handlerPSWD = new KeysListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                msg.setText(null);
            }
        };
        fields[0].addKeyListener(handlerPSWD);
        fields[1].addKeyListener(handlerPSWD);
        fields[2].addKeyListener(handlerPSWD);
        fields[3].addKeyListener(handlerPSWD);
        fields[5].addKeyListener(handlerPSWD);


        KeysListener handlerPh = new KeysListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 8)
                    if (e.getKeyCode() != 127)
                        if (Character.isDigit(e.getKeyChar())) {
                            if (fields[10].getText().length() == 3 || fields[10].getText().length() == 7) {
                                String t = fields[10].getText();
                                t += "-";
                                fields[10].setText(t);
                            }
                        } else {
                            String t = fields[10].getText();
                            fields[10].setText(t.substring(0, t.length() - 1));
                        }
            }
        };
        fields[10].addKeyListener(handlerPh);


        KeysListener handlerZP = new KeysListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                fields[9].setText(fields[9].getText().toUpperCase());
                if (e.getKeyCode() != 8)
                    if (e.getKeyCode() != 127)
                        if (fields[9].getText().length() == 3) {
                            String t = fields[9].getText();
                            t += "-";
                            fields[9].setText(t);
                        }
            }
        };
        fields[9].addKeyListener(handlerZP);

        for (int i = 0; i < labels.length; i++)
            labels[i].setSize(120, 20);

        int lastLoc = 20;
        for (int i = 0; i < labels.length; i++) {
            labels[i].setLocation(20, lastLoc);
            lastLoc += 25;
        }

        for (int i = 0; i < fields.length; i++)
            if (i == 9)
                fields[i].setSize(70, 20);
            else if (i == 2 || i == 3 || i == 4)
                fields[i].setSize(120, 20);
            else
                fields[i].setSize(180, 20);

        lastLoc = 20;
        for (int i = 0; i < fields.length; i++) {
            fields[i].setLocation(150, lastLoc);
            lastLoc += 25;
        }

        signUpB.setLocation(40, 315);
        signUpB.setSize(390, 180);

        msg.setSize(220, 20);
        msg.setLocation(280, 120);

        msgTA.setSize(190, 100);
        msgTA.setLocation(370, 150);

        for (int i = 0; i < labels.length; i++)
            paneSignUp.add(labels[i]);

        for (int i = 0; i < fields.length; i++)
            paneSignUp.add(fields[i]);
        paneSignUp.add(signUpB);
        paneSignUp.add(msg);
        paneSignUp.add(msgTA);

        paneSignUp.setVisible(true);

    }


}
