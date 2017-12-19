package com.yangting.hibernate.double_n_1.configurationFile;

import java.util.Set;

/**
 * Created by 18435 on 2017/11/29.
 */
public class Customer {
    private int customerId;
    private String customerName;
    private Set<Order> orderSet;

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
