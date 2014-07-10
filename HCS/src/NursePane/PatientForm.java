package NursePane;

/**
 *
 * @author Nadin
 */
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import hcssupport.Func;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class PatientForm {
    private DefaultListModel listMode;
    private Func function;
    private JTextPane[] patientcTP;
    private JList patientList;
    private JTabbedPane PFPanel;
    
    public PatientForm(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init();
    }
    
    private void init(){
        listMode = new DefaultListModel();
        this.function = new Func();
        this.patientcTP = new JTextPane[4];
        this.patientList = new JList(this.listMode);
    }
    
    public void patientForm(String[] labels, JPanel ptPane){
        JScrollPane[] patientSP = new JScrollPane[patientcTP.length];
        
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(8);
        
        // set opacity for panel
        for(int i = 0; i < patientcTP.length; i++){
        patientcTP[i] = new JTextPane();
        patientSP[i] = new JScrollPane(patientcTP[i]);
        this.function.setOpacity(this.patientcTP[i]);
        this.function.setOpacity(patientSP[i]);
        }
        //set Demensions
        patientList.setPreferredSize(new Dimension(120, 275));
        
        // set title for list
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patients");
        title.setTitleJustification(TitledBorder.LEFT);
        patientList.setBorder(title);
        patientList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) patientList.getCellRenderer()).setOpaque(false);
        
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
        
        String[] lb = {"Patient", "Patient's birth date"};
//
        JTextField[] patientTF = new JTextField[lb.length];
        for (int i = 0; i < lb.length; i++) {
            patientTF[i] = new JTextField();
            TitledBorder tl = BorderFactory.createTitledBorder
                    (BorderFactory.createLineBorder(Color.DARK_GRAY), lb[i]);
            tl.setTitleJustification(TitledBorder.LEFT);
            patientTF[i].setBorder(tl);
            patientTF[i].setOpaque(false);
        }
        patientTF[1].setPreferredSize(new Dimension(120, 40));
        //building pane
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.NORTHWEST, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patientTF[0], new GridBagConstraints(2, 0, 1, 1, 0.5, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patientTF[1], new GridBagConstraints(3, 0, 1, 1, 0.5, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patientList, new GridBagConstraints(3, 1, 1, 8, 0, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 45, 15), 0, 0));
        String[] lb1 = {"Patient's General Information",
            "Patient's Prescriptions", "Patient's Anamnesis", "Patient's Diagnosis"};
        int pozY = 1;
        
        for (int i = 0; i < lb1.length; i++) {
            ptPane.add(patientSP[i], new GridBagConstraints
                    (0, pozY, 3, 2, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));

            TitledBorder titleSP = BorderFactory.createTitledBorder
                    (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), lb1[i]);
            titleSP.setTitleJustification(TitledBorder.LEFT);
            patientSP[i].setBorder(titleSP);
            patientSP[i].setVisible(true);

            pozY+=2;
        }
        ptPane.add(new JButton("Next"), new GridBagConstraints(3, 8, 1, 1, 0, 1,
                GridBagConstraints.SOUTH,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
    }  
}
