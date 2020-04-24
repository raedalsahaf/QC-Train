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
public class AdminBusDetController implements Initializable {

    @FXML
    private TableView<Bus> tableview;
    @FXML
    private TableColumn<Bus, String> bus_model;
    @FXML
    private TableColumn<Bus, String> seats;
    Admin a = new Admin();

    ObservableList<Bus> oblist = FXCollections.observableArrayList();
    @FXML
    private Button backbutton;
    @FXML
    private TableColumn<Bus, String> bus_id;
    @FXML
    private TableColumn<Bus, String> Route_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bus_id.setCellValueFactory(new PropertyValueFactory<>("Bus_id"));
        bus_model.setCellValueFactory(new PropertyValueFactory<>("Bus_model"));
        seats.setCellValueFactory(new PropertyValueFactory<>("Seats"));
        Route_id.setCellValueFactory(new PropertyValueFactory<>("Route_id"));

        a = new Admin();
        try {
            ResultSet rs = a.ViewBus();
            while (rs.next()) {
                oblist.add(new Bus(rs.getString("BUS_ID"), rs.getString("BUS_MODEL"), rs.getString("SEATS"), rs.getString("ROUTE_ID")));

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminBusDetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.setItems(oblist);
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        backbutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
