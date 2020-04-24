/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusReservationSystem;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class ReservationController {

    private Label lbloutemail;
    private Label lbloutname;
    @FXML
    private Button exitbutton;

    private Label lbloutnumber;
    @FXML
    private MenuButton bookbutton;
    @FXML
    private MenuItem m1;
    @FXML
    private MenuItem m2;
    Users u = new Users();
    private Label lbladdress;
    int userid;
    @FXML
    private MenuItem m3;
    UserController u2 = new UserController();

    /**
     * Initializes the controller class.
     *
     * @param get
     */
    public void getI(int get) {
        System.out.println("Get is: :" + get);
        setUserid(get);
        System.out.println(getUserid());
//        u = new Users();
//        try {
//            ResultSet rs = u.userInfo(get);
//            while (rs.next()) {
//                lbloutname.setText(rs.getString("USER_NAME"));
//                lbloutemail.setText(rs.getString("EMAIL"));
//                lbloutnumber.setText(rs.getString("PHONE_NUMBER"));
//                lbladdress.setText(rs.getString("ADDRESS"));
//            }
//            rs.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    @FXML
    private void m1(ActionEvent event) throws IOException {

        try {
            bookbutton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewBooking.fxml"));
            Parent root = loader.load();
            NewBookingController n = loader.getController();
            n.getI(getUserid());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void m2(ActionEvent event) throws IOException {
        try {
            bookbutton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MyBooking.fxml"));
            Parent root = loader.load();
            MyBookingController m = loader.getController();
            m.getI(getUserid());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
        JOptionPane.showMessageDialog(null, "Thank you");

        exitbutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void m3(ActionEvent event) throws IOException {
        try {
            bookbutton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDelete.fxml"));
            Parent root = loader.load();
            UserDeleteController u = loader.getController();
            u.getI(getUserid());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

}
