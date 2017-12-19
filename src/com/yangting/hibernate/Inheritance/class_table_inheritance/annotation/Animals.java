package com.yangting.hibernate.Inheritance.class_table_inheritance.annotation;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/12/14.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="a_animals")
public class Animals {
    @Id
    @Column(name="animals_id")
    @GeneratedValue(generator = "animalsGenerator")
    @GenericGenerator(name = "animalsGenerator",strategy = "uuid")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="sex")
    @Type(type="true_false")
    private boolean sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
