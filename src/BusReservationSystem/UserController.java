/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusReservationSystem;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class UserController {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button logbutton;
    @FXML
    private Button regbutton;
    @FXML
    private Label ulLabel;
    @FXML
    private Button backbutton;
    @FXML
    private Label lblout;
    int p;
    Users u1 = new Users();

    /**
     * Initializes the controller class.
     */
    @FXML
    private void register(ActionEvent event) throws IOException {
        regbutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException {

        if (txtUser.getText().isEmpty()) {
            lblout.setText("Please enter user name");
        } else if (txtPass.getText().isEmpty()) {
            lblout.setText("Please enter password");
        } else if (u1.LoginUser(txtUser.getText(), txtPass.getText()) == true) {
            int i = u1.getX();
            JOptionPane.showMessageDialog(null, "Welcome");

            try {
                logbutton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Reservation.fxml"));
                Parent root = loader.load();
                ReservationController r = loader.getController();
                r.getI(i);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);

                stage.show();

            } catch (IOException ex) {
                System.err.println(ex);
            }
        } else {
            //lblout.setText("Wrong user name or password");
            JOptionPane.showMessageDialog(null, "Wrong user name or password");
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
