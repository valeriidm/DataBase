package musicstoreproject;

/**
 *
 * @author Leo Dubovyi, Valerii Doroshenko
 * Vanier College
 * System Analysis
 * Course Project
 * @Project  MusicStore @Class ManagerPanel
 */
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class UserPanel {

    static JPanel paneUser;
    static JPanel paneUserInfo;
    static JTabbedPane tabs;

    protected static void userPanel(JFrame wFrame) {

        final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        final DecimalFormat digitForm = new DecimalFormat("##,##0.00");
        UserPanel.paneUser = (JPanel) wFrame.getContentPane().
                add(new IPanelTabs("guitar.jpg"));
        UserPanel.paneUser.setLayout(null);
        UserPanel.paneUserInfo = (JPanel) wFrame.getContentPane().
                add(new IPanelTabs("guitar.jpg"));
        UserPanel.paneUserInfo.setLayout(null);
        
        final JTextArea infoTP = new JTextArea();
        JLabel[] paneUserLabels = {
            new JLabel("Song", JLabel.LEFT),
            new JLabel("Singer", JLabel.LEFT),
            new JLabel("Price, $CAN", JLabel.LEFT),
            new JLabel("Quantity", JLabel.LEFT),
            new JLabel("Total quantity", JLabel.LEFT),
            new JLabel("Total price ($CAN)", JLabel.LEFT),
        };

        JLabel[] payLabels = {
            new JLabel("Card Type", JLabel.LEFT),
            new JLabel("Expiry Date", JLabel.LEFT),
            new JLabel("Card number", JLabel.LEFT),
            new JLabel("Card Security Value number", JLabel.LEFT),
        };
/************************USER INFORMATION PANEL ************************************/
        JLabel[] labels = {
            new JLabel("First name:", JLabel.RIGHT),
            new JLabel("Second name:", JLabel.RIGHT),
            new JLabel("Username:", JLabel.RIGHT),
            new JLabel("E-mail:", JLabel.RIGHT),
            new JLabel("Country:", JLabel.RIGHT),
            new JLabel("State:", JLabel.RIGHT),
            new JLabel("Address:", JLabel.RIGHT),
            new JLabel("ZIP:", JLabel.RIGHT),
            new JLabel("Phone:", JLabel.RIGHT)};

        final JTextField[] fields = new JTextField[9];
        for (int i = 0; i < fields.length; i++)
                        fields[i] = new JTextField();

        fields[0].setEditable(false);
        fields[1].setEditable(false);
        fields[2].setEditable(false);
        fields[7].setDocument(new JTextFieldLimit(7));       //ZIP
        fields[8].setDocument(new JTextFieldLimit(12));     //Phone



/***********************************************************************************/

        final JTextField singerTF = new JTextField();
        final JTextField priceTF = new JTextField();
        final JTextField cardNumTF = new JTextField();
        final JTextField cardSecTF = new JTextField();
        final JTextField[] paneUserFields = new JTextField[3];
        for(int i = 0; i < paneUserFields.length; i++){
            paneUserFields[i] = new JTextField();
        }

        JButton addBuyB = new JButton("Add to cart");
        JButton delBuyB = new JButton("Make payment");
        JButton applyB = new JButton("Apply changes");
        JScrollPane infoSP = new JScrollPane(infoTP);
        Vector<String> songTitles = new Vector<String>();
        final Vector<Song> booked = new Vector<Song>();
        String[] months = {"Month", "01", "02", "03", "04", "05", "06",
            "07", "08", "09", "10", "11", "12",
        };
        String[] years = {"Year", "2014", "2015", "2016", "2017", "2018",
            "2019", "2020", "2021", "2022",
        };
        String[] cardType = {"Choose type", "VISA", "MASTERCARD", "DEBIT", };

        tabs = new JTabbedPane();
        tabs.add("Song order", UserPanel.paneUser);
        tabs.add("User Information", UserPanel.paneUserInfo);
        songTitles.add("Choose song");

        ResultSet res = DB.db.songsSelect();
        try {
            while (res.next()) {
                songTitles.add(res.getString(2));
            }
        } catch (SQLException ex) {
        }
        DB.db.close();
        final JComboBox songsCBox = new JComboBox(songTitles);
        final JComboBox cardTypeCBox = new JComboBox(cardType);
        final JComboBox monthCBox = new JComboBox(months);
        final JComboBox yearCBox = new JComboBox(years);

        try{
        res = DB.db.userSelect("y".charAt(0));
                        while(res.next()){
                          fields[0].setText(res.getString(5));
                          fields[1].setText(res.getString(6));
                          fields[2].setText(res.getString(2));
                          fields[3].setText(res.getString(7));
                          fields[4].setText(res.getString(8));
                          fields[5].setText(res.getString(9));
                          fields[6].setText(res.getString(10));
                          fields[7].setText(res.getString(11));
                          fields[8].setText(res.getString(12));
                        }
        } catch (Exception ex){
            
        }


        ItemListener handlerS = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == songsCBox) {
                    try {
                        paneUserFields[0].setText("1");
                        ResultSet res = DB.db.songsSelect(songsCBox.getSelectedItem().toString());
                        if (res.next()) {
                            singerTF.setText(res.getString("singer"));
                            priceTF.setText(String.valueOf(res.getDouble("price")));
                        }
                        DB.db.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(UserPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        ActionListener handlerB = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getActionCommand().equals("Add to cart")){
                    try {
                        if(songsCBox.getSelectedIndex() == 0)
                            throw new Exception("Please choose the title");
                        double price;
                        if (paneUserFields[2].getText().isEmpty())
                            price = 0;
                        else price=Double.parseDouble(paneUserFields[2].getText());
                        StringBuffer out = new StringBuffer();
                        ResultSet res = DB.db.songsSelect(songsCBox.getSelectedItem().toString());
                        if (res.next()) {
                            out.append("Title: ");
                            out.append(res.getString("title").concat("\n"));
                            out.append("Singer: ");
                            out.append(res.getString("singer").concat("\n"));
                            out.append("Price: $");
                            out.append(String.valueOf(res.getDouble("price")).concat("\t"));
                            price += res.getDouble("price")*Integer.parseInt(paneUserFields[0].getText());
                            DB.db.songsBookedUpd(res.getInt(1), Integer.parseInt(paneUserFields[0].getText()), true);
                            Song t = new Song();
                            t.setQtty(Integer.parseInt(paneUserFields[0].getText()));
                            t.setId(res.getInt(1));
                            booked.add(t);

                        }
                        if(paneUserFields[0].getText().isEmpty()) throw new Exception("Enter the quantity");
                        out.append("Quantity: ".concat(String.valueOf(Integer.parseInt(paneUserFields[0].getText())))
                                .concat("\t"));
                        int tcur = Integer.parseInt(paneUserFields[0].getText());
                        int ttot;
                        if(paneUserFields[1].getText().isEmpty())
                            ttot = 0;
                        else ttot = Integer.parseInt(paneUserFields[1].getText());
                        paneUserFields[1].setText(String.valueOf(tcur+ttot));
                        paneUserFields[2].setText(String.valueOf(digitForm.format(price)));
                        out.append("Status: booked\n\n");
                        infoTP.append(out.toString());

                        DB.db.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error in input fields", JOptionPane.ERROR_MESSAGE);
                    }
                } else if(e.getActionCommand().equalsIgnoreCase("Make payment")){
                    try{
                        if(infoTP.getText().isEmpty()) throw new Exception("You haven't chosen songs yet");
                        if(cardNumTF.getText().isEmpty()) throw new Exception("Please enter the card number");
                        if(cardSecTF.getText().isEmpty()) throw new Exception("Please enter the card security value");
                        if(cardTypeCBox.getSelectedIndex() == 0) throw new Exception("Please choose the card type");
                        if(monthCBox.getSelectedIndex() == 0 || yearCBox.getSelectedIndex() == 0) throw new Exception("Please choose the expiried date ");
                        StringBuffer transnum = new StringBuffer();
                        if(cardTypeCBox.getSelectedIndex() == 1 || cardTypeCBox.getSelectedIndex() == 2)
                            transnum.append("c");
                        else transnum.append("d");
                        transnum.append(cardNumTF.getText(0, 4));
                        transnum.append(Calendar.getInstance().getTime().hashCode());
                        int userId = 0;
                        ResultSet res = DB.db.userSelect("y".charAt(0));
                        while(res.next()){
                            userId = res.getInt(1);
                        }
                        
                        for(int i = 0; i < booked.size(); i++){
                           DB.db.songsBookedUpd(booked.get(i).getId(), booked.get(i).getQtty(), false);
                           DB.db.songsLicensUpd(booked.get(i).getId(), booked.get(i).getQtty(), false);
                           DB.db.paymentsIns(userId, booked.get(i).getId(), transnum.toString(),
                                   format.format(Calendar.getInstance().getTime()),
                                   cardTypeCBox.getSelectedItem().toString());
                           booked.remove(i);
                        } 
                      
                        infoTP.setText(null);
                        yearCBox.setSelectedIndex(0);
                        monthCBox.setSelectedIndex(0);
                        cardTypeCBox.setSelectedIndex(0);
                        cardNumTF.setText(null);
                        cardSecTF.setText(null);

                        DB.db.close();
                    } catch (Exception ex){
                       JOptionPane.showMessageDialog(null, ex.getMessage(), "Error in input fields", JOptionPane.ERROR_MESSAGE); 
                    }
                } else if(e.getActionCommand().equalsIgnoreCase("Apply changes")){
                    try {
                        if (!fields[3].getText().matches(
                                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"))
                            throw new Exception("Wrong e-mail");
                        String[] nm = new String[9];
                        for (int i = 0; i < fields.length; i++)
                                nm[i] = fields[i].getText();
                        DB.db.userUpdate(nm);
                        DB.db.close();
                    } catch (Exception ex){
                        
                    }
                }
            }
        };

        delBuyB.addActionListener(handlerB);
        applyB.addActionListener(handlerB);
        addBuyB.addActionListener(handlerB);
        songsCBox.addItemListener(handlerS);
        cardTypeCBox.addItemListener(handlerS);
        monthCBox.addItemListener(handlerS);
        yearCBox.addItemListener(handlerS);

        cardTypeCBox.setSize(130, 20);
        cardTypeCBox.setLocation(10, 270);
        monthCBox.setSize(60, 20);
        monthCBox.setLocation(10, 320);
        yearCBox.setSize(60, 20);
        yearCBox.setLocation(80, 320);


        addBuyB.setOpaque(false);
        addBuyB.setContentAreaFilled(false);
        addBuyB.setForeground(Color.YELLOW.brighter());
        addBuyB.setFocusable(false);
        addBuyB.setSize(130, 30);
        addBuyB.setLocation(270, 400);

        applyB.setOpaque(false);
        applyB.setContentAreaFilled(false);
        applyB.setForeground(Color.YELLOW.brighter());
        applyB.setFocusable(false);
        applyB.setSize(130, 30);
        applyB.setLocation(550, 400);

        delBuyB.setOpaque(false);
        delBuyB.setContentAreaFilled(false);
        delBuyB.setForeground(Color.YELLOW.brighter());
        delBuyB.setFocusable(false);
        delBuyB.setSize(130, 30);
        delBuyB.setLocation(270, 440);

        songsCBox.setSize(230, 20);
        songsCBox.setLocation(10, 30);

        infoSP.setLocation(430, 20);
        infoSP.setSize(350, 450);
        infoTP.setOpaque(false);
        infoTP.setEditable(false);
        infoTP.setForeground(Color.YELLOW.brighter());
        infoTP.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        infoTP.setFocusable(false);
        infoSP.setOpaque(false);
        infoSP.getViewport().setOpaque(false);
        infoSP.getHorizontalScrollBar().setOpaque(false);
        infoSP.getVerticalScrollBar().setOpaque(false);

        singerTF.setEditable(false);
        singerTF.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        singerTF.setForeground(Color.YELLOW.brighter());
        singerTF.setOpaque(false);
        singerTF.setSize(230, 20);
        singerTF.setLocation(10, 80);
        
        priceTF.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        priceTF.setForeground(Color.YELLOW.brighter());
        priceTF.setOpaque(false);
        priceTF.setSize(60, 20);
        priceTF.setLocation(90, 110);
        priceTF.setEditable(false);
        priceTF.setBorder(null);

        cardNumTF.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        cardNumTF.setForeground(Color.YELLOW.brighter());
        cardNumTF.setOpaque(false);
        cardNumTF.setSize(180, 20);
        cardNumTF.setLocation(10, 370);

        cardSecTF.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        cardSecTF.setForeground(Color.YELLOW.brighter());
        cardSecTF.setOpaque(false);
        cardSecTF.setSize(60, 20);
        cardSecTF.setLocation(10, 420);

        for(int i = 0; i < paneUserLabels.length; i++){
            paneUserLabels[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
            paneUserLabels[i].setForeground(Color.YELLOW.brighter());
            paneUserLabels[i].setSize(150, 20);
        }

        int start = 250;
        for(int i = 0; i < payLabels.length; i++){
            payLabels[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
            payLabels[i].setForeground(Color.YELLOW.brighter());
            payLabels[i].setSize(180, 20);
            payLabels[i].setLocation(10, start);
            start+=50;
        }


        paneUserLabels[0].setLocation(10, 10);
        paneUserLabels[1].setLocation(10, 60);
        paneUserLabels[2].setLocation(10, 110);
        start = 10;
        for(int i = 3; i < paneUserLabels.length; i++){
            paneUserLabels[i].setLocation(290, start);
            start+=50;
        }

        start = 30;
        for(int i = 0; i < paneUserFields.length; i++){
            paneUserFields[i].setOpaque(false);
            paneUserFields[i].setForeground(Color.YELLOW.brighter());
            paneUserFields[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
            paneUserFields[i].setSize(50, 20);
            paneUserFields[i].setLocation(290, start);
            start+=50;
        }

        paneUserFields[1].setEditable(false);
        paneUserFields[2].setEditable(false);
 
        for(int i=0;  i < paneUserLabels.length; i++)
            paneUser.add(paneUserLabels[i]);
        
        for(int i=0;  i < paneUserFields.length; i++)
            paneUser.add(paneUserFields[i]);

        for(int i = 0; i < payLabels.length; i++)
            paneUser.add(payLabels[i]);

        paneUser.add(addBuyB);
        paneUser.add(delBuyB);
        paneUser.add(songsCBox);
        paneUser.add(cardTypeCBox);
        paneUser.add(monthCBox);
        paneUser.add(yearCBox);
        paneUser.add(infoSP);
        paneUser.add(singerTF);
        paneUser.add(priceTF);
        paneUser.add(cardNumTF);
        paneUser.add(cardSecTF);

        int lastLoc = 30;
        for(int i = 0; i < labels.length; i++){
            labels[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
            labels[i].setForeground(Color.WHITE.brighter());
            labels[i].setSize(150, 20);
            labels[i].setLocation(350, lastLoc);
            lastLoc += 30;
        }
        for(int i = 0; i < labels.length; i++){
            paneUserInfo.add(labels[i]);
        }

        lastLoc = 30;
        for(int i = 0; i < fields.length; i++){
            fields[i].setOpaque(false);
            fields[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
            fields[i].setForeground(Color.YELLOW.brighter());
            fields[i].setSize(200, 20);
            fields[i].setLocation(520, lastLoc);
            lastLoc += 30;
        }
        for(int i = 0; i < fields.length; i++){
            paneUserInfo.add(fields[i]);
        }
        paneUserInfo.add(applyB);

        wFrame.add(tabs);
        tabs.setVisible(true);
    }
}
