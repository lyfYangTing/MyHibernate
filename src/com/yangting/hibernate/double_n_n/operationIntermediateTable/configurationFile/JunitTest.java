package com.yangting.hibernate.double_n_n.operationIntermediateTable.configurationFile;

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
 * Created by 18435 on 2017/12/11.
 */
public class JunitTest {
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

    @After
    public void destroy() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    /**
     *    Order(����)      Product(��Ʒ����Ʒ)       OrderItem(������¼)
     *      order1              pro                  item1(order1,pro)
     *      order2              p2                   item2(order1,p2)
     *                          p3                   item3(order2,p3)
     *                                               item4(order2,pro)
     */
    @Test
    public void saveTest() {
        //����һ
        Order order1 = new Order();
        order1.setTotal(1000);
        order1.setAddress("����");
        order1.setRealname("���Ȼ");
        order1.setPostcode("510990");
        order1.setPhone("87818998");

        //������
        Order order2 = new Order();
        order2.setTotal(5000);
        order2.setAddress("�д�");
        order2.setRealname("����");
        order2.setPostcode("510000");
        order2.setPhone("38008781");

        //��Ʒ1
        Product pro = new Product();
        pro.setName("thinkpad����");
        pro.setDescription("thinkpad");
        pro.setPrice(6780);

        //��Ʒ2
        Product p2 = new Product();
        p2.setName("�ʵ�");
        p2.setDescription("�Լ۱ȸ�");
        p2.setPrice(200);

        //��Ʒ3
        Product p3 = new Product();
        p3.setName("tcl�ʵ�");
        p3.setPrice(3600);
        p3.setDescription("tcl�ʵ�");

        //������¼
        OrderItem item1 = new OrderItem();
        OrderItem item2 = new OrderItem();
        OrderItem item3 = new OrderItem();
        OrderItem item4 = new OrderItem();

        //һ�������ж�����Ʒ��¼
        item1.setOrder(order1);//����������ϵ
        item1.setProduct(pro);//����������ϵ  ���ҵ���֮��������Ʒ����������pro
        item1.setQuantity(100);
        item1.setPurchase(6000);

        item2.setOrder(order1);//����������ϵ
        item2.setProduct(p2);//����������ϵ ���ҵ���֮��������Ʒ����������p2
        item2.setQuantity(200);
        item2.setPurchase(3000);
        //����order1ʱ���ҵ���֮������item��Ȼ��������item1��item2
        order1.getOrderitems().add(item1);
        order1.getOrderitems().add(item2);

        item3.setOrder(order2);//����������ϵ
        item3.setProduct(p3);//����������ϵ ���ҵ���֮��������Ʒ����������p3
        item3.setQuantity(30);
        item3.setPurchase(3300);

        item4.setOrder(order2);//����������ϵ
        item4.setProduct(pro);//����������ϵ ���ҵ���֮��������Ʒ����������pro
        item4.setQuantity(60);
        item4.setPurchase(6200);

        //����order2ʱ���ҵ���֮������item��Ȼ��������item3��item4
        order2.getOrderitems().add(item3);
        order2.getOrderitems().add(item4);

        //һ����Ʒ�ж�����Ʒ��¼
        pro.getOrderitems().add(item1);
        pro.getOrderitems().add(item4);

        //�����¼
        session.save(order1);
        session.save(order2);
    }

    @Test
    public void getTest() {
        Order myOrder = session.get(Order.class, "402881bd604871ff0160487215ae0000");
        if (myOrder != null) {
            int costMoney = 0;
            for (OrderItem item : myOrder.getOrderitems()) {
                costMoney += (item.getQuantity() * item.getPurchase());
            }
            System.out.println("������:" + myOrder.getId());
            System.out.println("�ܻ���:" + costMoney + "\n" + "������������Ʒ��");
            for (OrderItem item : myOrder.getOrderitems()) {
                System.out.println("��Ʒ��:" + item.getProduct().getName());
            }
        }
    }
}
