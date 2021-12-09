package com.example.HotelDemo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;
import java.util.List;


@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long restaurantId;
    private String restaurantName;
    private String restaurantPhoneNumber;
    private String restaurantType;



   /*@OneToMany(cascade = CascadeType.ALL, mappedBy="Restaurant")
    @JsonIgnore
    private List<Menu> menu;*/

    public Restaurant(){
        super();
    }

    public Restaurant(String restaurantName, String restaurantPhoneNumber, String restaurantType) {
        super();
        this.restaurantName = restaurantName;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.restaurantType = restaurantType;
    }


    public long getId() {
        return restaurantId;
    }
    public void setId(long id) {
        this.restaurantId = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPhoneNumber() {
        return restaurantPhoneNumber;
    }

    public void setRestaurantPhoneNumber(String restaurantPhoneNumber) {
        this.restaurantPhoneNumber = restaurantPhoneNumber;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

   /* public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }*/

}
