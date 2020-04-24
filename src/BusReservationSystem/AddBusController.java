/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusReservationSystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class AddBusController implements Initializable {

    @FXML
    private TextField txtbusmodel;
    @FXML
    private TextField txtseats;
    @FXML
    private Button savebutton;
    @FXML
    private Button cancelbutton;
    @FXML
    private Label lblout;
    Admin a = new Admin();
    int id;
    @FXML
    private TableView<Route> tableview;
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
    private TextField txtroute;
    ObservableList<Route> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Route, Integer> route_id;
    @FXML
    private Button clearbutton;

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
            Logger.getLogger(AddBusController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.setItems(oblist);

    }

    @FXML
    public void save(ActionEvent event) throws SQLException {
        a = new Admin();
        if (txtbusmodel.getText().isEmpty()) {
            lblout.setText("Enter bus model");
        } else if (txtseats.getText().isEmpty()) {
            lblout.setText("Enter seats number");
        } else {
            int x = Integer.parseInt(txtroute.getText());
            a.addBus(txtbusmodel.getText(), txtseats.getText(), x);
            lblout.setText("Bus added");
        }

    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        cancelbutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FXML
    private void getRow(MouseEvent event) {
        int index = tableview.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        Route route = tableview.getItems().get(index);
        setId(route.getRoute_id());
        String r = String.valueOf(route.getRoute_id());
        txtroute.setText(r);
    }

    @FXML
    private void clear(ActionEvent event) {
        txtbusmodel.clear();
        txtseats.clear();
        txtroute.clear();
        lblout.setText("");
    }
}
