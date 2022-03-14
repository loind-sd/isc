/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Shado
 */
public class OtherAddress {

    private int id;
    private String name;
    private String phoneNumber;
    private String address;
    private int orderId;

    public OtherAddress(String name, String phoneNumber, String address, int orderId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderId = orderId;
    }

    public OtherAddress(int id, String name, String phoneNumber, String address, int orderId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
