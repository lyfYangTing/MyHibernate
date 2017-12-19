package com.yangting.hibernate.double_n_n.operationIntermediateTable.annotation;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/12/12.
 */
@Entity
@Table(name="order_item")
public class OrderItem {
    @Id
    @Column(name="order_item_id")
    @GeneratedValue(generator = "orderItemGenerator")
    @GenericGenerator(name="orderItemGenerator",strategy = "uuid")
    private String id;

    @Column(name="quantity")
    private int quantity;

    @Column(name="purchase")
    private double purchase;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;

    public double getPurchase() {
        return purchase;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
