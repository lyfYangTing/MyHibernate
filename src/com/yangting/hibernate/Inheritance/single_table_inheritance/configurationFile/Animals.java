package com.yangting.hibernate.Inheritance.single_table_inheritance.configurationFile;

/**
 * Created by 18435 on 2017/12/13.
 */
public class Animals {

    private String id;
    private String name;
    private boolean sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
