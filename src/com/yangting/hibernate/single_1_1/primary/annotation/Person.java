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
     * cascade��ʾ��������˼
     * FetchMode.SELECT�ķ�ʽ��ʾ����ѯ��֮���������ݵ�ʱ��ʹ��select�ķ�ʽ����������������,����
     * ��@OneToOne()�м����fetch=FetchType.LAZY������������˼��һ���ġ�
     * �������ʱ���ڴ˴�����@PrimaryKeyJoinColumn�Ļ���person���л������һ�� idCard_idCard_id �����
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
