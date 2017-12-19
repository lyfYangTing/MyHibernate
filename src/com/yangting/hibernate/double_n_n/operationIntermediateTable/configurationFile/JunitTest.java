package com.yangting.hibernate.double_n_n.operationIntermediateTable.configurationFile;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by 18435 on 2017/12/11.
 */
public class JunitTest {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init() {//初始化
        // 配置类型安全的准服务注册类，在configure("cfg/hibernate.cfg.xml")方法中，如果不指定资源路径，默认在类路径下寻找名为hibernate.cfg.xml的文件,不传入参数的话，必须用默认名称hibernate.cfg.xml
        //传入参数的话就可以用别的名称了
        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        //根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    /**
     *    Order(订单)      Product(产品，商品)       OrderItem(订单记录)
     *      order1              pro                  item1(order1,pro)
     *      order2              p2                   item2(order1,p2)
     *                          p3                   item3(order2,p3)
     *                                               item4(order2,pro)
     */
    @Test
    public void saveTest() {
        //订单一
        Order order1 = new Order();
        order1.setTotal(1000);
        order1.setAddress("华软");
        order1.setRealname("李浩然");
        order1.setPostcode("510990");
        order1.setPhone("87818998");

        //订单二
        Order order2 = new Order();
        order2.setTotal(5000);
        order2.setAddress("中大");
        order2.setRealname("李明");
        order2.setPostcode("510000");
        order2.setPhone("38008781");

        //产品1
        Product pro = new Product();
        pro.setName("thinkpad电脑");
        pro.setDescription("thinkpad");
        pro.setPrice(6780);

        //产品2
        Product p2 = new Product();
        p2.setName("彩电");
        p2.setDescription("性价比高");
        p2.setPrice(200);

        //产品3
        Product p3 = new Product();
        p3.setName("tcl彩电");
        p3.setPrice(3600);
        p3.setDescription("tcl彩电");

        //订单记录
        OrderItem item1 = new OrderItem();
        OrderItem item2 = new OrderItem();
        OrderItem item3 = new OrderItem();
        OrderItem item4 = new OrderItem();

        //一个订单有多条商品记录
        item1.setOrder(order1);//建立关联关系
        item1.setProduct(pro);//建立关联关系  并找到与之关联的商品，级联保存pro
        item1.setQuantity(100);
        item1.setPurchase(6000);

        item2.setOrder(order1);//建立关联关系
        item2.setProduct(p2);//建立关联关系 并找到与之关联的商品，级联保存p2
        item2.setQuantity(200);
        item2.setPurchase(3000);
        //保存order1时，找到与之关联的item，然后级联保存item1，item2
        order1.getOrderitems().add(item1);
        order1.getOrderitems().add(item2);

        item3.setOrder(order2);//建立关联关系
        item3.setProduct(p3);//建立关联关系 并找到与之关联的商品，级联保存p3
        item3.setQuantity(30);
        item3.setPurchase(3300);

        item4.setOrder(order2);//建立关联关系
        item4.setProduct(pro);//建立关联关系 并找到与之关联的商品，级联保存pro
        item4.setQuantity(60);
        item4.setPurchase(6200);

        //保存order2时，找到与之关联的item，然后级联保存item3，item4
        order2.getOrderitems().add(item3);
        order2.getOrderitems().add(item4);

        //一个商品有多条商品记录
        pro.getOrderitems().add(item1);
        pro.getOrderitems().add(item4);

        //保存记录
        session.save(order1);
        session.save(order2);
    }

    @Test
    public void getTest() {
        Order myOrder = session.get(Order.class, "402881bd604871ff0160487215ae0000");
        if (myOrder != null) {
            int costMoney = 0;
            for (OrderItem item : myOrder.getOrderitems()) {
                costMoney += (item.getQuantity() * item.getPurchase());
            }
            System.out.println("订单号:" + myOrder.getId());
            System.out.println("总花费:" + costMoney + "\n" + "购买了以下商品：");
            for (OrderItem item : myOrder.getOrderitems()) {
                System.out.println("商品名:" + item.getProduct().getName());
            }
        }
    }
}
