package com.example.HotelDemo;


public class MenuPojo {

    String item_name;
    long menuId;
    int price;
    int quantity;
    long restaurantId;

    public MenuPojo(int menuId, String item_name, int price, int quantity, int restaurantId) {
        super();
        this.menuId = menuId;
        this.item_name = item_name;
        this.price = price;
        this.quantity = quantity;
       this.restaurantId = restaurantId;

    }


    public MenuPojo(){super();}

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

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

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

}
