package com.yangting.hibernate.double_n_1.annotation;

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
 * Created by 18435 on 2017/11/30.
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
    public void saveTestOne(){
        //保存一端，级联保存多端
        Order order = new Order();
        order.setOrderName("inverse注解双向多端1。。");

        Order order1 = new Order();
        order1.setOrderName("inverse注解双向多端2。。");

        Set<Order> orders = new HashSet<Order>();
        orders.add(order);
        orders.add(order1);

        Customer customer = new Customer();
        customer.setCustomerName("inverse注解双向一端。。");
        customer.setOrderSet(orders);

        session.save(customer);
        //session.persist(customer);
    }

    @Test
    public void saveTestTwo(){
        //保存多端，级联保存一端
        Customer customer = new Customer();
        customer.setCustomerName("..注解双向一端。。");

        Order order = new Order();
        order.setOrderName("..注解双向多端1。。");
        order.setCustomer(customer);

        Order order1 = new Order();
        order1.setOrderName("..注解双向多端2。。");
        order1.setCustomer(customer);

        session.save(order);
        session.save(order1);
//        session.persist(order);
//        session.persist(order1);
    }

    @Test
    public void updateTest(){
        Order order = session.get(Order.class,64);
        session.evict(order);
        order.setOrderName("好玩!好玩!");//会执行一条update语句，数据库中数据已经被改变
    }

    @Test
    public void TestDetached(){
        Customer customer = session.get(Customer.class,9);
        Order order = new Order();
        order.setOrderId(3);
        order.setOrderName("对持久化态的对象进行更新");
        order.setCustomer(customer);
        session.update(order);//仅仅将游离态的order转换成持久态，此时hibernate并没有发出update语句
        System.out.println("-----------上面是update方法，下面是对持久化对象的更新----------------------------------");
        order.setOrderName("对持久化态的对象进行更新");
    }

    @Test
    public void TestDetachedOne(){

        Customer customer = new Customer();
        customer.setCustomerId(9);
        customer.setCustomerName("对持久化态的对象进行更新");
        session.update(customer);//仅仅将游离态的order转换成持久态，此时hibernate并没有发出update语句
        System.out.println("-----------上面是update方法，下面是对持久化对象的更新----------------------------------");
        customer.setCustomerName("对持久化态的对象进行更新");

        Customer customer1 = session.get(Customer.class,9);
        System.out.println("---------------------"+customer1.getCustomerName()+"---------------------------");
    }
}
