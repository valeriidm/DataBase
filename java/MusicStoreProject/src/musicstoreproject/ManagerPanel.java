package musicstoreproject;

/**
 *
 * @author Leo Dubovyi, Valerii Doroshenko
 * Vanier College
 * System Analysis
 * Course Project
 * @Project  MusicStore @Class ManagerPanel
 */
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class ManagerPanel {

    static JPanel paneManager;
    static JPanel paneManagerDetail;
    static JPanel paneManagerAdd;
    static JTabbedPane tabs;

    protected static void managerPanel(JFrame wFrame) {

        final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        ManagerPanel.paneManager = (JPanel) wFrame.getContentPane().
                add(new IPanelTabs("manager.jpg"));
        ManagerPanel.paneManager.setLayout(null);
        ManagerPanel.paneManagerDetail = (JPanel) wFrame.getContentPane().
                add(new IPanelTabs("manager.jpg"));
        ManagerPanel.paneManagerDetail.setLayout(null);
        ManagerPanel.paneManagerAdd = (JPanel) wFrame.getContentPane().
                add(new IPanelTabs("manager.jpg"));
        ManagerPanel.paneManagerAdd.setLayout(null);

        tabs = new JTabbedPane();
        tabs.add("Current State", ManagerPanel.paneManager);
        tabs.add("Detailed Information", ManagerPanel.paneManagerDetail);
        tabs.add("Add Information", ManagerPanel.paneManagerAdd);


        final JTextPane infoTP = new JTextPane();
        JScrollPane infoSP = new JScrollPane(infoTP);

        JLabel titles = new JLabel("Title", JLabel.RIGHT);
        JLabel client = new JLabel("Clients", JLabel.RIGHT);
        JLabel supplL = new JLabel("Suppliers", JLabel.RIGHT);

        /**************************ADD INFORMATION PANE********************************************/
        JLabel suppladdL = new JLabel("Suppliers", JLabel.RIGHT);
        JButton addInfB = new JButton("Add info");
        JLabel[] songsLabel = {
            new JLabel("Title", JLabel.RIGHT),
            new JLabel("Singer", JLabel.RIGHT),
            new JLabel("Album", JLabel.RIGHT),
            new JLabel("Price for client", JLabel.RIGHT),
            new JLabel("Price from supplier", JLabel.RIGHT),
            new JLabel("Number of license", JLabel.RIGHT),
            new JLabel("End of realisation", JLabel.RIGHT),};

        final JTextField[] songsFields = new JTextField[7];
        for (int i = 0; i < songsFields.length; i++)
            songsFields[i] = new JTextField();


        final Vector<String> supplAdd = new Vector<String>();
        ResultSet res = DB.db.suplSelect();
        try {
            while (res.next()) {
                supplAdd.add(res.getString(2));
            }
        } catch (SQLException ex) {
        }
        final JComboBox supplAddCBox = new JComboBox(supplAdd);

        /*****************************************************************************************/
        JLabel[] mngLabelsSongs = {
            new JLabel("Singer", JLabel.RIGHT),
            new JLabel("Album", JLabel.RIGHT),
            new JLabel("Price for client", JLabel.RIGHT),
            new JLabel("Price from supplier", JLabel.RIGHT),
            new JLabel("Number of license", JLabel.RIGHT),
            new JLabel("End of realisation", JLabel.RIGHT),
            new JLabel("Total Booked", JLabel.RIGHT),
            new JLabel("Supplier", JLabel.RIGHT),};

        JLabel[] mngLabels = {
            new JLabel("e-mail", JLabel.RIGHT),
            new JLabel("Country", JLabel.RIGHT),
            new JLabel("State", JLabel.RIGHT),
            new JLabel("Address", JLabel.RIGHT),
            new JLabel("ZIP", JLabel.RIGHT),
            new JLabel("Phone", JLabel.RIGHT),
            new JLabel("Role", JLabel.RIGHT),
            new JLabel("Supplier Name", JLabel.RIGHT),};

        Vector<String> songTitles = new Vector<String>();
        Vector<String> clients = new Vector<String>();
        final Vector<String> suppl = new Vector<String>();
        songTitles.add("Choose title");
        clients.add("Choose client");

        res = DB.db.songsSelect();
        try {
            while (res.next()) {
                songTitles.add(res.getString(2));
            }
        } catch (SQLException ex) {
        }

        res = DB.db.suplSelect();
        try {
            while (res.next()) {
                suppl.add(res.getString(2));
            }
        } catch (SQLException ex) {
        }

        res = DB.db.userSelect();
        try {
            while (res.next()) {
                String full;
                full = res.getString(6) + " " + res.getString(5);
                clients.add(full);
            }
        } catch (SQLException ex) {
        }
        DB.db.close();

        final JComboBox songsCBox = new JComboBox(songTitles);
        final JComboBox clientCBox = new JComboBox(clients);
        final JComboBox supplCBox = new JComboBox(suppl);

        final JTextField[] fields = new JTextField[16];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new JTextField();
            fields[i].setEditable(false);
        }

        JButton searchB = new JButton("Current State");
        JButton changeSongB = new JButton("Change song price");
        JButton changeRoleB = new JButton("Change user's role");
        JButton addSuplB = new JButton("Add supplier");

        ItemListener handlerSCB = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == songsCBox) {
                    for (int i = 0; i < 8; i++)
                        fields[i].setText(null);
                    try {
                        int totalBooked = 0;
                        ResultSet res = DB.db.songsSelect(songsCBox.getSelectedItem().toString());
                        while (res.next()) {
                            fields[0].setText(res.getString(3));
                            fields[1].setText(res.getString(4));
                            fields[2].setText(String.valueOf(res.getDouble(5)));
                            fields[3].setText(String.valueOf(res.getDouble(6)));
                            fields[4].setText(String.valueOf(res.getInt(7)));
                            fields[5].setText(String.valueOf(format.format(res.getDate(8))));
                            ResultSet rsup = DB.db.suplSelect(res.getInt(10), null);
                            if (rsup.next())
                                fields[7].setText(rsup.getString(1));
                            totalBooked += res.getInt(9);
                        }
                        fields[6].setText(String.valueOf(totalBooked));
                        DB.db.close();
                    } catch (Exception ex) {
                    }

                } else if (e.getSource() == clientCBox)
                    try {
                        for (int i = 8; i < fields.length; i++)
                            fields[i].setText(null);
                        ResultSet res = DB.db.userSelect(
                                clientCBox.getSelectedItem().toString().split(" ")[0],
                                clientCBox.getSelectedItem().toString().split(" ")[1]);
                        if (res.next()) {
                            fields[8].setText(res.getString(7));
                            fields[9].setText(res.getString(8));
                            fields[10].setText(res.getString(9));
                            fields[11].setText(res.getString(10));
                            fields[12].setText(res.getString(11));
                            fields[13].setText(res.getString(12));
                            if (res.getString(4).equalsIgnoreCase("u"))
                                fields[14].setText("User");
                            else if (res.getString(4).equalsIgnoreCase("s")) {
                                fields[14].setText("Supplier's contact");
                                ResultSet sup = DB.db.suplSelect(null, res.getInt(1));
                                if (sup.next())
                                    fields[15].setText(sup.getString(2));
                            } else if (res.getString(4).equalsIgnoreCase("m"))
                                fields[14].setText("Manager");
                        }
                        DB.db.close();
                    } catch (SQLException ex) {
                    }
            }
        };

        songsCBox.addItemListener(handlerSCB);
        clientCBox.addItemListener(handlerSCB);

        ActionListener handlerTabFB = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equalsIgnoreCase("Current state"))
                    try {
                        StringBuffer info = new StringBuffer();
                        ResultSet res = DB.db.songsSelect();
                        ResultSet rpay, rs;
                        info.append("<table style=\"width:400px\"");
                        while (res.next()) {

                            if (res.getString(9) != null) {
                                info.append("<tr><td>Title: <b>");
                                info.append(res.getString(2) + "</b></td>");
                                info.append("<td>Artist: <b>");
                                info.append(res.getString(3) + "</b></td>");
                                info.append("<td> Booked: <b>");
                                info.append(res.getString(9) + "</b></td></tr>");

                                rpay = DB.db.paymentsSelect();
                                while (rpay.next()) {
                                    if (res.getInt(1) == rpay.getInt(2)) {
                                        info.append("<tr><td> Buyed by: ");
                                        rs = DB.db.userSelect(rpay.getInt(1));
                                        if (rs.next())
                                            info.append("<i>" + rs.getString(5) + " " + rs.getString(6) + "</i></td>");
                                        info.append("<td> by date <i>" + format.format(rpay.getDate(4)) + "</i></td>");
                                        info.append("<td> Payment method: <i>" + rpay.getString(5) + "</i></td></tr>");
                                    }
                                }
                                info.append("<tr></tr>");
                            }
                        }
                        infoTP.setText(info.toString());
                        DB.db.close();
                    } catch (Exception ex) {
                    }
                else if (e.getActionCommand().equalsIgnoreCase("Change song price"))
                    try {
                        if (songsCBox.getSelectedIndex() == 0)
                            throw new Exception("Please choose song title");
                        double price;
                        price = Double.parseDouble(JOptionPane.showInputDialog("Input new price"));
                        ResultSet res = DB.db.songsSelect(songsCBox.getSelectedItem().toString());
                        if (res.next())
                            DB.db.songsPriceUpd(res.getInt(1), price);
                        res = DB.db.songsSelect(songsCBox.getSelectedItem().toString());
                        int totalBooked = 0;

                        while (res.next()) {
                            fields[0].setText(res.getString(3));
                            fields[1].setText(res.getString(4));
                            fields[2].setText(String.valueOf(res.getDouble(5)));
                            fields[3].setText(String.valueOf(res.getDouble(6)));
                            fields[4].setText(String.valueOf(res.getInt(7)));
                            fields[5].setText(String.valueOf(format.format(res.getDate(8))));
                            ResultSet rsup = DB.db.suplSelect(res.getInt(10), null);
                            if (rsup.next())
                                fields[7].setText(rsup.getString(1));
                            totalBooked += res.getInt(9);
                        }
                        fields[6].setText(String.valueOf(totalBooked));
                        DB.db.close();
                    } catch (Exception ex) {
                        if (ex.getMessage() != null)
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Wrong input", JOptionPane.ERROR_MESSAGE);
                    }
                else if (e.getActionCommand().equalsIgnoreCase("Change user's role"))
                    try {
                        for (int i = 8; i < fields.length; i++)
                            fields[i].setText(null);
                        if (clientCBox.getSelectedIndex() == 0)
                            throw new Exception("Please choose the client");
                        String role = JOptionPane.showInputDialog("Input new role. (m - manager, s - suplier, u - user)").toUpperCase();

                        ResultSet res = DB.db.userSelect(
                                clientCBox.getSelectedItem().toString().split(" ")[0],
                                clientCBox.getSelectedItem().toString().split(" ")[1]);
                        while (res.next()) {
                            DB.db.userSetRole(res.getInt(1), role.charAt(0));
                            if (role.equalsIgnoreCase("s")) {
                                ResultSet rs = DB.db.suplSelect(supplCBox.getSelectedItem().toString());
                                if (rs.next())
                                    DB.db.suplUpdContact(rs.getInt(1), res.getInt(1));
                            } else if (role.equalsIgnoreCase("u") || role.equalsIgnoreCase("m")) {
                                ResultSet rs = DB.db.suplSelect(null, res.getInt(1));
                                if (rs.next())
                                    DB.db.suplUpdContact(rs.getInt(1), null);
                            }
                        }
                        res = DB.db.userSelect(
                                clientCBox.getSelectedItem().toString().split(" ")[0],
                                clientCBox.getSelectedItem().toString().split(" ")[1]);
                        if (res.next()) {
                            fields[8].setText(res.getString(7));
                            fields[9].setText(res.getString(8));
                            fields[10].setText(res.getString(9));
                            fields[11].setText(res.getString(10));
                            fields[12].setText(res.getString(11));
                            fields[13].setText(res.getString(12));
                            if (res.getString(4).equalsIgnoreCase("u"))
                                fields[14].setText("User");
                            else if (res.getString(4).equalsIgnoreCase("s")) {
                                fields[14].setText("Supplier's contact");
                                ResultSet sup = DB.db.suplSelect(null, res.getInt(1));
                                if (sup.next())
                                    fields[15].setText(sup.getString(2));
                            } else if (res.getString(4).equalsIgnoreCase("m"))
                                fields[14].setText("Manager");
                        }
                        DB.db.close();
                    } catch (Exception ex) {
                        if (ex.getMessage() != null)
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Wrong input", JOptionPane.ERROR_MESSAGE);
                    }
                else if (e.getActionCommand().equalsIgnoreCase("Add supplier")) {
                    String name = JOptionPane.showInputDialog("Input the supplier's name");
                    try {
                        if (name == null)
                            throw new Exception("Supplier name cannot be empty!");
                        DB.db.suplInsert(name);
                        supplCBox.addItem(name);
                        supplCBox.revalidate();
                        DB.db.close();
                    } catch (Exception ex) {
                        if (ex.getMessage() != null)
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Wrong input", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else if(e.getActionCommand().equalsIgnoreCase("Add info")){
                    try {
                        Song s = new Song();
                        s.setTitle(songsFields[0].getText());
                        s.setSinger(songsFields[1].getText());
                        s.setAlbum(songsFields[2].getText());
                        s.setPrice(Double.parseDouble(songsFields[3].getText()));
                        double enterprice = Double.parseDouble(songsFields[4].getText());
                        int numoflic = Integer.parseInt(songsFields[5].getText());
                        ResultSet res = DB.db.suplSelect(supplAddCBox.getSelectedItem().toString());
                        int suppid = 0;
                        if (res.next())
                            suppid = res.getInt(1);
                        DB.db.songIns(s, enterprice, numoflic, songsFields[6].getText(), suppid);
                        for(int i=0; i < songsFields.length; i++)
                            songsFields[i].setText(null);
                    } catch (SQLException ex) {

                    }
                }
            }
        };

        searchB.addActionListener(handlerTabFB);
        changeSongB.addActionListener(handlerTabFB);
        changeRoleB.addActionListener(handlerTabFB);
        addSuplB.addActionListener(handlerTabFB);
        addInfB.addActionListener(handlerTabFB);

        searchB.setLocation(200, 400);
        searchB.setOpaque(false);
        searchB.setContentAreaFilled(false);
        searchB.setFocusable(false);
        searchB.setSize(120, 30);
        ManagerPanel.paneManager.add(searchB);
        ManagerPanel.paneManager.add(infoSP);

        changeSongB.setOpaque(false);
        changeSongB.setContentAreaFilled(false);
        changeSongB.setFocusable(false);
        changeSongB.setLocation(200, 400);
        changeSongB.setSize(160, 30);
        ManagerPanel.paneManagerDetail.add(changeSongB);

        changeRoleB.setOpaque(false);
        changeRoleB.setContentAreaFilled(false);
        changeRoleB.setFocusable(false);
        changeRoleB.setLocation(380, 400);
        changeRoleB.setSize(160, 30);
        ManagerPanel.paneManagerDetail.add(changeRoleB);

        addInfB.setOpaque(false);
        addInfB.setContentAreaFilled(false);
        addInfB.setFocusable(false);
        addInfB.setLocation(380, 400);
        addInfB.setSize(160, 30);
        ManagerPanel.paneManagerAdd.add(addInfB);

        addSuplB.setOpaque(false);
        addSuplB.setContentAreaFilled(false);
        addSuplB.setFocusable(false);
        addSuplB.setLocation(560, 400);
        addSuplB.setSize(160, 30);
        ManagerPanel.paneManagerAdd.add(addSuplB);

        infoSP.setLocation(200, 20);
        infoSP.setSize(550, 350);
        infoTP.setContentType("text/html");
        infoTP.setOpaque(false);
        infoTP.setEditable(false);
        infoSP.setOpaque(false);
        infoSP.getViewport().setOpaque(false);
        infoSP.getHorizontalScrollBar().setOpaque(false);
        infoSP.getVerticalScrollBar().setOpaque(false);

        titles.setSize(120, 20);
        titles.setLocation(130, 30);
        songsCBox.setSize(200, 20);
        songsCBox.setLocation(260, 30);
        paneManagerDetail.add(titles);
        paneManagerDetail.add(songsCBox);

        supplL.setSize(120, 20);
        supplL.setLocation(430, 300);
        supplCBox.setSize(200, 20);
        supplCBox.setLocation(560, 300);
        paneManagerDetail.add(supplCBox);
        paneManagerDetail.add(supplL);


        client.setSize(120, 20);
        client.setLocation(430, 30);
        clientCBox.setSize(200, 20);
        clientCBox.setLocation(560, 30);
        paneManagerDetail.add(client);
        paneManagerDetail.add(clientCBox);


        int lastLoc = 55;
        for (int i = 0; i < mngLabelsSongs.length; i++) {
            fields[i].setSize(200, 20);
            fields[i].setLocation(260, lastLoc);
            mngLabelsSongs[i].setSize(120, 20);
            mngLabelsSongs[i].setLocation(130, lastLoc);
            paneManagerDetail.add(mngLabelsSongs[i]);
            paneManagerDetail.add(fields[i]);
            lastLoc += 25;

        }

        lastLoc = 55;
        for (int i = 0; i < mngLabels.length; i++) {
            fields[mngLabelsSongs.length + i].setSize(200, 20);
            fields[mngLabelsSongs.length + i].setLocation(560, lastLoc);
            mngLabels[i].setSize(120, 20);
            mngLabels[i].setLocation(430, lastLoc);
            paneManagerDetail.add(mngLabels[i]);
            paneManagerDetail.add(fields[mngLabelsSongs.length + i]);
            lastLoc += 25;
        }

        /****************ADD INFORMATION PANE***********************/
        lastLoc = 55;
        for (int i = 0; i < songsLabel.length; i++) {
            songsFields[i].setSize(200, 20);
            songsFields[i].setLocation(560, lastLoc);
            songsLabel[i].setSize(120, 20);
            songsLabel[i].setLocation(430, lastLoc);
            paneManagerAdd.add(songsLabel[i]);
            paneManagerAdd.add(songsFields[i]);
            lastLoc += 25;

        }

        suppladdL.setSize(120, 20);
        suppladdL.setLocation(430, 250);
        supplAddCBox.setSize(200, 20);
        supplAddCBox.setLocation(560, 250);
        paneManagerAdd.add(supplAddCBox);
        paneManagerAdd.add(suppladdL);

        /***********************************************************/

        wFrame.add(tabs);
        tabs.setVisible(true);
    }
}
