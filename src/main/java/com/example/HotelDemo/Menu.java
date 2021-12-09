package com.example.HotelDemo;

import javax.persistence.*;
import org.springframework.web.bind.annotation.*;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    String item_name;
    int price;
    int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant")
    private Restaurant restaurant;



    public Menu(String item_name, int price,int quantity, Restaurant restaurant) {
        super();
        this.item_name = item_name;
        this.price = price;
        this.quantity = quantity;
       this.restaurant = restaurant;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Menu(){super();}


    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
