package com.yangting.hibernate.Inheritance.single_table_inheritance.annotation;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by 18435 on 2017/12/13.
 */
@Entity
@DiscriminatorValue("pig")
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
