package com.gaurav.pagingwithroom.modals;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "bike")
public class Bike {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String brand;
    private String model;
    private String owner;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Ignore
    public Bike(String brand, String model, int price, String owner){
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.owner = owner;
    }

    public Bike(int id, String brand, String model, int price, String owner){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.owner = owner;
    }
}
