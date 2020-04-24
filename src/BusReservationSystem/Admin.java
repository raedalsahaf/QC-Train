/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusReservationSystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mohammed
 */
public class Admin {

    private Connection connect() throws SQLException {
        Connection con;
        String url = "jdbc:sqlite:C:/Users/Osama basudan/OneDrive/Documents/NetBeansProjects/DataBase1/BusProject.db";
        con = DriverManager.getConnection(url);
        System.out.println("The Connection established");
        return con;
    }

    public boolean LoginAdmin(String admin_name, String password) {
        boolean t = false;
        String sql = "SELECT * FROM ADMIN";
        try {
            Connection con = this.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                String name = rs.getString("ADMIN_NAME");
                String pass = rs.getString("PASSWORD");
                if (admin_name.equals(name) && password.equals(pass)) {
                    t = true;
                    break;
                } else {
                    t = false;
                }
            }
            st.close();
            //con.commit();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public void addBus(String busModel, String seats, int route) throws SQLException {

        String sql1 = "INSERT INTO BUS (BUS_MODEL,SEATS,ROUTE_ID)VALUES('" + busModel + "','" + seats + "','" + route + "')";
        Connection con = this.connect();
        System.out.println("222222");
        Statement st = con.createStatement();
        System.out.println("333333333");
        st.executeUpdate(sql1);
        st.close();
        System.out.println("444444444");

        // con.commit();
        con.close();
        System.out.println("User Created Successfully");

    }

    public void addRoute(String source, String destination, String date, String time, String price) throws SQLException {
        String sql1 = "INSERT INTO ROUTE (SOURCE,DESTINATION,DATE,TIME,PRICE)VALUES('" + source + "','" + destination + "','" + date + "','" + time + "','" + price + "')";
        System.out.println("222224");
        Connection con = this.connect();
        System.out.println("222222");
        Statement st = con.createStatement();
        System.out.println("333333333");
        st.executeUpdate(sql1);
        st.close();
        System.out.println("444444444");

        // con.commit();
        con.close();
        System.out.println("User Created Successfully");
    }

    public ResultSet ViewUsers() throws SQLException {
        String sql = "SELECT USER_NAME, PASSWORD, EMAIL, PHONE_NUMBER, ADDRESS FROM USERS";
        System.out.println("222222222");
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet ViewBus() throws SQLException {
        String sql = "SELECT * FROM BUS";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet ViewRoute() throws SQLException {
        String sql = "SELECT * FROM ROUTE";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet ViewRouteSource() throws SQLException {
        String sql = "SELECT DISTINCT SOURCE FROM ROUTE";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet ViewRouteDest() throws SQLException {
        String sql = "SELECT DISTINCT DESTINATION FROM ROUTE";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet ViewBooking() throws SQLException {
        String sql = "SELECT T.BUS_ID,T.USER_ID,R.TIME,R.DATE, R.PRICE, T.SEAT_NUMBER FROM BOOKING T JOIN BUS B ON T.BUS_ID=B.BUS_ID JOIN ROUTE R ON B.ROUTE_ID=R.ROUTE_ID ";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public void editBus(String busmodel, String seats, String id, String r) throws IOException, SQLException {
//Edit the data to Database
        //int id = Integer.parseInt(x);
        String sql = "UPDATE BUS SET BUS_MODEL ='" + busmodel + "',SEATS ='" + seats + "',ROUTE_ID ='" + r + "' where bus_id='" + id + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        //con.commit();
        con.close();
        System.out.println("User Updated Successfully");
    }

    public void removeBus(int id) throws IOException, SQLException {
        String sql = "DELETE FROM BUS WHERE BUS_ID='" + id + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        //con.commit();
        con.close();
        System.out.println(" Removed Successfully");
    }

    public void removeRoute(int id) throws IOException, SQLException {
        String sql = "DELETE FROM ROUTE WHERE ROUTE_ID='" + id + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        //con.commit();
        con.close();
        System.out.println(" Removed Successfully");
    }

    public void editRoute(int id, String s, String d, String da, String t, String p) throws IOException, SQLException {
        String sql = "UPDATE ROUTE  SET SOURCE ='" + s + "',DESTINATION ='" + d + "' ,DATE='" + da + "', TIME ='" + t + "', PRICE='" + p + "'WHERE ROUTE_ID='" + id + "'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        //con.commit();
        con.close();
        System.out.println(" Removed Successfully");
    }

    public ResultSet searchRoute(String s, String d) throws SQLException {
        String sql = "SELECT *  FROM ROUTE where source like '" + s + "%' AND DESTINATION LIKE '" + d + "%'";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }
     public void AddAdmin(String adminName, String password, String email, String phon, String addr) throws SQLException {
//        user.add(username);
//        pass.add(password);
        String sql1 = "INSERT INTO ADMIN (ADMIN_NAME,PASSWORD,EMAIL,PHONE_NUMBER,ADDRESS)VALUES('" + adminName + "','" + password + "','" + email + "','" + phon + "','" + addr + "')";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql1);
        st.close();

        // con.commit();
        con.close();
        System.out.println("Created Successfully");

    }
     public ResultSet ViewAdmins() throws SQLException {
        String sql = "SELECT ADMIN_NAME, PASSWORD, EMAIL, PHONE_NUMBER, ADDRESS FROM ADMIN";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

}
