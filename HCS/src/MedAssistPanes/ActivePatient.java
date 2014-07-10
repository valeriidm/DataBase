/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MedAssistPanes;

import hcsmain.PatientInfo;
import hcssupport.Func;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ActivePatient implements KeyListener, ListSelectionListener
{

    private JTextPane shortinfoTP;
    private JTextPane patinfoTP;
    private JTextField[] patientTF;
    private JList patientList;
    private DefaultListModel listModel;
    private JTextField findPatientTF;
    private Vector<PatientInfo> actPatient;
    private JTabbedPane MAPanel;
    private Func function;

    public ActivePatient(JTabbedPane Panel) {
        this.MAPanel = Panel;
        this.init();
    }//end constructor

    private void init() {

        this.shortinfoTP = new JTextPane();
        listModel = new DefaultListModel();
        this.patientList = new JList(this.listModel);
        this.patinfoTP = new JTextPane();
        this.findPatientTF = new JTextField(25);
        this.function = new Func();
    }//end init


    public void ActivePatient(String[] labels, JPanel ActivePatientPane) {

        JScrollPane shortinfoSP = new JScrollPane(shortinfoTP);
        JScrollPane patinfoSP = new JScrollPane(patinfoTP);

        // patient list for auto filling
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(8);

        //creating labels
        patientTF = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            patientTF[i] = new JTextField(15);            
            TitledBorder ttl = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLUE), labels[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            patientTF[i].setBorder(ttl);
            patientTF[i].setOpaque(false);
            
        }
        
        // set opacity for left panel        
        this.function.setOpacity(patinfoTP);

        //set opacity for info panel
        this.function.setOpacity(patinfoTP);
        this.function.setOpacity(patinfoSP);
        this.function.setOpacity(shortinfoTP);

        // set dimensions
        shortinfoSP.setPreferredSize(new Dimension(400, 100));
        patinfoSP.setPreferredSize(new Dimension(200, 200));
        patientList.setPreferredSize(new Dimension(150, 175));

        // set title for list
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patients");
        title.setTitleJustification(TitledBorder.LEFT);
        patientList.setBorder(title);
        patientList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) patientList.getCellRenderer()).setOpaque(false);
        // set title for list2
        TitledBorder title2 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Short List");
        title.setTitleJustification(TitledBorder.LEFT);
        shortinfoSP.setBorder(title2);
        shortinfoSP.setOpaque(false);
        //((javax.swing.DefaultListCellRenderer) shortinfoSP.getCellRenderer()).setOpaque(false);
        // set title for list3
        TitledBorder title3 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Full List");
        title.setTitleJustification(TitledBorder.LEFT);
        patinfoSP.setBorder(title3);
        patinfoSP.setOpaque(false);
        //((javax.swing.DefaultListCellRenderer) shortinfoSP.getCellRenderer()).setOpaque(false);

        //Timer
        final JTextPane timeTP = new JTextPane();
        timeTP.setContentType("text/html");
        timeTP.setPreferredSize(new Dimension(200, 24));
        timeTP.setEditable(false);
        timeTP.setOpaque(false);
        timeTP.setBorder(null);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM d, yyyy | h:mm:ss a");

        new javax.swing.Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Calendar date = Calendar.getInstance();
                timeTP.setText("<b>" + dateFormat.format(date.getTime()) + "</b>");
            }
        }).start();

        // building pane
        ActivePatientPane.add(timeTP, new GridBagConstraints(0, 0, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        ActivePatientPane.add(patinfoSP, new GridBagConstraints(0, 1, 2, 14, 0.5, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ActivePatientPane.add(new JLabel("Enter Patient's Name:"), new GridBagConstraints(2, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 0, 15, 0), 0, 0));
        ActivePatientPane.add(patientList, new GridBagConstraints(4, 0, 2, 4, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 5, 5));
        ActivePatientPane.add(findPatientTF, new GridBagConstraints(2, 1, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        ActivePatientPane.add(shortinfoSP, new GridBagConstraints(2, 2, 2, 2, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        int pozY = 4, pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            ActivePatientPane.add(patientTF[i], new GridBagConstraints(++pozX, pozY, 1, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));
            if (i > 2) {
                pozY++;
                pozX = 1;
                ActivePatientPane.add(patientTF[i], new GridBagConstraints(2, pozY, 3, 1, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));
            }
        }
            
    }//end active Patient

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {

    }

     public void valueChanged(ListSelectionEvent e) {
         
     }
}//end active patient
