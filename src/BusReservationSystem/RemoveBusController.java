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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class RemoveBusController implements Initializable {

    @FXML
    private TableView<Bus> tableview;
    @FXML
    private TableColumn<Bus, String> bus_id;
    @FXML
    private TableColumn<Bus, String> bus_model;
    @FXML
    private TableColumn<Bus, String> seats;
    @FXML
    private TableColumn<Bus, String> route_id;
    Admin a = new Admin();
    ObservableList<Bus> oblist = FXCollections.observableArrayList();

    @FXML
    private Button removebutton;
    @FXML
    private Button backbutton;
    @FXML
    private TextField txtdelete;
    int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bus_id.setCellValueFactory(new PropertyValueFactory<>("Bus_id"));
        bus_model.setCellValueFactory(new PropertyValueFactory<>("Bus_model"));
        seats.setCellValueFactory(new PropertyValueFactory<>("Seats"));
        route_id.setCellValueFactory(new PropertyValueFactory<>("Route_id"));

        a = new Admin();
        try {
            ResultSet rs = a.ViewBus();
            while (rs.next()) {
                oblist.add(new Bus(rs.getString("BUS_ID"), rs.getString("BUS_MODEL"), rs.getString("SEATS"), rs.getString("ROUTE_ID")));

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemoveBusController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.setItems(oblist);
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

    @FXML
    private void getRow(MouseEvent event) {
        int index = tableview.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        Bus buses = tableview.getItems().get(index);
        txtdelete.setText("Bus id: " + buses.getBus_id() + "    Bus model: " + buses.getBus_model() + "    Seats: " + buses.getSeats() + "    Route_id: " + buses.getRoute_id());
        Integer x = Integer.parseInt(buses.getBus_id());
        setId(x);

    }

    @FXML
    private void remove(ActionEvent event) throws IOException, SQLException {
        a = new Admin();
        int x = getId();
        System.out.println("Remove x :" + x);
        a.removeBus(x);
        tableview.getItems().clear();
        try {
            ResultSet rs = a.ViewBus();
            while (rs.next()) {
                oblist.add(new Bus(rs.getString("BUS_ID"), rs.getString("BUS_MODEL"), rs.getString("SEATS"), rs.getString("ROUTE_ID")));

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemoveBusController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.setItems(oblist);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
