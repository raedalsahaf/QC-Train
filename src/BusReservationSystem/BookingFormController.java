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
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class BookingFormController {

    @FXML
    private Button cancelbutton;
    Users u = new Users();

    @FXML
    private Label lblBusN;
    @FXML
    private Label lblDes;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblSource;
    @FXML
    private Label lblPrice;
    @FXML
    private Button savebutton;
    int userid, busid;
    @FXML
    private TextField txtseatnumber;
    @FXML
    private Label lblseats;
    @FXML
    private Label lblout;

    /**
     * Initializes the controller class.
     *
     */
    public void getI(int get, int getBus) {
        System.out.println("Data is :" + get + " " + getBus);
        setUserid(get);
        setBusid(getBus);
        u = new Users();
        try {
            ResultSet rs = u.getBusInfo(getBusid());

            lblBusN.setText(rs.getString("BUS_ID"));
            lblSource.setText(rs.getString("SOURCE"));
            lblDes.setText(rs.getString("DESTINATION"));
            lblPrice.setText(rs.getString("PRICE"));
            lblTime.setText(rs.getString("TIME"));
            int s = Integer.parseInt(rs.getString("SEATS"));
            ResultSet rs3 = u.getCount(getBus);
            int c = rs3.getInt("COUNT(BOOKING_ID)");
            // TODO

            String a = String.valueOf(s - c);
            lblseats.setText(a);
            rs.close();
            rs3.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
            cancelbutton.getScene().getWindow().hide();
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
    private void save(ActionEvent event) throws IOException, SQLException {
        if (txtseatnumber.getText().isEmpty()) {
            lblout.setText("Enter Seat Number");
        } else {
            if (u.checkSeat(getBusid(), txtseatnumber.getText()) == false) {
                lblout.setText("The seat is booked");
            } else {
                   if (u.check(getBusid(), getUserid()) == true) {
                try {
                    u.addTicket(getBusid(), txtseatnumber.getText(), getUserid());

                } catch (SQLException e) {
                    System.err.println(e);
                }

                try {

                    savebutton.getScene().getWindow().hide();
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
                else {
                    JOptionPane.showMessageDialog(null, "You have been book this route");
                    try {
                        cancelbutton.getScene().getWindow().hide();
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
            }

        }
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBusid() {
        return busid;
    }

    public void setBusid(int busid) {
        this.busid = busid;
    }

}
