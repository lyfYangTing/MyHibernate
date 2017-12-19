package com.yangting.hibernate.Inheritance.class_table_inheritance.annotation;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/12/14.
 */
@Entity
@PrimaryKeyJoinColumn(name="bid")
@Table(name="a_bird")
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
