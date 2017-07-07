package com.example.malik.foodstore.model;

import java.util.ArrayList;

/**
 * Created by malik on 7/6/2017.
 */

public class FoodItemList {
    private ArrayList<FoodItem> foodItems = new ArrayList<>();

    /**
     * @return The food items
     */
    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    /**
     * @param foodItems The foodItems
     */
    public void setFood(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}
