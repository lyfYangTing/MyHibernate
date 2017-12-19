package com.yangting.hibernate.double_n_1.configurationFile;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 18435 on 2017/11/29.
 */
public class JunitTest {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init(){//初始化
        // 配置类型安全的准服务注册类，在configure("cfg/hibernate.cfg.xml")方法中，如果不指定资源路径，默认在类路径下寻找名为hibernate.cfg.xml的文件,不传入参数的话，必须用默认名称hibernate.cfg.xml
        //传入参数的话就可以用别的名称了
        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        //根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

//    @Before
//    public void init(){
//        Configuration configuration = new Configuration().configure();
////News.class(hibernate会根据被加载的entity去找与其相应的xx.hbm.xml文件)   加载实体类与其hbm.xml文件
//        configuration.addClass(Customer.class);
//        configuration.addClass(Order.class);
//        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        sessionFactory = configuration.buildSessionFactory(registry);
//        session = sessionFactory.openSession();
//        transaction = session.beginTransaction();
//    }

    @After
    public void destroy(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void saveTestOne(){//保存一端，级联保存多端
        Order order = new Order();
        order.setOrderName("inverse双向多端1。。");

        Order order1 = new Order();
        order1.setOrderName("inverse双向多端2。。");

        Set<Order> orders = new HashSet<Order>();
        orders.add(order);
        orders.add(order1);

        Customer customer = new Customer();
        customer.setCustomerName("inverse双向一端。。");
        customer.setOrderSet(orders);

        session.save(customer);
    }

    @Test
    public void saveTestTwo(){//保存多端，级联保存一端
        Customer customer = new Customer();
        customer.setCustomerName("..双向一端。。");

        Order order = new Order();
        order.setOrderName("..双向多端1。。");
        order.setCustomer(customer);

        Order order1 = new Order();
        order1.setOrderName("..双向多端2。。");
        order1.setCustomer(customer);

        session.save(order);
        session.save(order1);
    }

    @Test
    public void updateTest(){//更新一端 级联更新多端
        //新增多端元素   执行插入操作
        Order order = new Order();
        order.setOrderName("更新双向多端1。。");

        //修改原多端元素  执行修改操作
        Order order2 = session.get(Order.class,15);
        order2.setOrderName("更新双向多端。。");

        Customer customer = session.get(Customer.class,12);
        //注意两种获取方式  Set<Order> orders = customer.getOrderSet(); 原来的多端数据已经在集合中了，如果原来已经被建立关系的多端元素被更新，直接修改该元素即可，不需要再执行orders.add(被更新元素)，只需要将新的元素添加进集合即可;
        //Set<Order> orders =new HashSet<>();  这是一个新的set集合，只有被添加进该集合的多端对象会建立新的关联关系，以前的关系都将被解除，原来的多端外键列会被删除关联关系，外键列被置空
        Set<Order> orders = customer.getOrderSet();

        orders.add(order);

        customer.setCustomerName("更新双向一端。。");
        customer.setOrderSet(orders);

        session.save(customer);
    }
}
