package com.yangting.hibernate.double_1_1.foreign.annotation;

import javax.persistence.*;

/**
 * Created by 18435 on 2017/12/1.
 */
@Entity
@Table(name="managers")
public class Manager {
    @Id
    @Column(name="MGR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mgrId;

    @Column(name="MGR_NAME")
    private String mgrName;

    @OneToOne(mappedBy = "manager",cascade =CascadeType.ALL)
    private Department department;

    public Integer getMgrId() {
        return mgrId;
    }

    public void setMgrId(Integer mgrId) {
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
