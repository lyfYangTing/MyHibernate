package com.yangting.hibernate.single_n_n.configurationFile;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 18435 on 2017/12/8.
 */
public class Category {
    private String categoryId;
    private String catregoryName;
    private Set<Item> items = new HashSet<Item>();

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCatregoryName() {
        return catregoryName;
    }

    public void setCatregoryName(String catregoryName) {
        this.catregoryName = catregoryName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
