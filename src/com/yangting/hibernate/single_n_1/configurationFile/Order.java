package com.yangting.hibernate.single_n_1.configurationFile;

/**
 * Created by 18435 on 2017/11/28.
 */
public class Order {
    private int orderId;
    private String orderName;
    private Customer customer;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
