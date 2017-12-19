package com.yangting.hibernate.double_n_n.annotation;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 18435 on 2017/12/11.
 */
@Entity
@Table(name="item")
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(generator = "itemGenerator")
    @GenericGenerator(name="itemGenerator",strategy = "uuid")
    private String itemId;

    @Column(name="item_name")
    private String itemName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="categories_items",joinColumns = {@JoinColumn(name="it_id")},inverseJoinColumns = {@JoinColumn(name="cate_id")})
    private Set<Category> categories = new HashSet<Category>();

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
