package com.yangting.hibernate.Inheritance.single_table_inheritance.configurationFile;

/**
 * Created by 18435 on 2017/12/13.
 */
public class Pig extends Animals{
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
