package com.yangting.hibernate.single_1_1.foreign.configurationFile;

/**
 * Created by 18435 on 2017/12/7.
 */
public class Person {
    private String id;
    private String name;
    private IdCard card;//����һ��һ�Ĺ�ϵ������ӳ�����ʵ������

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
