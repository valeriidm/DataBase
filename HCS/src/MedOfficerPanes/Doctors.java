/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MedOfficerPanes;

import hcsmain.Staff;
import hcssupport.Func;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ContEd Student
 */
public class Doctors implements KeyListener, ListSelectionListener, ActionListener {

    private JTextPane doctorsTP;
    private JTextPane docHospTP;
    private JTextPane docinfoTP;
    private JTextField[] docTF;
    private JList hospDoctorList;
    private JList doctorList;
    private Func function;
    private JTextField findDoctorTF;
    private DefaultListModel listModel;
    private DefaultListModel listModelHosp;
    private Vector<Staff> doctor;
    private JTabbedPane MOPanel;
    private JButton btnAddDoctor;
    private JButton btnEditDoctor;
    private String[] labels;
    private JScrollPane docHospSP;
    private JTable table;

    public Doctors(JTabbedPane Panel) {
        this.MOPanel = Panel;
        this.init();
    }

    private void init() {
        Object[] tabHead = {"Day of Week", "AM", "PM"};
        Object[][] tabRows = {  
            {"Sunday", "", ""},
            {"Monday", "", ""},
            {"Tuesday", "", ""},
            {"Wednesday", "", ""},
            {"Thursday", "", ""},
            {"Friday", "", ""},
            {"Saturday", "", ""}
        };
        listModel = new DefaultListModel();
        listModelHosp = new DefaultListModel();
        this.doctorsTP = new JTextPane();
        this.docHospTP = new JTextPane();
        this.docinfoTP = new JTextPane();
        this.findDoctorTF = new JTextField(25);
        this.doctorList = new JList(this.listModel);
        this.hospDoctorList = new JList(this.listModelHosp);
        this.function = new Func();
        this.doctor = new Vector();
        this.function.fillStaff(doctor, 1);
        this.btnAddDoctor = new JButton();
        this.btnEditDoctor = new JButton();
        table = new JTable(tabRows, tabHead);
//        this.docHospSP = new JScrollPane(docHospTP);
        this.docHospSP = new JScrollPane(table);


    }

    public void doctor(String[] lab, JPanel doctorsPane) {

        JScrollPane doctorsSP = new JScrollPane(doctorsTP);
        JScrollPane docinfoSP = new JScrollPane(docinfoTP);

        this.labels = lab;

        // doctor list for auto filling
        doctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        doctorList.setSelectedIndex(0);
        doctorList.setVisibleRowCount(8);

        hospDoctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hospDoctorList.setSelectedIndex(0);
        hospDoctorList.setVisibleRowCount(8);

        docTF = new JTextField[labels.length];

        this.btnAddDoctor.setText("Add Doctor");
        this.btnEditDoctor.setText("Edit Doctor");

        for (int i = 0; i < labels.length; i++) {
            docTF[i] = new JTextField(15);
            TitledBorder ttl = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY.darker()), labels[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            docTF[i].setBorder(ttl);
            docTF[i].setOpaque(false);
            docTF[i].setEditable(false);
        }

        // set opacity for left panel
        this.function.setOpacity(doctorsTP);
        this.function.setOpacity(doctorsSP);

        //set opacity for info panel
        this.function.setOpacity(docinfoTP);
        this.function.setOpacity(docinfoSP);

//        this.function.setOpacity(docHospTP);
        this.function.setOpacity(docHospSP);
        this.table.setRowHeight(20);
        this.table.setEnabled(false);

//        this.docHospTP.setContentType("text/plain");

        // set dimensions
        docinfoSP.setPreferredSize(new Dimension(400, 100));
        doctorsSP.setPreferredSize(new Dimension(300, 200));
        doctorList.setPreferredSize(new Dimension(60, 175));
        this.hospDoctorList.setPreferredSize(new Dimension(60, 175));

        // set title for list
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Doctors");
        title.setTitleJustification(TitledBorder.LEFT);
        doctorList.setBorder(title);
        doctorList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) doctorList.getCellRenderer()).setOpaque(false);

        title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Hospitals");
        title.setTitleJustification(TitledBorder.LEFT);
        hospDoctorList.setBorder(title);
        hospDoctorList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) hospDoctorList.getCellRenderer()).setOpaque(false);

        title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Schedule");
        title.setTitleJustification(TitledBorder.LEFT);
        docHospSP.setBorder(title);
        docHospSP.setOpaque(false);

        final JTextPane timeTP = new JTextPane();
        timeTP.setContentType("text/html");
        timeTP.setPreferredSize(new Dimension(200, 24));
        timeTP.setEditable(false);
        timeTP.setOpaque(false);
        timeTP.setBorder(null);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM d, yyyy | h:mm:ss a");

        // building pane
        doctorsPane.add(timeTP, new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        doctorsPane.add(doctorsSP, new GridBagConstraints(0, 1, 2, 14, 0.7, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        doctorsPane.add(new JLabel("Doctor"), new GridBagConstraints(2, 0, 2, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 0, 15, 0), 0, 0));
        doctorsPane.add(doctorList, new GridBagConstraints(4, 0, 1, 4, 0.1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 5, 5));
        doctorsPane.add(findDoctorTF, new GridBagConstraints(2, 1, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        doctorsPane.add(docinfoSP, new GridBagConstraints(2, 2, 2, 2, 0.7, 0.1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        doctorsPane.add(hospDoctorList, new GridBagConstraints(2, 8, 1, 5, 0.1, 0.2, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        doctorsPane.add(docHospSP, new GridBagConstraints(3, 8, 2, 5, 0.1, 0.2, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        doctorsPane.add(this.btnAddDoctor, new GridBagConstraints(4, 13, 1, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));
        doctorsPane.add(this.btnEditDoctor, new GridBagConstraints(3, 13, 2, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));

        int pozY = 4, pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            doctorsPane.add(docTF[i], new GridBagConstraints(++pozX, pozY, 1, 1, 0.9, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 0, 0));
            if ((i + 1) % 3 == 0) {
                pozY++;
                pozX = 1;
            }
        }

        // fill the informations through the timer (
//        new javax.swing.Timer(500000, new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                function.fillStaff(doctor, 1);
//                doctorsTP.setText(function.fillStaffInfo("gp"));
//
//            }
//        }).start();

        //time and date
        new javax.swing.Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Calendar date = Calendar.getInstance();
                timeTP.setText("<b>" + dateFormat.format(date.getTime()) + "</b>");
            }
        }).start();


        // filling left textbox
        doctorsTP.setText(this.function.fillStaffInfo("gp"));
        findDoctorTF.addKeyListener(this);
        doctorList.addListSelectionListener(this);
        this.btnAddDoctor.addActionListener(this);
        this.btnEditDoctor.addActionListener(this);
        this.hospDoctorList.addListSelectionListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.MOPanel.getSelectedIndex() == 0 && listModel.size() == 1) {
                String t = this.listModel.get(0).toString();
                System.out.println(t);
                function.staffPersonalInfo(doctor, t, docTF);
            }
        } else if (this.MOPanel.getSelectedIndex() == 0 &&
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!findDoctorTF.getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)))) {
            function.staffListInfo(doctor, docinfoTP, findDoctorTF, listModel);
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (this.MOPanel.getSelectedIndex() == 0) {
                this.docHospTP.setText(null);
                this.listModelHosp.removeAllElements();
                docinfoTP.setText(null);
                listModel.removeAllElements();
                for (int i = 0; i < docTF.length; i++) {
                    docTF[i].setText(null);
                }
            }

        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (this.MOPanel.getSelectedIndex() == 0 &&
                    doctorList.getSelectedIndex() >= 0 &&
                    e.getSource() == doctorList) {
                String st = doctorList.getSelectedValue().toString();
                function.staffPersonalInfo(doctor, st, docTF);
                function.staffListHosp(doctor, st, this.listModelHosp);
            } else if (this.MOPanel.getSelectedIndex() == 0 &&
                    this.hospDoctorList.getSelectedIndex() >= 0 &&
                    e.getSource() == this.hospDoctorList) {
                String st = doctorList.getSelectedValue().toString();
                function.checkSchedule(doctor, st, this.table, this.hospDoctorList);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {

        if ((JButton) e.getSource() == this.btnEditDoctor) {
            if (this.btnEditDoctor.getText().equalsIgnoreCase("Edit doctor")) {
                // Check if another button is pressed
                if (this.btnAddDoctor.getText().equalsIgnoreCase("Save changes")) {
                    this.btnAddDoctor.setText("Add doctor");
                    for (int i = 0; i < this.docTF.length; i++) {
                        docTF[i].setEditable(false);
                        TitledBorder ttl = BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(Color.LIGHT_GRAY.darker()), labels[i]);
                        ttl.setTitleJustification(TitledBorder.LEFT);
                        docTF[i].setBorder(ttl);
                    }
                    this.table.setEnabled(false);
                }
                // prepare for changing
                this.btnEditDoctor.setText("Save changes");
                for (int i = 4; i < this.docTF.length; i++) {
                    docTF[i].setEditable(true);
                    TitledBorder ttl = BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.RED), labels[i]);
                    ttl.setTitleJustification(TitledBorder.LEFT);
                    docTF[i].setBorder(ttl);
                }
                this.docHospTP.setEditable(true);
                TitledBorder title = BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.RED), "Schedule");
                title.setTitleJustification(TitledBorder.LEFT);
                docHospSP.setBorder(title);
                this.table.setEnabled(true);

            } else if (this.btnEditDoctor.getText().equalsIgnoreCase("Save changes")) {
                this.btnEditDoctor.setText("Edit doctor");
                for (int i = 4; i < this.docTF.length; i++) {
                    docTF[i].setEditable(false);
                    TitledBorder ttl = BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.LIGHT_GRAY.darker()), labels[i]);
                    ttl.setTitleJustification(TitledBorder.LEFT);
                    docTF[i].setBorder(ttl);
                }
                this.docHospTP.setEditable(false);
                TitledBorder title = BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Schedule");
                title.setTitleJustification(TitledBorder.LEFT);
                docHospSP.setBorder(title);
                this.table.setEnabled(false);
            }
        } else if ((JButton) e.getSource() == this.btnAddDoctor) {
            if (this.btnAddDoctor.getText().equalsIgnoreCase("Add doctor")) {
                if (this.btnEditDoctor.getText().equalsIgnoreCase("Save changes")) {
                    this.btnEditDoctor.setText("Edit doctor");
                    for (int i = 4; i < this.docTF.length; i++) {
                        docTF[i].setEditable(false);
                        TitledBorder ttl = BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(Color.LIGHT_GRAY.darker()), labels[i]);
                        ttl.setTitleJustification(TitledBorder.LEFT);
                        docTF[i].setBorder(ttl);
                    }
                    TitledBorder title = BorderFactory.createTitledBorder(
                            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Schedule");
                    title.setTitleJustification(TitledBorder.LEFT);
                    docHospSP.setBorder(title);
                    this.table.setEnabled(true);
                }
                this.btnAddDoctor.setText("Save changes");
                for (int i = 0; i < this.docTF.length; i++) {
                    docTF[i].setEditable(true);
                    docTF[i].setText(null);
                    TitledBorder ttl = BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.RED), labels[i]);
                    ttl.setTitleJustification(TitledBorder.LEFT);
                    docTF[i].setBorder(ttl);
                }
                TitledBorder title = BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.RED), "Schedule");
                title.setTitleJustification(TitledBorder.LEFT);
                docHospSP.setBorder(title);
                this.docHospTP.setEditable(true);
                this.listModel.removeAllElements();
                this.listModelHosp.removeAllElements();
                this.docinfoTP.setText(null);
                this.findDoctorTF.setText(null);
                this.docHospTP.setText(null);
                this.table.setEnabled(true);
            } else if (this.btnAddDoctor.getText().equalsIgnoreCase("Save changes")) {
                this.btnAddDoctor.setText("Add doctor");
                for (int i = 0; i < this.docTF.length; i++) {
                    docTF[i].setEditable(false);
                    TitledBorder ttl = BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.LIGHT_GRAY.darker()), labels[i]);
                    ttl.setTitleJustification(TitledBorder.LEFT);
                    docTF[i].setBorder(ttl);
                }
                this.docHospTP.setEditable(false);
                this.table.setEnabled(false);
                TitledBorder title = BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Schedule");
                title.setTitleJustification(TitledBorder.LEFT);
                docHospSP.setBorder(title);
            }
        }


    }
}
