package com.yangting.hibernate.double_n_n.operationIntermediateTable.configurationFile;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 18435 on 2017/12/11.
 */
public class Order {
    private String id;
    private double total;
    private String realname;
    private String phone;
    private String address;
    private String postcode;
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
