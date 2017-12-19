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
 * ����N-1��ϵ��
 * �������˶�Ӧһ����ַ��ֻ�����ʵ��˿����ҵ���Ӧ�ĵ�ַʵ�壬�����ϵĳ����ַ��ȫ��ס����
 * ����n-1 ����ֻ��� n ��һ�˿��Է��� 1 ��һ�ˡ�
 */
public class JunitTest {
    private Logger logger = Logger.getLogger(JunitTest.class);
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
    public void saveTest(){
        Customer customer = new Customer();
        customer.setCustomerName("a");

        Order order = new Order();
        order.setOrderName("128�����������");
        order.setCustomer(customer);

        Order order1 = new Order();
        order1.setOrderName("128�����������");
        order1.setCustomer(customer);

        //�õ��˼�������   cascade = CascadeType.ALL(��ʾ���������� ���������£�ɾ��)
        session.save(order);
        session.save(order1);
    }

    @Test
    public void persistTest(){
        Customer customer = session.get(Customer.class,2);
        customer.setCustomerName("һ�˸��¡���");

        Order order = new Order();
        order.setOrderName("128�����������");
        order.setCustomer(customer);

        Order order1 = new Order();
        order1.setOrderName("128�����������");
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
        //�����Ƚ�����̬��customer����ת��Ϊ�־�̬���ſ��Խ�һ������order�ĸ���
        //��������object references an unsaved transient instance - save the transient instance before flushing�쳣
        //          ����������δ�����˲̬ʵ��-��ˢ��֮ǰ����˲̬ʵ����  ��������쳣����Ҫԭ���Ǽ�������û�����ü�������
        //session.save(customer);
        //customer.setCustomerName("���ǡ�����");
        session.saveOrUpdate(order);
    }

    @Test
    public void updateTwoText(){
        Customer customer = session.get(Customer.class, 4);
        customer.setCustomerName("һ�˸��¡���");

        Order order = session.get(Order.class,15);
        order.setOrderName("��˸��¡���");
        order.setCustomer(customer);

        session.update(order);//����session.saveOrUpdate(order);
    }

    @Test
    public void getTest(){
        Order order = session.get(Order.class,3);
        logger.info("��ѯorder��SQl�Ѿ�ִ�С���");
        logger.info("orderName"+order.getOrderName());
        Customer customer = order.getCustomer();
        logger.info("��ѯCustomer��SQL��δ���ͣ���Ϊ fetch = FetchType.LAZY �ӳټ��أ�ֻ�е���ʹ��Customer��ʱ��Żᷢ��SQL");
        logger.info("customer----->"+customer.getCustomerName());
    }

    @Test
    public void loadTest(){
        Order order = session.load(Order.class,13);
        logger.info("��ѯOrder��SQL��δ���ͣ�ֻ�е�ʹ�õ�ʱ��Ż����SQL");
        logger.info("orderName"+order.getOrderName());
        Customer customer = order.getCustomer();
        logger.info("��ѯCustomer��SQL��δ���ͣ���Ϊ fetch = FetchType.LAZY �ӳټ��أ�ֻ�е���ʹ��Customer��ʱ��Żᷢ��SQL");
        logger.info("customer----->"+customer.getCustomerName());
    }

    @Test
    public void deleteTest(){
        //ɾ�����
        Order order = new Order();
        order.setOrderId(15);
        session.delete(order);
        //ɾ��һ��
        Customer customer = new Customer();
        customer.setCustomerId(2);
        session.delete(customer);
    }
}
