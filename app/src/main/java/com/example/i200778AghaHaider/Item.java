package com.example.i200778AghaHaider;

import java.time.LocalDate;

public class Item {

    private String Name, Description, City, ImageURI;
    private double Rate;

    public Item() {
    }

    public Item(String name, String description, String city, double rate, String imageURI) {
        this.Name = name;
        this.Description = description;
        this.City = city;
        this.Rate = rate;
        this.ImageURI = imageURI;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getImageURI() {
        return ImageURI;
    }

    public void setImageURI(String imageURI) {
        this.ImageURI = imageURI;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        this.Rate = rate;
    }
}
