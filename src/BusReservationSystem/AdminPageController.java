/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusReservationSystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */

public class AdminPageController implements Initializable {

    @FXML
    private MenuButton busmangebutton;
    @FXML
    private MenuButton routebutton;
    @FXML
    private Button exitButton;
    @FXML
    private MenuItem mr1;
    @FXML
    private MenuItem mr2;
    @FXML
    private MenuItem mr3;
    @FXML
    private MenuItem mr4;
    @FXML
    private MenuItem mu1;
    @FXML
    private MenuItem mu2;
    @FXML
    private MenuItem busDetailes;
    @FXML
    private MenuItem addBus;
    @FXML
    private MenuItem editBus;
    @FXML
    private MenuItem removeBus;
    @FXML
    private MenuButton userbutton;
    @FXML
    private MenuItem adminDetbutton;
    @FXML
    private MenuItem addAdminbutton;
    @FXML
    private MenuButton adminbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    public void busDetailes(ActionEvent event) throws IOException {
        busmangebutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AdminBusDet.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addBus(ActionEvent event) throws IOException {
        busmangebutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AddBus.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void editBus(ActionEvent event) throws IOException {
        busmangebutton.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("EditBus.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void removeBus(ActionEvent event) throws IOException {
        busmangebutton.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("RemoveBus.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void mr1(ActionEvent event) throws IOException {
        routebutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AdminRouteDet.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void mr2(ActionEvent event) throws IOException {
        routebutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AddRoutes.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void mr3(ActionEvent event) throws IOException {
        routebutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("EditRoute.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void mr4(ActionEvent event) throws IOException {
        routebutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("RemoveRoute.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void mu1(ActionEvent event) throws IOException {
        userbutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AdminUsers.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void mu2(ActionEvent event) throws IOException {
        userbutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AdminDisplay.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void exit(ActionEvent event) throws IOException {
JOptionPane.showMessageDialog(null, "Thank you");
        exitButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void adminDetailes(ActionEvent event) throws IOException {
        adminbutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AdminDetailes.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addAdmin(ActionEvent event) throws IOException {
        adminbutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("AddAdmin.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
