package com.yangting.hibernate.double_n_n.operationIntermediateTable.configurationFile;

/**
 * Created by 18435 on 2017/12/11.
 * 中间表（记录订单，商品，购买数量，购买单价）
 */
public class OrderItem {
    private String id;
    private Order order;
    private Product product;
    private int quantity;
    private double purchase;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchase() {
        return purchase;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }
}
