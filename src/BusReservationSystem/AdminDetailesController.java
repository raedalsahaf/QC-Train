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
public class AdminDetailesController implements Initializable {

    @FXML
    private TableView<AdminTable> tableview;
    @FXML
    private TableColumn<AdminTable, String> admin_name;
    @FXML
    private TableColumn<AdminTable, String> phone_number;
    @FXML
    private TableColumn<AdminTable, String> password;
    @FXML
    private TableColumn<AdminTable, String> email;
    @FXML
    private TableColumn<AdminTable, String> address;
    @FXML
    private Button backbutton;
    Admin a = new Admin();

    ObservableList<AdminTable> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        a = new Admin();
        admin_name.setCellValueFactory(new PropertyValueFactory<>("Admin_name"));

        password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        phone_number.setCellValueFactory(new PropertyValueFactory<>("Phone_number"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));

        try {
            ResultSet rs = a.ViewAdmins();
            while (rs.next()) {
                oblist.add(new AdminTable(rs.getString("ADMIN_NAME"), rs.getString("PASSWORD"), rs.getString("EMAIL"), rs.getString("PHONE_NUMBER"), rs.getString("ADDRESS")));

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminDetailesController.class.getName()).log(Level.SEVERE, null, ex);
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

}
