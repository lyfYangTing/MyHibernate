package com.yangting.hibernate.double_n_1.annotation;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by 18435 on 2017/11/30.
 */
@Entity
@Table(name = "customers" )
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "customer_name")
    private String customerName;

    //���ΪmappedBy�Ĺ������ܶ�����@JoinTable��@JoinColumn���������ݿ�ӳ�䡣 ��һ�Զ������ ���ʵ����Ķ�Ӧһ��ʵ����������
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Set<Order> orderSet;

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

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }
}
