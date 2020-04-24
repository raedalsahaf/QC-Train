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
public class RemoveRouteController implements Initializable {

    @FXML
    private TableView<Route> tableview;
    @FXML
    private TableColumn<Route, String> route_id;
    @FXML
    private TableColumn<Route, String> source;
    @FXML
    private TableColumn<Route, String> destination;
    @FXML
    private TableColumn<Route, String> date;
    @FXML
    private TableColumn<Route, String> time;
    @FXML
    private TableColumn<Route, String> price;
    @FXML
    private TextField txtroute_id;
    @FXML
    private Button backbutton;
    @FXML
    private Button removebutton;
    ObservableList<Route> oblist = FXCollections.observableArrayList();
    Admin a = new Admin();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        route_id.setCellValueFactory(new PropertyValueFactory<>("Route_id"));
        source.setCellValueFactory(new PropertyValueFactory<>("Source"));
        destination.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        a = new Admin();

        try {
            ResultSet rs = a.ViewRoute();
            while (rs.next()) {
                oblist.add(new Route(rs.getInt("ROUTE_ID"), rs.getString("SOURCE"), rs.getString("DESTINATION"), rs.getString("DATE"), rs.getString("TIME"), rs.getString("PRICE")));

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemoveRouteController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void remove(ActionEvent event) throws IOException, SQLException {
        a = new Admin();
        int x = Integer.parseInt(txtroute_id.getText());
        System.out.println("Remove x :" + x);
        a.removeRoute(x);
        tableview.getItems().clear();
        try {
            ResultSet rs = a.ViewRoute();
            while (rs.next()) {
                oblist.add(new Route(rs.getInt("ROUTE_ID"), rs.getString("SOURCE"), rs.getString("DESTINATION"), rs.getString("DATE"), rs.getString("TIME"), rs.getString("PRICE")));

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemoveRouteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.setItems(oblist);
    }

    @FXML
    private void getRow(MouseEvent event) {
        int index = tableview.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        Route route = tableview.getItems().get(index);
        String x = String.valueOf(route.getRoute_id());
        txtroute_id.setText(x);
    }

}
