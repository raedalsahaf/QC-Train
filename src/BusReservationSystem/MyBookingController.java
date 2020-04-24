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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class MyBookingController {

    @FXML
    private Button backbutton;

    Users u = new Users();
    @FXML
    private TableView<Table> tableBooking;
    @FXML
    private TableColumn<Table, Integer> bus_number;
    @FXML
    private TableColumn<Table, String> paid;
    @FXML
    private TableColumn<Table, String> seat_Number;
    @FXML
    private TableColumn<Table, String> date;
    @FXML
    private TableColumn<Table, String> time;
    ObservableList<Table> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Table, String> source;
    @FXML
    private TableColumn<Table, String> destination;
int userid;
    /**
     * Initializes the controller class.
     */

public void getI(int get){
    setUserid(get);
    bus_number.setCellValueFactory(new PropertyValueFactory<>("Bus_number"));
        source.setCellValueFactory(new PropertyValueFactory<>("Source"));
        destination.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        paid.setCellValueFactory(new PropertyValueFactory<>("Paid"));
        seat_Number.setCellValueFactory(new PropertyValueFactory<>("Seat_Number"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));

        u = new Users();
        try {
            ResultSet rs = u.ViewUserBooking(get);
            while (rs.next()) {
                oblist.add(new Table(rs.getInt("USER_ID"), rs.getInt("BUS_ID"), rs.getString("SOURCE"),rs.getString("DESTINATION"),rs.getString("PRICE"), rs.getString("SEAT_NUMBER"), rs.getString("DATE"), rs.getString("TIME")));

            }
            
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(MyBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableBooking.setItems(oblist);
}
    @FXML
    public void back(ActionEvent event) throws IOException {
         try {
            backbutton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reservation.fxml"));
            Parent root = loader.load();
            ReservationController r = loader.getController();
            r.getI(getUserid());
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
