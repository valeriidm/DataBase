/*
 Vanier College
 DataBase Design Project
 Health Care System
 Nurse face
 */
package hcsmain;

import NursePane.PatientFile;
import NursePane.PatientForm;
import hcssupport.Func;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*Nurse Console */
public class Nurse {
     //Creating references of frame and tabbed panels objects
    private JFrame frame;
    private JTabbedPane NSPanel;
    private PatientForm ptform;
    private PatientFile ptfile;

    protected void init() {
        //Creating objects of frame and tabbed panels objects
        this.frame = new JFrame();
        this.NSPanel = new JTabbedPane();
        this.ptform = new PatientForm(this.NSPanel);
        this.ptfile = new PatientFile(this.NSPanel);

        //Creating pane objects
        JPanel patientPane = new JPanel();
        JPanel patientcPane = new JPanel();
//        JPanel historygPane = new JPanel();
//        JPanel historyilPane = new JPanel();
//
        //Setting names of panes
        this.NSPanel.add("Patient Form", patientPane);
        this.NSPanel.add("Patient  File", patientcPane);
//        this.NSPanel.add("Medical General History", historygPane);
//        this.NSPanel.add("Medical Illness History", historyilPane);
//
        patientPane.setLayout(new GridBagLayout());
        
       String[] labels = {"First Name", "Last Name", "Date of birth", "e-mail",
            "Address", "ZIP", "Phone", "Medical Card Number", "Inssurance Number",
            "Social Insurance Number","Anamnesis","Diagnosis"};
       
       this.ptform.patientForm(labels, patientPane);
       
       this.frame.setContentPane(this.NSPanel);
       this.frame.setTitle("HEALTH CARE SYSTEM. Medical Officer");
       this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 200,
                Toolkit.getDefaultToolkit().getScreenSize().height - 100);
       this.frame.setMinimumSize(new Dimension(900, 600));
       this.frame.setLocationRelativeTo(null);
       this.frame.setVisible(true);
       this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        patientcPane.setLayout(new GridBagLayout());
//        historygPane.setLayout(new GridBagLayout());
//        historyilPane.setLayout(new GridBagLayout());
//        /************* Patient's Form Components***********/
//        //Text panes with scrolls
//        JTextPane[] patientTP = new JTextPane[4];
//        JScrollPane[] patientSP = new JScrollPane[patientTP.length];
//
//        for (int i = 0; i < patientTP.length; i++) {
//            patientTP[i] = new JTextPane();
//            patientSP[i] = new JScrollPane(patientTP[i]);
//            patientTP[i].setContentType("text/html");
//            patientTP[i].setOpaque(false);
//            patientTP[i].setEditable(false);
//            patientSP[i].getViewport().setOpaque(false);
//            patientSP[i].getHorizontalScrollBar().setOpaque(false);
//            patientSP[i].getVerticalScrollBar().setOpaque(false);
//            patientSP[i].setPreferredSize(new Dimension(600, 150));
//        }
//
//        /************* Patient's Active File***********/
//        JTextPane patientcTP = new JTextPane();
//        JScrollPane patientcSP = new JScrollPane(patientcTP);
//
//        /************* Patient's Medical History***********/
//        JTextPane patienthTP = new JTextPane();
//        JScrollPane patienthSP = new JScrollPane(patienthTP);
//
//        //Labels for text field
//        String[] labels = {"Patient", "Patient's birth date", "Previous Doctor",
//            "Previous Medical Institution", "Date of the Last Exam",
//            "The Reason of the Last Exam", "The chronic diseases, if any",
//            "The Hepatitis virus, if any", "The Allergies, if any",
//            "The Drug reactions, if any", "The Tabacoo History, if any",
//            "The Alcohol History, if any", "If yes, specify the reason and date.",
//            "If yes, specify the type of hipatitis and date.",
//            "If yes, specify the type and date.", "If yes, specify the date.",
//            "If yes, specify the reason and the date."};
//        //text fields
//        JTextField[] medhTF = new JTextField[labels.length];
//
//        //creating text fields with lables
//        for (int i = 0; i < labels.length; i++) {
//            medhTF[i] = new JTextField();
//            TitledBorder ttl = BorderFactory.createTitledBorder
//                    (BorderFactory.createLineBorder(Color.DARK_GRAY), labels[i]);
//            ttl.setTitleJustification(TitledBorder.LEFT);
//            medhTF[i].setBorder(ttl);
//            medhTF[i].setOpaque(false);
//        }
//        //labels for radio buttons
//        String[] rbstr = {"Yes", "No", "Yes", "No", "Yes", "No", "Yes", "No",
//            "Yes", "No", "Positive", "Negative", "Yes", "No", "Positive",
//            "Negative", "Yes", "No"};
//        //creating radio buttons
//        JRadioButton[] rb = new JRadioButton[18];
//        for (int i = 0; i < rbstr.length; i++) {
//            rb[i] = new JRadioButton(rbstr[i]);
////                   rb[i].addItemListener(this);
//            rb[i].setSelected(false);
//        }
//        //creating button groups
//        ButtonGroup[] group = new ButtonGroup[9];
//        for (int i = 0; i < 9; i++) {
//            group[i] = new ButtonGroup();
//        }
//
//        //groups the rbuttons
//        int j = 0;
//        for (int i = 0; i < group.length; i++) {
//            group[i].add(rb[j++]);
//            group[i].add(rb[j++]);
//        }
//        /************* Patient's Medical History***********/
//        //lables for check boxes
//        String[] cbstr = {"Heart disease/Murmur/Angina",
//            "High cholesterole", "High blood pressure",
//            "Low blood pressure", "Heartburn(reflux)",
//            "Anemia or blood problems", "Swollen ankles",
//            "Diarrhea/Constipation", "Shortness of breathe", "Asthma",
//            "Lung problems/cough", "Sinus problems",
//            "Seasonal allergies", "Tonsillitis", "Ear problems",
//            "AIDS/HIV disease", "Eye disorder/Glaucoma",
//            "Seizures", "Stroke", "Headaches/Migraines",
//            "Neurological problems", "Depression/Anxiety",
//            "Psychiatric care", "Diabetes", "Kidney/Bladder problems",
//            "Liver problems/Hepatitas", "Arthritis", "Cancer",
//            "Ulcers/colitis", "Thyroid problems", "Epilepsy",
//            "Mental illness", "Palpitations(rapid heart beats)",
//            "Spell of unconsiousness", "Anemia (low blood count)",
//            "Excessive bleeding or bruising", "Painful urination",
//            "Chest pain", "Joint stiffness, pain or swelling",
//            "Attempted suicide"};
//        // creating check boxes with labels
//        JCheckBox[] cb = new JCheckBox[44];
//        for (int i = 0; i < cbstr.length; i++) {
//            cb[i] = new JCheckBox(cbstr[i]);
//        }
//        //labels for text fields
//        String[] immstr = {"Patient", "Patient's birth date",
//            "In case of other deseasis specify, please.", "Tetanus:",
//            "Pneumovax:", "Hepatitis B:", "Hepatitis A:", "Varicella:",
//            "Zostavax:", "Gardisil:", "Other specify:"};
//        //creating text fields with lables
//        JTextField[] medilTF = new JTextField[immstr.length];
//        for (int i = 0; i < immstr.length; i++) {
//            medilTF[i] = new JTextField();
//            TitledBorder tl = BorderFactory.createTitledBorder
//                    (BorderFactory.createLineBorder(Color.DARK_GRAY), immstr[i]);
//            tl.setTitleJustification(TitledBorder.LEFT);
//            medilTF[i].setBorder(tl);
//            medilTF[i].setOpaque(false);
//        }
//
//        /*********************** Patient's Form ****************************/
//
////
//////        patientfTP.setText(this.selectPatient("pt"));
//////        patientpTP.setText(this.selectPatient("pr"));
//////        patientaTP.setText(this.selectPatient("an"));
//////        patientdTP.setText(this.selectPatient("dg"));
//        String[] lb = {"Patient", "Patient's birth date"};
//
//        JTextField[] patientTF = new JTextField[lb.length];
//        for (int i = 0; i < lb.length; i++) {
//            patientTF[i] = new JTextField();
//            TitledBorder tl = BorderFactory.createTitledBorder
//                    (BorderFactory.createLineBorder(Color.DARK_GRAY), lb[i]);
//            tl.setTitleJustification(TitledBorder.LEFT);
//            patientTF[i].setBorder(tl);
//            patientTF[i].setOpaque(false);
//        }
//
//        patientPane.add(patientTF[0], new GridBagConstraints(0, 0, 2, 1, 1, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//        patientPane.add(patientTF[1], new GridBagConstraints(2, 0, 2, 1, 1, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//
//        String[] lb1 = {"Patient's General Information",
//            "Patient's Prescriptions", "Patient's Anamnesis", "Patient's Diagnosis"};
//
//        int pozY = 3, pozX = 0;
//        for (int i = 0; i < lb1.length; i++) {
//            patientPane.add(patientSP[i], new GridBagConstraints
//                    (0, pozY, 4, 2, 0.5, 1, GridBagConstraints.NORTHWEST,
//                    GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
//
//            TitledBorder titleSP = BorderFactory.createTitledBorder
//                    (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), lb1[i]);
//            titleSP.setTitleJustification(TitledBorder.LEFT);
//            patientSP[i].setBorder(titleSP);
//            patientSP[i].setVisible(true);
//
//            pozY+=2;
//        }
//        patientPane.add(new JButton("Next"), new GridBagConstraints(3, 13, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHEAST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        /*********************** Patient's Active File ****************************/
//
//        String[] lb2 = {"Patient", "Patient's birth date"};
//
//        JTextField[] patient2TF = new JTextField[lb2.length];
//        for (int i = 0; i < lb2.length; i++) {
//            patient2TF[i] = new JTextField();
//            TitledBorder tl = BorderFactory.createTitledBorder
//                    (BorderFactory.createLineBorder(Color.DARK_GRAY), lb2[i]);
//            tl.setTitleJustification(TitledBorder.LEFT);
//            patient2TF[i].setBorder(tl);
//            patient2TF[i].setOpaque(false);
//        }
//
//        patientcTP.setContentType("text/html");
//        patientcTP.setOpaque(false);
//        patientcTP.setEditable(false);
//        patientcSP.setOpaque(false);
//        patientcSP.getViewport().setOpaque(false);
//        patientcSP.getHorizontalScrollBar().setOpaque(false);
//        patientcSP.getVerticalScrollBar().setOpaque(false);
//        patientcSP.setPreferredSize(new Dimension(600, 150));
//
//        patientcPane.add(patient2TF[0], new GridBagConstraints(1, 0, 1, 1, 1, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//
//        patientcPane.add(patient2TF[1], new GridBagConstraints(3, 0, 1, 1, 1, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//
//        patientcPane.add(patientcSP, new GridBagConstraints(0, 3, 4, 2, 0, 1,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.BOTH, new Insets(0, 15, 15, 15), 0, 0));
//
//        String[] lbta = {"Patient's Anamnesis", "Patient's Diagnosis", "Patient's Diagnosis"};
//
//        JTextArea[] patientTA = new JTextArea[lbta.length];
//        JScrollPane[] taJS = new JScrollPane[lbta.length];
//
//        for (int i = 0; i < lbta.length; i++) {
//            patientTA[i] = new JTextArea();
//            patientTA[i].setLineWrap(true);
//            taJS[i] = new JScrollPane(patientTA[i]);
//            TitledBorder titlePresc = BorderFactory.createTitledBorder
//                (BorderFactory.createLineBorder(Color.DARK_GRAY), lbta[i]);
//            titlePresc.setTitleJustification(TitledBorder.LEFT);
//            taJS[i].setBorder(titlePresc);
//            taJS[i].setVisible(true);
//
//        }
//
//        pozY = 5; pozX = 0;
//        for (int i = 0; i < lbta.length; i++) {
//            patientcPane.add(taJS[i], new GridBagConstraints
//                    (0, pozY, 4, 2, 0.5, 1, GridBagConstraints.NORTHWEST,
//                    GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
//            pozY+=2;
//        }
//
//        TitledBorder titlecSP = BorderFactory.createTitledBorder
//                (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's General Information");
//        titlecSP.setTitleJustification(TitledBorder.LEFT);
//        patientcSP.setBorder(titlecSP);
//        patientcSP.setVisible(true);
//
//        patientcPane.add(new JButton("Submit"), new GridBagConstraints(3, 13, 1, 1, 0, 0,
//        GridBagConstraints.LAST_LINE_END,
//        GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
//
//        /*********************** Patient's Active File ****************************/
//        patienthTP.setContentType("text/html");
//        patienthTP.setOpaque(false);
//        patienthTP.setEditable(false);
//        patienthSP.setOpaque(false);
//        patienthSP.getViewport().setOpaque(false);
//        patienthSP.getHorizontalScrollBar().setOpaque(false);
//        patienthSP.getVerticalScrollBar().setOpaque(false);
//        patienthSP.setPreferredSize(new Dimension(600, 150));
//
//        historygPane.add(medhTF[0], new GridBagConstraints(0, 1, 2, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//
//        historygPane.add(medhTF[1], new GridBagConstraints(2, 1, 2, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//
//        pozY = 3;
//        pozX = 0;
//        for (int i = 2; i < labels.length - 5; i++) {
//            historygPane.add(medhTF[i], new GridBagConstraints
//                    (pozX, pozY, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
//                    GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//            pozX += 2;
//            if ((i + 1) % 2 == 0) {
//                pozY++;
//                pozX = 0;
//            }
//        }
//
//        historygPane.add(new JLabel("Have you ever been hospitalized?"),
//                new GridBagConstraints(0, 14, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHEAST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[0], new GridBagConstraints(1, 14, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[1], new GridBagConstraints(1, 14, 1, 1, 0.5, 0.5,
//                GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//
//        historygPane.add(new JLabel("Have you ever been tested for Hepatitis?"),
//                new GridBagConstraints(2, 14, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHEAST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[2], new GridBagConstraints(3, 14, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[3], new GridBagConstraints(3, 14, 1, 1, 0.5, 0.5,
//                GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//
//        historygPane.add(medhTF[12], new GridBagConstraints(0, 15, 2, 1, 0, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//        historygPane.add(medhTF[13], new GridBagConstraints(2, 15, 2, 1, 0, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//
//        historygPane.add(new JLabel("Have you had a sexually transmitted disease?"),
//                new GridBagConstraints(0, 16, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHEAST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[4], new GridBagConstraints(1, 16, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[5], new GridBagConstraints(1, 16, 1, 1, 0.5, 0.5,
//                GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(new JLabel("Have you ever been tested for HIV disease?"),
//                new GridBagConstraints(2, 16, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHEAST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[6], new GridBagConstraints(3, 16, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[7], new GridBagConstraints(3, 16, 1, 1, 0.5, 0.5,
//                GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//
//        historygPane.add(medhTF[14], new GridBagConstraints(0, 17, 2, 1, 0, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//        historygPane.add(medhTF[15], new GridBagConstraints(2, 17, 2, 1, 0, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//
//        historygPane.add(new JLabel("Have you had a Tuberculsis (TB) Screening?"),
//                new GridBagConstraints(0, 18, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHEAST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[8], new GridBagConstraints(1, 18, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[9], new GridBagConstraints(1, 18, 1, 1, 0.5, 0.5,
//                GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//
//        historygPane.add(new JLabel("If yes, what were the results?"),
//                new GridBagConstraints(2, 18, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[10], new GridBagConstraints(2, 18, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHEAST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[11], new GridBagConstraints(3, 18, 1, 1, 0.5, 0.5,
//                GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//
//        historygPane.add(new JLabel("Have you had a TB screen or an x-ray?"),
//                new GridBagConstraints(0, 19, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHEAST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[12], new GridBagConstraints(1, 19, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[13], new GridBagConstraints(1, 19, 1, 1, 0.5, 0.5,
//                GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//
//        historygPane.add(new JLabel("If yes, what were the results?"),
//                new GridBagConstraints(2, 19, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[14], new GridBagConstraints(2, 19, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHEAST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[15], new GridBagConstraints(3, 19, 1, 1, 0.5, 0.5,
//                GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//
//        historygPane.add(medhTF[16], new GridBagConstraints(0, 20, 2, 1, 0, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//
//
//        historygPane.add(new JLabel("Could you provide the copies of tests?"),
//                new GridBagConstraints(2, 20, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHEAST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[16], new GridBagConstraints(3, 20, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(rb[17], new GridBagConstraints(3, 20, 1, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHEAST,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//        historygPane.add(new JButton("Next"), new GridBagConstraints(3, 21, 1, 1, 0.5, 0.5,
//                GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
//
//        /************* Patient's Medical illness History***********/
//        historyilPane.add(medilTF[0], new GridBagConstraints(0, 0, 2, 1, 0.5, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//
//        historyilPane.add(medilTF[1], new GridBagConstraints(2, 0, 2, 1, 0.5, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//
//        pozY = 1;
//        pozX = -1;
//
//        for (int i = 0; i < 40; i++) {
//            historyilPane.add(cb[i], new GridBagConstraints
//                    (++pozX, pozY, 1, 1, 0.5, 0.5, GridBagConstraints.NORTHWEST,
//                    GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
//            if ((i + 1) % 4 == 0) {
//                pozY++;
//                pozX = -1;
//            }
//        }
//
//        historyilPane.add(medilTF[2], new GridBagConstraints(0, 11, 4, 1, 0, 0,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//        historyilPane.add(new JLabel("Indicate the last time the following vaccines were performed (or 'never'):"),
//                new GridBagConstraints(0, 13, 4, 1, 0.5, 0.5,
//                GridBagConstraints.NORTHWEST,
//                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
//
//        pozY = 14;
//        pozX = -1;
//
//        for (int i = 3; i < immstr.length; i++) {
//            historyilPane.add(medilTF[i], new GridBagConstraints
//                    (++pozX, pozY, 1, 1, 0.5, 0.5, GridBagConstraints.NORTHWEST,
//                    GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
//
//            if ((i + 2) % 4 == 0) {
//                pozY++;
//                pozX = -1;
//            }
//        }
//
//        historyilPane.add(new JButton("Submit"), new GridBagConstraints(3, 17, 1, 1, 0.5, 0.5,
//                GridBagConstraints.LAST_LINE_END,
//                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
//
//        this.frame.setContentPane(this.NSPanel);
//        this.frame.setTitle("HEALTH CARE SYSTEM. Nurse");
//        this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 200,
//                Toolkit.getDefaultToolkit().getScreenSize().height - 100);
//        this.frame.setMinimumSize(new Dimension(900, 600));
//        this.frame.setLocationRelativeTo(null);
//        this.frame.setVisible(true);
//        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//
//    private String selectPatient(String patient) {
//        ResultSet rs = DB.db.patient();
//        StringBuffer info = new StringBuffer();
//        try {
//            info.append("<table>");
//            while (rs.next()) {
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return info.toString();
//    }
}
}
