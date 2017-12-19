package com.yangting.hibernate.double_1_1.primary.annotation;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/12/8.
 */
@Entity
@Table(name="department_pri")
public class Department {

    @Id
    @Column(name="dept_id")
    @GeneratedValue(generator = "departmentGenerator")
    @GenericGenerator(name = "departmentGenerator",strategy = "uuid")
    private String deptId;

    @Column(name="dept_name")
    private String deptName;

    /**
     * cascade��ʾ��������˼
     * FetchMode.SELECT�ķ�ʽ��ʾ����ѯ��֮���������ݵ�ʱ��ʹ��select�ķ�ʽ����������������,����
     * ��@OneToOne()�м����fetch=FetchType.LAZY������������˼��һ���ġ�
     * @PrimaryKeyJoinColumn����������������һ��ָ������Ȼ���ڱ�������һ�����һ��
     */
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Manager manager;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
