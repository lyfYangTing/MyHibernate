package com.yangting.hibernate.single_1_1.foreign.annotation;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/12/7.
 */
@Entity
@Table(name="idCard_foreign")
public class IdCard {
    @Id
    @Column(name="idCard_id")
    @GeneratedValue(generator = "idCardForGenerator")
    @GenericGenerator(name = "idCardForGenerator",strategy = "uuid")
    private String id;

    @Column(name="card_no")
    private String cardNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
