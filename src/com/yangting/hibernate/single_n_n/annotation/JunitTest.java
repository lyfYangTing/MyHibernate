package com.yangting.hibernate.single_n_n.annotation;

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

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 18435 on 2017/12/11.
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
    public void testSave() {
        Category c1 = new Category();
        c1.setCatregoryName("Cate-AA-Annotation");

        Category c2 = new Category();
        c2.setCatregoryName("Cate-BB-Annotation");

        Item i1 = new Item();
        i1.setItemName("Item-AA-Annotation");
        Item i2 = new Item();
        i2.setItemName("Item-BB-Annotation");

        c1.getItems().add(i1);
        c1.getItems().add(i2);
        c2.getItems().add(i1);
        c2.getItems().add(i2);

        session.save(c1);
        session.save(c2);
//        session.save(i1); @ManyToMany������������ϵʱ����ʡ��
//        session.save(i2); @ManyToMany��ǩ������������ϵʱ����ʡ��
    }

    @Test
    public void updateTest(){//���½���������ϵ����ǰ�Ѿ����ڵĹ�ϵȫ����Ҫ��
        Category category = session.get(Category.class,"402881bd604340f10160434107570000");

        Set<Item> set = new HashSet<>();
        Item item1 = new Item();
        item1.setItemName("���²���-Annotation");

        Item item = new Item();
        item.setItemName("���²���1-Annotation");

        set.add(item);
        set.add(item1);
        category.setItems(set);

        session.update(category);
    }
}
