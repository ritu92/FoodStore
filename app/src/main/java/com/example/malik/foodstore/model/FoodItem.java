package com.example.malik.foodstore.model;

/**
 * Created by malik on 7/4/2017.
 * model class for food items
 * name, price, category and image of food can be specified
 */

public class FoodItem {
    private final String food_name;
    private final String food_price;
    private final String food_category;
    private final String food_img;
    public String getFood_name() {
        return food_name;
    }

    public String getFood_price() {
        return food_price;
    }

    public String getFood_category() {
        return food_category;
    }

    public String getFood_img() {
        return food_img;
    }
    public FoodItem(String food_name, String food_price, String food_category, String food_img){
        this.food_name = food_name;
        this.food_category = food_category;
        this.food_img = food_img;
        this.food_price = food_price;
    }

}
