package com.yangting.hibernate.single_n_1.annotation;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/11/28.
 */
@Entity//声明当前类为hibernate映射到数据库中的实体类
@Table(name = "customers")
public class Customer {
    @Id//声明此列为主键,作为映射对象的标识符
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "customer_name")
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
