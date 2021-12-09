package com.example.HotelDemo;

import javax.persistence.*;

@Entity
public class Customer {
    String name;
    long phoneNumber;


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long customerId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant")
    private Restaurant restaurant;


    public Customer(String name, long phoneNumber) {
        super();
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public long getCustomerId() {
        return customerId;
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


}
