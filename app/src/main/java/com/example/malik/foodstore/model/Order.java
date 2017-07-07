package com.example.malik.foodstore.model;

/**
 * Created by malik on 7/5/2017.
 */

public class Order {
    String orderId;
    String orderName;
    String orderQuantity;
    String orderPrice;
    String orderStatus;
    public String getOrderId() {
        return orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
    public Order(String orderId, String orderName, String orderQuantity, String orderPrice, String orderStatus){
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
    }

}
