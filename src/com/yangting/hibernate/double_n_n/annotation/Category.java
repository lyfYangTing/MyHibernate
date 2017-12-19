package com.yangting.hibernate.double_n_n.annotation;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 18435 on 2017/12/11.
 */
@Entity
@Table(name="category")
public class Category {
    @Id
    @Column(name="category_id")
    @GeneratedValue(generator = "categoryGenerator")
    @GenericGenerator(name = "categoryGenerator",strategy = "uuid")
    private String categoryId;

    @Column(name="category_name")
    private String catregoryName;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "categories")
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
