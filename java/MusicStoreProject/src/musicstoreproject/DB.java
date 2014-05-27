/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package musicstoreproject;

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

    public ResultSet suplSelect(Integer id, Integer userid) {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            if (userid == null) {
                PreparedStatement ins =
                        c.prepareStatement("SELECT name, contact FROM suppliers WHERE id = ?");
                ins.setInt(1, id);
                res = ins.executeQuery();
            } else if (id == null) {
                PreparedStatement ins =
                        c.prepareStatement("SELECT id,name FROM suppliers WHERE contact = ?");
                ins.setInt(1, userid);
                res = ins.executeQuery();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet suplSelect(String name) {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM suppliers WHERE name = ?");
            ins.setString(1, name);
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet suplSelect() {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM suppliers ORDER BY id");
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public void suplInsert(String name) {
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("INSERT INTO suppliers (name) VALUES (?)");
            ins.setString(1, name);
            ins.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void suplUpdContact(Integer id, Integer usrid) {
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            if (usrid != null) {
                PreparedStatement ins =
                        c.prepareStatement("UPDATE suppliers SET contact = ? WHERE id = ?");
                ins.setInt(1, usrid);
                ins.setInt(2, id);
                ins.executeUpdate();
            } else {
                PreparedStatement ins =
                        c.prepareStatement("UPDATE suppliers SET contact = ? WHERE id = ?");
                ins.setNull(1, Types.NULL);
                ins.setInt(2, id);
                ins.executeUpdate();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet paymentsSelect() {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM payments ORDER BY paymentDate DESC");
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public void paymentsIns(int userId, int songId, String transnum, String payDate, String type) {
        java.util.Date day;
        java.sql.Date rv = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        try {
            day = format.parse(payDate);
            rv = new java.sql.Date(day.getTime());
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("INSERT INTO payments VALUES(?,?,?,?,?)");
            ins.setInt(1, userId);
            ins.setInt(2, songId);
            ins.setString(3, transnum);
            ins.setDate(4, rv);
            ins.setString(5, type);
            ins.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
     }

    public void songIns(Song song, double enterprice, int numoflic, String dateofreal, int suppid) {
        java.util.Date day;
        java.sql.Date rv = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        try {
            day = format.parse(dateofreal);
            rv = new java.sql.Date(day.getTime());
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            StringBuffer query = new StringBuffer();
            query.append("INSERT INTO songs ");
            query.append("(title, singer, album, price, ");
            query.append("enterPrice, numOfLic, endOfReal, suppid)");
            query.append(" VALUES(?,?,?,?,?,?,?,?)");
            PreparedStatement ins =
                    c.prepareStatement(query.toString());
            ins.setString(1, song.getTitle());
            ins.setString(2, song.getSinger());
            ins.setString(3, song.getAlbum());
            ins.setDouble(4, song.getPrice());
            ins.setDouble(5, enterprice);
            ins.setInt(6, numoflic);
            ins.setDate(7, rv);
            ins.setInt(8, suppid);
            ins.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
     }

    public ResultSet songsSelect() {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM songs ORDER BY id");
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet songsSelect(String title) {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM songs WHERE title = ?");
            ins.setString(1, title);
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public void songsPriceUpd(int id, double price) {
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("UPDATE songs SET price = ? WHERE id = ?");
            ins.setDouble(1, price);
            ins.setInt(2, id);
            ins.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void songsBookedUpd(int id, int numberofbooked, boolean set) {
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            if (set) {
                PreparedStatement ins =
                        c.prepareStatement("UPDATE songs SET booked = NVL(booked,0) + ? WHERE id = ?");
                ins.setInt(1, numberofbooked);
                ins.setInt(2, id);
                ins.executeUpdate();
            } else {
                PreparedStatement ins =
                        c.prepareStatement("UPDATE songs SET booked = NVL(booked,0) - ? WHERE id = ?");
                ins.setInt(1, numberofbooked);
                ins.setInt(2, id);
                ins.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void songsLicensUpd(int id, int numberoflic, boolean set) {
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            if (set) {
                PreparedStatement ins =
                        c.prepareStatement("UPDATE songs SET numoflic = NVL(numoflic,0) + ? WHERE id = ?");
                ins.setInt(1, numberoflic);
                ins.setInt(2, id);
                ins.executeUpdate();
            } else {
                PreparedStatement ins =
                        c.prepareStatement("UPDATE songs SET numoflic = NVL(numoflic,0) - ? WHERE id = ?");
                ins.setInt(1, numberoflic);
                ins.setInt(2, id);
                ins.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public ResultSet songsBookedSelect(int id) {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT NVL(booked,0) FROM songs WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet songsBookedSelect() {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT NVL(booked,0) FROM songs order by id");
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet userSelect() {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM users ORDER BY id");
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet userSelect(String login) {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM users WHERE login = ? ORDER BY id");

            ins.setString(1, login);
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet userSelect(String lName, String fName) {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM users WHERE lname = ? AND fname = ?");

            ins.setString(1, lName);
            ins.setString(2, fName);
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet userSelect(int id) {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM users WHERE id = ?");

            ins.setInt(1, id);
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public void userSetRole(int id, char role) {
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("UPDATE users SET role=? WHERE id = ?");

            ins.setString(1, String.valueOf(role));
            ins.setInt(2, id);
            ins.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet userSelect(char sess) {
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM users WHERE sess = ?");

            ins.setString(1, String.valueOf(sess).toUpperCase());
            res = ins.executeQuery();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public void userSetSession(String login, boolean sess) {
        String active;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            if (sess)
                active = "Y";
            else
                active = "N";
            PreparedStatement ins =
                    c.prepareStatement("UPDATE users SET sess = ? WHERE login=?");
            ins.setString(1, active);
            ins.setString(2, login);
            ins.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void userCloseSession() {
        String active;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            active = "N";
            PreparedStatement ins =
                    c.prepareStatement("UPDATE users SET sess = ?");
            ins.setString(1, active);
            ins.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void userInsert(User currentUser) {
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            StringBuffer query = new StringBuffer();
            query.append("INSERT INTO users ");
            query.append("(fname, lname, login, pass, email,");
            query.append("country, state, address, zip, phone, sess) ");
            query.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement ins =
                    c.prepareStatement(query.toString());

            ins.setString(1, currentUser.getName()[0]);
            ins.setString(2, currentUser.getName()[1]);
            ins.setString(3, currentUser.getName()[2]);
            ins.setString(4, currentUser.getName()[3]);
            ins.setString(5, currentUser.getName()[5]);
            if (currentUser.getAddress()[0] != null)
                ins.setString(6, currentUser.getAddress()[0]);
            else
                ins.setNull(6, Types.NULL);
            if (currentUser.getAddress()[1] != null)
                ins.setString(7, currentUser.getAddress()[1]);
            else
                ins.setNull(7, Types.NULL);
            if (currentUser.getAddress()[2] != null)
                ins.setString(8, currentUser.getAddress()[2]);
            else
                ins.setNull(8, Types.NULL);
            if (currentUser.getAddress()[3] != null)
                ins.setString(9, currentUser.getAddress()[3]);
            else
                ins.setNull(9, Types.NULL);
            if (currentUser.getAddress()[4] != null)
                ins.setString(10, currentUser.getAddress()[4]);
            else
                ins.setNull(10, Types.NULL);
            ins.setString(11, "Y");
            ins.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void userUpdate(String ... userC) {
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            StringBuffer query = new StringBuffer();
            query.append("UPDATE users SET ");
            query.append("email = ?,");
            query.append(" country = ?, state = ?, address = ?, zip = ? , phone = ? ");
            query.append(" WHERE login = ?");
            PreparedStatement ins =
                    c.prepareStatement(query.toString());

            ins.setString(1, userC[3]);
            if (userC[4] != null)
                ins.setString(2, userC[4]);
            else
                ins.setNull(2, Types.NULL);
            if (userC[5] != null)
                ins.setString(3, userC[5]);
            else
                ins.setNull(3, Types.NULL);
            if (userC[6] != null)
                ins.setString(4, userC[6]);
            else
                ins.setNull(4, Types.NULL);
            if (userC[7] != null)
                ins.setString(5, userC[7]);
            else
                ins.setNull(5, Types.NULL);
            if (userC[8] != null)
                ins.setString(6, userC[8]);
            else
                ins.setNull(6, Types.NULL);
            ins.setString(7, userC[2]);
            ins.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet Query(String query) {
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            Statement s = c.createStatement();
            ResultSet res = s.executeQuery(query);
            return res;
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            c.close();
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
