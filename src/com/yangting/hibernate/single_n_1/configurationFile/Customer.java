package com.yangting.hibernate.single_n_1.configurationFile;

/**
 * Created by 18435 on 2017/11/28.
 */
public class Customer {
    private int customerId;
    private String customerName;

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
