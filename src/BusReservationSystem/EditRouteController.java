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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class EditRouteController implements Initializable {

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
    private TextField txtsource;
    @FXML
    private TextField txtprice;
    @FXML
    private TextField txtdestination;
    @FXML
    private TextField txttime;
    @FXML
    private TextField txtdate;
    @FXML
    private Button editbutton;
    Admin a = new Admin();
    int x;
    ObservableList<Route> oblist = FXCollections.observableArrayList();
    @FXML
    private Button backbutton;
    @FXML
    private TextField txtSsoursce;
    @FXML
    private TextField txtSdestination;
    @FXML
    private Button searchbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            Logger.getLogger(EditRouteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.setItems(oblist);
    }

    @FXML
    private void getRow(MouseEvent event) {
        int index = tableview.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        Route route = tableview.getItems().get(index);
        setX(route.getRoute_id());
        txtsource.setText(route.getSource());
        txtdestination.setText(route.getDestination());
        txtdate.setText(route.getDate());
        txttime.setText(route.getTime());
        txtprice.setText(route.getPrice());
        editbutton.setDisable(false);

    }

    @FXML
    private void edit(ActionEvent event) throws IOException, SQLException {
        a = new Admin();
        a.editRoute(getX(), txtsource.getText(), txtdestination.getText(), txtdate.getText(), txttime.getText(), txtprice.getText());

        tableview.getItems().clear();
        try {
            ResultSet rs = a.ViewRoute();
            while (rs.next()) {
                oblist.add(new Route(rs.getInt("ROUTE_ID"), rs.getString("SOURCE"), rs.getString("DESTINATION"), rs.getString("DATE"), rs.getString("TIME"), rs.getString("PRICE")));

            }
// TODO
        } catch (SQLException ex) {
            Logger.getLogger(EditRouteController.class.getName()).log(Level.SEVERE, null, ex);
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @FXML
    private void search(KeyEvent event) {
        String source1 = txtSsoursce.getText();
        String destination1 = txtSdestination.getText();

        tableview.getItems().clear();

        txtsource.clear();
        txtdestination.clear();
        txtdate.clear();
        txttime.clear();
        txtprice.clear();

        editbutton.setDisable(true);
        a = new Admin();
        try {
            ResultSet rs = a.searchRoute(source1, destination1);
            while (rs.next()) {
                oblist.add(new Route(rs.getInt("ROUTE_ID"), rs.getString("SOURCE"), rs.getString("DESTINATION"), rs.getString("DATE"), rs.getString("TIME"), rs.getString("PRICE")));
            }
// TODO
        } catch (SQLException ex) {
            Logger.getLogger(EditRouteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.setItems(oblist);
    }

}
