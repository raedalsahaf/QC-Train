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
public class Bus {

    private String bus_id;
    private String bus_model;
    private String seats;
    private String route_id;

    public Bus( String bus_id, String bus_model, String seats,String rout_id) {
        this.bus_id = bus_id;
        this.bus_model = bus_model;
        this.seats = seats;
        this.route_id=rout_id;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getBus_model() {
        return bus_model;
    }

    public void setBus_model(String bus_model) {
        this.bus_model = bus_model;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

}
