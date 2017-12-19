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
    public void init() {//��ʼ��
        // �������Ͱ�ȫ��׼����ע���࣬��configure("cfg/hibernate.cfg.xml")�����У������ָ����Դ·����Ĭ������·����Ѱ����Ϊhibernate.cfg.xml���ļ�,����������Ļ���������Ĭ������hibernate.cfg.xml
        //��������Ļ��Ϳ����ñ��������
        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        //���ݷ���ע���ഴ��һ��Ԫ������Դ����ͬʱ����Ԫ���ݲ�����Ӧ��һ��Ψһ�ĵ�session����
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
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
        //ͨ��c1,c2�ҵ���֮������i1,i2
        c1.getItems().add(i1);
        c1.getItems().add(i2);
        c2.getItems().add(i1);
        c2.getItems().add(i2);
        //Item��ά��������ϵ�������ϵ�����������
        i1.getCategories().add(c1);
        i1.getCategories().add(c2);
        i2.getCategories().add(c1);
        i2.getCategories().add(c2);

        session.save(c1);
        session.save(c2);
//        session.save(i1); set��ǩ������������ϵʱ����ʡ��
//        session.save(i2); set��ǩ������������ϵʱ����ʡ��
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

        //Item�ҵ�c1,c2��ά��������ϵ�������ϵ�����������
        i1.getCategories().add(c1);
        i1.getCategories().add(c2);
        i2.getCategories().add(c1);
        i2.getCategories().add(c2);

        session.save(i1);
        session.save(i2);
    }

    @Test
    public void updateCategoryTest() {//���½���������ϵ����ǰ�Ѿ����ڵĹ�ϵȫ����Ҫ��
        Category category = session.get(Category.class, "402881bd604340f10160434107570000");

        //��Ϊ������ϵ��Item��ά������Ҫ����Category�Ĺ�����ϵ����Ҫ�ֶ�����ǰ�Ĺ�ϵɾ��
        //category.getItems().removeAll(category.getItems()); ����ɾ����ǰ�Ĺ�ϵ����Ϊcategory����ά��������ϵ
        for (Item item : category.getItems()) {//402881bd6044c355016044c35a020000  402881bd6044c355016044c35a0f0001
            item.getCategories().remove(category);
        }
        Set<Item> set = new HashSet<>();

        Item item1 = new Item();
        item1.setItemName("���²���-double");
        item1.getCategories().add(category);//��ӵ��µĹ�����ϵ

        Item item2 = new Item();
        item2.setItemName("���²���1-double");
        item2.getCategories().add(category);//��ӵ��µĹ�����ϵ

        set.add(item2);
        set.add(item1);
        category.setItems(set);//���ڼ���������ʱ���ҵ������Ԫ��

        session.update(category);
    }

    @Test
    public void updateItemTest(){//���½���������ϵ����ǰ�Ѿ����ڵĹ�ϵȫ����Ҫ��
        Item item = session.get(Item.class,"402881bd6043a01a016043a027720001");

        Set<Category> set = new HashSet<>();

        Category category = new Category();
        category.setCatregoryName("���²���-updateItemTest");

        Category category1 = session.get(Category.class,"402881bd60435a130160435a172a0000");

        set.add(category);
        set.add(category1);

        item.setCategories(set);//�����¹�ϵ��ͬʱ��ɾȥԭ���Ĺ�����ϵ

        session.update(item);
    }
}
