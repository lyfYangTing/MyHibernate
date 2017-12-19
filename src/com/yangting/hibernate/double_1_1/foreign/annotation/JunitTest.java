package com.yangting.hibernate.double_1_1.foreign.annotation;

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
 * Created by 18435 on 2017/12/1.
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
    public void saveTest(){

        Manager manager = new Manager();
        manager.setMgrName("ͯ��");

        Department department = new Department();
        department.setDeptName("������");
        department.setManager(manager);//department����ά��˫��������ϵ��<many-to-one>��������
        manager.setDepartment(department);//�����ҵ�department

        session.save(manager);
    }

    @Test
    public void saveTestOne(){
        Manager manager = new Manager();
        manager.setMgrName("ͯ��");

        Department department = new Department();
        department.setDeptName("������");
        //�ڱ���department��ʱ���ܹ��ҵ���֮������manager ������˫��������ϵ
        department.setManager(manager);//department����ά��˫��������ϵ <one-to-one>����Ϊ������

        session.save(department);
    }
}
