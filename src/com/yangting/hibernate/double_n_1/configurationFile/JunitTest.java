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
    public void saveTestOne(){//����һ�ˣ�����������
        Order order = new Order();
        order.setOrderName("inverse˫����1����");

        Order order1 = new Order();
        order1.setOrderName("inverse˫����2����");

        Set<Order> orders = new HashSet<Order>();
        orders.add(order);
        orders.add(order1);

        Customer customer = new Customer();
        customer.setCustomerName("inverse˫��һ�ˡ���");
        customer.setOrderSet(orders);

        session.save(customer);
    }

    @Test
    public void saveTestTwo(){//�����ˣ���������һ��
        Customer customer = new Customer();
        customer.setCustomerName("..˫��һ�ˡ���");

        Order order = new Order();
        order.setOrderName("..˫����1����");
        order.setCustomer(customer);

        Order order1 = new Order();
        order1.setOrderName("..˫����2����");
        order1.setCustomer(customer);

        session.save(order);
        session.save(order1);
    }

    @Test
    public void updateTest(){//����һ�� �������¶��
        //�������Ԫ��   ִ�в������
        Order order = new Order();
        order.setOrderName("����˫����1����");

        //�޸�ԭ���Ԫ��  ִ���޸Ĳ���
        Order order2 = session.get(Order.class,15);
        order2.setOrderName("����˫���ˡ���");

        Customer customer = session.get(Customer.class,12);
        //ע�����ֻ�ȡ��ʽ  Set<Order> orders = customer.getOrderSet(); ԭ���Ķ�������Ѿ��ڼ������ˣ����ԭ���Ѿ���������ϵ�Ķ��Ԫ�ر����£�ֱ���޸ĸ�Ԫ�ؼ��ɣ�����Ҫ��ִ��orders.add(������Ԫ��)��ֻ��Ҫ���µ�Ԫ����ӽ����ϼ���;
        //Set<Order> orders =new HashSet<>();  ����һ���µ�set���ϣ�ֻ�б���ӽ��ü��ϵĶ�˶���Ὠ���µĹ�����ϵ����ǰ�Ĺ�ϵ�����������ԭ���Ķ������лᱻɾ��������ϵ������б��ÿ�
        Set<Order> orders = customer.getOrderSet();

        orders.add(order);

        customer.setCustomerName("����˫��һ�ˡ���");
        customer.setOrderSet(orders);

        session.save(customer);
    }
}
