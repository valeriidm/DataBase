/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hcsmain;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author ContEd Student
 */
public class MedAssist {

    private JFrame frame;
    private JPanel MAPanel;

    protected void init(){

        this.frame = new JFrame();
        this.MAPanel = new JPanel();
        
        
        
        
        this.frame.setContentPane(this.MAPanel);
        this.frame.setTitle("HEALTH CARE SYSTEM. Medical Assistent");
        this.frame.setSize(1150, 900);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    
}
