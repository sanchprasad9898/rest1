package com.example.HotelDemo;


public class RestaurantPojo {


    private long id;
    private String restaurantName;
    private String restaurantPhoneNumber;
    private String restaurantType;


    public RestaurantPojo(){

    }

    public RestaurantPojo(String restaurantName, String restaurantPhoneNumber, String restaurantType) {
        this.restaurantName = restaurantName;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.restaurantType = restaurantType;
    }

    public long getId() {
        return id;
    }

    public void setId(long restaurantId) {
        this.id = id;
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

}
