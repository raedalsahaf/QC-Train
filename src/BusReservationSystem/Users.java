/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusReservationSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author XPS 15 OYMSLV
 */
public class Users {
//

    public static ArrayList<String> user = new ArrayList<String>();
    public static ArrayList<String> pass = new ArrayList<String>();

    private Connection connect() throws SQLException {
        Connection con;
        String url = "jdbc:sqlite:C:/Users/Osama basudan/OneDrive/Documents/NetBeansProjects/DataBase1/BusProject.db";
        con = DriverManager.getConnection(url);
        System.out.println("The Connection established");
        return con;
    }

    public void AddUser(String username, String password, String email, String phon, String addr) throws SQLException {

        String sql1 = "INSERT INTO USERS (USER_NAME,PASSWORD,EMAIL,PHONE_NUMBER,ADDRESS)VALUES('" + username + "','" + password + "','" + email + "','" + phon + "','" + addr + "')";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql1);
        st.close();

        // con.commit();
        con.close();
        System.out.println("User Created Successfully");

    }
    int x;

    public boolean LoginUser(String username, String password) throws SQLException {
        boolean t = false;
        String sql = "SELECT * FROM USERS";
        try {
            Connection con = this.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                x = rs.getInt("ID");

                String name = rs.getString("USER_NAME");
                String pass = rs.getString("PASSWORD");
                if (username.equals(name) && password.equals(pass)) {
                    t = true;
                    setX(x);
                    break;
                } else {
                    t = false;
                }
            }
            st.close();
            //con.commit();
            con.close();

        } catch (SQLException e) {
        }
        return t;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public ResultSet View(String s, String d, String date) throws SQLException {
        String sql = "SELECT B.BUS_ID, R.SOURCE, R.DESTINATION,R.DATE,R.TIME, R.PRICE, B.SEATS FROM BUS B JOIN ROUTE R ON B.ROUTE_ID=R.ROUTE_ID WHERE R.SOURCE LIKE '" + s + "' AND R.DESTINATION LIKE '" + d + "'AND R.DATE ='" + date + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
        //con.commit();
    }

    public ResultSet getSource() throws SQLException {
        String sql = "SELECT DISTINCT SOURCE FROM ROUTE ";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet getDes() throws SQLException {
        String sql = "SELECT distinct DESTINATION FROM ROUTE ";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
        
    }

    public void addTicket(int bus_id, String seat, int user_id) throws SQLException {
        
        String sql = "INSERT INTO BOOKING (BUS_ID,SEAT_NUMBER,USER_ID)VALUES ('" + bus_id + "','" + seat + "','" + user_id + "')";
        try {
          Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();

        // con.commit();
        con.close();
   
        } catch (SQLException e) {
            System.err.println(e);
        }
       
        System.out.println("Created Successfully");
    }

    public ResultSet getBusInfo(int id) throws SQLException {
        String sql = "SELECT B.BUS_ID, R.SOURCE, R.DESTINATION, R.TIME, R.PRICE, B.SEATS FROM BUS B JOIN ROUTE R ON B.ROUTE_ID=R.ROUTE_ID WHERE B.BUS_ID = '" + id + "' ";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet ViewUserBooking(int id) throws SQLException {
        String sql = "SELECT B.BUS_ID,B.BUS_MODEL,R.SOURCE,R.DESTINATION,T.USER_ID,R.TIME,R.DATE, R.PRICE, T.SEAT_NUMBER FROM BOOKING T JOIN BUS B ON T.BUS_ID=B.BUS_ID JOIN ROUTE R ON B.ROUTE_ID=R.ROUTE_ID WHERE t.user_ID = '" + id + "' ";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet userInfo(int id) throws SQLException {

        String sql = "SELECT * FROM USERS WHERE ID ='" + id + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public boolean check(int bus_id, int user_id) throws SQLException {
        boolean t = true;

        String sql = "SELECT * FROM BOOKING ";
        try {
            Connection con = this.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                x = rs.getInt("BOOKING_ID");
                int bus = rs.getInt("BUS_ID");
                int user = rs.getInt("USER_ID");
                System.out.println(x + "  mkkkkkk");
                if (bus_id == bus && user_id == user) {
                    t = false;
                    break;
                } else {
                    t = true;
                }
            }
            st.close();
            //con.commit();
            con.close();
            return t;
        } catch (SQLException e) {
        }
        return t;
    }

    public boolean checkSeat(int bus_id, String seat) throws SQLException {
        boolean t = true;

        String sql = "SELECT * FROM BOOKING WHERE  BUS_ID='" + bus_id + "'AND SEAT_NUMBER='" + seat + "'";
        try {
            Connection con = this.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String seatn = rs.getString("SEAT_NUMBER");
                System.out.println(x + "  mkkkkkk");
                if (seat.equals(seatn)) {
                    t = false;
                    break;
                } else {
                    t = true;
                }
            }
            st.close();
            //con.commit();
            con.close();

        } catch (SQLException e) {
        }
        return t;
    }

    public void deleteBooking(int bus_id, int user_id) throws SQLException {
        String sql = "DELETE FROM BOOKING WHERE BUS_ID='" + bus_id + "' AND USER_ID='" + user_id + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        //con.commit();
        con.close();
        System.out.println(" Deleted Successfully");
    }

    public ResultSet ViewWithDate(String s, String d, LocalDate date) throws SQLException {
        String sql = "SELECT B.BUS_ID, R.SOURCE, R.DESTINATION,R.DATE,R.TIME, R.PRICE, B.SEATS FROM BUS B JOIN ROUTE R ON B.ROUTE_ID=R.ROUTE_ID WHERE R.SOURCE LIKE '" + s + "' AND R.DESTINATION LIKE '" + d + "'AND R.DATE LIKE '" + date + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;

    }

    public ResultSet userGetName(String s) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE USER_NAME LIKE '" + s + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet getCount(int bus_id) throws SQLException {
        String sql = "SELECT COUNT(BOOKING_ID) FROM BOOKING WHERE BUS_ID = '" + bus_id + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }
     public boolean checkRegistration(String username, String password) throws SQLException {
        boolean t = false;
        String sql = "SELECT * FROM USERS";
        try {
            Connection con = this.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                x = rs.getInt("ID");

                String name = rs.getString("USER_NAME");
                String pass = rs.getString("PASSWORD");
                if (username.equals(name) && password.equals(pass)) {
                    t = true;
                    break;
                } else {
                    t = false;
                }
            }
            st.close();
            //con.commit();
            con.close();

        } catch (SQLException e) {
        }
        return t;
    }
     
      public ResultSet ViewAll() throws SQLException {
        String sql = "SELECT B.BUS_ID, R.SOURCE, R.DESTINATION,R.DATE,R.TIME, R.PRICE, B.SEATS FROM BUS B JOIN ROUTE R ON B.ROUTE_ID=R.ROUTE_ID";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
        //con.commit();
    }
}
