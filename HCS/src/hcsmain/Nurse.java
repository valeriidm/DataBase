/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hcsmain;

import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ContEd Student
 */
public class Nurse {

    private JFrame frame;
    private JTabbedPane NSPanel;

    protected void init(){

        this.frame = new JFrame();
        this.NSPanel = new JTabbedPane();

        JPanel patientPane =  new JPanel();
        JPanel patientcPane =  new JPanel();
        JPanel historygPane = new JPanel();
        JPanel historyilPane = new JPanel();

        this.NSPanel.add("Patient Form", patientPane);
        this.NSPanel.add("Patient  File", patientcPane);
        this.NSPanel.add("Medical General History", historygPane);
        this.NSPanel.add("Medical Illness History", historyilPane);

        /************* Patient's Form Components***********/

        JTextPane patientfTP = new JTextPane();
        JScrollPane patientfSP = new JScrollPane(patientfTP);
        JTextPane patientpTP = new JTextPane();
        JScrollPane patientpSP = new JScrollPane(patientpTP);
        JTextPane patientaTP = new JTextPane();
        JScrollPane patientaSP = new JScrollPane(patientaTP);
        JTextPane patientdTP = new JTextPane();
        JScrollPane patientdSP = new JScrollPane(patientdTP);

        JTextField patientTF = new JTextField(10);
        JTextField patientdbTF = new JTextField(10);

        /************* Patient's Active File***********/
        JTextPane patientcTP = new JTextPane();
        JScrollPane patientcSP = new JScrollPane(patientcTP);

        JTextField patient1TF = new JTextField(10);
        JTextField patientdb1TF = new JTextField(10);

        JTextArea prescTA = new JTextArea();
        prescTA.setLineWrap(true);
        JTextArea anamTA = new JTextArea();
        anamTA.setLineWrap(true);
        JTextArea diagnTA = new JTextArea();
        diagnTA.setLineWrap(true);

        JScrollPane prescJS = new JScrollPane(prescTA);
        JScrollPane anamJS = new JScrollPane(anamTA);
        JScrollPane diagnJS = new JScrollPane(diagnTA);

        /************* Patient's Medical History***********/
        JTextPane patienthTP = new JTextPane();
        JScrollPane patienthSP = new JScrollPane(patienthTP);

        JTextField[] medhTF = new JTextField[20];
        for(int i =0; i<20; i++){
            medhTF[i] = new JTextField();}

        String[] rbstr = {"Yes", "No", "Yes", "No", "Yes", "No", "Yes", "No",
                          "Yes", "No", "Positive", "Negative", "Yes", "No", "Positive",
                          "Negative", "Yes", "No"};


        JRadioButton[] rb = new JRadioButton[18];
        for(int i=0; i<rbstr.length; i++){
           rb[i]= new JRadioButton(rbstr[i]);
//                   rb[i].addItemListener(this);
           rb[i].setSelected(false);
        }

        ButtonGroup[] group = new ButtonGroup[9];

        for(int i = 0; i<9; i++){
        group[i] = new ButtonGroup();}

        for(int i = 0; i<rb.length; i++)
        {
            for(int j =0; j<9; j++)
            {
            group[j].add(rb[i]);
            j++;
            group[j-1].add(rb[i]);
            }
        }

//        for(int i = 0; i<9; i++){
//        group[i] = new ButtonGroup();}
//
//        group[0].add(rb[0]);
//        group[0].add(rb[1]);
//        group[1].add(rb[2]);
//        group[1].add(rb[3]);
//        group[2].add(rb[4]);
//        group[2].add(rb[5]);
//        group[3].add(rb[6]);
//        group[3].add(rb[7]);
//        group[4].add(rb[8]);
//        group[4].add(rb[9]);
//        group[5].add(rb[10]);
//        group[5].add(rb[11]);
//        group[6].add(rb[12]);
//        group[6].add(rb[13]);
//        group[7].add(rb[14]);
//        group[7].add(rb[15]);
//        group[8].add(rb[16]);
//        group[8].add(rb[17]);

        /************* Patient's Medical History***********/
        JTextPane patientilTP = new JTextPane();
        JScrollPane patientilSP = new JScrollPane(patientilTP);

        JTextField[] medilTF = new JTextField[20];
        for(int i =0; i<20; i++){
            medilTF[i] = new JTextField(20);}

        String[] cbstr = {"Heart disease/Murmur/Angina",
                          "High cholesterole", "High blood pressure",
                          "Low blood pressure", "Heartburn(reflux)",
                          "Anemia or blood problems", "Swollen ankles",
                          "Diarrhea/Constipation", "Shortness of breathe", "Asthma",
                          "Lung problems/cough", "Sinus problems",
                          "Seasonal allergies", "Tonsillitis", "Ear problems",
                          "AIDS/HIV disease", "Eye disorder/Glaucoma",
                          "Seizures", "Stroke", "Headaches/Migraines",
                          "Neurological problems", "Depression/Anxiety",
                          "Psychiatric care", "Diabetes", "Kidney/Bladder problems",
                          "Liver problems/Hepatitas", "Arthritis", "Cancer",
                          "Ulcers/colitis", "Thyroid problems", "Epilepsy",
                          "Mental illness", "Palpitations(rapid heart beats)",
                          "Spell of unconsiousness", "Anemia (low blood count)",
                          "Excessive bleeding or bruising","Painful urination",
                          "Chest pain", "Joint stiffness, pain or swelling",
                           "Attempted suicide"};

        JCheckBox[] cb = new JCheckBox[44];
        for(int i=0; i<cbstr.length; i++){
        cb[i] = new JCheckBox(cbstr[i]);}

        String[] immstr = {"Tetanus:", "Pneumovax:", "Hepatitis B:",
            "Hepatitis A:", "Varicella:", "Zostavax:",
        "Gardisil:", "Other specify:"};

        JLabel[] immL = new JLabel[9];
        for(int i = 0; i<immstr.length; i++){
            immL[i] = new JLabel(immstr[i]);
        }

//        JTextField[] immTF = new JTextField[9];
//        for(int i = 0; i<9; i++){
//            immTF[i] = new JTextField(20);}

        patientPane.setLayout(new GridBagLayout());
        patientcPane.setLayout(new GridBagLayout());
        historygPane.setLayout(new GridBagLayout());
        historyilPane.setLayout(new GridBagLayout());



        /*********************** Patient's Form ****************************/

        patientfTP.setContentType("text/html");
        patientfTP.setOpaque(false);
        patientfTP.setEditable(false);
        patientfSP.setOpaque(false);
        patientfSP.getViewport().setOpaque(false);
        patientfSP.getHorizontalScrollBar().setOpaque(false);
        patientfSP.getVerticalScrollBar().setOpaque(false);
        patientfSP.setPreferredSize(new Dimension(600, 150));

        patientpTP.setContentType("text/html");
        patientpTP.setOpaque(false);
        patientpTP.setEditable(false);
        patientpSP.setOpaque(false);
        patientpSP.getViewport().setOpaque(false);
        patientpSP.getHorizontalScrollBar().setOpaque(false);
        patientpSP.getVerticalScrollBar().setOpaque(false);
        patientpSP.setPreferredSize(new Dimension(600, 150));

        patientaTP.setContentType("text/html");
        patientaTP.setOpaque(false);
        patientaTP.setEditable(false);
        patientaSP.setOpaque(false);
        patientaSP.getViewport().setOpaque(false);
        patientaSP.getHorizontalScrollBar().setOpaque(false);
        patientaSP.getVerticalScrollBar().setOpaque(false);
        patientaSP.setPreferredSize(new Dimension(600, 150));

        patientdTP.setContentType("text/html");
        patientdTP.setOpaque(false);
        patientdTP.setEditable(false);
        patientdSP.setOpaque(false);
        patientdSP.getViewport().setOpaque(false);
        patientdSP.getHorizontalScrollBar().setOpaque(false);
        patientdSP.getVerticalScrollBar().setOpaque(false);
        patientdSP.setPreferredSize(new Dimension(600, 150));

        patientfTP.setText(this.selectPatient("pt"));
        patientpTP.setText(this.selectPatient("pr"));
        patientaTP.setText(this.selectPatient("an"));
        patientdTP.setText(this.selectPatient("dg"));


        patientPane.add(new JLabel("Patient"), new GridBagConstraints(0,0,1,1,1,0,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        patientPane.add(patientTF, new GridBagConstraints(1,0,1,1,1,0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        patientPane.add(new JLabel("Patient's birth date"), new GridBagConstraints(2,0,1,1,1,0,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        patientPane.add(patientdbTF, new GridBagConstraints(3,0,1,1,1,0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));

        patientPane.add(patientfSP, new GridBagConstraints(0,3,4,2,0,1,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0,15,15,15), 0, 0));


        TitledBorder titlefSP = BorderFactory.createTitledBorder
        (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's General Information");
        titlefSP.setTitleJustification(TitledBorder.LEFT);
        patientfSP.setBorder(titlefSP);
        patientfSP.setVisible(true);

        patientPane.add(patientpSP, new GridBagConstraints(0,7,4,2,0,1,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0,15,15,15), 0, 0));

        TitledBorder titlepSP = BorderFactory.createTitledBorder
        (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's Prescriptions");
        titlepSP.setTitleJustification(TitledBorder.LEFT);
        patientpSP.setBorder(titlepSP);
        patientpSP.setVisible(true);

        patientPane.add(patientaSP, new GridBagConstraints(0,10,4,2,0,1,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0,15,15,15), 0, 0));

        TitledBorder titleaSP = BorderFactory.createTitledBorder
        (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's Anamnesis");
        titleaSP.setTitleJustification(TitledBorder.LEFT);
        patientaSP.setBorder(titleaSP);
        patientaSP.setVisible(true);

        patientPane.add(patientdSP, new GridBagConstraints(0,13,4,2,1,1,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0,15,15,15), 0, 0));

         TitledBorder titledSP = BorderFactory.createTitledBorder
        (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's Diagnosis");
        titledSP.setTitleJustification(TitledBorder.LEFT);
        patientdSP.setBorder(titledSP);
        patientdSP.setVisible(true);

/*********************** Patient's Active File ****************************/

        patientcTP.setContentType("text/html");
        patientcTP.setOpaque(false);
        patientcTP.setEditable(false);
        patientcSP.setOpaque(false);
        patientcSP.getViewport().setOpaque(false);
        patientcSP.getHorizontalScrollBar().setOpaque(false);
        patientcSP.getVerticalScrollBar().setOpaque(false);
        patientcSP.setPreferredSize(new Dimension(600, 150));

        patientcPane.add(new JLabel("Patient"), new GridBagConstraints(0,0,1,1,1,0,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        patientcPane.add(patient1TF, new GridBagConstraints(1,0,1,1,1,0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        patientcPane.add(new JLabel("Patient's birth date"), new GridBagConstraints(2,0,1,1,1,0,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        patientcPane.add(patientdb1TF, new GridBagConstraints(3,0,1,1,1,0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));

        patientcPane.add(patientcSP, new GridBagConstraints(0,3,4,2,0,1,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0,15,15,15), 0, 0));

        TitledBorder titlecSP = BorderFactory.createTitledBorder
        (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's General Information");
        titlecSP.setTitleJustification(TitledBorder.LEFT);
        patientcSP.setBorder(titlecSP);
        patientcSP.setVisible(true);

        patientcPane.add(prescJS, new GridBagConstraints(0,7,4,2,0,1,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0,15,15,15), 0, 0));

        TitledBorder titlePresc = BorderFactory.createTitledBorder
        (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's Anamnesis");
        titlePresc.setTitleJustification(TitledBorder.LEFT);
        prescJS.setBorder(titlePresc);
        prescJS.setVisible(true);

        patientcPane.add(anamJS, new GridBagConstraints(0,10,4,2,0,1,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0,15,15,15), 0, 0));

        TitledBorder titleAnam = BorderFactory.createTitledBorder
        (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's Diagnosis");
        titleAnam.setTitleJustification(TitledBorder.LEFT);
        anamJS.setBorder(titleAnam);
        anamJS.setVisible(true);

         patientcPane.add(diagnJS, new GridBagConstraints(0,13,4,2,0,1,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0,15,15,15), 0, 0));

        TitledBorder titleDiag = BorderFactory.createTitledBorder
        (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's Prescription");
        titleDiag.setTitleJustification(TitledBorder.LEFT);
        diagnJS.setBorder(titleDiag);
        diagnJS.setVisible(true);


        /*********************** Patient's Active File ****************************/

        patienthTP.setContentType("text/html");
        patienthTP.setOpaque(false);
        patienthTP.setEditable(false);
        patienthSP.setOpaque(false);
        patienthSP.getViewport().setOpaque(false);
        patienthSP.getHorizontalScrollBar().setOpaque(false);
        patienthSP.getVerticalScrollBar().setOpaque(false);
        patienthSP.setPreferredSize(new Dimension(600, 150));

        historygPane.add(new JLabel("Patient"), new GridBagConstraints(0,0,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historygPane.add(medhTF[0], new GridBagConstraints(1,0,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historygPane.add(new JLabel("Patient's birth date"), new GridBagConstraints(2,0,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historygPane.add(medhTF[1], new GridBagConstraints(3,0,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historygPane.add(patienthSP, new GridBagConstraints(0,3,4,2,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0,15,15,15), 0, 0));

        TitledBorder titlehSP = BorderFactory.createTitledBorder
        (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's General Information");
        titlehSP.setTitleJustification(TitledBorder.LEFT);
        patienthSP.setBorder(titlehSP);
        patienthSP.setVisible(true);

        historygPane.add(new JLabel("Previous Doctor"), new GridBagConstraints(0,6,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[2], new GridBagConstraints(1,6,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("Previous Medical Institution"),
                new GridBagConstraints(2,6,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[3], new GridBagConstraints(3,6,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("Date of the Last Exam"),
                new GridBagConstraints(0,7,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[4], new GridBagConstraints(1,7,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("The Reason of the Last Exam"),
                new GridBagConstraints(2,7,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[5], new GridBagConstraints(3,7,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("The chronic diseases, if any"),
                new GridBagConstraints(0,8,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[6], new GridBagConstraints(1,8,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("The Hepatitis virus, if any"),
                new GridBagConstraints(2,8,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[7], new GridBagConstraints(3,8,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("The Allergies, if any"),
                new GridBagConstraints(0,9,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[8], new GridBagConstraints(1,9,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("The Drug reactions, if any"),
                new GridBagConstraints(2,9,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[9], new GridBagConstraints(3,9,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("The Tabacoo History, if any"),
                new GridBagConstraints(0,10,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[10], new GridBagConstraints(1,10,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("The Alcohol History, if any"),
                new GridBagConstraints(2,10,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[11], new GridBagConstraints(3,10,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("Have you ever been hospitalized?"),
                new GridBagConstraints(0,11,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[0], new GridBagConstraints(1,11,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[1], new GridBagConstraints(1,11,1,1,0.5,0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("Have you ever been tested for Hepatitis?"),
                new GridBagConstraints(2,11,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[2], new GridBagConstraints(3,11,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[3], new GridBagConstraints(3,11,1,1,0.5,0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("If yes, specify the reason and date."),
                new GridBagConstraints(0,12,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[12], new GridBagConstraints(1,12,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("If yes, specify the type of hipatitis and date."),
                new GridBagConstraints(2,12,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[13], new GridBagConstraints(3,12,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("Have you had a sexually transmitted disease?"),
                new GridBagConstraints(0,13,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[4], new GridBagConstraints(1,13,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[5], new GridBagConstraints(1,13,1,1,0.5,0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("Have you ever been tested for HIV disease?"),
                new GridBagConstraints(2,13,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[6], new GridBagConstraints(3,13,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[7], new GridBagConstraints(3,13,1,1,0.5,0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("If yes, specify the type and date."),
                new GridBagConstraints(0,14,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[14], new GridBagConstraints(1,14,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("If yes, specify the date."),
                new GridBagConstraints(2,14,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[15], new GridBagConstraints(3,14,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("Have you had a Tuberculsis (TB) Screening?"),
                new GridBagConstraints(0,15,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[8], new GridBagConstraints(1,15,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[9], new GridBagConstraints(1,15,1,1,0.5,0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("If yes, what were the results?"),
                new GridBagConstraints(2,15,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[10], new GridBagConstraints(3,15,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[11], new GridBagConstraints(3,15,1,1,0.5,0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("Have you had a TB screen or an x-ray?"),
                new GridBagConstraints(0,16,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[12], new GridBagConstraints(1,16,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[13], new GridBagConstraints(1,16,1,1,0.5,0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("If yes, what were the results?"),
                new GridBagConstraints(2,16,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[14], new GridBagConstraints(3,16,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[15], new GridBagConstraints(3,16,1,1,0.5,0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("If yes, specify the reason and the date."),
                new GridBagConstraints(0,17,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(medhTF[16], new GridBagConstraints(1,17,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0,15,15,15), 0, 0));
        historygPane.add(new JLabel("Could you provide the copies of tests?"),
                new GridBagConstraints(2,17,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[16], new GridBagConstraints(3,17,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
        historygPane.add(rb[17], new GridBagConstraints(3,17,1,1,0.5,0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));
       historygPane.add(new JButton("Next"), new GridBagConstraints(3,18,1,1,0.5,0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0,15,15,15), 0, 0));

        /************* Patient's Medical illness History***********/
        historyilPane.add(new JLabel("Patient"), new GridBagConstraints(0,0,1,1,0.5,0,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(medilTF[0], new GridBagConstraints(1,0,1,1,0.5,0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(new JLabel("Patient's birth date"),
                new GridBagConstraints(3,0,1,1,0.5,0,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(medilTF[1], new GridBagConstraints(4,0,1,1,0.5,0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[0], new GridBagConstraints(0,1,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[1], new GridBagConstraints(1,1,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[2], new GridBagConstraints(2,1,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[3], new GridBagConstraints(3,1,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[4], new GridBagConstraints(0,2,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[5], new GridBagConstraints(1,2,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[6], new GridBagConstraints(2,2,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[7], new GridBagConstraints(3,2,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[8], new GridBagConstraints(0,3,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[9], new GridBagConstraints(1,3,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[10], new GridBagConstraints(2,3,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[11], new GridBagConstraints(3,3,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[12], new GridBagConstraints(0,4,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[13], new GridBagConstraints(1,4,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[14], new GridBagConstraints(2,4,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[15], new GridBagConstraints(3,4,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[16], new GridBagConstraints(0,5,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[17], new GridBagConstraints(1,5,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[18], new GridBagConstraints(2,5,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[19], new GridBagConstraints(3,5,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
         historyilPane.add(cb[20], new GridBagConstraints(0,6,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[21], new GridBagConstraints(1,6,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[22], new GridBagConstraints(2,6,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[23], new GridBagConstraints(3,6,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[24], new GridBagConstraints(0,7,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[25], new GridBagConstraints(1,7,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[26], new GridBagConstraints(2,7,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[27], new GridBagConstraints(3,7,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[28], new GridBagConstraints(0,8,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[29], new GridBagConstraints(1,8,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[30], new GridBagConstraints(2,8,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[31], new GridBagConstraints(3,8,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[32], new GridBagConstraints(0,9,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[33], new GridBagConstraints(1,9,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[34], new GridBagConstraints(2,9,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[35], new GridBagConstraints(3,9,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[36], new GridBagConstraints(0,10,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[37], new GridBagConstraints(1,10,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[38], new GridBagConstraints(2,10,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(cb[39], new GridBagConstraints(3,10,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(new JLabel("In case of other deseasis specify, please."),
                new GridBagConstraints(0,11,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(medilTF[2], new GridBagConstraints(1,11,2,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(new JLabel("Indicate the last time the following vaccines were performed (or 'never'):"),
                new GridBagConstraints(0,12,6,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(immL[0], new GridBagConstraints(0,13,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(medilTF[3], new GridBagConstraints(1,13,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(immL[1], new GridBagConstraints(2,13,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(medilTF[4], new GridBagConstraints(3,13,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(immL[2], new GridBagConstraints(0,14,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(medilTF[5], new GridBagConstraints(1,14,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(immL[3], new GridBagConstraints(2,14,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(medilTF[6], new GridBagConstraints(3,14,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(immL[4], new GridBagConstraints(0,15,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(medilTF[7], new GridBagConstraints(1,15,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(immL[5], new GridBagConstraints(2,15,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(medilTF[8], new GridBagConstraints(3,15,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(immL[6], new GridBagConstraints(0,16,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(medilTF[9], new GridBagConstraints(1,16,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(immL[7], new GridBagConstraints(2,16,1,1,0.5,0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(medilTF[10], new GridBagConstraints(3,16,1,1,0.5,0.5,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0));
        historyilPane.add(new JButton("Submit"), new GridBagConstraints(3,17,1,1,0.5,0.5,
                GridBagConstraints.LAST_LINE_END,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0));

        this.frame.setContentPane(this.NSPanel);
        this.frame.setTitle("HEALTH CARE SYSTEM. Nurse");
         this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 200,
                Toolkit.getDefaultToolkit().getScreenSize().height - 100);
        this.frame.setMinimumSize(new Dimension(900, 600));
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private String selectPatient(String patient){
    ResultSet rs = DB.db.patient();
    StringBuffer info = new StringBuffer();
    try{
        info.append("<table>");
        while(rs.next()){

        }
    }catch (SQLException ex){
        Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
    }
    return info.toString();
}

}
