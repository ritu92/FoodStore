package com.example.malik.foodstore.model;

/**
 * Created by malik on 7/10/2017.
 */

public class CartList {
    public String getCart_price() {
        return cart_price;
    }

    public String getCart_name() {
        return cart_name;
    }

    private String cart_price;
    private String cart_name;
    public CartList(String cart_name, String cart_price){
        this.cart_name = cart_name;
        this.cart_price = cart_price;

    }

}
