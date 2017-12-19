package com.yangting.hibernate.single_1_1.primary.annotation;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 18435 on 2017/12/7.
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(generator = "personGenerator")
    @GenericGenerator(name = "personGenerator",strategy = "foreign",parameters = {@org.hibernate.annotations.Parameter(value="idCard",name="property")})
    private String id;

    @Column(name = "name")
    private String name;

    /**
     * cascade表示级联的意思
     * FetchMode.SELECT的方式表示，查询与之关联的数据的时候，使用select的方式，而不是左外连接,与在
     * 在@OneToOne()中间加上fetch=FetchType.LAZY所表达出来的意思是一样的。
     * 单向关联时，在此处不加@PrimaryKeyJoinColumn的话，person表中会多生成一列 idCard_idCard_id 外键列
     */
    @OneToOne(cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)
    @PrimaryKeyJoinColumn
    private IdCard idCard;

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

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }
}
