package com.yangting.hibernate.Inheritance.single_table_inheritance.annotation;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by 18435 on 2017/12/13.
 */
@Entity
@DiscriminatorValue("bird")
public class Bird extends Animals{

    @Column(name="height")
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
