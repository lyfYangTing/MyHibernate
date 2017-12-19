package com.yangting.hibernate.double_1_1.primary.annotation;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 18435 on 2017/12/8.
 */
@Entity
@Table(name="manager_pri")
public class Manager {
    @Id
    @Column(name="mgr_id")
    @GeneratedValue(generator = "managerGenerator")
    @GenericGenerator(name = "managerGenerator",strategy = "foreign",parameters = {@org.hibernate.annotations.Parameter(name = "property",value = "department")})
    private String mgrId;

    @Column(name="mgr_name")
    private String mgrName;

    @OneToOne(mappedBy = "manager",fetch = FetchType.LAZY)
    private Department department;

    public String getMgrId() {
        return mgrId;
    }

    public void setMgrId(String mgrId) {
        this.mgrId = mgrId;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
