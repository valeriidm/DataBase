package hcsmain;

import hcssupport.Func;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
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
public class MedOfficer implements KeyListener, ListSelectionListener {

    private JFrame frame;
    private JTabbedPane MOPanel;
    private DefaultListModel listModel;
    private DefaultListModel listModelHosp;
    private DefaultListModel listModelN;
    private DefaultListModel listModelMA;
    private Vector<Staff> doctor;
    private Vector<Staff> nurse;
    private Vector<Staff> medas;
    private Func function;
    private JTextPane doctorsTP;
    private JTextPane docHospTP;
    private JTextPane docinfoTP;
    private JTextField findDoctorTF;
    private JTextField findNurseTF;
    private JTextField findMedAsTF;
    private JTextPane nurseTP;
    private JTextPane nurseinfoTP;
    private JTextPane medAsTP;
    private JTextPane medAsinfoTP;
    private JList doctorList;
    private JList nurseList;
    private JList medAsList;
    private JList hospDoctorList;
    private JTextField[] docTF;
    private JTextField[] nurseTF;
    private JTextField[] medAsTF;

    protected void init() {

        listModel = new DefaultListModel();
        listModelN = new DefaultListModel();
        listModelMA = new DefaultListModel();
        listModelHosp= new DefaultListModel();
        this.doctor = new Vector();
        this.nurse = new Vector();
        this.medas = new Vector();
        this.function = new Func();
        this.frame = new JFrame();
        this.MOPanel = new JTabbedPane();
        this.doctorsTP = new JTextPane();
        this.docHospTP = new JTextPane();
        this.docinfoTP = new JTextPane();
        this.findDoctorTF = new JTextField(25);
        this.findNurseTF = new JTextField(25);
        this.findMedAsTF = new JTextField(25);
        this.nurseTP = new JTextPane();
        this.nurseinfoTP = new JTextPane();
        this.medAsTP = new JTextPane();
        this.medAsinfoTP = new JTextPane();
        this.doctorList = new JList(this.listModel);
        this.nurseList = new JList(this.listModelN);
        this.medAsList = new JList(this.listModelMA);
        this.hospDoctorList = new JList(this.listModelHosp);

        JPanel doctorsPane = new JPanel();
        JPanel nursesPane = new JPanel();
        JPanel medAssistPane = new JPanel();

        this.MOPanel.add("Doctor's records", doctorsPane);
        this.MOPanel.add("Nurse's records", nursesPane);
        this.MOPanel.add("Medical Assistens's records", medAssistPane);

        JScrollPane doctorsSP = new JScrollPane(doctorsTP);
        JScrollPane docinfoSP = new JScrollPane(docinfoTP);
        JScrollPane docHospSP = new JScrollPane(docHospTP);
        JScrollPane nurseSP = new JScrollPane(nurseTP);
        JScrollPane nurseinfoSP = new JScrollPane(nurseinfoTP);
        JScrollPane medAsSP = new JScrollPane(medAsTP);
        JScrollPane medAsinfoSP = new JScrollPane(medAsinfoTP);


        // set layouts
        doctorsPane.setLayout(new GridBagLayout());
        nursesPane.setLayout(new GridBagLayout());
        medAssistPane.setLayout(new GridBagLayout());

        this.function.fillStaff(doctor, nurse, medas);

        String[] labels = {"First Name", "Last Name", "Date of birth", "e-mail",
            "Address", "ZIP", "Phone", "Social Security Number", "Position",
            "Qualification",};

        /*********************** Doctor's record ****************************/
        // doctor list for auto filling
        doctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        doctorList.setSelectedIndex(0);
        doctorList.setVisibleRowCount(8);

        hospDoctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hospDoctorList.setSelectedIndex(0);
        hospDoctorList.setVisibleRowCount(8);

        docTF = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            docTF[i] = new JTextField(15);
            TitledBorder ttl = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLUE), labels[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            docTF[i].setBorder(ttl);
            docTF[i].setOpaque(false);
        }

        // set opacity for left panel
        this.function.setOpacity(doctorsTP);
        this.function.setOpacity(doctorsSP);

        //set opacity for info panel
        this.function.setOpacity(docinfoTP);
        this.function.setOpacity(docinfoSP);

        this.function.setOpacity(docHospTP);
        this.function.setOpacity(docHospSP);


        // set dimensions
        docinfoSP.setPreferredSize(new Dimension(400, 100));
        doctorsSP.setPreferredSize(new Dimension(200, 200));
        doctorList.setPreferredSize(new Dimension(150, 175));
//        this.hospDoctorList.setPreferredSize(new Dimension(200,80));

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

        // building pane
        doctorsPane.add(doctorsSP, new GridBagConstraints(0, 0, 2, 14, 0.5, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        doctorsPane.add(new JLabel("Doctor"), new GridBagConstraints(2, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 0, 15, 0), 0, 0));
        doctorsPane.add(doctorList, new GridBagConstraints(4, 0, 2, 4, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 5, 5));
        doctorsPane.add(findDoctorTF, new GridBagConstraints(2, 1, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        doctorsPane.add(docinfoSP, new GridBagConstraints(2, 2, 2, 2, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        doctorsPane.add(hospDoctorList, new GridBagConstraints(2, 8, 1, 5, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        doctorsPane.add(docHospSP, new GridBagConstraints(3, 8, 2, 5, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        doctorsPane.add(new JButton("Add Doctor"), new GridBagConstraints(4, 13, 1, 1, 0.5, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));
           doctorsPane.add(new JButton("Edit Doctor"), new GridBagConstraints(3, 13, 2, 1, 0.5, 0, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));



        int pozY = 4, pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            doctorsPane.add(docTF[i], new GridBagConstraints(++pozX, pozY, 1, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));
            if ((i + 1) % 3 == 0) {
                pozY++;
                pozX = 1;
            }
        }

       
        // filling left textbox
        doctorsTP.setText(this.function.fillStaffInfo("gp"));
        findDoctorTF.addKeyListener(this);
        doctorList.addListSelectionListener(this);
        this.hospDoctorList.addListSelectionListener(this);

        /******************* End of doctor's record ************************/
        /*********************** Nurse's record ****************************/
        // nurse list for auto filling
        nurseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nurseList.setSelectedIndex(0);
        nurseList.setVisibleRowCount(8);

        this.nurseTF = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            nurseTF[i] = new JTextField(15);
            TitledBorder ttl = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLUE), labels[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            nurseTF[i].setBorder(ttl);
            nurseTF[i].setOpaque(false);
        }

        // set opacity for left panel
        this.function.setOpacity(nurseTP);
        this.function.setOpacity(nurseSP);

        //set opacity for info panel
        this.function.setOpacity(nurseinfoTP);
        this.function.setOpacity(nurseinfoSP);

        // set dimensions
        nurseinfoSP.setPreferredSize(new Dimension(400, 100));
        nurseSP.setPreferredSize(new Dimension(200, 200));
        nurseList.setPreferredSize(new Dimension(150, 175));

        // set title for list
        title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Nurses");
        title.setTitleJustification(TitledBorder.LEFT);
        nurseList.setBorder(title);
        nurseList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) nurseList.getCellRenderer()).setOpaque(false);

        // building pane
        nursesPane.add(nurseSP, new GridBagConstraints(0, 0, 2, 8, 0.5, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        nursesPane.add(new JLabel("Nurse"), new GridBagConstraints(2, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15, 0, 15, 0), 0, 0));
        nursesPane.add(nurseList, new GridBagConstraints(4, 0, 2, 4, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 5, 5));
        nursesPane.add(findNurseTF, new GridBagConstraints(2, 1, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 5, 5));
        nursesPane.add(nurseinfoSP, new GridBagConstraints(2, 2, 2, 2, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 5, 5));

        pozY = 4;
        pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            nursesPane.add(nurseTF[i], new GridBagConstraints(++pozX, pozY, 1, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 0, 0));
            if ((i + 1) % 3 == 0) {
                pozY++;
                pozX = 1;
            }
        }

        // filling left textbox
        nurseTP.setText(this.function.fillStaffInfo("ns"));
        findNurseTF.addKeyListener(this);
        nurseList.addListSelectionListener(this);

        /******************* End of nurse's record ************************/
        /*********************** Med Assistant's record ****************************/
        medAsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        medAsList.setSelectedIndex(0);
        medAsList.setVisibleRowCount(8);

        this.medAsTF = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            medAsTF[i] = new JTextField(15);
            TitledBorder ttl = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLUE), labels[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            medAsTF[i].setBorder(ttl);
            medAsTF[i].setOpaque(false);
        }


        // set opacity for left panel
        this.function.setOpacity(this.medAsTP);
        this.function.setOpacity(medAsSP);

        //set opacity for info panel
        this.function.setOpacity(medAsinfoTP);
        this.function.setOpacity(medAsinfoSP);

        // set dimensions
        medAsinfoSP.setPreferredSize(new Dimension(400, 100));
        medAsSP.setPreferredSize(new Dimension(200, 200));
        medAsList.setPreferredSize(new Dimension(150, 175));

        // set title for list
        title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Nurses");
        title.setTitleJustification(TitledBorder.LEFT);
        medAsList.setBorder(title);
        medAsList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) medAsList.getCellRenderer()).setOpaque(false);

        // building pane
        medAssistPane.add(medAsSP, new GridBagConstraints(0, 0, 2, 8, 0.5, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        medAssistPane.add(new JLabel("Medical Assistant"), new GridBagConstraints(2, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15, 0, 15, 0), 0, 0));
        medAssistPane.add(medAsList, new GridBagConstraints(4, 0, 2, 4, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 5, 5));
        medAssistPane.add(findMedAsTF, new GridBagConstraints(2, 1, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 5, 5));
        medAssistPane.add(medAsinfoSP, new GridBagConstraints(2, 2, 2, 2, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 5, 5));

        pozY = 4;
        pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            medAssistPane.add(medAsTF[i], new GridBagConstraints(++pozX, pozY, 1, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 0, 0));
            if ((i + 1) % 3 == 0) {
                pozY++;
                pozX = 1;
            }
        }

        // filling left textbox
        medAsTP.setText(this.function.fillStaffInfo("ma"));
        findMedAsTF.addKeyListener(this);
        medAsList.addListSelectionListener(this);


        /******************* End of Med Assist's record ************************/
        // fill the informations through the timer (
        new javax.swing.Timer(500000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                function.fillStaff(doctor, nurse, medas);
                if (checkTab() == 0)
                    doctorsTP.setText(function.fillStaffInfo("gp"));
                else if (checkTab() == 1)
                    nurseTP.setText(function.fillStaffInfo("ns"));
                else if (checkTab() == 2)
                    medAsTP.setText(function.fillStaffInfo("ma"));
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

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(this.MOPanel.getSelectedIndex() == 0 && listModel.size() == 1){
                String st = this.docinfoTP.getText();
                String t = st.substring(st.indexOf("<b>")+3, st.indexOf("</b>"));
                function.staffPersonalInfo(doctor, t, docTF);
            } else if (this.MOPanel.getSelectedIndex() == 1 && listModelN.size() == 1){
                String st = this.nurseinfoTP.getText();
                String t = st.substring(st.indexOf("<b>")+3, st.indexOf("</b>"));
                function.staffPersonalInfo(nurse, t, nurseTF);
            } else if (this.MOPanel.getSelectedIndex() == 2 && listModelMA.size() == 1){
                String st = this.medAsinfoTP.getText();
                String t = st.substring(st.indexOf("<b>")+3, st.indexOf("</b>"));
                function.staffPersonalInfo(medas, t, medAsTF);
            }
        } else if (this.MOPanel.getSelectedIndex() == 0 &&
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!findDoctorTF.getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE))))
            function.staffListInfo(doctor, docinfoTP, findDoctorTF, listModel);
        else if (this.MOPanel.getSelectedIndex() == 1 &&
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!findNurseTF.getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE))))
            function.staffListInfo(nurse, nurseinfoTP, findNurseTF, listModelN);
        else if (this.MOPanel.getSelectedIndex() == 2 &&
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!findMedAsTF.getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE))))
            function.staffListInfo(medas, medAsinfoTP, findMedAsTF, listModelMA);
        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if(this.MOPanel.getSelectedIndex() == 0){
                docinfoTP.setText(null);
                listModel.removeAllElements();
            } else if (this.MOPanel.getSelectedIndex() == 1){
                nurseinfoTP.setText(null);
                listModelN.removeAllElements();
            } else if (this.MOPanel.getSelectedIndex() == 2){
                medAsinfoTP.setText(null);
                listModelMA.removeAllElements();
            }

        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting())
            if (this.MOPanel.getSelectedIndex() == 0 &&
                    doctorList.getSelectedIndex() >= 0 &&
                    e.getSource() == doctorList) {
                String st = doctorList.getSelectedValue().toString();
                function.staffPersonalInfo(doctor, st, docTF);
                function.staffListHosp(doctor, st, this.listModelHosp);
            } else if (this.MOPanel.getSelectedIndex() == 1 &&
                    nurseList.getSelectedIndex() >= 0) {
                String st = nurseList.getSelectedValue().toString();
                function.staffPersonalInfo(nurse, st, nurseTF);
            } else if (this.MOPanel.getSelectedIndex() == 2 &&
                    medAsList.getSelectedIndex() >= 0) {
                String st = medAsList.getSelectedValue().toString();
                function.staffPersonalInfo(medas, st, medAsTF);
            } else if (this.MOPanel.getSelectedIndex() == 0 &&
                    this.hospDoctorList.getSelectedIndex() >= 0 &&
                    e.getSource() == this.hospDoctorList) {
                System.out.println("HERE");
            }
    }
}


