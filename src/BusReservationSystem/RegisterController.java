/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusReservationSystem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class RegisterController implements Initializable {
    
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPass;
    @FXML
    private Button backbutton;
    Users u = new Users();
    @FXML
    private Label lblout;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtAddress;
    @FXML
    private Button regbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void register(ActionEvent event) throws SQLException {
        
        if (txtUser.getText().isEmpty()) {
            lblout.setText("Please Enter Username");
        } else if (txtPass.getText().isEmpty()) {
            lblout.setText("Please Enter Password");
        } else if (txtEmail.getText().isEmpty()) {
            lblout.setText("Please Enter Your Email");
        } else if (txtNumber.getText().isEmpty()) {
            lblout.setText("Please Enter Your Phone Number");
        } else if (txtAddress.getText().isEmpty()) {
            lblout.setText("Please Enter Your Address");
        } else {
            if (u.checkRegistration(txtUser.getText(), txtPass.getText()) == true) {
                lblout.setText("User name and password are used");
            } else {                
                u.AddUser(txtUser.getText(), txtPass.getText(), txtEmail.getText(), txtNumber.getText(), txtAddress.getText());
                lblout.setText("Registerd Successfully");
            }
        }
        
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException {
        backbutton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
}
