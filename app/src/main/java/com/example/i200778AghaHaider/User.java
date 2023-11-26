package com.example.i200778AghaHaider;

public class User {

    String Email,Name,Password,ContactNo,City,Country;
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }



    public User(String email, String name, String password, String contactNo, String city, String country) {
        Email = email;
        Name = name;
        Password = password;
        ContactNo = contactNo;
        City = city;
        Country = country;
    }



}


