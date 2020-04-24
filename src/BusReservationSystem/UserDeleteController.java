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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class UserDeleteController {

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
    private Button deletebutton;
    @FXML
    private Button backbutton;
    @FXML
    private TextField txtdelete;
    int busId;
    int userId;
    @FXML
    private Label lblout;
    @FXML
    private TableColumn<Table, String> source;
    @FXML
    private TableColumn<Table, String> destination;

    /**
     * Initializes the controller class.
     */
    public void getI(int get) {
        setUserId(get);
        bus_number.setCellValueFactory(new PropertyValueFactory<>("Bus_number"));
        source.setCellValueFactory(new PropertyValueFactory<>("Source"));
        destination.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        paid.setCellValueFactory(new PropertyValueFactory<>("Paid"));
        seat_Number.setCellValueFactory(new PropertyValueFactory<>("Seat_Number"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));

        u = new Users();
        try {
            ResultSet rs = u.ViewUserBooking(getUserId());
            while (rs.next()) {
                oblist.add(new Table(rs.getInt("USER_ID"), rs.getInt("BUS_ID"), rs.getString("SOURCE"), rs.getString("DESTINATION"), rs.getString("PRICE"), rs.getString("SEAT_NUMBER"), rs.getString("DATE"), rs.getString("TIME")));

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDeleteController.class.getName()).log(Level.SEVERE, null, ex);
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
            r.getI(getUserId());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void getRow(MouseEvent event) {
        int index = tableBooking.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        Table table = tableBooking.getItems().get(index);
        String x = String.valueOf(table.getUser());
        String y = String.valueOf(table.getBus_number());
        txtdelete.setText("Bus Number: " + y + "    Source: " + table.getSource() + "    Destination: " + table.getDestination() + "   Paid: " + table.getPaid() + "   Seat Number: " + table.getSeat_Number() + "   Date: " + table.getDate() + "   Time: " + table.getTime());
        setBusId(table.getBus_number());

    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        u = new Users();
        u.deleteBooking(getBusId(), getUserId());
        lblout.setText("Booking deleted");
        tableBooking.getItems().clear();
        txtdelete.clear();
        u = new Users();
        try {
            ResultSet rs = u.ViewUserBooking(getUserId());
            while (rs.next()) {
                oblist.add(new Table(rs.getInt("USER_ID"), rs.getInt("BUS_ID"), rs.getString("SOURCE"), rs.getString("DESTINATION"), rs.getString("PRICE"), rs.getString("SEAT_NUMBER"), rs.getString("DATE"), rs.getString("TIME")));

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableBooking.setItems(oblist);
    }

}
