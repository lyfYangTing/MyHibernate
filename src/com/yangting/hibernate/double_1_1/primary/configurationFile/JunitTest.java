package com.yangting.hibernate.double_1_1.primary.configurationFile;

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
 * Created by 18435 on 2017/12/8.
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
        Department department = new Department();
        department.setDeptName("设计部");

        Manager manager = new Manager();
        manager.setMgrName("童战");

        manager.setDepartment(department);
        //department.setManager(manager);

        session.save(manager);
    }

    @Test
    public void saveTestTwo(){
        Department department = new Department();
        department.setDeptName("运营部");

        Manager manager = new Manager();
        manager.setMgrName("穆庭轩");

        manager.setDepartment(department);//建立关联关系，不能少
        department.setManager(manager);

        session.save(department);
    }

    @Test
    public void getTest(){
        // Department department = session.get(Department.class,"402881c06034d52b016034d52fcc0000");
        Manager manager = session.get(Manager.class,"402881c06034d52b016034d52fcc0000");
    }
}
