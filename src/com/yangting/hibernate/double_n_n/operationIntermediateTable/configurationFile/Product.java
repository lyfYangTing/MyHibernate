package com.yangting.hibernate.double_n_n.operationIntermediateTable.configurationFile;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 18435 on 2017/12/11.
 */
public class Product {
    private String id;
    private String name;
    private double price;
    private String description;
    private Set<OrderItem> orderitems = new HashSet<OrderItem>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<OrderItem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(Set<OrderItem> orderitems) {
        this.orderitems = orderitems;
    }
}
