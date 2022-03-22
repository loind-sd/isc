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
public class Order {

    private int id;
    private int accountId;
    private String date;
    private double totalPrice;
    private String note;
    private int status;
    private int sellBy;
    private String name;
    private String phone;
    private String address;

    public Order(int accountId, double totalPrice, String note, int status) {
        this.accountId = accountId;
        this.totalPrice = totalPrice;
        this.note = note;
        this.status = status;
    }
    
    public Order(int accountId, double totalPrice, String note, int status, int sellBy) {
        this.accountId = accountId;
        this.totalPrice = totalPrice;
        this.note = note;
        this.status = status;
        this.sellBy = sellBy;
    }

    public Order(int id, int accountId, String date, double totalPrice, String note, int status) {
        this.id = id;
        this.accountId = accountId;
        this.date = date;
        this.totalPrice = totalPrice;
        this.note = note;
        this.status = status;
    }
    
    public Order(int id, int accountId, String date, double totalPrice, String note, int status, String name, String phone, String address) {
        this.id = id;
        this.accountId = accountId;
        this.date = date;
        this.totalPrice = totalPrice;
        this.note = note;
        this.status = status;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Order(int accountId, double totalPrice, String note, int status, int sellBy, String name, String phone, String address) {
        this.accountId = accountId;
        this.totalPrice = totalPrice;
        this.note = note;
        this.status = status;
        this.sellBy = sellBy;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    

    public Order() {
    }

    public int getSellBy() {
        return sellBy;
    }

    public void setSellBy(int sellBy) {
        this.sellBy = sellBy;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
    public String getFmDate() {
        String[] str = this.date.split("-");
        return str[2] + "-" + str[1] + "-" + str[0];
    }
}
