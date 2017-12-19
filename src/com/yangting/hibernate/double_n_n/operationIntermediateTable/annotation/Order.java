package com.yangting.hibernate.double_n_n.operationIntermediateTable.annotation;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 18435 on 2017/12/12.
 */
@Entity
@Table(name="order_oper")
public class Order {
    @Id
    @Column(name="order_id")
    @GeneratedValue(generator = "orderOperGenerator")
    @GenericGenerator(name="orderOperGenerator",strategy = "uuid")
    private String id;

    @Column(name="total")
    private double total;

    @Column(name="real_name")
    private String realname;

    @Column(name="phone")
    private String phone;

    @Column(name="address")
    private String address;

    @Column(name="postcode")
    private String postcode;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    private Set<OrderItem> orderitems = new HashSet<OrderItem>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Set<OrderItem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(Set<OrderItem> orderitems) {
        this.orderitems = orderitems;
    }
}
