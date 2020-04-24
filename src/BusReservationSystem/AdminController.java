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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class AdminController implements Initializable {

    @FXML
    private Button logbutton;
    @FXML
    private Button backbutton;
    @FXML
    private Label lblout;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtpass;
    Admin a = new Admin();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        a = new Admin();
        if (txtname.getText().isEmpty()) {
            lblout.setText("Enter admin id");
        } else if (txtpass.getText().isEmpty()) {
            lblout.setText("Enter password");
        } else if (a.LoginAdmin(txtname.getText(), txtpass.getText()) == true) {
            JOptionPane.showMessageDialog(null, "Welcome");

            backbutton.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } else {
            lblout.setText("Wrong admin id");
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        backbutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
