/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hcsmain;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author ContEd Student
 */
public class MedOfficer {

    private JFrame frame;
    private JTabbedPane MOPanel;

    protected void init(){

        this.frame = new JFrame();
        this.MOPanel = new JTabbedPane();

        JPanel doctorsPane = new JPanel();
        JPanel nursesPane = new JPanel();
        JPanel medAssistPane = new JPanel();


        this.MOPanel.add("Doctor's records", doctorsPane);
        this.MOPanel.add("Nurse's records", nursesPane);
        this.MOPanel.add("Medical Assistens's records", medAssistPane);

        JTextArea doctorsTA = new JTextArea(15,20);

        doctorsPane.setLayout(new GridBagLayout());
        doctorsPane.add(doctorsTA, new GridBagConstraints(0,0,5,5,1,1,GridBagConstraints.NORTHWEST,
                GridBagConstraints.VERTICAL, new Insets(15,15,15,15), 0, 0));
        

        /*********************** Doctor's record ****************************/

        ResultSet rs = DB.db.staff();
        try {
            while (rs.next()) {
                ResultSet res = DB.db.position(rs.getInt("posid"));
                while (res.next())
                if(res.getString("posdesc").equalsIgnoreCase("gp")){
                    doctorsTA.append(rs.getString("fname"));
                    doctorsTA.append("\n");
                    doctorsTA.append(rs.getString("lname"));
                    doctorsTA.append("\n");
                    doctorsTA.append(rs.getString("phone"));
                    doctorsTA.append("\n\n");
                }
            }
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }



        /********************************************************************/
        
        
        this.frame.setContentPane(this.MOPanel);
        this.frame.setTitle("HEALTH CARE SYSTEM. Medical Officer");
        this.frame.setSize(1150, 900);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    
}
