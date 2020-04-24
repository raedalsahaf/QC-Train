/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusReservationSystem;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class AdminDisplayController implements Initializable {

    @FXML
    private TableView<Table> tableBooking;
    @FXML
    private TableColumn<Table, Integer> user;
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
    @FXML
    private Button backbutton;
    ObservableList<Table> oblist = FXCollections.observableArrayList();
    Admin a = new Admin();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        user.setCellValueFactory(new PropertyValueFactory<>("User"));
        bus_number.setCellValueFactory(new PropertyValueFactory<>("Bus_number"));
        paid.setCellValueFactory(new PropertyValueFactory<>("Paid"));
        seat_Number.setCellValueFactory(new PropertyValueFactory<>("Seat_Number"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));

        a = new Admin();
        try {
            ResultSet rs = a.ViewBooking();
            while (rs.next()) {
                oblist.add(new Table(rs.getInt("USER_ID"), rs.getInt("BUS_ID"), "", "", rs.getString("PRICE"), rs.getString("SEAT_NUMBER"), rs.getString("DATE"), rs.getString("TIME")));

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableBooking.setItems(oblist);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        backbutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
