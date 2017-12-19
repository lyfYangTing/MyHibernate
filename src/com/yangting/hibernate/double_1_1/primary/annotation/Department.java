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
     * cascade表示级联的意思
     * FetchMode.SELECT的方式表示，查询与之关联的数据的时候，使用select的方式，而不是左外连接,与在
     * 在@OneToOne()中间加上fetch=FetchType.LAZY所表达出来的意思是一样的。
     * @PrimaryKeyJoinColumn必须在生产主键的一方指定，不然会在被关联的一方多出一列
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
