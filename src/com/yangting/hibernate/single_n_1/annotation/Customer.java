package com.yangting.hibernate.single_n_1.annotation;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/11/28.
 */
@Entity//������ǰ��Ϊhibernateӳ�䵽���ݿ��е�ʵ����
@Table(name = "customers")
public class Customer {
    @Id//��������Ϊ����,��Ϊӳ�����ı�ʶ��
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
