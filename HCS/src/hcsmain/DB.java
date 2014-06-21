package hcsmain;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Leo Dubovyi
 * Vanier College
 *
 * Lab #
 * @Project  @Class DB
 */
public class DB {

    static DB db;
    private String user;
    private String pass;
    private String url;
    private Connection c;

    public DB() {
        try {
            Scanner in = new Scanner(new FileReader("db.conf"));
            user = in.next();
            pass = in.next();
            url = in.next();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            in.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet staff(){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff ORDER BY id");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet staff(String login){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff WHERE login = ?");
            ins.setString(1, login);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }


        public ResultSet staff(String name, int pos){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff WHERE posid = ? AND lname LIKE ?");
            ins.setInt(1, pos);
            ins.setString(2, name+"%");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet position(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM position WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet qualification(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM qualification WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }


    public ResultSet staffSchedule(int staffid){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staffSchedule WHERE staffid = ?");
            ins.setInt(1, staffid);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet hospital(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM hospital WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet patient(){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM patient ORDER BY id");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public void close() {
        try {
            c.close();
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
