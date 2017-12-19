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
//        session.save(i1); @ManyToMany中声明级联关系时可以省略
//        session.save(i2); @ManyToMany标签中声明级联关系时可以省略
    }

    @Test
    public void updateTest(){//重新建立关联关系，以前已经存在的关系全部不要了
        Category category = session.get(Category.class,"402881bd604340f10160434107570000");

        Set<Item> set = new HashSet<>();
        Item item1 = new Item();
        item1.setItemName("更新测试-Annotation");

        Item item = new Item();
        item.setItemName("更新测试1-Annotation");

        set.add(item);
        set.add(item1);
        category.setItems(set);

        session.update(category);
    }
}
