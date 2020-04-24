/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusReservationSystem;

/**
 *
 * @author Mohammed
 */
public class Table {

    private int user;
    private int bus_number;
    private String source;
    private String destination;
    private String paid;
    private String seat_Number;

    private String date;
    private String time;

    public Table(int user, int bus_number, String source, String destination, String paid, String seat_Number, String date, String time) {
        this.user = user;
        this.bus_number = bus_number;
        this.source = source;
        this.destination = destination;
        this.paid = paid;
        this.seat_Number = seat_Number;
        this.date = date;
        this.time = time;
    }

    
    

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getBus_number() {
        return bus_number;
    }

    public void setBus_number(int bus_number) {
        this.bus_number = bus_number;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getSeat_Number() {
        return seat_Number;
    }

    public void setSeat_Number(String seat_Number) {
        this.seat_Number = seat_Number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
