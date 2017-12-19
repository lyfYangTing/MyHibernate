package com.yangting.hibernate.Inheritance.class_table_inheritance.annotation;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/12/14.
 */
@Entity
@PrimaryKeyJoinColumn(name="pid")
@Table(name="a_pig")
public class Pig extends Animals{
    @Column(name="weight")
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
