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
    public void init(){//��ʼ��
        // �������Ͱ�ȫ��׼����ע���࣬��configure("cfg/hibernate.cfg.xml")�����У������ָ����Դ·����Ĭ������·����Ѱ����Ϊhibernate.cfg.xml���ļ�,����������Ļ���������Ĭ������hibernate.cfg.xml
        //��������Ļ��Ϳ����ñ��������
        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        //���ݷ���ע���ഴ��һ��Ԫ������Դ����ͬʱ����Ԫ���ݲ�����Ӧ��һ��Ψһ�ĵ�session����
        sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

//    @Before
//    public void init(){
//        Configuration configuration = new Configuration().configure();
////News.class(hibernate����ݱ����ص�entityȥ��������Ӧ��xx.hbm.xml�ļ�)   ����ʵ��������hbm.xml�ļ�
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
        //����һ�ˣ�����������
        Order order = new Order();
        order.setOrderName("inverseע��˫����1����");

        Order order1 = new Order();
        order1.setOrderName("inverseע��˫����2����");

        Set<Order> orders = new HashSet<Order>();
        orders.add(order);
        orders.add(order1);

        Customer customer = new Customer();
        customer.setCustomerName("inverseע��˫��һ�ˡ���");
        customer.setOrderSet(orders);

        session.save(customer);
        //session.persist(customer);
    }

    @Test
    public void saveTestTwo(){
        //�����ˣ���������һ��
        Customer customer = new Customer();
        customer.setCustomerName("..ע��˫��һ�ˡ���");

        Order order = new Order();
        order.setOrderName("..ע��˫����1����");
        order.setCustomer(customer);

        Order order1 = new Order();
        order1.setOrderName("..ע��˫����2����");
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
        order.setOrderName("����!����!");//��ִ��һ��update��䣬���ݿ��������Ѿ����ı�
    }

    @Test
    public void TestDetached(){
        Customer customer = session.get(Customer.class,9);
        Order order = new Order();
        order.setOrderId(3);
        order.setOrderName("�Գ־û�̬�Ķ�����и���");
        order.setCustomer(customer);
        session.update(order);//����������̬��orderת���ɳ־�̬����ʱhibernate��û�з���update���
        System.out.println("-----------������update�����������ǶԳ־û�����ĸ���----------------------------------");
        order.setOrderName("�Գ־û�̬�Ķ�����и���");
    }

    @Test
    public void TestDetachedOne(){

        Customer customer = new Customer();
        customer.setCustomerId(9);
        customer.setCustomerName("�Գ־û�̬�Ķ�����и���");
        session.update(customer);//����������̬��orderת���ɳ־�̬����ʱhibernate��û�з���update���
        System.out.println("-----------������update�����������ǶԳ־û�����ĸ���----------------------------------");
        customer.setCustomerName("�Գ־û�̬�Ķ�����и���");

        Customer customer1 = session.get(Customer.class,9);
        System.out.println("---------------------"+customer1.getCustomerName()+"---------------------------");
    }
}
