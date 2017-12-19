package com.yangting.hibernate.double_1_1.foreign.annotation;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/12/1.
 */
@Entity
@Table(name="departments")
public class Department {
    @Id
    @Column(name="DEPT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer deptId;

    @Column(name="DEPT_NAME")
    private String deptName;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="MGR_ID",unique = true) //Íâ¼üÎ¨Ò»Ô¼Êø
    private Manager manager;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
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
