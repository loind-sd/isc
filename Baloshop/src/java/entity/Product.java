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
public class Product {

    private int id;
    private String name;
    private int categoryId;
    private double price;
    private String description;
    private int quantity;
    private int status;
    private String imageLink;
    private String note;
    private int sellBy;

    public Product(int id, String name, int categoryId, double price, String description, int quantity, int status, String imageLink, String note, int sellBy) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
        this.imageLink = imageLink;
        this.note = note;
        this.sellBy = sellBy;
    }

    public Product(String name, int categoryId, double price, String description, int quantity, int status, String imageLink, String note) {
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
        this.imageLink = imageLink;
        this.note = note;
    }
    
    public Product(int id, String name, int categoryId, double price, String description, int quantity, int status, String imageLink, String note) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
        this.imageLink = imageLink;
        this.note = note;
    }

    public Product(String name, int categoryId, double price, String description, int quantity, int status, String imageLink, String note, int sellBy) {
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
        this.imageLink = imageLink;
        this.note = note;
        this.sellBy = sellBy;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
