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
public class AdminUsersController implements Initializable {

    @FXML
    private TableView<UsersTable> tableview;
    @FXML
    private TableColumn<UsersTable, String> colname;
    @FXML
    private TableColumn<UsersTable, String> colpassword;
    @FXML
    private TableColumn<UsersTable, String> colemail;
    @FXML
    private TableColumn<UsersTable, String> colnumber;
    @FXML
    private TableColumn<UsersTable, String> coladdress;

    Admin a = new Admin();

    ObservableList<UsersTable> oblist = FXCollections.observableArrayList();
    @FXML
    private Button backbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));

        colpassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colnumber.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("Address"));

        try {
            ResultSet rs = a.ViewUsers();
            while (rs.next()) {
                oblist.add(new UsersTable(rs.getString("USER_NAME"), rs.getString("PASSWORD"), rs.getString("EMAIL"), rs.getString("PHONE_NUMBER"), rs.getString("ADDRESS")));

            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminUsersController.class.getName()).log(Level.SEVERE, null, ex);
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
