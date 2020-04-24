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
public class EditBusController implements Initializable {

    @FXML
    private TableView<Bus> tableview;
    @FXML
    private TableColumn<Bus, String> bus_model;
    @FXML
    private TableColumn<Bus, String> seats;
    @FXML
    private TableColumn<Bus, String> route_id;
    ObservableList<Bus> oblist = FXCollections.observableArrayList();
    Admin a = new Admin();
    @FXML
    private TextField txtbus_model;
    @FXML
    private TextField txtseats;
    @FXML
    private TextField txtroute_id;
    @FXML
    private Button editbutton;
    @FXML
    private Button backbutton;
    String x;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bus_model.setCellValueFactory(new PropertyValueFactory<>("Bus_model"));
        seats.setCellValueFactory(new PropertyValueFactory<>("Seats"));
        route_id.setCellValueFactory(new PropertyValueFactory<>("Route_id"));
        try {
            a = new Admin();
            ResultSet rs = a.ViewBus();
            while (rs.next()) {
                oblist.add(new Bus(rs.getString("BUS_ID"), rs.getString("BUS_MODEL"), rs.getString("SEATS"), rs.getString("ROUTE_ID")));
            }
                        rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditBusController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.setItems(oblist);

    }

    @FXML
    private void getRows(MouseEvent event) {
        int index = tableview.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        Bus buses = tableview.getItems().get(index);
        setX(buses.getBus_id());
        txtbus_model.setText(buses.getBus_model());
        txtseats.setText(buses.getSeats());
        txtroute_id.setText(buses.getRoute_id());

    }

    @FXML
    private void edit(ActionEvent event) throws IOException, SQLException {
        a = new Admin();
        a.editBus(txtbus_model.getText(), txtseats.getText(), getX(),txtroute_id.getText());
        tableview.getItems().clear();
        try {
            a = new Admin();
            ResultSet rs = a.ViewBus();
            while (rs.next()) {
                oblist.add(new Bus(rs.getString("BUS_ID"), rs.getString("BUS_MODEL"), rs.getString("SEATS"), rs.getString("ROUTE_ID")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditBusController.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getX() {
        System.out.println("Gettt :" + x);

        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

}
