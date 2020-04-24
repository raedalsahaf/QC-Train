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
public class AdminTable {
    private String admin_name;
        private String password;
    private String email;
    private String phone_number;
    private String address;

    public AdminTable(String admin_name, String password, String email, String phone_number, String address) {
        this.admin_name = admin_name;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
