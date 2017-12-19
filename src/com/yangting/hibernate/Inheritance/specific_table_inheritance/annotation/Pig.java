package com.yangting.hibernate.Inheritance.specific_table_inheritance.annotation;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/12/14.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="tt_pig")
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
