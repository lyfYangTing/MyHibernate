package com.yangting.hibernate.double_n_n.operationIntermediateTable.annotation;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 18435 on 2017/12/12.
 */
@Entity
@Table(name="product")
public class Product {
    @Id
    @Column(name="product_id")
    @GeneratedValue(generator = "productGenerator")
    @GenericGenerator(name="productGenerator",strategy = "uuid")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @Column(name="description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
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
