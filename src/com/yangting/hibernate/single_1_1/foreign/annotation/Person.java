package com.yangting.hibernate.single_1_1.foreign.annotation;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/12/7.
 */
@Entity
@Table(name="person_foreign")
public class Person {
    @Id
    @Column(name="person_id")
    @GeneratedValue(generator = "personForGenerator")
    @GenericGenerator(name = "personForGenerator",strategy = "uuid")
    private String id;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="idCard_id",unique = true)
    private IdCard card;//体现一对一的关系。保存映射类的实例对象。

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

    public IdCard getCard() {
        return card;
    }

    public void setCard(IdCard card) {
        this.card = card;
    }
}
