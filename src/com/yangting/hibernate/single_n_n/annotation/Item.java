package com.yangting.hibernate.single_n_n.annotation;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
}
