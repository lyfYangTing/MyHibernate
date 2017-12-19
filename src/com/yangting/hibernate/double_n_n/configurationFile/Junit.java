package com.yangting.hibernate.double_n_n.configurationFile;

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
public class Junit {
    private Logger logger = Logger.getLogger(Junit.class);
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init() {//初始化
        // 配置类型安全的准服务注册类，在configure("cfg/hibernate.cfg.xml")方法中，如果不指定资源路径，默认在类路径下寻找名为hibernate.cfg.xml的文件,不传入参数的话，必须用默认名称hibernate.cfg.xml
        //传入参数的话就可以用别的名称了
        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        //根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
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
    public void destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testSave() {
        Category c1 = new Category();
        c1.setCatregoryName("Cate-AA-double");

        Category c2 = new Category();
        c2.setCatregoryName("Cate-BB-double");

        Item i1 = new Item();
        i1.setItemName("Item-AA-double");
        Item i2 = new Item();
        i2.setItemName("Item-BB-double");
        //通过c1,c2找到与之关联的i1,i2
        c1.getItems().add(i1);
        c1.getItems().add(i2);
        c2.getItems().add(i1);
        c2.getItems().add(i2);
        //Item方维护关联关系，即向关系表中添加数据
        i1.getCategories().add(c1);
        i1.getCategories().add(c2);
        i2.getCategories().add(c1);
        i2.getCategories().add(c2);

        session.save(c1);
        session.save(c2);
//        session.save(i1); set标签中声明级联关系时可以省略
//        session.save(i2); set标签中声明级联关系时可以省略
    }

    @Test
    public void saveTest() {
        Category c1 = new Category();
        c1.setCatregoryName("Cate-AA-double");

        Category c2 = new Category();
        c2.setCatregoryName("Cate-BB-double");

        Item i1 = new Item();
        i1.setItemName("Item-AA-double");
        Item i2 = new Item();
        i2.setItemName("Item-BB-double");

        //Item找到c1,c2并维护关联关系，即向关系表中添加数据
        i1.getCategories().add(c1);
        i1.getCategories().add(c2);
        i2.getCategories().add(c1);
        i2.getCategories().add(c2);

        session.save(i1);
        session.save(i2);
    }

    @Test
    public void updateCategoryTest() {//重新建立关联关系，以前已经存在的关系全部不要了
        Category category = session.get(Category.class, "402881bd604340f10160434107570000");

        //因为关联关系由Item方维护，则要重置Category的关联关系，需要手动将以前的关系删除
        //category.getItems().removeAll(category.getItems()); 不能删掉以前的关系，因为category并不维护关联关系
        for (Item item : category.getItems()) {//402881bd6044c355016044c35a020000  402881bd6044c355016044c35a0f0001
            item.getCategories().remove(category);
        }
        Set<Item> set = new HashSet<>();

        Item item1 = new Item();
        item1.setItemName("更新测试-double");
        item1.getCategories().add(category);//添加的新的关联关系

        Item item2 = new Item();
        item2.setItemName("更新测试1-double");
        item2.getCategories().add(category);//添加的新的关联关系

        set.add(item2);
        set.add(item1);
        category.setItems(set);//用于级联操作的时候找到其关联元素

        session.update(category);
    }

    @Test
    public void updateItemTest(){//重新建立关联关系，以前已经存在的关系全部不要了
        Item item = session.get(Item.class,"402881bd6043a01a016043a027720001");

        Set<Category> set = new HashSet<>();

        Category category = new Category();
        category.setCatregoryName("更新测试-updateItemTest");

        Category category1 = session.get(Category.class,"402881bd60435a130160435a172a0000");

        set.add(category);
        set.add(category1);

        item.setCategories(set);//建立新关系的同时，删去原来的关联关系

        session.update(item);
    }
}
