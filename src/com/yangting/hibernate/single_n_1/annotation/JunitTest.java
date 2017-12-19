package com.yangting.hibernate.single_n_1.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by 18435 on 2017/11/28.
 * 单向N-1关系，
 * 比如多个人对应一个地址，只需从人实体端可以找到对应的地址实体，无须关系某个地址的全部住户。
 * 单向n-1 关联只需从 n 的一端可以访问 1 的一端。
 */
public class JunitTest {
    private Logger logger = Logger.getLogger(JunitTest.class);
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
    public void saveTest(){
        Customer customer = new Customer();
        customer.setCustomerName("a");

        Order order = new Order();
        order.setOrderName("128多端新增。。");
        order.setCustomer(customer);

        Order order1 = new Order();
        order1.setOrderName("128多端新增。。");
        order1.setCustomer(customer);

        //用到了级联新增   cascade = CascadeType.ALL(表示允许级联操作 新增，更新，删除)
        session.save(order);
        session.save(order1);
    }

    @Test
    public void persistTest(){
        Customer customer = session.get(Customer.class,2);
        customer.setCustomerName("一端更新。。");

        Order order = new Order();
        order.setOrderName("128多端新增。。");
        order.setCustomer(customer);

        Order order1 = new Order();
        order1.setOrderName("128多端新增。。");
        order1.setCustomer(customer);

        session.save(order);
    }

    @Test
    public void mergeTest(){
        Customer customer = new Customer();
        customer.setCustomerName("AAA");

        Order order = session.get(Order.class,3);
        order.setOrderName("FFF");
        order.setCustomer(customer);
        //必须先将游离态的customer对象转换为持久态，才可以进一步进行order的更新
        //否则会出现object references an unsaved transient instance - save the transient instance before flushing异常
        //          （对象引用未保存的瞬态实例-在刷新之前保存瞬态实例）  出现这个异常的主要原因是级联设置没有设置级联新增
        //session.save(customer);
        //customer.setCustomerName("我是。。。");
        session.saveOrUpdate(order);
    }

    @Test
    public void updateTwoText(){
        Customer customer = session.get(Customer.class, 4);
        customer.setCustomerName("一端更新。。");

        Order order = session.get(Order.class,15);
        order.setOrderName("多端更新。。");
        order.setCustomer(customer);

        session.update(order);//或者session.saveOrUpdate(order);
    }

    @Test
    public void getTest(){
        Order order = session.get(Order.class,3);
        logger.info("查询order的SQl已经执行。。");
        logger.info("orderName"+order.getOrderName());
        Customer customer = order.getCustomer();
        logger.info("查询Customer的SQL还未发送，因为 fetch = FetchType.LAZY 延迟加载，只有当在使用Customer的时候才会发送SQL");
        logger.info("customer----->"+customer.getCustomerName());
    }

    @Test
    public void loadTest(){
        Order order = session.load(Order.class,13);
        logger.info("查询Order的SQL还未发送，只有当使用的时候才会放松SQL");
        logger.info("orderName"+order.getOrderName());
        Customer customer = order.getCustomer();
        logger.info("查询Customer的SQL还未发送，因为 fetch = FetchType.LAZY 延迟加载，只有当在使用Customer的时候才会发送SQL");
        logger.info("customer----->"+customer.getCustomerName());
    }

    @Test
    public void deleteTest(){
        //删除多端
        Order order = new Order();
        order.setOrderId(15);
        session.delete(order);
        //删除一端
        Customer customer = new Customer();
        customer.setCustomerId(2);
        session.delete(customer);
    }
}
