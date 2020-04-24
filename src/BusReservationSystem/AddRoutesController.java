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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class AddRoutesController implements Initializable {

    @FXML
    private TextField txtSource;
    @FXML
    private TextField txtDest;
    @FXML
    private Button addbutton;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtTime;
    @FXML
    private Label lblout;
    Admin a = new Admin();
    @FXML
    private Button backbutton;
    @FXML
    private DatePicker txtdate;
    @FXML
    private Button clearbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void add(ActionEvent event) throws SQLException {
        if (txtSource.getText().isEmpty()) {
            lblout.setText("Enter source");
        } else if (txtDest.getText().isEmpty()) {
            lblout.setText("Enter destination");
        } else if (txtdate.getValue() == null) {
            lblout.setText("Enter date");
        } else if (txtTime.getText().isEmpty()) {
            lblout.setText("Enter time");
        } else if (txtPrice.getText().isEmpty()) {
            lblout.setText("Enter price");
        } else {
            String date = txtdate.getValue().toString();
            a.addRoute(txtSource.getText(), txtDest.getText(), date, txtTime.getText(), txtPrice.getText());
            lblout.setText("Route added");
        }
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

    @FXML
    private void clear(ActionEvent event) {
        txtSource.clear();
        txtDest.clear();
        txtTime.clear();
        txtPrice.clear();
        txtdate.setValue(null);
        lblout.setText("");

    }
}
