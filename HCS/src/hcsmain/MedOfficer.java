/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hcsmain;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ContEd Student
 */
public class MedOfficer {

    private JFrame frame;
    private JTabbedPane MOPanel;
    private DefaultListModel listModel;
    private Vector<Staff> doctor;
    private Vector<Staff> nurse;
    private Vector<Staff> medas;

    protected void init() {


        listModel = new DefaultListModel();
        this.doctor = new Vector();
        this.nurse = new Vector();
        this.medas = new Vector();
        this.frame = new JFrame();
        this.MOPanel = new JTabbedPane();
        JPanel doctorsPane = new JPanel();
        JPanel nursesPane = new JPanel();
        JPanel medAssistPane = new JPanel();
        final JList doctorList = new JList(this.listModel);


        ResultSet rs = DB.db.staff();
        try {
            while (rs.next()) {
                Staff s = new Staff();
                s.setLname(rs.getString("lname"));
                s.setFname(rs.getString("fname"));
                s.setbDate(rs.getDate("bDate"));
                if (rs.getInt("posid") == 1)
                    doctor.add(s);
                if (rs.getInt("posid") == 2)
                    nurse.add(s);
                if (rs.getInt("posid") == 3)
                    medas.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }



        this.MOPanel.add("Doctor's records", doctorsPane);
        this.MOPanel.add("Nurse's records", nursesPane);
        this.MOPanel.add("Medical Assistens's records", medAssistPane);

        final JTextPane doctorsTP = new JTextPane();
        final JTextPane docinfoTP = new JTextPane();

        JScrollPane doctorsSP = new JScrollPane(doctorsTP);
        JScrollPane docinfoSP = new JScrollPane(docinfoTP);

        final JTextPane nurseTP = new JTextPane();
        JScrollPane nurseSP = new JScrollPane(nurseTP);

        final JTextPane medAsTP = new JTextPane();
        JScrollPane medAsSP = new JScrollPane(medAsTP);

        // doctor list for auto filling
        doctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        doctorList.setSelectedIndex(0);
        doctorList.setVisibleRowCount(8);

        // set layouts
        doctorsPane.setLayout(new GridBagLayout());
        nursesPane.setLayout(new GridBagLayout());
        medAssistPane.setLayout(new GridBagLayout());



        /*********************** Doctor's record ****************************/
        String[] labels = {"First Name", "Last Name", "Date of birth", "e-mail",
            "Address", "ZIP", "Phone", "Social Security Number", "Position",
            "Qualification",};

        JTextField[] docTF = new JTextField[labels.length];

//        TitledBorder ttl = BorderFactory.createTitledBorder(
//                BorderFactory.createBevelBorder(
//                BevelBorder.LOWERED), labels[0]);
//        TitledBorder ttl = BorderFactory.createTitledBorder(
//                BorderFactory.createEtchedBorder(
//                Color.RED, Color.gray), labels[0]);

        for (int i = 0; i < labels.length; i++) {
            docTF[i] = new JTextField(15);
            TitledBorder ttl = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLUE), labels[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            docTF[i].setBorder(ttl);
            docTF[i].setOpaque(false);
        }

        final JTextField findDoctorTF = new JTextField(25);

        // set opacity for left panel
        doctorsTP.setContentType("text/html");
        doctorsTP.setOpaque(false);
        doctorsTP.setEditable(false);
        doctorsSP.setOpaque(false);
        doctorsSP.getViewport().setOpaque(false);
        doctorsSP.getHorizontalScrollBar().setOpaque(false);
        doctorsSP.getVerticalScrollBar().setOpaque(false);

        //set opacity for info panel
        docinfoTP.setContentType("text/html");
        docinfoTP.setOpaque(false);
        docinfoTP.setEditable(false);
        docinfoSP.setOpaque(false);
        docinfoSP.getViewport().setOpaque(false);
        docinfoSP.getHorizontalScrollBar().setOpaque(false);
        docinfoSP.getVerticalScrollBar().setOpaque(false);

        // set dimensions
        docinfoSP.setPreferredSize(new Dimension(400, 100));
        doctorsSP.setPreferredSize(new Dimension(200, 200));
        doctorList.setPreferredSize(new Dimension(150, 175));

        // set title for list
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Doctors");
        title.setTitleJustification(TitledBorder.LEFT);
        doctorList.setBorder(title);
        doctorList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) doctorList.getCellRenderer()).setOpaque(false);

        // building pane
        doctorsPane.add(doctorsSP, new GridBagConstraints(0, 0, 2, 8, 0.5, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        doctorsPane.add(new JLabel("Doctors"), new GridBagConstraints(2, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15, 0, 15, 0), 0, 0));
        doctorsPane.add(doctorList, new GridBagConstraints(4, 0, 2, 4, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 5, 5));
        doctorsPane.add(findDoctorTF, new GridBagConstraints(2, 1, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 5, 5));
        doctorsPane.add(docinfoSP, new GridBagConstraints(2, 2, 2, 2, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 5, 5));

        int pozY = 4, pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            doctorsPane.add(docTF[i], new GridBagConstraints(++pozX, pozY, 1, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 0, 0));
            if ((i + 1) % 3 == 0) {
                pozY++;
                pozX = 1;
            }
        }

        // filling left textbox
        doctorsTP.setText(this.fillStaffInfo("gp"));

        KeyListener keys = new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                } else if ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                        (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                        (!findDoctorTF.getText().isEmpty() &&
                        (e.getKeyCode() == KeyEvent.VK_BACK_SPACE))) {
                    //clear list
                    listModel.removeAllElements();
                    StringBuffer st = new StringBuffer();
                    Vector<StringBuffer> t = new Vector();
                    st.append("<table width='250'>");
                    for (int i = 0; i < doctor.size(); i++)
                        // find the matching
                        if (doctor.get(i).getLname().toLowerCase().startsWith(
                                findDoctorTF.getText().toLowerCase())) {
                            // preparing information for doctor info
                            st.append("<tr><td>Dr. <b>");
                            st.append(doctor.get(i).getLname());
                            st.append(", " + doctor.get(i).getFname());
                            st.append("</b></td>");
                            st.append("<td><b>");
                            st.append(doctor.get(i).getbDate().toString());
                            st.append("</b></td>");
                            st.append("</tr>");
                            // preparing information for list
                            StringBuffer tmp = new StringBuffer();
                            tmp.append(doctor.get(i).getLname() + ", ");
                            tmp.append(doctor.get(i).getFname().charAt(0) + ". (");
                            tmp.append(doctor.get(i).getbDate().toString() + ")");
                            t.add(tmp);
                        }
                    //finishing and sending information to doctors info
                    st.append("</table>");
                    docinfoTP.setText(st.toString());
                    // building list
                    for (int i = 0; i < t.size(); i++)
                        listModel.add(i, t.get(i).toString());
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    docinfoTP.setText(null);
                    listModel.removeAllElements();
                }
            }
        };
        findDoctorTF.addKeyListener(keys);

        doctorList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                 if (!e.getValueIsAdjusting()) {
                        System.out.println(doctorList.getSelectedValue());
                 }
            }
        });

        /******************* End of doctor's record ************************/
        /*********************** Nurse's record ****************************/
        nurseTP.setContentType("text/html");
        nurseTP.setOpaque(false);
        nurseTP.setEditable(false);
        nurseSP.setOpaque(false);
        nurseSP.getViewport().setOpaque(false);
        nurseSP.getHorizontalScrollBar().setOpaque(false);
        nurseSP.getVerticalScrollBar().setOpaque(false);
        nurseSP.setPreferredSize(new Dimension(300, 200));

        nurseTP.setText(this.fillStaffInfo("ns"));

        nursesPane.add(nurseSP, new GridBagConstraints(0, 0, 5, 5, 1, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.VERTICAL, new Insets(15, 15, 15, 15), 0, 0));


        /******************* End of nurse's record ************************/
        /*********************** Med Assistant's record ****************************/
        medAsTP.setContentType("text/html");
        medAsTP.setOpaque(false);
        medAsTP.setEditable(false);
        medAsSP.setOpaque(false);
        medAsSP.getViewport().setOpaque(false);
        medAsSP.getHorizontalScrollBar().setOpaque(false);
        medAsSP.getVerticalScrollBar().setOpaque(false);
        medAsSP.setPreferredSize(new Dimension(300, 200));

        medAsTP.setText(this.fillStaffInfo("ma"));

        medAssistPane.add(medAsSP, new GridBagConstraints(0, 0, 5, 5, 1, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.VERTICAL, new Insets(15, 15, 15, 15), 0, 0));




        /******************* End of Med Assist's record ************************/
        // fill the informations through the timer (
        new javax.swing.Timer(500000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (checkTab() == 0)
                    doctorsTP.setText(fillStaffInfo("gp"));
                if (checkTab() == 1)
                    nurseTP.setText(fillStaffInfo("ns"));
                if (checkTab() == 2)
                    medAsTP.setText(fillStaffInfo("ma"));
            }
        }).start();


        this.frame.setContentPane(this.MOPanel);
        this.frame.setTitle("HEALTH CARE SYSTEM. Medical Officer");
        this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 200,
                Toolkit.getDefaultToolkit().getScreenSize().height - 100);
        this.frame.setMinimumSize(new Dimension(900, 600));
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private int checkTab() {
        //listener to trace the number of active pane
        final JTextField tmp = new JTextField();
        tmp.setText(String.valueOf(0));
        this.MOPanel.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                tmp.setText(String.valueOf(MOPanel.getSelectedIndex()));
            }
        });
        return Integer.parseInt(tmp.getText());
    }

    private Vector<Staff> findChoosenStaff(String staffPos, String name) {

        Vector<Staff> st = new Vector();
        // ,\make it through calling the db
        int pos;
        if (staffPos.equalsIgnoreCase("gp"))
            pos = 1;
        else if (staffPos.equalsIgnoreCase("ns"))
            pos = 2;
        else if (staffPos.equalsIgnoreCase("ma"))
            pos = 3;
        else
            pos = 4;
        ////
        ResultSet rs = DB.db.staff(name, pos);
        try {
            while (rs.next()) {
                Staff t = new Staff();
                t.setFname(rs.getString("fname"));
                t.setLname(rs.getString("lname"));
                t.setAddress(rs.getString("address"));
                t.setEmail(rs.getString("email"));
                t.setPhone(rs.getString("phone"));
                t.setSsn(rs.getString("SSN"));
                t.setZip(rs.getString("zip"));
//                t.setbDate(rs.getDate("bdate").toString());
                ResultSet rss = DB.db.staffSchedule(rs.getInt("id"));
                while (rss.next()) {
                    ResultSet ress = DB.db.hospital(rss.getInt("hospid"));
                    while (ress.next()) {
                        t.setHospitals(ress.getString("name"));
                    }
                }
                rss = DB.db.qualification(rs.getInt("qualid"));
                while (rss.next()) {
                    t.setQualif(rss.getString("qualdesc"));
                }
                st.add(t);
            }
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return st;
    }

    private String fillStaffInfo(String staffPos) {

        ResultSet rs = DB.db.staff();
        StringBuffer info = new StringBuffer();
        try {
            info.append("<table>");
            while (rs.next()) {
                // check through the positions
                ResultSet res = DB.db.position(rs.getInt("posid"));
                // fill panes according to the position
                while (res.next()) {
                    if (res.getString("posdesc").equalsIgnoreCase(staffPos)) {
                        if (staffPos.equalsIgnoreCase("gp"))
                            info.append("<tr><td>Dr. <b>");
                        else if (staffPos.equalsIgnoreCase("ns"))
                            info.append("<tr><td>R.N. <b>");
                        else if (staffPos.equalsIgnoreCase("ma"))
                            info.append("<tr><td>Med.As. <b>");
                        info.append(rs.getString("lname"));
                        info.append(", " + rs.getString("fname"));
                        info.append("</b></td></tr>");
                        info.append("<tr><td>Phone: <b>");
                        info.append(rs.getString("phone") + "</b></td></tr>");
                        // find the qualification
                        ResultSet ress = DB.db.qualification(rs.getInt("qualid"));
                        while (ress.next()) {
                            info.append("<tr><td>Qualification: <b>");
                            info.append(ress.getString("qualdesc") + "</b></td></tr>");
                        }
                        //find the hospital
                        ResultSet rss = DB.db.staffSchedule(rs.getInt("id"));
                        while (rss.next()) {
                            ress = DB.db.hospital(rss.getInt("hospid"));
                            while (ress.next()) {
                                info.append("<tr><td>Hospital: <b>");
                                info.append(ress.getString("name") + "</b></td></tr>");
                            }
                        }
                        info.append("<tr></tr>");
                    }
                }
            }
            info.append("</table>");
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info.toString();
    }
}
