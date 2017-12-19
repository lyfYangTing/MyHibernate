package com.yangting.hibernate.Inheritance.single_table_inheritance.configurationFile;

/**
 * Created by 18435 on 2017/12/13.
 */
public class Bird extends Animals {

    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
