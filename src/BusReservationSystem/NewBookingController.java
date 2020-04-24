/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusReservationSystem;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Mohammed
 */
public class NewBookingController {
    
    private Label l;
    @FXML
    private Button searchbutton;
    @FXML
    private ChoiceBox<String> choicesourse = new ChoiceBox<String>();
    @FXML
    private ChoiceBox<String> choicedest;
    @FXML
    private Button backbutton;
    @FXML
    private TableColumn<Data, String> source;
    @FXML
    private TableColumn<Data, String> destination;
    @FXML
    private TableColumn<Data, String> time;
    @FXML
    private TableColumn<Data, String> price;
    @FXML
    private TableColumn<Data, String> seats;
    @FXML
    private TableView<Data> tableData;
    ObservableList<Data> oblist = FXCollections.observableArrayList();
    ObservableList<Integer> oblist2 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Data, String> bus_number;
    Users u = new Users();
    @FXML
    private TableColumn<Data, LocalDate> date;
    @FXML
    private DatePicker txtdate12;
    @FXML
    private Label lblout;
    int userid;
    @FXML
    private Button allB;

    /**
     * Initializes the controller class.
     */
    public void getI(int get) {
        System.out.println("Get is: :" + get);
        setUserid(get);
        try {
            u = new Users();
            ResultSet r1 = u.getSource();
            while (r1.next()) {
                choicesourse.getItems().add(r1.getString("SOURCE"));
            }
            r1.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(NewBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            u = new Users();
            ResultSet r1 = u.getDes();
            while (r1.next()) {
                choicedest.getItems().add(r1.getString("DESTINATION"));
            }
            r1.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(NewBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bus_number.setCellValueFactory(new PropertyValueFactory<>("Bus_number"));
        source.setCellValueFactory(new PropertyValueFactory<>("Source"));
        destination.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        seats.setCellValueFactory(new PropertyValueFactory<>("Seats"));
        
    }
    
    @FXML
    public void search(ActionEvent event) throws IOException {
        if (choicesourse.getValue() == null) {
            lblout.setText("Select Source");
        } else if (choicedest.getValue() == null) {
            lblout.setText("Select Destination");
        } else if (txtdate12.getValue() == null) {
            lblout.setText("Enter Date");
            
        } else {
            lblout.setText("");
            try {
                u = new Users();
                
                String s = choicesourse.getValue();
                
                String d = choicedest.getValue();
                String date1 = txtdate12.getValue().toString();
                
                tableData.getItems().clear();
                ResultSet rs = u.View(s, d, date1);
                while (rs.next()) {
                    oblist.add(new Data(rs.getInt("BUS_ID"), rs.getString("SOURCE"), rs.getString("DESTINATION"), rs.getString("DATE"), rs.getString("TIME"), rs.getString("PRICE"), rs.getString("SEATS")));
                    
                }
                rs.close();

// TODO
            } catch (SQLException ex) {
                Logger.getLogger(NewBookingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableData.setItems(oblist);
        }
    }
    
    @FXML
    public void back(ActionEvent event) throws IOException {
        try {
            choicedest.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reservation.fxml"));
            Parent root = loader.load();
            ReservationController r = loader.getController();
            r.getI(getUserid());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    @FXML
    private void getRow(MouseEvent event) throws IOException, SQLException {
        int index = tableData.getSelectionModel().getSelectedIndex();
        Data data = tableData.getItems().get(index);
        
        int x;
        x = data.getBus_number();
        oblist2.add(data.getBus_number());
        System.out.println("Id of    " + x);
        //  b.setId(x);

        try {
            tableData.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingForm.fxml"));
            Parent root = loader.load();
            BookingFormController b = loader.getController();
            b.getI(getUserid(), x);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex);
        }
        
    }
    
    public int getUserid() {
        return userid;
    }
    
    public void setUserid(int userid) {
        this.userid = userid;
    }
    
    @FXML
    private void allroot(ActionEvent event) {
        choicesourse.getItems().clear();
        choicedest.getItems().clear();
        txtdate12.setValue(null);
        
        try {
            u = new Users();
            
            ResultSet rs = u.ViewAll();
            while (rs.next()) {
                oblist.add(new Data(rs.getInt("BUS_ID"), rs.getString("SOURCE"), rs.getString("DESTINATION"), rs.getString("DATE"), rs.getString("TIME"), rs.getString("PRICE"), rs.getString("SEATS")));
                
            }
            rs.close();

// TODO
        } catch (SQLException ex) {
            Logger.getLogger(NewBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableData.setItems(oblist);
    }
    
}
