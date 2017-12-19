package com.yangting.hibernate.single_1_1.foreign.annotation;

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
 * Created by 18435 on 2017/12/7.
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
    public void saveTest(){
        IdCard idCard = new IdCard();
        idCard.setCardNo("12345");
//        session.save(idCard);（不需要） 设置了casecade 级联

        Person person = new Person();
        person.setName("穆庭轩11");
        person.setCard(idCard);

        session.save(person);
    }

    @Test
    public void getTest(){
        Person person = session.get(Person.class,"402881c0603072120160307219fb0000");
        person.getCard().getCardNo();
    }
}
