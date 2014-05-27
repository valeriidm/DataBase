package musicstoreproject;

/**
 *
 * @author Leo Dubovyi, Valerii Doroshenko
 * Vanier College
 * System Analysis
 * Course Project
 * @Project  MusicStore @Class MainPanel
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class MainPanel {

    static JPanel paneMain;


    protected static void mainPanel(JFrame wFrame) {

        paneMain = (JPanel) wFrame.getContentPane().
                add(new IPanel("guitar.jpg"));

        paneMain.setLayout(null);

        JButton searchB = new JButton("Search...");
        final JTextField searchTF = new JTextField(50);
        JLabel searchL = new JLabel("Search Song or Album");
        final JTextArea searchResultTA = new JTextArea();
        JScrollPane searchResultSP = new JScrollPane(searchResultTA);

        ActionListener handlerSB = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Search...")) {
                    try {
                        searchResultTA.setText(null);
                        ResultSet res = DB.db.songsSelect();
                        while (res.next()) {
                            if(res.getString("title").toLowerCase().contains(searchTF.getText().toLowerCase()) ||
                                    res.getString("singer").toLowerCase().contains(searchTF.getText().toLowerCase()) || 
                                    res.getString("album").toLowerCase().contains(searchTF.getText().toLowerCase()))
                                searchResultTA.append("Title: " + res.getString("title") +
                                        "\nAlbum: " + res.getString("album") + 
                                        "\nSinger: " +  res.getString("singer") + "\n------------\n");
                        }
                        searchTF.setText(null);
                    } catch (SQLException ex) {
                    }
                }
            }
        };

        KeysListener enterKey = new KeysListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        searchResultTA.setText(null);
                        ResultSet res = DB.db.songsSelect();
                        while (res.next()) {
                            if(res.getString("title").toLowerCase().contains(searchTF.getText().toLowerCase()) ||
                                    res.getString("singer").toLowerCase().contains(searchTF.getText().toLowerCase()) ||
                                    res.getString("album").toLowerCase().contains(searchTF.getText().toLowerCase()))
                                searchResultTA.append("Title: " + res.getString("title") +
                                        "\nAlbum: " + res.getString("album") +
                                        "\nSinger: " +  res.getString("singer") + "\n------------\n");
                        }
                        searchTF.setText(null);
                    } catch (SQLException ex) {
                    }
                }
            }
        };
        searchB.addActionListener(handlerSB);
        searchTF.addKeyListener(enterKey);
        searchB.setOpaque(false);
        searchB.setContentAreaFilled(false);
        searchB.setForeground(Color.YELLOW.brighter());
        searchB.setFocusable(false);

        searchL.setSize(200, 25);
        searchL.setLocation(10, 10);
        searchL.setForeground(Color.DARK_GRAY);

        searchTF.setSize(300, 25);
        searchTF.setLocation(10, 30);
        searchTF.setForeground(Color.white);
        searchTF.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        searchTF.setOpaque(false);

        searchResultTA.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        searchResultTA.setForeground(Color.YELLOW.brighter());
        searchResultTA.setOpaque(false);

        searchResultSP.setSize(320, 480);
        searchResultSP.setLocation(460, 10);
        searchResultSP.setOpaque(false);
        searchResultSP.getViewport().setOpaque(false);
        searchResultSP.getHorizontalScrollBar().setOpaque(false);
        searchResultSP.getVerticalScrollBar().setOpaque(false);
        searchResultSP.setBorder(null);
        searchResultSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        searchResultSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        searchB.setSize(90, 25);
        searchB.setLocation(220, 60);

        paneMain.add(searchL);
        paneMain.add(searchTF);
        paneMain.add(searchResultSP);
        paneMain.add(searchB);
        paneMain.setVisible(true);
    }

    
}
